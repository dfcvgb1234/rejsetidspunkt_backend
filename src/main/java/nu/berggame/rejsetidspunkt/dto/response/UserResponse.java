package nu.berggame.rejsetidspunkt.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nu.berggame.rejsetidspunkt.entities.User;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
    }

    private String email;
    private String username;
}
