package jungle_week13.jungle_week13.dashboard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid", nullable = false)
    private User user;

    // 게시물 번호(생성 순서대로 할당됨)
    @Column(nullable = false, name = "post_no")
    @ColumnDefault(value = "nextval('post_no_seq')")
    private Long number;

    @OneToMany(fetch = LAZY, mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    // 게시물의 제목
    @Column(nullable = false, name = "post_title")
    private String title;

    // 게시물 내용
    @Column(nullable = false, name = "post_content")
    private String content;

    // 게시물 조회 수
    @Column(nullable = false, name = "post_view")
    @ColumnDefault(value = "1")
    private Long viewCount = 1L;

    // 게시물 생성일
    @Column(nullable = false, name = "post_date")
    @ColumnDefault(value = "to_char(current_date, 'YYYY.MM.DD')")
    private LocalDateTime createdDate = LocalDateTime.now();

    // 게시물 소프트 딜리트 여부
    @Column(nullable = false, name = "soft_delete")
    @ColumnDefault(value = "false")
    private Boolean softDelete = false;

    /**
     * 엔티티 메서드
     */

    // 게시물 수정
    public Post updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

    // 게시물 삭제
    public void deletePost() {
        this.softDelete = true;
    }
}