function logout() {
    axios.delete("/api/v1/session")
        .then(() => {
            sessionStorage.removeItem("user");
            alert("로그아웃되었습니다.");
            location.href = "/";
        });
}

function disconnect() {
    eventSource.close();
    console.log(`[${clientId}] 연결 종료`);
}