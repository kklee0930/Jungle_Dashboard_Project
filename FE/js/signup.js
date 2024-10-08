function checkUsername(){
    const username = document.getElementById("username").value; // 입력된 닉네임을 받아온다.
    // 닉네임이 입력되지 않으면 중복확인 절차를 가지 않음
    if(!username){
        alert("닉네임을 입력해주세요!");
        return;
    }
    // 서버로 닉네임 중복 확인 요청 보내기
    fetch("/users/" + username + "/duplicate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username: username})     // 닉네임을 서버로 보낸다.
    })
    .then(response => response.json())      // 서버 응답을 JSON 형식으로 파싱
    // .then((data) => console.log(data));
    .then(data => {
        if (data.isAvailable) {
            alert("사용 가능한 닉네임입니다!"); // 사용 가능 시 알림
        } else {
            alert("중복된 닉네임입니다. 다른 닉네임을 사용해주세요!"); // 중복 시 알림
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("서버 오류가 발생했습니다. 다시 시도해주세요."); // 서버 오류 발생 시 알림
    });
}

function checkEmail(){
    const email = document.getElementById("email").value; 
    if(!email){
        alert("이메일을 입력해주세요!");
        return;
    }
    
    fetch("/users/" + email + "/duplicate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email: email})
    })
    .then(response => response.json())
    .then(data => {
        if (data.isAvailable) {
            alert("사용 가능한 이메일입니다!");
        } else {
            alert("중복된 이메일입니다. 다른 이메일을 사용해주세요!");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
    });
}

// 회원가입 완료
document.addEventListener("DOMContentLoaded", function () {
    const signupForm = document.querySelector("form");

    signupForm.addEventListener("submit", function (event) {
        // 기본 폼 제출 동작을 막음 (페이지 리로드 방지)
        event.preventDefault();

        // 여기에 서버로 데이터를 전송하는 코드가 들어갈 수 있습니다.
        // 예를 들어 fetch API를 이용해 서버로 회원가입 요청을 보낼 수 있습니다.

        // 회원가입 성공 시 alert 창 띄움
        alert("회원가입이 완료되었습니다! 로그인해주세요~");

        // 회원가입 후 로그인 페이지로 이동 (선택 사항)
        window.location.href = "login.html";
    });
});
