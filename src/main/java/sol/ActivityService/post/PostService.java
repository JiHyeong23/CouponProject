package sol.ActivityService.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.external.NewsFeedClient;
import sol.ActivityService.external.dto.UserNewsDto;
import sol.ActivityService.post.dto.PostCreationDto;
import sol.ActivityService.dummy.User;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class PostService {
    private UtilMethods utilMethods;
    private PostRepository postRepository;
    private PostMapper postMapper;
    private NewsFeedClient newsFeedClient;

    public ResponseDto savePost(PostCreationDto postCreationDto, Long userId) {
        Post post = postMapper.creationDtoToPost(postCreationDto);
        post.setUser(userId);
        postRepository.save(post);

        UserNewsDto userNewsDto = UserNewsDto.builder()
                .userId(userId)
                .contentType("POST")
                .contentId(post.getId())
                .contentedBy(post.getUserId())
                .createdAt(post.getCreatedAt())
                .build();
        Long activityId = newsFeedClient.saveActivity(userNewsDto);
        if (activityId == null) {
            return utilMethods.makeFailResponseDto("Failed saved", activityId);
        }
        return utilMethods.makeSuccessResponseDto("Successfully saved", post.getTitle());
    }
}
