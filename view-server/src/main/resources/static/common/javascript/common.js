function logout() {
    axios.delete("/api/v1/session", {
        withCredentials: true, // 쿠키 포함 요청
    }).then(() => {
        document.cookie = "token=; Max-Age=0; path=/;";
        sessionStorage.removeItem("user");

        alert("로그아웃되었습니다.");
        location.href = "/";
    }).catch(error => {
        console.error("로그아웃 실패", error);
    });
}
