package sol.NewsFeedService.userActivity;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sol.NewsFeedService.external.ActivityClient;
import sol.NewsFeedService.external.UserClient;
import sol.NewsFeedService.external.dto.TokenDto;
import sol.NewsFeedService.userActivity.dto.UserNewsDto;
import sol.NewsFeedService.util.ResponseDto;
import sol.NewsFeedService.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class UserActivityController {
    private UtilMethods utilMethods;
    private UserActivityService userActivityService;
    private UserClient userClient;
    private ActivityClient activityClient;

    @GetMapping
    public ResponseEntity getNewsFeed(HttpServletRequest request) {
        Long user = userClient.getUser(TokenDto.builder().token(utilMethods.getToken(request)).build());
        System.out.println("@@@@@@" + user);
        List<Long> followings = activityClient.getFollowings(user);
        ResponseDto news = userActivityService.getNews(followings);
        return ResponseEntity.status(HttpStatus.OK).body(news);
    }

    @PostMapping("/client")
    public Long saveActivity(@RequestBody UserNewsDto userNewsDto) {
        return userActivityService.saveActivity(userNewsDto);
    }

//    @GetMapping
//    public ResponseEntity getNews(HttpServletRequest request) {
//        Long userId = userClient.getUser(TokenDto.builder().token(utilMethods.getToken(request)).build());
//
//        ResponseDto responseDto = userActivityService.getNews(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//    }
}
