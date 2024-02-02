package sol.ActivityService.comment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.ActivityService.comment.dto.CommentCreationDto;
import sol.ActivityService.external.dto.TokenDto;
import sol.ActivityService.external.UserClient;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private UtilMethods utilMethods;
    private CommentService commentService;
    private UserClient userClient;
    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentCreationDto commentCreationDto, HttpServletRequest request) {
        Long user = userClient.getUser(TokenDto.builder().token(utilMethods.getToken(request)).build());
        ResponseDto responseDto = commentService.saveComment(commentCreationDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
