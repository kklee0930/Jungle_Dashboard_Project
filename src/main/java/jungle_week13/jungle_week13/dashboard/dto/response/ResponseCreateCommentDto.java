package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseCreateCommentDto {
    private Long commentId;
    private Long postId;
    private String username;
    private String content;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Boolean isSuccessful;
}
