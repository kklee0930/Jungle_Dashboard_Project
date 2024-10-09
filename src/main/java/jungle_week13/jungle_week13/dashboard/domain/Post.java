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
    @JoinColumn(name = "uuid")
    private User user;

//    // 게시물 번호(생성 순서대로 할당됨)
//    @Column(name = "post_no")
//    @ColumnDefault(value = "nextval('post_no_seq')")
//    private Long number;

    @OneToMany(fetch = LAZY, mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    // 게시물의 제목
    @Column(name = "post_title")
    private String title;

    // 게시물 내용
    @Column(name = "post_content")
    private String content;

    // 게시물 조회 수
    @Column(name = "post_view")
    @Builder.Default
    private Long viewCount = 1L;

    // 게시물 생성일
    @Column(name = "post_date")
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    // 게시물 소프트 딜리트 여부
    @Column(name = "soft_delete")
    @Builder.Default
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

    @Builder
    @Getter
    public static class PostDTO {
        private Long id;
        private String title;
        private String content;
        private Long viewCount;
        private LocalDateTime createdDate;
    }

    public PostDTO entityToDTO() {
        return PostDTO.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .viewCount(this.viewCount)
                .createdDate(this.createdDate)
                .build();
    }
}