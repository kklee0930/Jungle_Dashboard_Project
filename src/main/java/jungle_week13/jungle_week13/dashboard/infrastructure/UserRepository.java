package jungle_week13.jungle_week13.dashboard.infrastructure;

import jungle_week13.jungle_week13.dashboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email); // 이메일 중복검사
    Boolean existsUserByUsername(String username); // 유저네임 중복검사
    Optional<User> getByEmailAndPassword(String email, String password); // 로그인 위한 검사
    Optional<User> findByUuid(UUID uuid); // uuid로 유저 찾기
}