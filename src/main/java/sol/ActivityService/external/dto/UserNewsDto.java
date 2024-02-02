package sol.ActivityService.external.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserNewsDto {
    private Long userId;
    private String contentType;
    private Long contentId;
    private Long contentedBy;
    private LocalDateTime createdAt;
}
