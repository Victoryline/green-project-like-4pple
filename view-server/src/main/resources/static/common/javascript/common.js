function logout() {
    axios.delete("/api/v1/session")
        .then(() => {
            sessionStorage.removeItem("user");
            alert("로그아웃되었습니다.");
            location.href = "/";
        });
}