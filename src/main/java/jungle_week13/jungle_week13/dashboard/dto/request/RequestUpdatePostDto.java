package jungle_week13.jungle_week13.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdatePostDto {
    private UUID uuid;
    private String postTitle;
    private String postContent;
}
