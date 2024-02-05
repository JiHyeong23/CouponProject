package sol.ActivityService.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.comment.dto.CommentCreationDto;
import sol.ActivityService.post.Post;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class CommentService {
    private UtilMethods utilMethods;
    private CommentRepository commentRepository;

    public ResponseDto saveComment(CommentCreationDto commentCreationDto, Long userId) {
        Post post = utilMethods.findPost(commentCreationDto.getPostId());
        Comment comment = Comment.builder()
                .body(commentCreationDto.getBody())
                .post(post)
                .userId(userId)
                .build();
        commentRepository.save(comment);

        //utilMethods.saveActivity(userId, Activity.COMMENT, comment.getId(), comment.getPost().getUserId());
        return utilMethods.makeSuccessResponseDto("Successfully saved", comment.getBody());
    }
}
