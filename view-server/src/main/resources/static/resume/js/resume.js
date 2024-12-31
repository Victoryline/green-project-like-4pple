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

// 다른 섹션들을 토글 버튼으로 보여주기
const sections = {
    'education-toggle': 'education-section',
    'military-toggle': 'military-section',
    'experience-toggle': 'experience-section',
    'license-toggle': 'license-section',
    'skills-toggle': 'skills-section',
    'projects-toggle': 'projects-section',
    'introduce-toggle': 'introduce-section'
};

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
            this.src = '/static/images/checked-icon.png';// 체크된 이미지
        } else {
            section.style.display = 'none';
            this.src = '/static/images/unchecked-icon.png';// 체크되지 않은 이미지
        }
    });
});

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

document.querySelector('.edu').addEventListener('click', function () {
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <label>입학 연도</label>
            <input type="text" name="enterDate" placeholder="YYYY.MM">
            <label>졸업 연도</label>
            <input type="text" name="graduateDate" placeholder="YYYY.MM">
            <label>학교명</label>
            <input type="text" placeholder="학교명을 입력해주세요">
            <label>학과명</label>
            <input type="text" placeholder="학과명을 입력해주세요">
            <button class="edu-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.edu-delete-button').addEventListener('click', function () {
        entry.remove();
    });

    document.querySelector('#education-section').appendChild(entry);
});

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

document.querySelector('.skill').addEventListener('click', function () {
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <textarea name="skillCode" placeholder="기술스택 입력"></textarea>
            <button class="skill-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.skill-delete-button').addEventListener('click', function () {
        entry.remove();
    });

    document.querySelector('#skills-section').appendChild(entry);
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





