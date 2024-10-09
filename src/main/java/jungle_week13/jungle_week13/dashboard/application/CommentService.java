package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDeleteCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDeleteCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdateCommentDto;

public interface CommentService {
    // 댓글 생성
    ResponseCreateCommentDto createComment(Long postId, RequestCreateCommentDto requestDto);
    // 댓글 수정
    ResponseUpdateCommentDto updateComment(Long commentId, RequestUpdateCommentDto requestDto);
    // 댓글 삭제
    ResponseDeleteCommentDto deleteComment(Long commentId, RequestDeleteCommentDto requestDto);
}
