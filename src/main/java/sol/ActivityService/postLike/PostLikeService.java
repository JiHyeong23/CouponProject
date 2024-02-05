package sol.ActivityService.postLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.post.Post;
import sol.ActivityService.post.PostRepository;
import sol.ActivityService.postLike.dto.PostLikeDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class PostLikeService {
    private UtilMethods utilMethods;
    private PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public ResponseDto savePostLike(PostLikeDto postLikeDto, Long userId) {
        Post post = utilMethods.findPost(postLikeDto.getPostId());

        PostLike postLike = PostLike.builder()
                .post(post)
                .userId(userId)
                .build();
        postLikeRepository.save(postLike);

        post.updateLikeCount();
        postRepository.save(post);

       //utilMethods.saveActivity(user, Activity.POST_LIKE, postLike.getId(), post.getUser());

        return utilMethods.makeSuccessResponseDto("Successfully saved", postLike.getId());
    }
}
