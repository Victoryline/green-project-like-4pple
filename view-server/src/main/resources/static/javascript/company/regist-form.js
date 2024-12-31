document.getElementById('profileImage').addEventListener('change', function (event) {
    const file = event.target.files[0];
    const preview = document.getElementById('image-preview');
    const placeholder = document.getElementById('image-placeholder');
    const removeBtn = document.getElementById('remove-image-btn');

    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            base64String = e.target.result.split(',')[1];
            preview.src = loadImage(base64String);
            preview.style.display = 'block';
            placeholder.style.display = 'none';
            removeBtn.style.display = 'block';

            // Base64 문자열을 확인하고 활용
            // console.log("Base64 String:", base64String); // 필요시 주석 처리
        };
        reader.readAsDataURL(file);
    } else {
        base64String = '';
        preview.style.display = 'none';
        placeholder.style.display = 'block';
        removeBtn.style.display = 'none';
    }
});

function triggerFileInput() {
    document.getElementById('profileImage').click();
}

function removeImage(event) {
    event.stopPropagation();
    const fileInput = document.getElementById('profileImage');
    const preview = document.getElementById('image-preview');
    const placeholder = document.getElementById('image-placeholder');
    const removeBtn = document.getElementById('remove-image-btn');

    fileInput.value = '';
    preview.style.display = 'none';
    placeholder.style.display = 'block';
    removeBtn.style.display = 'none';
}

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

let base64String;
let isDuplication = true;

function validation() {
    let isValidation = true;

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    const confirmPassword = document.getElementById('confirm-password').value.trim();
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const employee = document.getElementById('employee').value.trim();
    const contact = document.getElementById('contact').value.trim();
    const businessNumber = document.getElementById('businessNumber').value.trim();
    const birth = document.getElementById('birth').value.trim();
    const address = document.getElementById('address').value.trim();
    const zoneCode = document.getElementById('zoneCode').value.trim();

    // 정규식
    const usernameRegex = /^[a-zA-Z0-9_]{4,20}$/;
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,16}$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^\d{9,11}$/;

    // 유효성 검사
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
        alert('기업명을 입력해주세요.');
        isValidation = false;
    } else if (!businessNumber) {
        alert('사업자 등록번호를 입력해주세요.');
        isValidation = false;
    } else if (!emailRegex.test(email)) {
        alert('올바른 이메일 형식을 입력해주세요.');
        isValidation = false;
    } else if (!employee || employee <= 0) {
        alert('직원 수를 입력해주세요.');
        isValidation = false;
    } else if (!phoneRegex.test(contact)) {
        alert('대표 번호는 9~11자리 숫자여야 합니다.');
        isValidation = false;
    } else if (!birth) {
        alert('창립일을 선택해주세요.');
        isValidation = false;
    } else if (!address || !zoneCode) {
        alert('주소를 입력해주세요.');
        isValidation = false;
    } else if (isDuplication) {
        alert('아이디 중복확인을 해주세요.');
        isValidation = false;
    }

    return isValidation;
}

function register() {
    if (!validation()) return;

    const company = {
        username: $('#username').val(),
        businessNumber: $('#businessNumber').val(),
        email: $('#email').val(),
        employee: $('#employee').val(),
        contact: $('#contact').val(),
        birth: $('#birth').val(),
        address: $('#address').val(),
        addressDetail: $('#addressDetail').val(),
        zoneCode: $('#zoneCode').val(),
        website: $('#website').val(),
        info: $('#info').val(),
        profileImage: base64String
    };

    const user = {
        username: $('#username').val(),
        password: $('#password').val(),
        name: $('#name').val(),
        role: 'ROLE_COMPANY',
        company: company,
    };

    console.log(user);
    api.post('/api/v1/users/register', user)
        .then(response => {
            alert('회원가입이 완료되었습니다!');
            window.location.href = '/login?role=ROLE_COMPANY';
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
