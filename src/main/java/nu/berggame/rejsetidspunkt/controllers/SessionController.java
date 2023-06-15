package nu.berggame.rejsetidspunkt.controllers;

import nu.berggame.rejsetidspunkt.dto.request.LoginRequest;
import nu.berggame.rejsetidspunkt.dto.request.SessionCheckRequest;
import nu.berggame.rejsetidspunkt.dto.response.UserResponse;
import nu.berggame.rejsetidspunkt.entities.Session;
import nu.berggame.rejsetidspunkt.repositories.SessionRepository;
import nu.berggame.rejsetidspunkt.services.SessionService;
import nu.berggame.rejsetidspunkt.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        boolean success = userService.checkUserCredentials(request);

        if (success) {
            String accessKey = sessionService.instantiateNewSession(request);
            return ResponseEntity.ok(accessKey);
        }
        else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("/check")
    public ResponseEntity checkLogin(@RequestBody SessionCheckRequest request) {

        var success = sessionService.checkLogin(request.getAccessKey(), request.getHardwareKey());

        if (success) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> getUserBySession(@RequestBody SessionCheckRequest request) {

        var user = sessionService.getUserBySession(request);

        if (user != null) {
            return ResponseEntity.ok(new UserResponse(user));
        }
        else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser(@RequestBody SessionCheckRequest request) {
        sessionService.logoutUser(request);

        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }
}
