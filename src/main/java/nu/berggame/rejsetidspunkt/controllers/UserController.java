package nu.berggame.rejsetidspunkt.controllers;

import nu.berggame.rejsetidspunkt.dto.request.UserRequest;
import nu.berggame.rejsetidspunkt.entities.User;
import nu.berggame.rejsetidspunkt.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {

        var user = userService.getUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }

    @PostMapping("")
    public void addUser(@RequestBody UserRequest request) {

        User user = new User(request);
        userService.saveUser(user);
    }
}
