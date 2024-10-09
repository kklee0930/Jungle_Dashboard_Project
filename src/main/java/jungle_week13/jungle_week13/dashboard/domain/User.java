package jungle_week13.jungle_week13.dashboard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "CLIENT_USER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // 회원가입 시 입력한 회원의 이메일
    @Column(nullable = false)
    private String email;

    @OneToMany(fetch = LAZY, mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    // 회원가입 시 입력한 유저의 username
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private UUID uuid;

    // 회원가입 시 입력한 유저의 password
    @Column(nullable = false)
    private String password;
}