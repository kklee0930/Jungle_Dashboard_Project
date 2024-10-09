package jungle_week13.jungle_week13.dashboard.dto.response;

import jungle_week13.jungle_week13.dashboard.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseGetPostListDto {
    List<Post> postList;
}
