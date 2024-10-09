package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseLoginDto {

    // TODO: jwt 사용할 거면 어떻게 해야할지 고민해보고 정하기.
    private String email;
    private String password;
}
