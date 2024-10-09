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

        // // 입력한 값 가져오기
        // const uuid = 1234;
        // const username = "용's"

        // // 로컬스토리지에 저장
        // saveToLocalStorage(uuid, username);


        // 서버로 로그인 요청 보내기
        fetch("http://192.168.1.22:2000/api/v1/auth/sign-in", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email: email, password: password })
        })
            .then(response => response.json())
            .then(data => {
                if (data.successful) {
                    // 로그인 성공 시 메인 페이지로 이동
                    alert("환영합니다!");
                    window.location.href = "board_list.html"; // 메인 페이지로 리다이렉트
                } else {
                    // 로그인 실패 시 에러 메시지 표시
                    alert("로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
            });
    });
});
