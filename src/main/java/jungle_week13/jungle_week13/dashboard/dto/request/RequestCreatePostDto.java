package jungle_week13.jungle_week13.dashboard.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestCreatePostDto {

    private String postTitle;
    private String postContent;
}
