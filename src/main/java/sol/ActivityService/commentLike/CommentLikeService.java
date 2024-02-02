package sol.ActivityService.commentLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.comment.Comment;
import sol.ActivityService.comment.CommentRepository;
import sol.ActivityService.commentLike.dto.CommentLikeDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class CommentLikeService {
    private UtilMethods utilMethods;
    private CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public ResponseDto saveCommentLike(CommentLikeDto commentLikeDto, Long userId) {
        Comment comment = utilMethods.findComment(commentLikeDto.getCommentId());

        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .userId(userId)
                .build();
        commentLikeRepository.save(commentLike);

        comment.updateLikeCount();
        commentRepository.save(comment);

        //utilMethods.saveActivity(userId, Activity.COMMENT_LIKE, commentLike.getId(), comment.getUserId());

        return utilMethods.makeSuccessResponseDto("Successfully saved", commentLike.getId());
    }
}
