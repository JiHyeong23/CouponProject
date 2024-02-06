package sol.ActivityService.follow;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sol.ActivityService.external.dto.RequestFollowDto;
import sol.ActivityService.external.dto.ResponseFollowDto;
import sol.ActivityService.external.UserClient;
import sol.ActivityService.follow.dto.FollowUserDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/follows")
@AllArgsConstructor
public class FollowController {
    private FollowService followService;
    private UtilMethods utilMethods;
    private UserClient userClient;
    @PostMapping
    public ResponseEntity followUser(@RequestBody FollowUserDto followUserDto, HttpServletRequest request) {
        RequestFollowDto followDto = RequestFollowDto.builder()
                .followerId(utilMethods.getToken(request))
                .followingId(followUserDto.getEmail()).build();
        ResponseFollowDto responseFollow = userClient.getUsers(followDto);
        ResponseDto responseDto = followService.saveFollow(responseFollow);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/client")
    public List<Long> getFollowings(@RequestBody Long userId) {
        return followService.findFollows(userId);
    }
}
