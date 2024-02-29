package sol.NewsFeedService.userActivity;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sol.NewsFeedService.userActivity.dto.UserNewsDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserActivityMapper {
    UserActivity dtoToEntity(UserNewsDto userNewsDto);
}
