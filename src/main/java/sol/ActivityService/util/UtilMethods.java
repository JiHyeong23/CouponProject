package sol.ActivityService.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sol.ActivityService.comment.Comment;
import sol.ActivityService.comment.CommentRepository;
import sol.ActivityService.follow.FollowRepository;
import sol.ActivityService.post.Post;
import sol.ActivityService.post.PostRepository;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class UtilMethods {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private FollowRepository followRepository;

    public String getToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    public ResponseDto makeSuccessResponseDto(String message) {
        return ResponseDto.builder()
                .result(Result.SUCCESS).message(message)
                .build();
    }

    public <T>ResponseDto makeSuccessResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(Result.SUCCESS).message(message).response(response)
                .build();
    }

    public <T>ResponseDto makeFailResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(Result.FAIL).message(message).response(response)
                .build();
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }
    public Comment findComment(Long commentId) {return commentRepository.findById(commentId).get();}
//    public List<User> getFollowing(User user) {
//        return followRepository.findAllByFollowing(user);
//
//    }
//    public void saveActivity(Long userId, Activity activity, Long activityId, Long contentedBy) {
//        UserActivity userActivity = UserActivity.builder()
//                .userId(userId).activity(activity).activityId(activityId).contentedBy(contentedBy).build();
//        userActivityRepository.save(userActivity);
//    }
}
