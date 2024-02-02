package sol.ActivityService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sol.ActivityService.external.dto.RequestFollowDto;
import sol.ActivityService.external.dto.ResponseFollowDto;
import sol.ActivityService.external.dto.TokenDto;

@FeignClient(name = "user-service", url = "http://localhost:8000/users")
public interface UserClient {

    @PostMapping("/client/getUser")
    Long getUser(@RequestBody TokenDto token);

    @PostMapping("/client/getFollow")
    ResponseFollowDto getUsers(@RequestBody RequestFollowDto followDto);
}
