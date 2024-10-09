package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUpdateCommentDto {
    private Long commentId;
    private String username;
    private String content;
    private String createdDate;
    private Integer likeCount;
    private Boolean isSuccessful;
}
