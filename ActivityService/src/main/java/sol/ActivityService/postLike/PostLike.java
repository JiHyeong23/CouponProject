package sol.ActivityService.postLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sol.ActivityService.post.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setPost(Post post){
        this.post = post;
    }
}
