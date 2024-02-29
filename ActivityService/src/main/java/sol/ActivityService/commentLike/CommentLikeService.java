package sol.ActivityService.commentLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.comment.Comment;
import sol.ActivityService.comment.CommentRepository;
import sol.ActivityService.commentLike.dto.CommentLikeDto;
import sol.ActivityService.external.NewsFeedClient;
import sol.ActivityService.external.dto.UserNewsDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class CommentLikeService {
    private UtilMethods utilMethods;
    private CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private NewsFeedClient newsFeedClient;

    public ResponseDto saveCommentLike(CommentLikeDto commentLikeDto, Long userId) {
        Comment comment = utilMethods.findComment(commentLikeDto.getCommentId());

        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .userId(userId)
                .build();
        commentLikeRepository.save(commentLike);

        comment.updateLikeCount();
        commentRepository.save(comment);

        UserNewsDto userNewsDto = UserNewsDto.builder()
                .userId(userId)
                .contentType("COMMENT_LIKE")
                .contentId(comment.getUserId())
                .contentedBy(comment.getUserId())
                .createdAt(commentLike.getCreatedAt())
                .build();

        newsFeedClient.saveActivity(userNewsDto);

        return utilMethods.makeSuccessResponseDto("Successfully saved", commentLike.getId());
    }
}
