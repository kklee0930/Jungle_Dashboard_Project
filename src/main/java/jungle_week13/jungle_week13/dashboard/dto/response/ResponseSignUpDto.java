package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ResponseSignUpDto {

    private UUID uuid;
    private String email;
    private String username;
    private String password;
    private Boolean isSuccessful;
}
