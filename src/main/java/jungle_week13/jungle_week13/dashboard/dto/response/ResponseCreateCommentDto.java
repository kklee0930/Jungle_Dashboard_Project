package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreateCommentDto {
    private Long commentId;
    private Long postId;
    private String username;
    private String content;
    private String createdDate;
    private Integer likeCount;
    private Boolean isSuccessful;
}
