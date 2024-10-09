package jungle_week13.jungle_week13.dashboard.infrastructure;

import jungle_week13.jungle_week13.dashboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


}
