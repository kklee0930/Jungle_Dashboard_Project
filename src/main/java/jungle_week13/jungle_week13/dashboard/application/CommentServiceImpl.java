package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.domain.Comment;
import jungle_week13.jungle_week13.dashboard.domain.Post;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreateCommentDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdateCommentDto;
import jungle_week13.jungle_week13.dashboard.infrastructure.CommentRepository;
import jungle_week13.jungle_week13.dashboard.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository; // TODO: commentRepository 여기에 injection 하는게 맞는지 고민 필요
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    // 댓글 작성
    @Override
    public ResponseCreateCommentDto createComment(Long postId, RequestCreateCommentDto requestDto) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

            Comment comment = Comment.builder()
                    .post(post)
                    .username(requestDto.getUsername())
                    .content(requestDto.getCommentContent())
                    .uuid(requestDto.getUuid())
                    .build();

            commentRepository.save(comment);
            return ResponseCreateCommentDto.builder()
                    .commentCreated(true)
                    .build();
        } catch (Exception e) {
            log.error("댓글 작성 실패", e);
            return ResponseCreateCommentDto.builder()
                    .commentCreated(false)
                    .build();
        }
    }

    // 댓글 수정
    @Override
    public ResponseUpdateCommentDto updateComment(Long commentId, RequestUpdateCommentDto requestDto) {
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        }
        return null;
    }
}
