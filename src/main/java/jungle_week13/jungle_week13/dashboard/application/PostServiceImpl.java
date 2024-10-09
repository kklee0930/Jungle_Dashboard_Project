package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.domain.Post;
import jungle_week13.jungle_week13.dashboard.domain.User;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDeletePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.*;
import jungle_week13.jungle_week13.dashboard.infrastructure.PostRepository;
import jungle_week13.jungle_week13.dashboard.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    // 게시글 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseFindPostDto findPost(Long postId) {
        try {
            Optional<Post> post = postRepository.findById(postId);

            if(post.isEmpty() || post.get().getSoftDelete()) {
                log.error("해당 게시글이 존재하지 않습니다.");
                return ResponseFindPostDto.builder()
                        .isSuccessful(false)
                        .build();
            }

            // DateTimeFormatter 사용해서 YYYY.MM.DD 형식으로 변환
            String formattedDate = post.get().getCreatedDate().format(formatter);

            return ResponseFindPostDto.builder()
                    .postId(post.get().getId())
                    .username(post.get().getUser().getUsername())
                    .postTitle(post.get().getTitle())
                    .postContent(post.get().getContent())
                    .viewCount(post.get().getViewCount())
                    .isSuccessful(true)
                    .createdDate(formattedDate)
                    .build();
        } catch (Exception e) {
            log.error("게시글 조회 실패", e);
            return ResponseFindPostDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }

    // 게시글 생성
    @Override
    public ResponseCreatePostDto createPost(RequestCreatePostDto requestDto) {
        try {
            Optional<User> user = userRepository.findByUuid(requestDto.getUuid());
            // 유저가 없으면 게시글 생성 실패
            if(user.isEmpty()) {
                log.error("해당 유저가 존재하지 않습니다.");
                return ResponseCreatePostDto.builder()
                        .postCreated(false)
                        .build();
            }
            Post post = Post.builder()
                    .title(requestDto.getPostTitle())
                    .content(requestDto.getPostContent())
                    .user(user.get())
                    .build();

            Post savedPost = postRepository.save(post);

            // DateTimeFormatter 사용해서 YYYY.MM.DD 형식으로 변환
            String formattedDate = savedPost.getCreatedDate().format(formatter);

            return ResponseCreatePostDto.builder()
                    .postId(savedPost.getId())
                    .createdDate(formattedDate)
                    .title(savedPost.getTitle())
                    .content(savedPost.getContent())
                    .postCreated(true)
                    .viewCount(savedPost.getViewCount())
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
            Optional<Post> post = postRepository.findById(postId);
            if(post.isEmpty() || post.get().getSoftDelete()) {
                log.error("해당 게시글이 존재하지 않습니다.");
                return ResponseUpdatePostDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            if(!post.get().getUser().getUuid().equals(requestDto.getUuid())) {
                log.error("게시글 작성자만 수정 가능.");
                return ResponseUpdatePostDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            Post updatedPost = post.get().updatePost(requestDto.getPostTitle(), requestDto.getPostContent());

            // DateTimeFormatter 사용해서 YYYY.MM.DD 형식으로 변환
            String formattedDate = post.get().getCreatedDate().format(formatter);

            return ResponseUpdatePostDto.builder()
                    .postId(updatedPost.getId())
                    .isSuccessful(true)
                    .username(updatedPost.getUser().getUsername())
                    .postTitle(updatedPost.getTitle())
                    .postContent(updatedPost.getContent())
                    .viewCount(updatedPost.getViewCount())
                    .createdDate(formattedDate)
                    .build();
        } catch (Exception e) {
            log.error("게시글 수정 실패", e);
            return ResponseUpdatePostDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }

    // 게시글 삭제
    @Override
    public ResponseDeletePostDto deletePost(Long postId, RequestDeletePostDto requestDto) {
        try {
            Optional<Post> post = postRepository.findById(postId);
            if(post.isEmpty()) {
                log.error("해당 게시글이 존재하지 않습니다.");
                return ResponseDeletePostDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            if(!post.get().getUser().getUuid().equals(requestDto.getUuid())) {
                log.error("게시글 작성자만 수정 가능.");
                return ResponseDeletePostDto.builder()
                        .isSuccessful(false)
                        .build();
            }
            post.get().deletePost(); // soft delete 처리
            return ResponseDeletePostDto.builder()
                    .isSuccessful(true)
                    .build();
        } catch (Exception e) {
            log.error("게시글 삭제 실패", e);
            return ResponseDeletePostDto.builder()
                    .isSuccessful(false)
                    .build();
        }
    }

    // 모든 게시글 조회
    @Override
    public ResponseGetPostListDto getPostList() {
        List<Post> postList = postRepository.findAllBySoftDeleteIsFalseOrderById();
        List<Post.PostDTO> postDTOList = new ArrayList<>();

        for(Post post : postList) {
            postDTOList.add(post.entityToDTO());
        }
        return ResponseGetPostListDto.builder()
                .postList(postDTOList)
                .build();
    }
}