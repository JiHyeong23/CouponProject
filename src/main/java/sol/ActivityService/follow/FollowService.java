package sol.ActivityService.follow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sol.ActivityService.external.dto.ResponseFollowDto;
import sol.ActivityService.util.ResponseDto;
import sol.ActivityService.util.UtilMethods;

@Service
@AllArgsConstructor
public class FollowService {
    private UtilMethods utilMethods;
    private FollowRepository followRepository;

    public ResponseDto saveFollow(ResponseFollowDto followDto) {
        Follow find = followRepository.findByFollowerIdAndFollowingId(followDto.getFollowerId(), followDto.getFollowingId());
        if (find != null) {
            return utilMethods.makeFailResponseDto("이미 팔로우하고 있는 유저입니다", find.getId());
        }
        Follow follow = Follow.builder()
                .followerId(followDto.getFollowerId())
                .followingId(followDto.getFollowingId())
                .build();

        followRepository.save(follow);
        return utilMethods.makeSuccessResponseDto("Successfully saved");
    }

//    public ResponseDto saveFollow(FollowUserDto followUserDto, User followingId) {
//        User following = utilMethods.findUser(followUserDto.getEmail());
//
//        Follow result = followRepository.findByFollowerAndFollowing(follower, following);
//        if (result != null) {
//            return utilMethods.makeFailResponseDto("이미 팔로우하고 있는 유저입니다", following.getName());
//        }
//        Follow follow = new Follow();
//        follow.setFollower(follower);
//        follow.setFollowing(following);
//
//        followRepository.save(follow);
//
//        utilMethods.saveActivity(follower, Activity.FOLLOW, following.getId(), follower);
//
//        return utilMethods.makeSuccessResponseDto("Successfully saved", follow.getFollowing().getName());
//    }
}
