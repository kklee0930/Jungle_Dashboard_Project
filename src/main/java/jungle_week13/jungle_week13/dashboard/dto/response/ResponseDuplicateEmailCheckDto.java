package jungle_week13.jungle_week13.dashboard.dto.response;

import jungle_week13.jungle_week13.dashboard.dto.request.RequestDuplicateEmailCheckDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDuplicateEmailCheckDto {

    private Boolean isDuplicate;
}
