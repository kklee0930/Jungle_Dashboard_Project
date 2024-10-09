package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseCreateCommentDto {
    private Boolean commentCreated;
}
