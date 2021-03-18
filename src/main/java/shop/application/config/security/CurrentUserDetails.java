package shop.application.config.security;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import shop.application.model.User;

public class CurrentUserDetails extends org.springframework.security.core.userdetails.User{

    @Getter
    private User user;

    public CurrentUserDetails(User user) {
        super(user.getUsername(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserRole().name()));
        this.user = user;
    }
}
