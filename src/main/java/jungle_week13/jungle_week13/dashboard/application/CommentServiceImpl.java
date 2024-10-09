package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.domain.Comment;
import jungle_week13.jungle_week13.dashboard.domain.Post;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDeleteCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseDeleteCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.infrastructure.CommentRepository;
import jungle_week13.jungle_week13.dashboard.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
//    private final ModelMapper modelMapper;

    // 댓글 작성
    @Override
    public ResponseCreateCommentDto createComment(Long postId, RequestCreateCommentDto requestDto) {
        try {
            Optional<Post> post = postRepository.findById(postId);
            if(post.isEmpty() || post.get().getSoftDelete()) {
                log.error("해당 게시글이 존재하지 않습니다.");
                return ResponseCreateCommentDto.builder()
                        .isSuccessful(false)
                        .build();
            }

            Comment comment = Comment.builder()
                    .post(post.get())
                    .username(requestDto.getUsername())
                    .content(requestDto.getCommentContent())
                    .uuid(requestDto.getUuid())
                    .build();

            commentRepository.save(comment);

            return ResponseCreateCommentDto.builder()
                    .commentId(comment.getId())
                    .postId(postId)
                    .username(comment.getUsername())
                    .content(comment.getContent())
                    .createdDate(comment.getCreatedDate())
                    .likeCount(comment.getLikeCount())
                    .isSuccessful(true)
                    .build();
        } catch (Exception e) {
            log.error("댓글 작성 실패", e);
            return ResponseCreateCommentDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }

    // 댓글 수정
    @Override
    public ResponseUpdateCommentDto updateComment(Long commentId, RequestUpdateCommentDto requestDto) {
        try {
            Optional<Comment> comment = commentRepository.findById(commentId);
            if(comment.isEmpty() || comment.get().getSoftDelete()) {
                log.error("해당 댓글이 존재하지 않습니다.");
                return ResponseUpdateCommentDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            // 본인 댓글인지 확인
            if(!comment.get().getUuid().equals(requestDto.getUuid())) {
                log.error("댓글 작성자만 삭제 가능.");
                return ResponseUpdateCommentDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            Comment updatedComment = comment.get().updateComment(requestDto.getCommentContent());
            return ResponseUpdateCommentDto.builder()
                    .commentId(updatedComment.getId())
                    .username(updatedComment.getUsername())
                    .content(updatedComment.getContent())
                    .createdDate(updatedComment.getCreatedDate())
                    .likeCount(updatedComment.getLikeCount())
                    .build();
        } catch (Exception e) {
            log.error("댓글 수정 실패", e);
            return ResponseUpdateCommentDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }

    // 댓글 삭제
    @Override
    public ResponseDeleteCommentDto deleteComment(Long commentId, RequestDeleteCommentDto requestDto) {
        try {
            Optional<Comment> comment = commentRepository.findById(commentId);
            if(comment.isEmpty() || comment.get().getSoftDelete()) {
                log.error("해당 댓글이 존재하지 않습니다.");
                return ResponseDeleteCommentDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            // 본인 댓글인지 확인
            if(!comment.get().getUuid().equals(requestDto.getUuid())) {
                log.error("댓글 작성자만 삭제 가능.");
                return ResponseDeleteCommentDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            comment.get().deleteComment();
            return ResponseDeleteCommentDto.builder()
                    .isSuccessful(true)
                    .build();
        } catch (Exception e) {
            log.error("댓글 삭제 실패", e);
            return ResponseDeleteCommentDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }
}