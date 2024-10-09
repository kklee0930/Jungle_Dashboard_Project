package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseUpdateCommentDto {
    private Long commentId;
    private String username;
    private String content;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Boolean isSuccessful;
}
