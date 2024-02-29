package sol.ActivityService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sol.ActivityService.external.dto.UserNewsDto;

@FeignClient(name = "newsfeed-service", url = "http://localhost:8082/news")
public interface NewsFeedClient {
    @PostMapping("/client")
    Long saveActivity(@RequestBody UserNewsDto userNewsDto);
}
