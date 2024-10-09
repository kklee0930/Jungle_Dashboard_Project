package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestDeletePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.*;

public interface PostService {

    // 게시물 조회
    ResponseFindPostDto findPost(Long postId);
    // 게시글 생성
    ResponseCreatePostDto createPost(RequestCreatePostDto requestDto);
    // 게시글 수정
    ResponseUpdatePostDto updatePost(Long postId, RequestUpdatePostDto requestDto);
    // 게시글 삭제
    ResponseDeletePostDto deletePost(Long postId, RequestDeletePostDto requestDto);
    // 모든 게시글 조회
    ResponseGetPostListDto getPostList();

}