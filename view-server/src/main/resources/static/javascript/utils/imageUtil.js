/**
 * 이미지 타입 분석 함수
 * 이미지 출력시 src="return값" + base64String으로 사용
 * @param base64String
 * @returns {string}
 */
function loadImage(base64String) {

    // console.log(base64String);
    let returnSrc = '';
    if (!!base64String) {
        // Base64 문자열 디코딩
        const binaryString = atob(base64String);
        const header = [
            binaryString.charCodeAt(0),
            binaryString.charCodeAt(1),
            binaryString.charCodeAt(2),
            binaryString.charCodeAt(3),
        ].join(",");

        // 첫 4바이트를 추출하여 MIME 타입 분석
        switch (header) {
            case "255,216,255,224": // JPEG 파일 서명
            case "255,216,255,225": // JPEG EXIF 서명
                returnSrc = "data:image/jpeg;base64,";
                break;
            case "137,80,78,71": // PNG 파일 서명
                returnSrc = "data:image/png;base64,";
                break;
            case "71,73,70,56": // GIF 파일 서명
                returnSrc = "data:image/gif;base64,";
                break;
        }
    }
    return returnSrc === '' ? '/static/images/logo.png' : returnSrc + base64String;
}

/**
 * 이미지 파일을 Base64로 인코딩하는 함수
 * @param file - File 객체
 * @returns {Promise<string>} - Base64 문자열 반환
 */
function encodeImageToBase64(file) {
    return new Promise((resolve, reject) => {
        if (!file) {
            reject(new Error("파일이 제공되지 않았습니다."));
            return;
        }

        const reader = new FileReader();
        reader.onload = function (event) {
            const base64String = event.target.result.split(',')[1]; // Base64 데이터만 추출
            resolve(base64String);
        };
        reader.onerror = function () {
            reject(new Error("이미지를 Base64로 변환하는 중 오류가 발생했습니다."));
        };
        reader.readAsDataURL(file);
    });
}