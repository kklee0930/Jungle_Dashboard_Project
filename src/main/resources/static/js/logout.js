// 1. 로그아웃 버튼 클릭 시 로그아웃 API 호출 및 로그인 페이지로 이동
// document.querySelector(".logout_button").addEventListener("click", async function(event) {
//     console.log("로그아웃 버튼 클릭");
//     event.preventDefault();
//     try {
//         console.log("로그아웃 버튼 클릭");
//         const response = await fetch("/api/v1/users/logout", {
//             method: "POST",  // 로그아웃은 POST
//             headers: {
//                 "Content-Type": "application/json",
//                 // 필요한 경우 인증 토큰 등을 헤더에 추가할 수 있음
//                 // "Authorization": "Bearer YOUR_TOKEN"
//             }
//         })
//
//         if(response.ok) {
//             console.log("로그아웃 성공");
//             localStorage.clear();
//             alert("로그아웃 되었습니다.")
//
//             // 로그아웃 성공 시 로그인 페이지로 이동
//             window.location.href = "/login";  // 로그인 페이지로 리디렉션
//         }
//         else {
//             // 로그아웃 실패 시 에러 메시지 표시
//             alert("로그아웃 실패. 다시 시도해주세요.");
//         }
//     } catch (error) {
//         // 네트워크 오류 등 예외 처리
//         console.error("로그아웃 중 오류 발생:", error);
//         alert("로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.");
//     }
//
//     // .then(response => {
//     //     if (response.ok) {
//     //
//     //         // local storage에 있는 데이터 삭제하기
//     //         localStorage.clear();
//     //         alert("로그아웃 되었습니다.")
//     //
//     //         // 로그아웃 성공 시 로그인 페이지로 이동
//     //         window.location.href = "/login";  // 로그인 페이지로 리디렉션
//     //     } else {
//     //         // 로그아웃 실패 시 에러 메시지 표시
//     //         alert("로그아웃 실패. 다시 시도해주세요.");
//     //     }
//     // })
//     // .catch(error => {
//     //     // 네트워크 오류 등 예외 처리
//     //     console.error("로그아웃 중 오류 발생:", error);
//     //     alert("로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.");
//     // });
// });
document.addEventListener("DOMContentLoaded", function () {
    const logoutButton = document.querySelector(".logout_button");  // 로그아웃 버튼 선택

    if (logoutButton) {
        console.log("로그아웃 버튼이 연결되었습니다.");  // 버튼이 제대로 선택되었는지 확인

        // 로그아웃 버튼에 클릭 이벤트 리스너 추가
        logoutButton.addEventListener("click", function(event) {
            event.preventDefault();  // 기본 동작 방지 (페이지 리로드 방지)
            console.log("로그아웃 버튼 클릭됨");  // 클릭 이벤트가 발생하는지 확인

            // 로그아웃 API 호출
            fetch("/api/v1/users/logout", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                }
            })
                .then(response => {
                    if (response.ok) {
                        console.log("로그아웃 성공");
                        localStorage.clear();  // 로컬 스토리지에서 유저 데이터 제거
                        alert("성공적으로 로그아웃되었습니다.");
                        window.location.href = "/login";  // 로그인 페이지로 리다이렉트
                    } else {
                        console.error("로그아웃 실패");
                        alert("로그아웃에 실패했습니다.");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("서버 오류가 발생했습니다.");
                });
        });
    } else {
        console.error("로그아웃 버튼을 찾을 수 없습니다.");  // 버튼을 찾지 못했을 때 에러 로그
    }
});