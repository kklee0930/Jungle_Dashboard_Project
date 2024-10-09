package jungle_week13.jungle_week13.dashboard.presentation;

import jungle_week13.jungle_week13.dashboard.application.UserService;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestSignUpDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseSignUpDto;
import jungle_week13.jungle_week13.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
//    private AuthenticationManager authenticationManager;
//    private JwtUtil jwtUtil;

    /**
     * 유저 도메인 관련 API
     */

    // 1. 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<Object> signUp(@Validated @RequestBody RequestSignUpDto requestDto) {

        ResponseSignUpDto responseDto = userService.signUp(requestDto);
        if (!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }
        return ApiResponse.ofSuccess(responseDto);
    }

    // 2. 이메일 중복검사 API
    @GetMapping("/{email}/email-duplicate")
    public ApiResponse<Object> emailDuplicateCheck(@PathVariable String email) {

        RequestDuplicateEmailCheckDto requestDto = RequestDuplicateEmailCheckDto.builder()
                .email(email)
                .build();

        ResponseDuplicateEmailCheckDto responseDto = userService.checkDuplicateEmail(requestDto);

        return ApiResponse.ofSuccess(responseDto);
    }

    // 3. 유저네임 중복검사 API
    @GetMapping("/{username}/username-duplicate")
    public ApiResponse<Object> usernameDuplicateCheck(@PathVariable String username) {

        RequestDuplicateUsernameCheckDto requestDto = RequestDuplicateUsernameCheckDto.builder()
                .username(username)
                .build();

        ResponseDuplicateUsernameCheckDto responseDto = userService.checkDuplicateUsername(requestDto);

        return ApiResponse.ofSuccess(responseDto);
    }

    // 4. 로그아웃 API
    @PostMapping("/logout")
    public ApiResponse<Object> logout() {
        return ApiResponse.ofSuccess("로그아웃 되었습니다.");
    }

    // 5. 로그인 API
    @PostMapping("/login")
    public ApiResponse<Object> login(@Validated @RequestBody RequestLoginDto requestDto) {

        ResponseLoginDto responseDto = userService.login(requestDto);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }
        return ApiResponse.ofSuccess(responseDto);

        // jwt 사용 가능 시 아래 코드로 변경
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(requestVo.getEmail(), requestVo.getPassword())
//            );
//            String token = jwtUtil.createToken(requestVo.getEmail());
//            return ApiResponse.ofSuccess(token);
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
//        }

//        RequestLoginDto requestDto = modelMapper.map(requestVo, RequestLoginDto.class);
//        Boolean loginSuccess = userService.login(requestDto);
//        ResponseLoginDto responseDto = modelMapper.map(loginSuccess, ResponseLoginDto.class);
    }
}