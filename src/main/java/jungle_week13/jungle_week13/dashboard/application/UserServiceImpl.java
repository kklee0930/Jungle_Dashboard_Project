package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.domain.User;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestSignUpDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseSignUpDto;
import jungle_week13.jungle_week13.dashboard.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // 1. 회원가입
    @Override
    public ResponseSignUpDto signUp(RequestSignUpDto requestDto) {
        try {
            UUID uuid = UUID.randomUUID();
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .username(requestDto.getUsername())
                    .password(requestDto.getPassword())
                    .uuid(uuid)
                    .build();
            userRepository.save(user);
            return ResponseSignUpDto.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .username(user.getUsername())
                    .uuid(uuid)
                    .isSuccessful(true)
                    .build();
        } catch (Exception e) {
            log.error("회원가입 실패", e);
            return ResponseSignUpDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }

    // 2. 회원가입 시 이메일 중복 검사
    @Override
    @Transactional(readOnly = true)
    public ResponseDuplicateEmailCheckDto checkDuplicateEmail(RequestDuplicateEmailCheckDto requestDto) {
        Boolean isDuplicate = userRepository.existsUserByEmail(requestDto.getEmail());
        if(isDuplicate) {
            return ResponseDuplicateEmailCheckDto.builder().isDuplicate(true).build();
        }
        return ResponseDuplicateEmailCheckDto.builder().isDuplicate(false).build();
    }

    // 3. 회원가입 시 유저네임 중복 검사
    @Override
    @Transactional(readOnly = true)
    public ResponseDuplicateUsernameCheckDto checkDuplicateUsername(RequestDuplicateUsernameCheckDto requestDto) {
        Boolean isDuplicate = userRepository.existsUserByUsername(requestDto.getUsername());
        if (isDuplicate) {
            return ResponseDuplicateUsernameCheckDto.builder().isDuplicate(true).build();
        }
        return ResponseDuplicateUsernameCheckDto.builder().isDuplicate(false).build();
    }

    // 4. 유저 로그인
    @Override
    @Transactional(readOnly = true)
    public ResponseLoginDto login(RequestLoginDto requestDto) {
        try {
            Optional<User> searchedUser = userRepository.getByEmailAndPassword(
                    requestDto.getEmail(), requestDto.getPassword());

            if(searchedUser.isPresent()) {
                return ResponseLoginDto.builder()
                        .isSuccessful(true)
                        .username(searchedUser.get().getUsername())
                        .uuid(searchedUser.get().getUuid())
                        .build();
            }
            log.error("아이디 혹은 비밀번호 불일치");
            return ResponseLoginDto.builder()
                    .isSuccessful(false)
                    .build();

        } catch (Exception e) {
            log.error("로그인 실패", e);
            return ResponseLoginDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }
}