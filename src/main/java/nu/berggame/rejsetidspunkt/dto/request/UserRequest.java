package nu.berggame.rejsetidspunkt.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    private String email;
    private String username;
    private String password;
}
