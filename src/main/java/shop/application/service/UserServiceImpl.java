package shop.application.service;

import io.jsonwebtoken.impl.DefaultClaims;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.application.dto.request.SignInRequestDto;
import shop.application.dto.request.SignUpRequestDto;
import shop.application.dto.response.SignInResponseDto;
import shop.application.dto.response.UserResponseDto;
import shop.application.exception.AuthenticationFailedException;
import shop.application.exception.UserBlockedException;
import shop.application.exception.UserNotFoundException;
import shop.application.model.User;
import shop.application.model.UserRole;
import shop.application.repository.UserRepository;
import shop.application.service.interfaces.UserService;
import shop.application.util.JwtUtil;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findByUsername(signInRequestDto.getUsername()).orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword())) {
            if (user.isBlocked()) {
                throw new UserBlockedException();
            }
            UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
            HashMap<String, Object> claimsMap = new HashMap<>();
            claimsMap.put("username", user.getUsername());
            return SignInResponseDto
                    .builder()
                    .authorizationToken(jwtUtil.generateToken(new DefaultClaims(claimsMap)))
                    .user(userResponseDto)
                    .build();
        }
        throw new AuthenticationFailedException();
    }

    @Override
    public UserResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = mapper.map(signUpRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisteredDate(LocalDateTime.now());
        user.setUserRole(UserRole.USER);
        return mapper.map(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public void blockUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    public void unblockUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setBlocked(false);
        userRepository.save(user);
    }
}
