package jungle_week13.jungle_week13.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoginDto {

    private String username;
    private UUID uuid;
    private Boolean isSuccessful;
}
