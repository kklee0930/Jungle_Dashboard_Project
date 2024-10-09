package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestSignUpDto;

public interface UserService {

    // 유저의 회원가입
    Long signUp(RequestSignUpDto requestDto);

    // 유저의 회원가입 시 이메일 중복검사
    Boolean checkDuplicateEmail(RequestDuplicateEmailCheckDto requestDto);

    // 유저의 회원가입 시 유저네임 중복검사
    Boolean checkDuplicateUsername(RequestDuplicateUsernameCheckDto requestDto);

    // 유저의 로그인
    Boolean login(RequestLoginDto requestDto);
}
