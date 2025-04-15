package realproject.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import realproject.security.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhone(String phone);
}