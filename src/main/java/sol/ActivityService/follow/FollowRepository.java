package sol.ActivityService.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{

    List<Follow> findAllByFollowerId(Long userId);

    Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
