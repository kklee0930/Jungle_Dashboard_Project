package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.domain.User;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateUsernameCheckDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestLoginDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestSignUpDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseLoginDto;
import jungle_week13.jungle_week13.dashboard.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // TODO: try catch 문 다시 확인해보기. 이렇게 짜는게 맞나?
    // 1. 회원가입
    @Override
    public Long signUp(RequestSignUpDto requestDto) {

        try {
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .username(requestDto.getUsername())
                    .password(requestDto.getPassword())
                    .uuid(UUID.randomUUID())
                    .build();
            User saved = userRepository.save(user);
            return saved.getId();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 2. 회원가입 시 이메일 중복 검사
    @Override
    @Transactional(readOnly = true)
    public Boolean checkDuplicateEmail(RequestDuplicateEmailCheckDto requestDto) {
        try{
            return userRepository.existsUserByEmail(requestDto.getEmail());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 3. 회원가입 시 유저네임 중복 검사
    @Override
    @Transactional(readOnly = true)
    public Boolean checkDuplicateUsername(RequestDuplicateUsernameCheckDto requestDto) {
        try {
            return userRepository.existsUserByUsername(requestDto.getUsername());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 4. 유저 로그인
    @Override
    @Transactional(readOnly = true)
    public Boolean login(RequestLoginDto requestDto) {
        try {
            return userRepository.findByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}