package sol.ActivityService.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sol.ActivityService.external.UserClient;
import sol.ActivityService.post.dto.PostCreationDto;
import sol.ActivityService.external.dto.TokenDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    private UserClient userClient;
    private UtilMethods utilMethods;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostCreationDto postCreationDto, HttpServletRequest request) {
        String token = utilMethods.getToken(request);
        Long userId = userClient.getUser(TokenDto.builder().token(token).build());
        ResponseDto responseDto = postService.savePost(postCreationDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


}
