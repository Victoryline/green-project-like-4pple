// 기본정보는 항상 보이도록 설정
document.getElementById('basic-info-section').style.display = 'block';

// 다른 섹션들을 토글 버튼으로 보여주기
const sections = {
    'education-toggle': 'education-section',
    'experience-toggle': 'experience-section',
    'skills-toggle': 'skills-section',
    'projects-toggle': 'projects-section',
    'certifications-toggle': 'certifications-section',
    'introduce-toggle': 'introduce-section'
};

Object.keys(sections).forEach(toggleId => {
    document.getElementById(toggleId).addEventListener('click', function() {
        const sectionId = sections[toggleId];
        const section = document.getElementById(sectionId);

        // 토글 이미지를 변경
        if (section.style.display === 'none' || section.style.display === '') {
            section.style.display = 'block';
            this.src = '/static/images/checked-icon.png'; // 체크된 이미지
        } else {
            section.style.display = 'none';
            this.src = '/static/images/unchecked-icon.png'; // 체크되지 않은 이미지
        }
    });
});

// 이미지 미리보기 함수
function previewImage(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function() {
        const imagePreview = document.getElementById('imagePreview');
        imagePreview.src = reader.result;
        imagePreview.style.display = 'block'; // 이미지를 보여줌
    };

    if (file) {
        reader.readAsDataURL(file); // 파일을 데이터 URL로 읽음
    }
}

document.querySelector('.edu').addEventListener('click', function(){
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <label>졸업 연도</label>
            <input type="text" placeholder="YYYY.MM">
            <label>학교명</label>
            <input type="text" placeholder="학교명을 입력해주세요">
            <label>학과명</label>
            <input type="text" placeholder="학과명을 입력해주세요">
            <button class="edu-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.edu-delete-button').addEventListener('click', function() {
        entry.remove();
    });

    document.querySelector('#education-section').appendChild(entry);
});

document.querySelector('.exp').addEventListener('click', function(){
    const entry = document.createElement('div');
    entry.classList.add('section-body', 'entry');

    entry.innerHTML = `
            <label>회사명</label>
            <input type="text" placeholder="회사명 입력">
            <label>기간</label>
            <input type="text" placeholder="YYYY-MM ~ YYYY-MM">
            <button class="edu-delete-button del-button">
                <i class="fas fa-trash-alt"></i>삭제
            </button>
        `;

    entry.querySelector('.exp-delete-button').addEventListener('click', function() {
        entry.remove();
    });

    document.querySelector('#education-section').appendChild(entry);
});