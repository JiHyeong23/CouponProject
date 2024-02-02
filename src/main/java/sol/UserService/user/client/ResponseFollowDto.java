package sol.UserService.user.client;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseFollowDto {
    private Long followerId;
    private Long followingId;
}
