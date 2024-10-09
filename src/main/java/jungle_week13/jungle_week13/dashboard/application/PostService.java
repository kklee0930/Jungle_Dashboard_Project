package jungle_week13.jungle_week13.dashboard.application;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.request.RequestUpdatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseCreatePostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseFindPostDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseGetPostListDto;
import jungle_week13.jungle_week13.dashboard.dto.response.ResponseUpdatePostDto;

public interface PostService {

    // 게시물 조회
    ResponseFindPostDto findPost(Long postId);
    // 게시글 생성
    ResponseCreatePostDto createPost(RequestCreatePostDto requestDto);
    // 게시글 수정
    ResponseUpdatePostDto updatePost(Long postId, RequestUpdatePostDto requestDto);
    // 모든 게시글 조회
    ResponseGetPostListDto getPostList();
}