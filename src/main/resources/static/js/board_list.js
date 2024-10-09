// // 페이지가 로드되면 글 목록을 API로 받아오고 표시
// document.addEventListener("DOMContentLoaded", function() {
//     // API 호출로 글 목록을 받아오는 함수
//     fetch("/api/v1/board/list")
//         .then(response => response.json())  // JSON 응답을 처리
//         .then(data => {
//             if (data && Array.isArray(data.articles)) {
//                 const boardList = document.querySelector(".board_list"); // board_list 요소 선택
//                 const articles = data.articles; // API로부터 받은 글 목록 데이터

//                 // 글 목록을 반복해서 동적으로 HTML로 생성
//                 articles.forEach((article, index) => {
//                     // 각 글의 데이터에 맞춰 HTML 요소 생성
//                     const articleElement = document.createElement("div");
//                     articleElement.classList.add("article"); // 각 글에 클래스 추가

//                     articleElement.innerHTML = `
//                         <div class="num">${article.number}</div>
//                         <div class="title"><a href="board_read.html?id=${article.id}">${article.title}</a></div>
//                         <div class="writer">${article.writer}</div>
//                         <div class="date">${article.date}</div>
//                         <div class="count">${article.viewCount}</div>
//                     `;

//                     // 생성한 글 요소를 board_list에 추가
//                     boardList.appendChild(articleElement);
//                 });
//             } else {
//                 console.error("글 목록을 가져오는 데 실패했습니다.");
//             }
//         })
//         .catch(error => {
//             console.error("에러 발생:", error);
//         });
// });

// // 1. 페이지 넘기기 (이전, 다음 버튼)
// document.querySelectorAll(".board_page .bt").forEach(button => {
//     button.addEventListener("click", function(event) {
//         event.preventDefault();  // 기본 동작 방지

//         // 현재 페이지 번호 가져오기
//         const currentPage = document.querySelector(".board_page .num.on");
//         const currentPageNum = parseInt(currentPage.textContent);

//         // 이전 버튼 클릭
//         if (this.classList.contains("prev")) {
//             if (currentPageNum > 1) {
//                 currentPage.classList.remove("on");
//                 currentPage.previousElementSibling.classList.add("on");
//             }
//         }

//         // 다음 버튼 클릭
//         if (this.classList.contains("next")) {
//             if (currentPageNum < 5) {  // 여기서는 페이지 번호가 5까지 있다고 가정
//                 currentPage.classList.remove("on");
//                 currentPage.nextElementSibling.classList.add("on");
//             }
//         }

//         // <<, >> 버튼 클릭
//         if (this.classList.contains("first")) {
//             document.querySelector(".board_page .num.on").classList.remove("on");
//             document.querySelector(".board_page .num").classList.add("on");
//         }
//         if (this.classList.contains("last")) {
//             document.querySelector(".board_page .num.on").classList.remove("on");
//             document.querySelector(".board_page .num:last-child").classList.add("on");
//         }
//     });
// });

document.addEventListener("DOMContentLoaded", async function() {
    try {
        // 게시글 목록을 가져오는 API 호출
        const response = await fetch("/api/v1/posts/posts-list");
        if (!response.ok) {
            throw new Error("API 호출에 실패했습니다.");
        }
        const data = await response.json();

        // 데이터가 정상적으로 오면 글 목록을 화면에 표시
        if (data) {
            const boardList = document.querySelector(".board_list");
            const articles = data.data.postList;  // 게시글 목록
            console.log(articles);

            articles.forEach(article => {
                const articleElement = document.createElement("div");
                articleElement.classList.add("article");

                articleElement.innerHTML = `
                    <div class="num">${article.id}</div>
                    <div class="title"><a href="/board_read/${article.id}">${article.title}</a></div>
                    <div class="writer">${article.username}</div>
                    <div class="date">${article.createdDate}</div>
                    <div class="count">${article.viewCount}</div> 
                `;

                boardList.appendChild(articleElement);
            });
        } else {
            console.error("글 목록을 가져오는 데 실패했습니다.");
        }

        // 페이지네이션 버튼 클릭 처리
        document.querySelectorAll(".board_page .bt").forEach(button => {
            button.addEventListener("click", function(event) {
                event.preventDefault();  // 기본 동작 방지

                const currentPage = document.querySelector(".board_page .num.on");
                const currentPageNum = parseInt(currentPage.textContent);

                // 이전 버튼 클릭
                if (this.classList.contains("prev")) {
                    if (currentPageNum > 1) {
                        currentPage.classList.remove("on");
                        currentPage.previousElementSibling.classList.add("on");
                    }
                }

                // 다음 버튼 클릭
                if (this.classList.contains("next")) {
                    if (currentPageNum < 5) {  // 페이지 수를 동적으로 변경할 수 있습니다.
                        currentPage.classList.remove("on");
                        currentPage.nextElementSibling.classList.add("on");
                    }
                }

                // <<, >> 버튼 클릭
                if (this.classList.contains("first")) {
                    document.querySelector(".board_page .num.on").classList.remove("on");
                    document.querySelector(".board_page .num").classList.add("on");
                }
                if (this.classList.contains("last")) {
                    document.querySelector(".board_page .num.on").classList.remove("on");
                    document.querySelector(".board_page .num:last-child").classList.add("on");
                }
            });
        });

    } catch (error) {
        console.error("에러 발생:", error);
    }
});
