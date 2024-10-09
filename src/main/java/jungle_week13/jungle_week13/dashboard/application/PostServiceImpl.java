package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.domain.Comment;
import jungle_week13.jungle_week13.dashboard.domain.Post;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseFindPostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseGetPostListDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdatePostDto;
import jungle_week13.jungle_week13.dashboard.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    // 게시글 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseFindPostDto findPost(Long postId) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
            return ResponseFindPostDto.builder()
                    .postId(post.getId())
                    .username(post.getUser().getUsername())
                    .postTitle(post.getTitle())
                    .postContent(post.getContent())
                    .viewCount(post.getViewCount())
                    .createdDate(post.getCreatedDate())
                    .build();
        } catch (Exception e) {
            log.error("게시글 조회 실패", e);
            return null;
        }
    }

    // 게시글 생성
    @Override
    public ResponseCreatePostDto createPost(RequestCreatePostDto requestDto) {
        try {
            Post post = Post.builder()
                    .title(requestDto.getPostTitle())
                    .content(requestDto.getPostContent())
                    .build();
            postRepository.save(post);
            return ResponseCreatePostDto.builder()
                    .postCreated(true)
                    .build();
        } catch (Exception e) {
            log.error("게시글 생성 실패", e);
            return ResponseCreatePostDto.builder()
                    .postCreated(false)
                    .build();
        }
    }

    // 게시글 수정
    @Override
    public ResponseUpdatePostDto updatePost(Long postId, RequestUpdatePostDto requestDto) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
            post.updatePost(requestDto.getPostTitle(), requestDto.getPostContent());
            return ResponseUpdatePostDto.builder()
                    .postUpdated(true)
                    .build();
        } catch (Exception e) {
            log.error("게시글 수정 실패", e);
            return ResponseUpdatePostDto.builder()
                    .postUpdated(false)
                    .build();
        }
    }

    // 모든 게시글 조회
    @Override
    public ResponseGetPostListDto getPostList() {
        List<Post> postList = postRepository.getAllBySoftDeleteAndOrderById(false);
        return modelMapper.map(postList, ResponseGetPostListDto.class);
    }
}