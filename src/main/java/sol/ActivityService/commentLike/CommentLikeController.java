package sol.ActivityService.commentLike;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ActivityService.commentLike.dto.CommentLikeDto;
import sol.ActivityService.external.UserClient;
import sol.ActivityService.external.dto.TokenDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/commentLikes")
@AllArgsConstructor
public class CommentLikeController {
    private UtilMethods utilMethods;
    private CommentLikeService commentLikeService;
    private UserClient userClient;
    @PostMapping
    public ResponseEntity commentLike(@RequestBody CommentLikeDto commentLikeDto, HttpServletRequest request) {
        Long userId = userClient.getUser(TokenDto.builder().token(utilMethods.getToken(request)).build());
        ResponseDto responseDto = commentLikeService.saveCommentLike(commentLikeDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
