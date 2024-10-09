// 보드에 글 보여주기
// 댓글 리스트 나열
function getQueryParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);  // 현재 페이지 URL의 쿼리 문자열을 분석
    return urlParams.get(name);  // 원하는 쿼리 파라미터의 값을 반환
}
document.addEventListener("DOMContentLoaded", async function () {
    try {
        // 게시물 가져오는 API 호출
        // const id = getQueryParameter('id');
        const response = await fetch(`/api/v1/posts/${postId}`);
        if (!response.ok) {
            throw new Error("api 호출 실패");
        }
        const data = await response.json();     // JSON 형식으로 받아올거다.

        // 데이터 잘 받았으면 게시글 표시
        if (data) {
            // 제목, 작성자, 내용, 번호, 작성일, 조회수
            const postNo = data.data.postId;
            const title = data.data.postTitle;
            const username = data.data.username;
            const postContent = data.data.postContent;
            const postDate = data.data.createdDate;
            const postView = data.data.viewCount;

            // 게시물 정보 표시
            // const titleElement = document.querySelector(".title").textContent;
            // const num = document.querySelector(".board_read .num").textContent;
            // const writer = document.querySelector(".board_read .writer");
            // const date = document.querySelector(".board_read .date");
            // const count = document.querySelector(".board_read .count");
            // const content = document.querySelector(" .contents");

            // titleElement.textContent = title;
            // num.textContent = postNo;
            // writer.textContent = username;
            // date.textContent = postDate;
            // count.textContent = postView;
            // content.textContent = postContent;

            // const contentsElement = document.createElement("div");
            // contentsElement.innerHTML = `
            //     <div class="title">${title}</div>
            //     <div class="num">${postNo}</div>
            //     <div class="writer">${username}</div>
            //     <div class="date">${postDate}</div>
            //     <div class="count">${postView}</div>
            //     <div class="contents">${postContent}</div>
            // `
            const board = document.querySelector(".board_read")
            board.querySelector(".title").textContent = title;
            board.querySelector("#num").textContent = postNo;
            board.querySelector("#writer").textContent = username;
            board.querySelector("#date").textContent = postDate;
            board.querySelector("#count").textContent = postView;
            board.querySelector(".contents").textContent = postContent;
        } else {
            console.error("로딩 실패!");
        }

    } catch (error) {
        console.error("에러:", error);
    }
});