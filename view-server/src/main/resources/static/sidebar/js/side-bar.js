document.addEventListener('DOMContentLoaded', () => {
    // 현재 경로에 해당하는 메뉴 항목을 강조
    const currentPath = window.location.pathname;
    document.querySelectorAll('.menu li').forEach(li => {
        const link = li.querySelector('a');
        if (currentPath.includes(link.getAttribute('href'))) {
            li.classList.add('active');
        }
    });

    // 사이드바 토글 버튼
    const toggleButton = document.getElementById('sidebar-toggle');
    const sidebar = document.querySelector('.menu');

    toggleButton.addEventListener('click', () => {
        sidebar.classList.toggle('active');
    });
});
