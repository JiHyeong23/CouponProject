package sol.UserService.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sol.UserService.user.client.RequestFollowDto;
import sol.UserService.user.client.ResponseFollowDto;
import sol.UserService.user.client.TokenDto;
import sol.UserService.user.dto.UserModifyDto;
import sol.UserService.user.dto.UserPassUpdateDto;
import sol.UserService.user.dto.UserSignUpDto;
import sol.UserService.util.ResponseDto;
import sol.UserService.util.UtilMethods;
import sol.UserService.validation.ValidationSequence;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UtilMethods utilMethods;

    //회원가입
    @PostMapping("/signUp")
    public ResponseEntity registerUser(@Validated(ValidationSequence.class) @RequestBody UserSignUpDto userSignUpDto){
        ResponseDto responseDto = userService.saveUser(userSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //이름, 설명 업데이트
    @PostMapping("/update")
    public ResponseEntity modifyUseInfo(@RequestBody UserModifyDto userModifyDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = userService.updateUserInfo(userModifyDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //이미지 업데이트
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity imageUpdate(@RequestPart MultipartFile profileImage, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = userService.updateProfileImage(profileImage, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //비밀번호 업데이트
    @PostMapping("/password")
    public ResponseEntity pwUpdate(@RequestBody UserPassUpdateDto userPassUpdateDto, HttpServletRequest request) {
        User user = utilMethods.parseTokenForUser(request);
        ResponseDto responseDto = userService.updatePassword(userPassUpdateDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    @PostMapping("/client/getUser")
    public Long getUser(@RequestBody TokenDto token) {
        User user = utilMethods.parseTokenForUser(token.getToken());
        return user.getId();
    }

    @PostMapping("/client/getFollow")
    public ResponseFollowDto getUsers(@RequestBody RequestFollowDto followDto) {
        User follower = utilMethods.parseTokenForUser(followDto.getFollowerId());
        User following = utilMethods.findUser(followDto.getFollowingId());
        ResponseFollowDto response = ResponseFollowDto.builder()
                .followerId(follower.getId())
                .followingId(following.getId())
                .build();
        System.out.println("response= " + response.getFollowerId() + response.getFollowingId());
        return response;
    }

    @GetMapping("/makeUser")
    public void makeUser() {
        userService.makeUser();
    }
}
