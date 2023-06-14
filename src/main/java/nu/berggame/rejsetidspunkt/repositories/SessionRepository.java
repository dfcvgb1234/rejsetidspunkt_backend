package nu.berggame.rejsetidspunkt.repositories;

import nu.berggame.rejsetidspunkt.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByAccessKeyAndUser_Username(String accessKey, String username);

    Optional<Session> findByAccessKeyAndHardwareKey(String accessKey, String hardwareKey);
}
