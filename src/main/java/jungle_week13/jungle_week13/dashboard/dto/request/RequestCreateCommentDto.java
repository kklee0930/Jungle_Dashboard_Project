package jungle_week13.jungle_week13.dashboard.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RequestCreateCommentDto {
    private UUID uuid;
    private String username;
    private String commentContent;
}
