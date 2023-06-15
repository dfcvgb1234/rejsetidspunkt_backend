package nu.berggame.rejsetidspunkt.repositories;

import nu.berggame.rejsetidspunkt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findBySessions_AccessKey(String accessKey);

    Optional<User> findBySessions_AccessKeyAndSessions_HardwareKey(String accessKey, String hardwareKey);


}
