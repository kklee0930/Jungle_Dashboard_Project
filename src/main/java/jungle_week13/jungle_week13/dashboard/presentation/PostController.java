package jungle_week13.jungle_week13.dashboard.presentation;

import jungle_week13.jungle_week13.dashboard.application.PostService;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDeletePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.*;
import jungle_week13.jungle_week13.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
//    private final ModelMapper modelMapper;

    /**
     * 게시물 도메인 관련 API
     */

    // 1. 게시물 작성 API
    @PostMapping("")
    public ApiResponse<Object> createPost(@RequestBody RequestCreatePostDto requestDto) {
        ResponseCreatePostDto responseDto = postService.createPost(requestDto);
        if(!responseDto.getPostCreated()) {
            return ApiResponse.ofFail(responseDto);
        }
        return ApiResponse.ofSuccess(responseDto);
    }

    // 2. 단일 게시물 조회 API
    @GetMapping("/{postId}")
    public ApiResponse<Object> getPost(@PathVariable Long postId) {
        ResponseFindPostDto responseDto = postService.findPost(postId);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }
        else {
            return ApiResponse.ofSuccess(responseDto);
        }
    }

    // 3. 게시물 수정 API
    @PostMapping("/{postId}")
    public ApiResponse<Object> updatePost(@PathVariable Long postId, @RequestBody RequestUpdatePostDto requestDto) {
        ResponseUpdatePostDto responseDto = postService.updatePost(postId, requestDto);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }
        else {
            return ApiResponse.ofSuccess(responseDto);
        }
    }

    // 4. 게시물 삭제 API
    @PostMapping("/{postId}/removal")
    public ApiResponse<Object> removePost(@PathVariable Long postId, @RequestBody RequestDeletePostDto requestDto) {
        ResponseDeletePostDto responseDto = postService.deletePost(postId, requestDto);
        if(!responseDto.getIsSuccessful()) {
            return ApiResponse.ofFail(responseDto);
        }
        else {
            return ApiResponse.ofSuccess(responseDto);
        }
    }

    // 5. 게시물 리스트 조회 API(메인 페이지)
    @GetMapping("/posts-list")
    public ApiResponse<Object> getPostList() {
        ResponseGetPostListDto responseDto = postService.getPostList();
        return ApiResponse.ofSuccess(responseDto);
    }
}
