package sol.ActivityService.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sol.ActivityService.dummy.User;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{
    //@Query("SELECT f.following FROM Follow f WHERE f.follower = :user")
    //List<User> findAllByFollowing(User user);

    Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
