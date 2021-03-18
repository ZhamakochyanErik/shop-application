package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.application.dto.request.SignUpRequestDto;
import shop.application.dto.response.UserResponseDto;
import shop.application.service.interfaces.UserService;

@RestController
@RequestMapping("/signUp")
public class SignUpEndpoint {

    private final UserService userService;

    public SignUpEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(userService.signUp(signUpRequestDto));
    }
}
