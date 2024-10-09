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

    // TODO:GeneratedValue(strategy = GenerationType.IDENTITY) 사용하는거 맞는지 double check하기
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    // TODO: 설정한 연관관계 맞는지 확인하기
    @OneToOne(fetch = LAZY)
    @Column(nullable = false, name = "uuid")
    @JoinColumn(name = "uuid")
    private User user;

    // 게시물 번호(생성 순서대로 할당됨)
    @Column(nullable = false, name = "post_no")
    @ColumnDefault(value = "nextval('post_no_seq')")
    private Long number;

    // TODO: 설정한 연관관계 맞는지 확인하기
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
    private Long viewCount;

    // 게시물 생성일
    @Column(nullable = false, name = "post_date")
    @ColumnDefault(value = "to_char(current_date, 'YYYY.MM.DD')")
    private LocalDateTime createdDate;

    // 게시물 소프트 딜리트 여부
    @Column(nullable = false, name = "soft_delete")
    @ColumnDefault(value = "false")
    private Boolean softDelete;

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}