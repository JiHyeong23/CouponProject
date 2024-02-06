package sol.ActivityService.postLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.external.NewsFeedClient;
import sol.ActivityService.external.dto.UserNewsDto;
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
    private NewsFeedClient newsFeedClient;

    public ResponseDto savePostLike(PostLikeDto postLikeDto, Long userId) {
        Post post = utilMethods.findPost(postLikeDto.getPostId());

        PostLike postLike = PostLike.builder()
                .post(post)
                .userId(userId)
                .build();
        postLikeRepository.save(postLike);

        post.updateLikeCount();
        postRepository.save(post);

        UserNewsDto userNewsDto = UserNewsDto.builder()
                .userId(userId)
                .contentType("POST_LIKE")
                .contentId(post.getId())
                .contentedBy(post.getUserId())
                .createdAt(postLike.getCreatedAt())
                .build();

        newsFeedClient.saveActivity(userNewsDto);

        return utilMethods.makeSuccessResponseDto("Successfully saved", postLike.getId());
    }
}
