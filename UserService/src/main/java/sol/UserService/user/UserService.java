package sol.UserService.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import sol.UserService.user.dto.UserModifyDto;
import sol.UserService.user.dto.UserPassUpdateDto;
import sol.UserService.user.dto.UserSignUpDto;
import sol.UserService.util.FileUploader;
import sol.UserService.util.ResponseDto;
import sol.UserService.util.UtilMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UtilMethods utilMethods;
    private FileUploader fileUploader;
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            //User user = userRepository.findById(Long.valueOf(username)).get();
            User user = userRepository.findByEmail(username);
            if (user.getId() == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다");
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다");
        }
    }

    public ResponseDto saveUser(UserSignUpDto userSignUpDto) {
        userSignUpDto.setPassword(encoder.encode(userSignUpDto.getPassword()));
        User user = userMapper.UserSignUpDtoToUser(userSignUpDto);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        return utilMethods.makeSuccessResponseDto("Successfully saved", user.getName());
    }

    public ResponseDto updateUserInfo(UserModifyDto userModifyDto, User user) {
        if (user.getDescription() == null) {
            user.setDescription("");
        }
        user.updateUser(userModifyDto.getName(), userModifyDto.getDescription());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return utilMethods.makeSuccessResponseDto("Successfully updated", user.getUpdatedAt());
    }

    public ResponseDto updateProfileImage(MultipartFile profileImage, User user) {
        String beforeImage = user.getProfileImage();
        String imagePath = fileUploader.saveImage(profileImage, beforeImage);
        user.setProfileImage(imagePath);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return utilMethods.makeSuccessResponseDto("Successfully updated", user.getUpdatedAt());
    }

    public ResponseDto updatePassword(UserPassUpdateDto userPassUpdateDto, User user) {
        ResponseDto responseDto;
        if (encoder.matches(userPassUpdateDto.getPassword(), user.getPassword())) {
            user.setNewPassword(encoder.encode(userPassUpdateDto.getNewPassword()));
            userRepository.save(user);
            return responseDto = utilMethods.makeSuccessResponseDto("Successfully updated", user.getName());
        }
        else {
            return responseDto = utilMethods.makeFailResponseDto("비밀번호가 틀립니다", user.getName());
        }
    }

    public void makeUser() {
        for (int i = 0; i < 10; i++) {
            String username = UUID.randomUUID().toString();
            User user = User.builder()
                    .name(username)
                    .email(username+"@te.st")
                    .password(encoder.encode("1234test"))
                    .build();
            userRepository.save(user);
            System.out.println("Sending request with userId: " + username);
        }
    }


    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
