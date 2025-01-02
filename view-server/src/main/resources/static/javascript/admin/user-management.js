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
                    const username = this.getAttribute('data-username');
                    api.put(`/api/v1/users/deleteYn/${encodeURIComponent(username)}?deleteYn=${encodeURIComponent(selectVal)}`)
                        .then(res => {
                            if (res.body == 1) {
                                alert(selectTest + '처리되었습니다.');
                                // 알림 메시지 전송
                                api.post(`/api/v1/sse/send/${encodeURIComponent(username)}`, {
                                    message: `${selectTest} 처리가 완료되었습니다.`
                                }).then(() => {
                                    console.log("알림 메시지 전송 완료");
                                })
                                .catch(error => {
                                    console.error("알림 메시지 전송 실패:", error);
                                });
                                // location.reload();
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
        }
    )
})
