package jungle_week13.jungle_week13.dashboard.dto.response;

import jungle_week13.jungle_week13.dashboard.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetPostListDto {
    private List<Post.PostDTO> postList;
}
