package sol.NewsFeedService.userActivity.dto;

import lombok.Builder;
import lombok.Getter;
import sol.NewsFeedService.userActivity.ActivityType;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetUserNewsDto {
    private Long userId;
    private ActivityType contentType;
    private Long contentId;
    private Long contentedBy;
    private LocalDateTime createdAt;
    private String type;
}
