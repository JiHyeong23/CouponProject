package sol.NewsFeedService.userActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findTop10ByUserIdInOrderByCreatedAtDesc(List<Long> followings);
}
