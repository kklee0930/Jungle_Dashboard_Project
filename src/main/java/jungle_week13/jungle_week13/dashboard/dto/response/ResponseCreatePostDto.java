package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ResponseCreatePostDto {

    private Long postId;
    private Boolean postCreated;
    private String title;
    private String content;
    private Long viewCount;
    private LocalDateTime createdDate;
}
