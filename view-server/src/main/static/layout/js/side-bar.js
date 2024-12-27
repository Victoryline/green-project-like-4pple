document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname;
    document.querySelectorAll('.menu li').forEach(li => {
        const link = li.querySelector('a');
        if (link.getAttribute('href') === currentPath) {
            li.classList.add('active');
        }
    });
});