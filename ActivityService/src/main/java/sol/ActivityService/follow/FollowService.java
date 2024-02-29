package sol.ActivityService.follow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.external.NewsFeedClient;
import sol.ActivityService.external.dto.ResponseFollowDto;
import sol.ActivityService.external.dto.UserNewsDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FollowService {
    private UtilMethods utilMethods;
    private FollowRepository followRepository;
    private NewsFeedClient newsFeedClient;

    public ResponseDto saveFollow(Long followerId, Long followingId) {
        Follow find = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (find != null) {
            return utilMethods.makeFailResponseDto("이미 팔로우하고 있는 유저입니다", find.getId());
        }
        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();

        followRepository.save(follow);

        UserNewsDto userNewsDto = UserNewsDto.builder()
                .userId(follow.getFollowingId())
                .contentType("FOLLOW")
                .contentId(follow.getId())
                .contentedBy(follow.getFollowerId())
                .createdAt(follow.getCreatedAt())
                .build();

        newsFeedClient.saveActivity(userNewsDto);

        return utilMethods.makeSuccessResponseDto("Successfully saved");
    }

    public List<Long> findFollows(Long userId) {
        List<Follow> follows = followRepository.findAllByFollowerId(userId);
        return follows.stream()
                .map(Follow::getFollowingId)
                .collect(Collectors.toList());
    }
}
