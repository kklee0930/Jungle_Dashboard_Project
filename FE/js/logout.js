// 1. 로그아웃 버튼 클릭 시 로그아웃 API 호출 및 로그인 페이지로 이동
document.querySelector(".logout_button").addEventListener("click", function(event) {
    event.preventDefault();  // 기본 동작 방지 (새로고침 등)
    
    // 로그아웃 API 호출
    fetch("/api/v1/users/logout", {
        method: "POST",  // 로그아웃은 POST
        headers: {
            "Content-Type": "application/json",
            // 필요한 경우 인증 토큰 등을 헤더에 추가할 수 있음
            // "Authorization": "Bearer YOUR_TOKEN" 
        }
    })
    .then(response => {
        if (response.ok) {
            // 로그아웃 성공 시 로그인 페이지로 이동
            window.location.href = "../login.html";  // 로그인 페이지로 리디렉션
        } else {
            // 로그아웃 실패 시 에러 메시지 표시
            alert("로그아웃 실패. 다시 시도해주세요.");
        }
    })
    .catch(error => {
        // 네트워크 오류 등 예외 처리
        console.error("로그아웃 중 오류 발생:", error);
        alert("로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.");
    });
});