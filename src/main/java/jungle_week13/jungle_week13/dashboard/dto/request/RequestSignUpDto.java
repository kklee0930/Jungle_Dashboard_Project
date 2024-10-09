package jungle_week13.jungle_week13.dashboard.dto.request;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSignUpDto {

    private String email;
    private String username;
    private String password;
}
