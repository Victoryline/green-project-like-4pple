// 기본정보는 항상 보이도록 설정
document.getElementById('basic-info-section').style.display = 'block';
document.getElementById('wish-section').style.display = 'block';

// 부드러운 스크롤 처리
document.querySelectorAll('.sidebar a').forEach(link => {
    link.addEventListener('click', function (event) {
        event.preventDefault();  // 기본 동작 방지 (새로 고침 방지)

        // 링크의 href 속성에서 id 값 추출 (예: #basic-info-section)
        const targetId = this.getAttribute('href').substring(1);
        const targetSection = document.getElementById(targetId);

        if (targetSection) {
            // 해당 섹션으로 부드럽게 스크롤 이동
            targetSection.scrollIntoView({
                behavior: 'smooth',  // 부드러운 스크롤
                block: 'start'       // 섹션의 시작 부분으로 맞추기
            });
        }
    });
});

// 각 항목을 동적으로 표시하는 함수
function initializeSections(resume) {
    const sections = {
        'educations-toggle': 'education-section',
        'military-toggle': 'military-section',
        'activities-toggle': 'experience-section',
        'licenses-toggle': 'license-section',
        'skills-toggle': 'skills-section',
        'portfolios-toggle': 'projects-section',
        'introduces-toggle': 'introduce-section'
    };

    const resumeData = {
        educations: !!resume.educations,
        military: !!resume.military,
        activities: resume.activities && resume.activities.length > 0,
        licenses: resume.licenses && resume.licenses.length > 0,
        skills: resume.skills && resume.skills.length > 0,
        portfolios: resume.portfolios && resume.portfolios.length > 0,
        introduces: resume.introduces && resume.introduces.length > 0
    }

    Object.keys(sections).forEach(toggleId => {
        document.getElementById(toggleId).addEventListener('click', function () {
            const sectionId = sections[toggleId];
            const section = document.getElementById(sectionId);

            console.log(section);
            console.log(toggleId);
            document.getElementById(toggleId).classList.toggle('checked');
            // 토글 이미지를 변경
            if (section.style.display === 'none' || section.style.display === '') {
                section.style.display = 'block';
                this.src = '/static/images/checked-icon.png'; // 체크된 이미지
            } else {
                section.style.display = 'none';
                this.src = '/static/images/unchecked-icon.png'; // 체크되지 않은 이미지
            }
        });

        // 데이터 기반으로 초기 설정
        const sectionKey = toggleId.split('-')[0]; // toggleId에서 앞부분(예: education)을 추출
        // console.log(sectionKey);
        // console.log(section);
        console.log(resumeData[sectionKey]);
        // 기술스택은 사용자 입력이 있어야만 표시되도록 수정
        if (resumeData[sectionKey] === true) {
            console.log(toggleId);
            // // 해당 항목이 데이터로 존재하면 체크박스를 활성화하고 섹션을 표시
            document.getElementById(toggleId).src = '/static/images/checked-icon.png'; // 체크된 이미지
            document.getElementById(sections[toggleId]).style.display = 'block'; // 해당 섹션 표시
        } else if (sectionKey === 'skills') {
            if (resumeData.skills && resumeData.skills.length > 0) {
                console.log(resumeData.skills.length)
                document.getElementById(toggleId).src = '/static/images/checked-icon.png'; // 체크된 이미지
                document.getElementById(sections[toggleId]).style.display = 'block'; // 기술스택 섹션 표시
            } else {
                document.getElementById(toggleId).src = '/static/images/unchecked-icon.png'; // 체크되지 않은 이미지
                document.getElementById(sections[toggleId]).style.display = 'none'; // 기술스택 섹션 숨김
            }
        } else {
            // 데이터가 없으면 체크되지 않은 상태로 유지하고 섹션 숨김
            document.getElementById(toggleId).src = '/static/images/unchecked-icon.png'; // 체크되지 않은 이미지
            document.getElementById(sections[toggleId]).style.display = 'none'; // 해당 섹션 숨김
        }
    });
}

// 이미지 미리보기 함수
function previewImage(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function () {
        const imagePreview = document.getElementById('imagePreview');
        imagePreview.src = reader.result;
        imagePreview.style.display = 'block'; // 이미지를 보여줌
    };

    if (file) {
        reader.readAsDataURL(file); // 파일을 데이터 URL로 읽음
    }
}


document.querySelector('.exp').addEventListener('click', function () {
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <label for="activityType">군별</label>
            <select name="activityType" id="activityType">
                <option value="JOB_CAREEAR">직무 관련 경력</option>
                <option value="JOB_ACT">직무 관련 활동</option>
                <option value="SOCIAL_ACT">사회 경험 활동</option>
            </select>
            <label>회사/활동기관명</label>
            <input type="text" name="activityCenterName" placeholder="회사/활동기관명 입력">
            <label>회사/활동기관 주요 내용</label>
            <textarea name="activityContent" placeholder="회사/활동기관 주요 내용 입력"></textarea>
            <label>입사/시작 연월</label>
            <input type="text" name="startDate" placeholder="YYYY-MM">
            <label>퇴사/종료 연월</label>
            <input type="text" name="endDate" placeholder="YYYY-MM">
            <button class="edu-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.exp-delete-button').addEventListener('click', function () {
        entry.remove();
    });

    document.querySelector('#experience-section').appendChild(entry);
});

document.querySelector('.license').addEventListener('click', function () {
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <label>자격증명</label>
            <input type="text" name="licenseName" placeholder="자격증명 입력">
            <label>합격일</label>
            <input type="text" name="passDate" placeholder="YYYY-MM-DD">
            <label>발행처</label>
            <input type="text" name="licenseCenterName" placeholder="발행기관 이름">
            <button class="license-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.license-delete-button').addEventListener('click', function () {
        entry.remove();
    });

    document.querySelector('#license-section').appendChild(entry);
});

document.querySelector('.project').addEventListener('click', function () {
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <label>포트폴리오명</label>
            <input type="text" name="portfolioFilename" placeholder="프로젝트명 입력">
            <label>주소</label>
            <input type="text" name="portfolioLink" placeholder="프로젝트 주소">
            <button class="project-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.project-delete-button').addEventListener('click', function () {
        entry.remove();
    });

    document.querySelector('#projects-section').appendChild(entry);
});


// 자소서 추가 및 삭제 관리
let introduceItemCount = 1; // 자소서 항목 번호 관리

// 자소서 추가 함수
function addIntroduceItem() {
    introduceItemCount++; // 항목 추가 시 번호 증가

    const itemContainer = document.createElement('div');
    itemContainer.classList.add('introduce-item');
    itemContainer.setAttribute('data-id', introduceItemCount); // 고유 ID 설정

    itemContainer.innerHTML = `
        <input type="hidden" name="ord" value="${introduceItemCount}">
        <input type="text" name="title" placeholder="자소서 소제목을 입력해주세요">
        <textarea name="content" placeholder="자소서 내용 입력"></textarea>
        <button type="button" class="delete-button del-button" onclick="deleteIntroduceItem(${introduceItemCount})">삭제</button>
    `;

    // 추가된 항목을 자소서 컨테이너에 삽입
    document.getElementById('introduce-items-container').appendChild(itemContainer);
}

// 자소서 항목 삭제 함수
function deleteIntroduceItem(itemId) {
    // 해당 항목 삭제
    const itemContainer = document.querySelector(`.introduce-item[data-id="${itemId}"]`);
    itemContainer.remove();

    // 삭제된 항목 이후 아이디 재조정
    updateIntroduceItemIds();
}

// 자소서 항목 아이디 재조정 함수
function updateIntroduceItemIds() {
    const items = document.querySelectorAll('.introduce-item');
    introduceItemCount = 1; // 아이디를 재조정하기 전에 초기화

    items.forEach((item, index) => {
        introduceItemCount = index + 1; // 아이디를 1부터 재조정
        item.setAttribute('data-id', introduceItemCount);
        const hiddenInput = item.querySelector('input[type="hidden"]');
        if (hiddenInput) {
            hiddenInput.value = introduceItemCount;
        }
    });
}

// 자소서 추가 버튼 클릭 이벤트
document.querySelector('.add-button.introduce').addEventListener('click', addIntroduceItem);


