package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDuplicateUsernameCheckDto {

    private Boolean isDuplicate;
}
