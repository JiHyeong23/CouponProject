package sol.ActivityService.postLike;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ActivityService.external.UserClient;
import sol.ActivityService.external.dto.TokenDto;
import sol.ActivityService.postLike.dto.PostLikeDto;
import sol.ActivityService.dummy.User;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/postLikes")
@AllArgsConstructor
public class PostLikeController {
    private UtilMethods utilMethods;
    private PostLikeService postLikeService;
    private UserClient userClient;

    @PostMapping
    public ResponseEntity likePost(@RequestBody PostLikeDto postLikeDto, HttpServletRequest request) {
        Long user = userClient.getUser(TokenDto.builder().token(utilMethods.getToken(request)).build());
        ResponseDto responseDto = postLikeService.savePostLike(postLikeDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
