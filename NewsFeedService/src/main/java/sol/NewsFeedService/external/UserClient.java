package sol.NewsFeedService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sol.NewsFeedService.external.dto.TokenDto;

@FeignClient(name = "user-service", url = "http://localhost:8000/users")
public interface UserClient {

    @PostMapping("/client/getUser")
    Long getUser(@RequestBody TokenDto token);
}
