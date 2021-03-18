package shop.application.service.interfaces;

import shop.application.dto.request.SignInRequestDto;
import shop.application.dto.request.SignUpRequestDto;
import shop.application.dto.response.SignInResponseDto;
import shop.application.dto.response.UserResponseDto;

public interface UserService {
    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    UserResponseDto signUp(SignUpRequestDto signUpRequestDto);

    void blockUser(long userId);

    void unblockUser(long userId);
}
