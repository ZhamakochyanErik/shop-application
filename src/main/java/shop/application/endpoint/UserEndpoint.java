package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.application.service.interfaces.UserService;

@RestController
@RequestMapping("/user")
public class UserEndpoint {

    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<?> blockUser(@PathVariable long userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok("user is blocked");
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<?> unblockUser(@PathVariable long userId) {
        userService.unblockUser(userId);
        return ResponseEntity.ok("user is unblocked");
    }
}
