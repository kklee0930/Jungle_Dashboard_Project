package jungle_week13.jungle_week13.dashboard.presentation;

import jungle_week13.jungle_week13.dashboard.application.CommentService;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDeleteCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDeleteCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdateCommentDto;
import jungle_week13.jungle_week13.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 도메인 관련 API
     */
    // 1. 댓글 작성 API
    @PostMapping("/{postId}")
    public ApiResponse<Object> createComment(@PathVariable Long postId,
                                             @RequestBody RequestCreateCommentDto requestDto) {
        ResponseCreateCommentDto responseDto = commentService.createComment(postId, requestDto);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }

        return ApiResponse.ofSuccess(responseDto);
    }

    // 2. 댓글 수정 API
    @PutMapping("/{commentId}")
    public ApiResponse<Object> updateComment(@PathVariable Long commentId,
                                             @RequestBody RequestUpdateCommentDto requestDto) {
        ResponseUpdateCommentDto responseDto = commentService.updateComment(commentId, requestDto);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }

        return ApiResponse.ofSuccess(responseDto);
    }

    // 3. 댓글 삭제 API
    @PostMapping("/{commentId}/removal")
    public ApiResponse<Object> deleteComment(@PathVariable Long commentId,
                                             @RequestBody RequestDeleteCommentDto requestDto) {
        ResponseDeleteCommentDto responseDto = commentService.deleteComment(commentId, requestDto);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }

        return ApiResponse.ofSuccess(responseDto);
    }

    // 4. 댓글 리스트 조회 API
    public ApiResponse<Object> getCommentList(@PathVariable Long postId) {

        return ApiResponse.ofSuccess();
    }

    // 5. 단일 댓글 조회 API(수정 위한 조회)
    public ApiResponse<Object> getComment(@PathVariable Long commentId) {

        return ApiResponse.ofSuccess();
    }
}
