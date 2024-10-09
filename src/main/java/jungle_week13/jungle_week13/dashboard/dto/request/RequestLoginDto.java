package jungle_week13.jungle_week13.dashboard.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RequestLoginDto {

    private String email;
    private String password;
}
