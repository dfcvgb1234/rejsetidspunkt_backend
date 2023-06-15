package nu.berggame.rejsetidspunkt.services;

import nu.berggame.rejsetidspunkt.dto.request.LoginRequest;
import nu.berggame.rejsetidspunkt.dto.request.SessionCheckRequest;
import nu.berggame.rejsetidspunkt.entities.Session;
import nu.berggame.rejsetidspunkt.entities.User;
import nu.berggame.rejsetidspunkt.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SessionService {

    @Autowired
    SessionRepository repository;

    @Autowired
    UserService userService;

    public String instantiateNewSession(LoginRequest request) {

        String accessKey = createNewAccessKey(75);

        Session session = new Session();
        session.setUser(userService.getUserByUsername(request.username));
        session.setHardwareKey(request.hardwareKey);
        session.setDateCreated(LocalDateTime.now());
        session.setAccessKey(accessKey);

        repository.save(session);

        return accessKey;
    }

    public boolean checkLogin(String accessKey, String hardwareKey) {

        var session = repository.findByAccessKeyAndHardwareKey(accessKey, hardwareKey);

        return session.isPresent();
    }

    public User getUserBySession(SessionCheckRequest request) {
        return userService.getUserBySession(request.getAccessKey(), request.getHardwareKey());
    }

    private String createNewAccessKey(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
