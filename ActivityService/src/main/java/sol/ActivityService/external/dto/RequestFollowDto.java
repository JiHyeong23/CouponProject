package sol.ActivityService.external.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestFollowDto{
    private String followerId;
    private String followingId;
}
