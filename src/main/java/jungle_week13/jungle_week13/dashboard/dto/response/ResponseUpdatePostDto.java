package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseUpdatePostDto {

    private Long postId;
    private String username;
    private String postTitle;
    private String postContent;
    private Long viewCount;
    private LocalDateTime createdDate;
    private Boolean isSuccessful;
}
