const loggedInUserId = "김용성"; // 실제 로그인 사용자 ID로 교체

// 글쓴이 ID 가져오기
const writerId = document.getElementById('writer').innerText;

// 버튼 표시 제어
const deleteButton = document.getElementById('deleteButton');
const editButton = document.getElementById('editButton');
const buttonWrap = document.getElementById('buttonWrap');

if (loggedInUserId === writerId) {
    deleteButton.style.display = "inline"; // 삭제하기 버튼 표시
    editButton.style.display = "inline"; // 수정하기 버튼 표시
} else {
    deleteButton.style.display = "none"; // 삭제하기 버튼 숨김
    editButton.style.display = "none"; // 수정하기 버튼 숨김
}