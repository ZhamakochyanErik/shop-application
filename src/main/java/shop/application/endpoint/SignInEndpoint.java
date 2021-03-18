package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.application.dto.request.SignInRequestDto;
import shop.application.dto.response.SignInResponseDto;
import shop.application.service.interfaces.UserService;

@RestController
@RequestMapping("/signIn")
public class SignInEndpoint {

    private final UserService userService;

    public SignInEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok(userService.signIn(signInRequestDto));
    }
}
