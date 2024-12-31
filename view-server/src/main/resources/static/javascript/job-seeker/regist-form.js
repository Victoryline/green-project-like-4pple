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

let isDuplication = true;

function validation() {
    let isValidation = true;

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const confirmPassword = document.getElementById('confirm-password').value.trim();
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const birth = document.getElementById('birth').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const gender = document.querySelector('input[name="gender"]:checked');

    const usernameRegex = /^[a-zA-Z0-9_]{4,20}$/;
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,16}$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^010\d{7,8}$/;

    if (!usernameRegex.test(username)) {
        alert('아이디는 4~20자이며, 영문, 숫자, 특수문자 "_"만 사용할 수 있습니다.');
        isValidation = false;
    } else if (!passwordRegex.test(password)) {
        alert('비밀번호는 8~16자리로 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.');
        isValidation = false;
    } else if (password !== confirmPassword) {
        alert('비밀번호와 2차 비밀번호가 일치하지 않습니다.');
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
    } else if (isDuplication) {
        alert("중복확인을 해주세요.");
        isValidation = false;
    }

    return isValidation;
}

function register() {
    if (!validation()) return;

    const jobSeeker = {
        username: $('#username').val(),
        phone: $('#phone').val(),
        email: $('#email').val(),
        gender: $('input[name=gender]').val(),
        birth: $('#birth').val(),
        address: $('#address').val(),
        addressDetail: $('#addressDetail').val(),
        zoneCode: $('#zoneCode').val()
    }
    const user = {
        username: $('#username').val(),
        password: $('#password').val(),
        name: $('#name').val(),
        role: 'ROLE_JOB_SEEKER',
        jobSeeker: jobSeeker
    }

    api.post("/api/v1/users/register", user)
        .then(response => {
            alert('회원가입이 완료되었습니다!');
            window.location.href = '/login?role=ROLE_JOB_SEEKER';
        })
        .catch(error => {
            console.error(error);
            alert('회원가입 중 오류가 발생했습니다. 다시 시도해주세요.');
        });
}

function resetDuplication() {
    isDuplication = true;
}

function checkDuplicationUsername() {
    const username = $('#username').val().trim();

    api.get(`/api/v1/users/check-duplication-username?username=${username}`)
        .then(response => {
            if (!response.body) {
                $('#username-check-result').css('color', 'green').text('사용 가능한 아이디입니다.');
                isDuplication = false;
            } else {
                $('#username-check-result').css('color', 'red').text('이미 사용 중인 아이디입니다.');
            }
        })
        .catch(error => {
            console.error(error);
            $('#username-check-result').css('color', 'red').text('아이디 중복 확인 중 오류가 발생했습니다.');
        });
}

