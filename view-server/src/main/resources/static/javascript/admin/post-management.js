function showTab(tabId) {
    const tabs = document.querySelectorAll('.post-management .tab');
    const contents = document.querySelectorAll('.post-management .content');

    tabs.forEach(tab => {
        tab.classList.remove('active');
    });

    contents.forEach(content => {
        content.classList.remove('active');
    });

    document.querySelector(`.post-management .tab[onclick="showTab('${tabId}')"]`).classList.add('active');
    document.getElementById(tabId).classList.add('active');
}

$(function () {
    let previousValue = null;
    $(".status-select").on('focus', function () {
        previousValue = $(this).find(':selected').val();
    }).on('change', function (event) {
        const selectOption = $(this).find(':selected');
        const selectVal = selectOption.val();

        if (selectVal === 'D') {
            alert("삭제 처리는 불가능합니다.");
            $(this).val(previousValue);
        } else {
            if (confirm("정말 " + selectOption.text() + " 처리 하시겠습니까?")) {
                console.log({
                    id: this.getAttribute('data-id'),
                    status: selectVal
                });
                api.put(`/api/v1/posts/status/${this.getAttribute('data-id')}?status=${selectVal}`)
                    .then(res => {
                        if (res.data.success) {
                            alert(selectOption.text() + ' 처리되었습니다.');
                            location.reload();
                        } else {
                            alert("처리 실패");
                            $(this).val(previousValue);
                        }
                    })
                    .catch(error => {
                        console.error(error);
                        alert("오류가 발생했습니다.");
                        $(this).val(previousValue);
                    });
            } else {
                $(this).val(previousValue);
            }
        }
    });
});
