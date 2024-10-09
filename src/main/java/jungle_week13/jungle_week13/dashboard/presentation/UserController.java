package jungle_week13.jungle_week13.dashboard.presentation;

import jungle_week13.jungle_week13.config.JwtUtil;
import jungle_week13.jungle_week13.dashboard.application.UserService;
import jungle_week13.jungle_week13.dashboard.application.UserServiceImpl;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestSignUpDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseLoginDto;
import jungle_week13.jungle_week13.dashboard.vo.request.RequestLoginVo;
import jungle_week13.jungle_week13.dashboard.vo.request.RequestSignUpVo;
import jungle_week13.jungle_week13.dashboard.vo.response.ResponseDuplicateEmailCheckVo;
import jungle_week13.jungle_week13.dashboard.vo.response.ResponseDuplicateUsernameCheckVo;
import jungle_week13.jungle_week13.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    /**
     * 유저 도메인 관련 API
     */

    // 1. 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<Object> signUp(@Validated @RequestBody RequestSignUpVo requestVo) {

//        RequestSignUpDto requestDto = RequestSignUpDto.formRequestDto(
//                requestVo.getEmail(),
//                requestVo.getUsername(),
//                requestVo.getPassword()
//        );
        RequestSignUpDto requestDto = modelMapper.map(requestVo, RequestSignUpDto.class);
        userService.signUp(requestDto);
        return ApiResponse.ofSuccess();
    }

    // 2. 이메일 중복검사 API
    @GetMapping("/{email}/duplicate")
    public ApiResponse<Object> emailDuplicateCheck(@PathVariable String email) {

        RequestDuplicateEmailCheckDto requestDto =
                modelMapper.map(email, RequestDuplicateEmailCheckDto.class);

        Boolean isDuplicate = userService.checkDuplicateEmail(requestDto);
        ResponseDuplicateEmailCheckDto responseDto =
                modelMapper.map(isDuplicate, ResponseDuplicateEmailCheckDto.class);

        ResponseDuplicateEmailCheckVo responseVo =
                modelMapper.map(responseDto, ResponseDuplicateEmailCheckVo.class);

        return ApiResponse.ofSuccess(responseVo);
    }

    // 3. 유저네임 중복검사 API
    @GetMapping("/{username}/duplicate")
    public ApiResponse<Object> usernameDuplicateCheck(@PathVariable String username) {

        RequestDuplicateUsernameCheckDto requestDto =
                modelMapper.map(username, RequestDuplicateUsernameCheckDto.class);

        Boolean isDuplicate = userService.checkDuplicateUsername(requestDto);
        ResponseDuplicateUsernameCheckDto responseDto =
                modelMapper.map(isDuplicate, ResponseDuplicateUsernameCheckDto.class);

        ResponseDuplicateUsernameCheckVo responseVo =
                modelMapper.map(responseDto, ResponseDuplicateUsernameCheckVo.class);

        return ApiResponse.ofSuccess(responseVo);
    }

    // TODO: 4. 로그아웃 API
//    @PostMapping("/logout")
//    public ApiResponse<Object> logout(@RequestBody )

    // 5. 로그인 API
    @PostMapping("/auth/login")
    public ApiResponse<Object> login(@Validated @RequestBody RequestLoginVo requestVo) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestVo.getEmail(), requestVo.getPassword())
            );
            String token = jwtUtil.createToken(requestVo.getEmail());
            return ApiResponse.ofSuccess(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

//        RequestLoginDto requestDto = modelMapper.map(requestVo, RequestLoginDto.class);
//        // TODO: jwt 사용할 거면 어떻게 해야할지 고민해보고 정하기. return Boolean 아니고 다를 수도 있기 때문에 일단 보류.
//        Boolean loginSuccess = userService.login(requestDto);
//        ResponseLoginDto responseDto = modelMapper.map(loginSuccess, ResponseLoginDto.class);
    }
}