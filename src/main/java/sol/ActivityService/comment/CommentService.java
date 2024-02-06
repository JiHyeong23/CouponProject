package sol.ActivityService.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.comment.dto.CommentCreationDto;
import sol.ActivityService.external.NewsFeedClient;
import sol.ActivityService.external.dto.UserNewsDto;
import sol.ActivityService.post.Post;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class CommentService {
    private UtilMethods utilMethods;
    private CommentRepository commentRepository;
    private NewsFeedClient newsFeedClient;

    public ResponseDto saveComment(CommentCreationDto commentCreationDto, Long userId) {
        Post post = utilMethods.findPost(commentCreationDto.getPostId());

        Comment comment = Comment.builder()
                .body(commentCreationDto.getBody())
                .post(post)
                .userId(userId)
                .build();
        commentRepository.save(comment);

        UserNewsDto userNewsDto = UserNewsDto.builder()
                .userId(userId)
                .contentType("COMMENT")
                .contentId(comment.getId())
                .contentedBy(comment.getUserId())
                .createdAt(comment.getCreatedAt())
                .build();
        newsFeedClient.saveActivity(userNewsDto);

        return utilMethods.makeSuccessResponseDto("Successfully saved", comment.getBody());
    }
}
