package jungle_week13.jungle_week13.dashboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    // TODO: 설정한 연관관계 맞는지 확인하기
    // TODO: cascade 걸어줘야 하는지 확인하기
    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 댓글 작성자의 username
    @Column(nullable = false, name = "comment_username")
    private String username;

    // 댓글 내용
    @Column(nullable = false, name = "comment_content")
    private String content;

    // 댓글 작성일
    @Column(nullable = false, name = "comment_date")
    @ColumnDefault(value = "nextval('comment_no_seq')")
    private LocalDateTime createdDate;

    // 댓글 좋아요 수
    @Column(nullable = false, name = "comment_like")
    @ColumnDefault(value = "0")
    private Integer likeCount;

    // 댓글 작성자의 uuid
    @Column(nullable = false, name = "uuid")
    private UUID uuid;

    // 댓글 소프트 딜리트 여부
    @Column(nullable = false, name = "soft_delete")
    @ColumnDefault(value = "false")
    private Boolean softDelete;

    public void updateComment(String title, )
}