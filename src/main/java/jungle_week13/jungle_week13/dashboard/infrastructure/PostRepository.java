package jungle_week13.jungle_week13.dashboard.infrastructure;

import jungle_week13.jungle_week13.dashboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllBySoftDeleteFalseAndOrderById(); // 삭제되지 않은 모든 게시글 조회
}