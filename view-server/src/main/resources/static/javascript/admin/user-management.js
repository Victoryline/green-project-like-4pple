function showTab(tabId) {
    const tabs = document.querySelectorAll('.user-management .tab');
    const contents = document.querySelectorAll('.user-management .content');

    tabs.forEach(tab => {
        tab.classList.remove('active');
    });

    contents.forEach(content => {
        content.classList.remove('active');
    });

    document.querySelector(`.user-management .tab[onclick="showTab('${tabId}')"]`).classList.add('active');
    document.getElementById(tabId).classList.add('active');
}

$(function () {
    let previousValue = null;
    $(".deleteYn-select").on('focus', function () {
        previousValue = $(this).find(':selected').val();
    }).on('change', function (event) {
        const selectOption = $(this).find(':selected');
        const selectVal = selectOption.val();
        const selectTest = selectOption.text();

        if (selectVal === 'Y') {
            alert(selectTest + "처리는 불가능합니다.");
            $(this).val(previousValue);
        } else {
            if (confirm("정말 " + selectTest + "처리 하시겠습니까?")) {
                console.log({
                    username: this.getAttribute('data-username'),
                    deleteYn: selectVal
                });
                api.put(`/api/v1/users/deleteYn/${this.getAttribute('data-username')}?deleteYn=${selectVal}`)
                    .then(res => {
                        if (res.body == 1) {
                            alert(selectTest + '처리되었습니다.');
                            location.reload();
                        } else {
                            alert("처리 실패");
                            $(this).val(previousValue);
                        }
                    })
                    .catch(error => {
                        console.log(error);
                    })
            } else {
                $(this).val(previousValue);
            }
        }
    })
})
