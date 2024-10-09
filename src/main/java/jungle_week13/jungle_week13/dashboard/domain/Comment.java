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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    // 댓글 작성자의 username
    @Column(name = "comment_username")
    private String username;

    // 댓글 내용
    @Column(name = "comment_content")
    private String content;

    // 댓글 작성일
    @Column(name = "comment_date")
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    // 댓글 좋아요 수
    @Column(name = "comment_like")
    @Builder.Default
    private Integer likeCount = 0;

    // 댓글 작성자의 uuid
    @Column(name = "uuid")
    private UUID uuid;

    // 댓글 소프트 딜리트 여부
    @Column(name = "soft_delete")
    @Builder.Default
    private Boolean softDelete = false;

    /**
     * 엔티티 메서드
     */

    // 댓글 수정
    public Comment updateComment(String content) {
        this.content = content;
        return this;
    }

    // 댓글 삭제
    public void deleteComment() {
        this.softDelete = true;
    }
}