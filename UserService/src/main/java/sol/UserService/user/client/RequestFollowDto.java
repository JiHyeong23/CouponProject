package sol.UserService.user.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestFollowDto {
    private String followerId;
    private String followingId;
}
