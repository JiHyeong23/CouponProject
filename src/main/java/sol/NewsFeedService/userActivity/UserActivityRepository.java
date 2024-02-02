package sol.NewsFeedService.userActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    //List<UserActivity> findTop10ByUserInOrderByCreatedAtDesc(List<User> followings);
}
