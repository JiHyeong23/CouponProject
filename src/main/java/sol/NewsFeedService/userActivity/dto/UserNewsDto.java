package sol.NewsFeedService.userActivity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sol.NewsFeedService.userActivity.ActivityType;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserNewsDto {
    private Long userId;
    private ActivityType contentType;
    private Long contentId;
    private Long contentedBy;
    private LocalDateTime createdAt;
}
