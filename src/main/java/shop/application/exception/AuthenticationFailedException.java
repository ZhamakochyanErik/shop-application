package shop.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Username or Password is incorrect")
public class AuthenticationFailedException extends RuntimeException {
}
