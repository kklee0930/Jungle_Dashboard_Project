document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector("form");

    loginForm.addEventListener("submit", function (event) {
        // 기본 폼 제출 동작을 막음 (페이지 새로고침 방지)
        event.preventDefault();

        // 사용자가 입력한 이메일과 비밀번호 가져오기
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        // 입력값 확인
        if (!email || !password) {
            alert("이메일과 비밀번호를 모두 입력해주세요.");
            return;
        }
        // UUID랑 username을 로컬스토리지에 저장하는 함수
        function saveToLocalStorage(uuid, username) {
            // 객체 형태로 데이터를 저장
            const user = { uuid, username };

            // JSON 문자열로 변환하여 로컬스터리지에 저장
            localStorage.setItem('userData', JSON.stringify(user));
        }

        // 서버로 로그인 요청 보내기
        fetch("/api/v1/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email: email, password: password })
        })
            .then(response => response.json())
            .then(data => {
                // 로그인이 성공적으로 처리되었을 때
                if (data.data.isSuccessful) {
                    // 서버에서 응답으로 받은 uuid와 username 추출
                    const { uuid, username } = data.data;

                    // 로컬 스토리지에 uuid와 username 저장
                    localStorage.setItem('userData', JSON.stringify({ uuid, username }));

                    // 로그인 성공 메시지
                    alert("환영합니다!");

                    // 로그인 후 게시판 목록 페이지로 이동
                    window.location.href = "/dashboard";
                } else {
                    // 로그인 실패 시 메시지 출력
                    alert("로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
            });
    });
});