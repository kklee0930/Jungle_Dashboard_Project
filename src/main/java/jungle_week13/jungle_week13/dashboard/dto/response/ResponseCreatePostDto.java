package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCreatePostDto {

    private Long postId;
    private Boolean postCreated;
    private String title;
    private String content;
    private Long viewCount;
    private String createdDate;
}
