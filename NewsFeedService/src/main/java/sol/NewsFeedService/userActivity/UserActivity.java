package sol.NewsFeedService.userActivity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private Long contentId;
    private Long contentedBy;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
