package sol.NewsFeedService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "activityType-service", url = "http://localhost:8000")
public interface ActivityClient {

    @PostMapping("/client/getFollow/{userId}")
    List<Long> getFollowings(@PathVariable Long userId);
}
