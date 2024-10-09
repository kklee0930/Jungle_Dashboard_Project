// 게시물 삭제
// 댓글 삭제
document.addEventListener("DOMContentLoaded", function(){
    try{
        const response = await fetch(`/api/v1/board/${post-id}`);
        if(!response.ok){
            throw new Error("API 호출 실패");
        }

        const data = await response.json(); // JSON 형식으로 데이터 가져오기

        // 게이글 데이터를 화면에 표기



        const deleteButton = document.getElementById('deleteButton');
        const editButton = document.getElementById('editButton');

        if (loggedInUserId === writerId) {
            deleteButton.style.display = "inline"; // 삭제하기 버튼 표시
            editButton.style.display = "inline"; // 수정하기 버튼 표시
        } else {
            deleteButton.style.display = "none"; // 삭제하기 버튼 숨김
            editButton.style.display = "none"; // 수정하기 버튼 숨김
        }
        // 삭제 버튼 클릭 이벤트 처리
        deleteButton.addEventListener("click", async function () {
            const confirmDelete = confirm("정말로 이 게시글을 삭제하시겠습니까?");
            if (!confirmDelete) {
                return; // 사용자가 삭제를 취소한 경우 종료
            }

            try {
                const response = await fetch(`/posts/${data.id}/removal`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        uuid: "사용자_UUID_여기에_삽입" // 실제 로그인한 사용자의 UUID로 교체
                    })
                });

                const responseData = await response.json();

                if (!response.ok || !responseData.data.isSuccessful) {
                    throw new Error(responseData.message || "게시글 삭제에 실패했습니다.");
                }

                alert("게시글이 성공적으로 삭제되었습니다.");
                // 성공적으로 삭제되면 게시글 목록 페이지로 이동
                window.location.href = "board_list.html";
            } catch (error) {
                console.error("삭제 중 오류 발생:", error);
                alert("게시글 삭제 중 오류가 발생했습니다.");
            }
        }