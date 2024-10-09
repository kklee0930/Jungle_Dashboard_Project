package jungle_week13.jungle_week13.dashboard.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
public class RequestSignUpDto {

    private String email;
    private String username;
    private String password;

//    public static RequestSignUpDto formRequestDto(String email, String username, String password) {
//        return RequestSignUpDto.builder()
//                .email(email)
//                .username(username)
//                .password(password)
//                .build();
//    }
}
