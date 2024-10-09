package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ResponseLoginDto {

    private String email;
    private String username;
    private UUID uuid;
    private Boolean isSuccessful;
}
