package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdateCommentDto;

public interface CommentService {

    ResponseCreateCommentDto createComment(Long postId, RequestCreateCommentDto requestDto);
    ResponseUpdateCommentDto updateComment(Long commentId, RequestUpdateCommentDto requestDto);
}
