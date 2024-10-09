// function checkUsername() {
//     const username = document.getElementById("username").value; // 입력된 닉네임을 받아온다.
//     // 닉네임이 입력되지 않으면 중복확인 절차를 가지 않음
//     if (!username) {
//         alert("닉네임을 입력해주세요!");
//         return;
//     }
//     // 서버로 닉네임 중복 확인 요청 보내기
//     fetch("/api/v1/users/" + username + "/duplicate", {
//         method: "GET",
//     })
//         .then(response => response.json())      // 서버 응답을 JSON 형식으로 파싱
//         // .then((data) => console.log(data));
//         .then(data => {
//             if (data.successful) {
//                 alert("사용 가능한 닉네임입니다!"); // 사용 가능 시 알림
//             } else {
//                 alert("중복된 닉네임입니다. 다른 닉네임을 사용해주세요!"); // 중복 시 알림
//             }
//         })
//         .catch(error => {
//             console.error("Error:", error);
//             alert("서버 오류가 발생했습니다. 다시 시도해주세요."); // 서버 오류 발생 시 알림
//         });
// }

// function checkEmail() {
//     const email = document.getElementById("email").value;
//     if (!email) {
//         alert("이메일을 입력해주세요!");
//         return;
//     }

//     fetch("/api/v1/users/" + email + "/duplicate", {
//         method: "GET",
//     })
//         .then(response => response.json())
//         .then(data => {
//             if (data.successful) {
//                 alert("사용 가능한 이메일입니다!");
//             } else {
//                 alert("중복된 이메일입니다. 다른 이메일을 사용해주세요!");
//             }
//         })
//         .catch(error => {
//             console.error("Error:", error);
//             alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
//         });
// }

// // 회원가입 완료
// document.addEventListener("DOMContentLoaded", function () {
//     const signupForm = document.querySelector("form");

//     signupForm.addEventListener("submit", function (event) {
//         // 기본 폼 제출 동작을 막음 (페이지 리로드 방지)
//         event.preventDefault();

//         // 여기에 서버로 데이터를 전송하는 코드가 들어갈 수 있습니다.
//         // 예를 들어 fetch API를 이용해 서버로 회원가입 요청을 보낼 수 있습니다.

//         // 회원가입 성공 시 alert 창 띄움
//         alert("회원가입이 완료되었습니다! 로그인해주세요.");

//         // 회원가입 후 로그인 페이지로 이동 (선택 사항)
//         window.location.href = "../login.html";
//     });
// });


// 닉네임 중복 확인 함수
async function checkUsername() {
    const username = document.getElementById("username").value; // 입력된 닉네임을 받아온다.
    
    // 닉네임이 입력되지 않으면 중복확인 절차를 가지 않음
    if (!username) {
        alert("닉네임을 입력해주세요!");
        return;
    }

    try {
        // 서버로 닉네임 중복 확인 요청 보내기
        const response = await fetch("/api/v1/users/" + username + "/username-duplicate", {
            method: "GET",
        });

        // 응답을 JSON으로 파싱
        const data = await response.json();

        if (!data.data.isDuplicate) {
            alert("사용 가능한 닉네임입니다!"); // 사용 가능 시 알림
        } else {
            alert("중복된 닉네임입니다. 다른 닉네임을 사용해주세요!"); // 중복 시 알림
        }
    } catch (error) {
        console.error("Error:", error);
        alert("서버 오류가 발생했습니다. 다시 시도해주세요."); // 서버 오류 발생 시 알림
    }
}

// 이메일 중복 확인 함수
async function checkEmail() {
    const email = document.getElementById("email").value;

    // 이메일이 입력되지 않으면 중복확인 절차를 가지 않음
    if (!email) {
        alert("이메일을 입력해주세요!");
        return;
    }

    try {
        // 서버로 이메일 중복 확인 요청 보내기
        const response = await fetch("/api/v1/users/" + email + "/email-duplicate", {
            method: "GET",
        });

        // 응답을 JSON으로 파싱
        const data = await response.json();

        if (!data.data.isDuplicate) {
            alert("사용 가능한 이메일입니다!"); // 사용 가능 시 알림
        } else {
            alert("중복된 이메일입니다. 다른 이메일을 사용해주세요!"); // 중복 시 알림
        }
    } catch (error) {
        console.error("Error:", error);
        alert("서버 오류가 발생했습니다. 다시 시도해주세요."); // 서버 오류 발생 시 알림
    }
}

// 회원가입 완료
document.addEventListener("DOMContentLoaded", function () {
    const signupForm = document.querySelector("form");

    signupForm.addEventListener("submit", async function (event) {
        // 기본 폼 제출 동작을 막음 (페이지 리로드 방지)
        event.preventDefault();

        // 폼 데이터 가져오기
        const email = document.getElementById("email").value;
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        // 유효성 검사 (모든 필드가 입력되었는지 확인)
        if (!email || !username || !password) {
            alert("모든 필드를 입력해주세요!");
            return;
        }

        try {
            // 회원가입 POST 요청 보내기
            const response = await fetch("/api/v1/users/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    username: username,
                    password: password
                })
            });

            const data = await response.json();

            // 서버 응답 처리
            if (data.data.isSuccessful) {
                alert("회원가입이 완료되었습니다! 로그인해주세요.");
                window.location.href = "/login"; // 회원가입 후 로그인 페이지로 이동
            } else {
                alert("회원가입에 실패하였습니다. 다시 시도해주세요.");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("서버 오류가 발생했습니다. 다시 시도해주세요."); // 서버 오류 발생 시 알림
        }
    });
});

// 회원가입 완료된거 POST 작업 필요..