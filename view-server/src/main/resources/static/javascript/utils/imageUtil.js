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

function loadEditImage(base64String) {

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
    return returnSrc === '' ? '' : returnSrc + base64String;
}