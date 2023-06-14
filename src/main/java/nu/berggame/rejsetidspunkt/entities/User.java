package nu.berggame.rejsetidspunkt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nu.berggame.rejsetidspunkt.dto.request.UserRequest;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    public User(UserRequest newUserRequest) {
        this.email = newUserRequest.getEmail();
        this.username = newUserRequest.getUsername();
        this.password = newUserRequest.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    private Set<Session> sessions;
}
