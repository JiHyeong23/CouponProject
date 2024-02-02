package sol.NewsFeedService.userActivity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.NewsFeedService.userActivity.dto.UserNewsDto;
import sol.NewsFeedService.util.UtilMethods;

@Service
@AllArgsConstructor
public class UserActivityService {
    private UtilMethods utilMethods;
    private UserActivityRepository userActivityRepository;
    private UserActivityMapper userActivityMapper;

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

//    public ResponseDto getNews(Long userId) {
//        List<User> following = utilMethods.getFollowing(user);
//        List<UserActivity> activities = userActivityRepository.findTop10ByUserInOrderByCreatedAtDesc(following);
//        List<UserNewsDto> newsFeed = new ArrayList<>();
//        for (UserActivity activityType : activities) {
//            if (activityType.getContentedBy() == user) {
//                newsFeed.add(new UserNewsDto(
//                        activityType.getUser().getName(), activityType.getActivityType().toString(), activityType.getActivityId(), activityType.getCreatedAt(),
//                        user.getName(), "My")
//                );
//            } else {
//                newsFeed.add(new UserNewsDto(
//                        activityType.getUser().getName(), activityType.getActivityType().toString(), activityType.getActivityId(), activityType.getCreatedAt(),
//                        activityType.getContentedBy().getName(), "Follower")
//                );
//            }
//        }
//        return utilMethods.makeSuccessResponseDto("Successfully loads", newsFeed);
//    }
}
