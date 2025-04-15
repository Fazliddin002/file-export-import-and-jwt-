package realproject.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import realproject.security.entity.Role;
import realproject.security.entity.enums.RoleName;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findAllByRoleName(RoleName roleName);
}