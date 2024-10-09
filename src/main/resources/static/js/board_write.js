document.addEventListener("DOMContentLoaded", function () {
    const userData = JSON.parse(localStorage.getItem('userData'));

    const userUuid = userData.uuid; // 실제 로그인 사용자 ID로 교체

    // 글쓴이 ID 가져오기
    const writerId = userData.username;

    // // 버튼 표시 제어
    // const deleteButton = document.getElementById('deleteButton');
    // const editButton = document.getElementById('editButton');
    // const buttonWrap = document.getElementById('buttonWrap');
    //
    // if (loggedInUserId === writerId) {
    //     deleteButton.style.display = "inline"; // 삭제하기 버튼 표시
    //     editButton.style.display = "inline"; // 수정하기 버튼 표시
    // }
    // else {
    //     deleteButton.style.display = "none"; // 삭제하기 버튼 숨김
    //     editButton.style.display = "none"; // 수정하기 버튼 숨김
    // }

    // 게시글 작성 부분
    const submitButton = document.querySelector(".board_button_wrap .on");

    submitButton.addEventListener("click", async function (event) {
        event.preventDefault();     // 페이지 리로드 방지

        const title = document.querySelector(".board_write .title input").value;
        const content = document.querySelector(".board_write .contents textarea").value;

        // 제목과 내용이 비어있으면 경고
        if (!title || !content) {
            alert("제목과 내용을 입력해주세요.");
            return;
        }

        // 서버에 데이터 보내기 요청 (POST)
        const postData = {
            uuid: userUuid,          // 실제 유저 UUID로 교체
            postTitle: title,        // 제목
            postContent: content     // 내용
        };

        try {
            const response = await fetch("/api/v1/posts", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(postData)
            });

            if (!response.ok) {
                throw new Error("게시글 등록에 실패하였습니다.");
            }

            alert("등록이 완료되었습니다.");
            window.location.href = "/dashboard";   // 게시글 목록으로 이동
        } catch (error) {
            console.error("에러:", error);
            alert("에러가 발생했습니다.");
        }
    });
});
