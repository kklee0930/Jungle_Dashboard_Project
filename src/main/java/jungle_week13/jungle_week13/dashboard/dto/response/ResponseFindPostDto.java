package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseFindPostDto {
    private Long postId; // 게시물 번호
    private String username; // 작성자 유저네임
    private String postTitle; // 게시물 제목
    private String postContent; // 게시물 내용
    private Long viewCount; // 게시물 조회 수
    private LocalDateTime createdDate; // 게시물 작성일
}