package sol.NewsFeedService.userActivity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.NewsFeedService.userActivity.dto.GetUserNewsDto;
import sol.NewsFeedService.userActivity.dto.UserNewsDto;
import sol.NewsFeedService.util.ResponseDto;
import sol.NewsFeedService.util.UtilMethods;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserActivityService {
    private UtilMethods utilMethods;
    private UserActivityRepository userActivityRepository;

    public Long saveActivity(UserNewsDto userNewsDto) {
        UserActivity activity = UserActivity.builder()
                .userId(userNewsDto.getUserId())
                .activityType(userNewsDto.getContentType())
                .contentId(userNewsDto.getContentId())
                .contentedBy(userNewsDto.getContentedBy())
                .createdAt(userNewsDto.getCreatedAt()).build();
        userActivityRepository.save(activity);
        return activity.getId();
    }

    public ResponseDto getNews(List<Long> following) {
        for (Long follow : following) {
            System.out.println(follow);
        }
        List<UserActivity> activities = userActivityRepository.findTop10ByUserIdInOrderByCreatedAtDesc(following);
        System.out.println(activities);
        List<GetUserNewsDto> newsFeed = new ArrayList<>();
        for (UserActivity activity : activities) {
            if (activity.getContentedBy().equals(activity.getUserId())) {
                newsFeed.add(GetUserNewsDto.builder()
                        .userId(activity.getUserId())
                        .contentType(activity.getActivityType())
                        .contentId(activity.getContentId())
                        .contentedBy(activity.getContentedBy())
                        .createdAt(activity.getCreatedAt())
                        .type("My").build()
                );
            } else {
                newsFeed.add(GetUserNewsDto.builder()
                        .userId(activity.getUserId())
                        .contentType(activity.getActivityType())
                        .contentId(activity.getContentId())
                        .contentedBy(activity.getContentedBy())
                        .createdAt(activity.getCreatedAt())
                        .type("Follower").build()
                );
            }
        }
        return utilMethods.makeSuccessResponseDto("Successfully loads", newsFeed);
    }
}
