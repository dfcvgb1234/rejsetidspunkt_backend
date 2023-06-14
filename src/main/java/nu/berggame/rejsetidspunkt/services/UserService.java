package nu.berggame.rejsetidspunkt.services;

import nu.berggame.rejsetidspunkt.dto.request.LoginRequest;
import nu.berggame.rejsetidspunkt.entities.User;
import nu.berggame.rejsetidspunkt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUserByEmail(String email) {
        var user = repository.findByEmail(email);

        if (user.isPresent()) {
            return user.get();
        }
        else {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        var user = repository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        }
        else {
            return null;
        }
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public boolean checkUserCredentials(LoginRequest request) {
        var user =repository.findByUsernameAndPassword(request.username, request.userPassword);

        return user.isPresent();
    }

}
