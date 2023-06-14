package nu.berggame.rejsetidspunkt.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionCheckRequest {

    private String accessKey;
    private String hardwareKey;
}
