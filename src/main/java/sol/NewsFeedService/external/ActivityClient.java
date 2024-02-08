package sol.NewsFeedService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "activityType-service", url = "http://localhost:8081/follows")
public interface ActivityClient {

    @PostMapping("/client")
    List<Long> getFollowings(@RequestBody Long userId);
}
