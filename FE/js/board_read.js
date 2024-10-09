// 보드에 글 보여주기
// 댓글 리스트 나열
function getQueryParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);  // 현재 페이지 URL의 쿼리 문자열을 분석
    return urlParams.get(name);  // 원하는 쿼리 파라미터의 값을 반환
}
document.addEventListener("DOMContentLoaded", async function () {
    try {
        // 게시물 가져오는 API 호출
        const id = getQueryParameter('id');
        const response = await fetch(`posts/${post-id}`);
        if (!response.ok) {
            throw new Error("api 호출 실패");
        }
        const data = await response.json();     // JSON 형식으로 받아올거다.

        console.log(data);
        // 데이터 잘 받았으면 게시글 표시
        if (data) {
            // 게시물 정보 표시
            const titleElement = document.querySelector(".title");         
            const num = document.querySelector(".board_read .num");
            const writer = document.querySelector(".board_read .writer");
            const date = document.querySelector(".board_read .date");
            const count = document.querySelector(".board_read .count");
            const content = document.querySelector(" .contents");

            titleElement.textContent = data.title;
            num.textContent = data.num;
            writer.textContent = data.writer;
            date.textContent = data.date;
            count.textContent = data.count;
            content.textContent = data.content;

            const contentsElement = document.createElement("div");
            contentsElement.innerHTML = `
                <div class="title">${data.title}</div>
                <div class="num">${data.num}</div>
                <div class="writer">${data.writer}</div>
                <div class="date">${data.date}</div>
                <div class="count">${data.count}</div>
                <div class="contents">${data.content}</div>
            `
            console.log(data.title);
            title.appendChild(contentsElement);

        } else {
            console.error("로딩 실패!");
        }

    } catch (error) {
        console.error("에러:", error);
    }
});