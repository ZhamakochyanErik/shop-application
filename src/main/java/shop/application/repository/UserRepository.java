package shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.application.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
