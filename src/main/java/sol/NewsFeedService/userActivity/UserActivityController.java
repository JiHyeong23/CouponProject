package sol.NewsFeedService.userActivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sol.NewsFeedService.external.UserClient;
import sol.NewsFeedService.userActivity.dto.UserNewsDto;
import sol.NewsFeedService.util.UtilMethods;

@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class UserActivityController {
    private UtilMethods utilMethods;
    private UserActivityService userActivityService;
    private UserClient userClient;

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
