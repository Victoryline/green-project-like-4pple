function searchAddress() {
    const zoneCode = document.getElementById("zoneCode");
    const address = document.getElementById("address");

    new daum.Postcode({
        oncomplete: function (data) {
            zoneCode.value = data.zonecode;
            address.value = data.address;
        }
    }).open();
}

function validation() {
    let isValidation = true;

    const password = document.getElementById('password').value.trim();
    const confirmPassword = document.getElementById('confirm-password').value.trim();
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const birth = document.getElementById('birth').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const gender = document.querySelector('input[name="gender"]:checked');

    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,16}$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^010\d{7,8}$/;

    if (password && !passwordRegex.test(password)) {
        alert('비밀번호는 8~16자리로 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.');
        isValidation = false;
    } else if (password !== confirmPassword) {
        alert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다.');
        isValidation = false;
    } else if (!name) {
        alert('이름을 입력해주세요.');
        isValidation = false;
    } else if (!emailRegex.test(email)) {
        alert('올바른 이메일 형식을 입력해주세요.');
        isValidation = false;
    } else if (!birth) {
        alert('생년월일을 선택해주세요.');
        isValidation = false;
    } else if (!gender) {
        alert('성별을 선택해주세요.');
        isValidation = false;
    } else if (!phoneRegex.test(phone)) {
        alert('휴대폰 번호는 "010"으로 시작하며 10~11자리 숫자여야 합니다.');
        isValidation = false;
    }

    return isValidation;
}

function updateProfile() {
    if (!validation()) return;

    const usernameVal = $('#username').val();

    const jobSeeker = {
        username: usernameVal,
        phone: $('#phone').val(),
        email: $('#email').val(),
        gender: $('input[name=gender]').val(),
        birth: $('#birth').val(),
        address: $('#address').val(),
        addressDetail: $('#addressDetail').val(),
        zoneCode: $('#zoneCode').val()
    }
    const user = {
        username: usernameVal,
        password: $('#password').val(),
        name: $('#name').val(),
        role: getRole(),
        jobSeeker: jobSeeker
    }

    api.put(`/api/v1/users/update/${usernameVal}`, user)
        .then(response => {
            alert('수정이 완료되었습니다!');
            window.location.reload();
        })
        .catch(error => {
            console.error(error);
            alert('수정 중 오류가 발생했습니다. 다시 시도해주세요.');
        });
}

function secession() {
    if (confirm("정말 탈퇴 하시겠습니까?")) {
        const username = getUsername();
        api.put(`/api/v1/users/delete-yn/${encodeURIComponent(username)}?deleteYn=Y`)
            .then(res => {
                if (res.body == 1) {
                    alert('탈퇴 되었습니다.');
                    logout();
                    location.reload();
                } else {
                    alert("처리 실패");
                }
            })
            .catch(error => {
                console.log(error);
            })
    }
}