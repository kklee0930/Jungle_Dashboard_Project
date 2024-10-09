package jungle_week13.jungle_week13.dashboard.dto.response;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDuplicateEmailCheckDto {

    private Boolean isDuplicate;
}
