package sol.ActivityService.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sol.ActivityService.dummy.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private Long followerId;
    private Long followingId;

    public void setFollower(Long userId) {
        this.followerId = userId;
    }
    public void setFollowing(Long userId) {
        this.followingId = userId;
    }
}
