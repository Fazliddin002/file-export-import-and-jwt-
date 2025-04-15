package realproject.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import realproject.security.entity.Role;
import realproject.security.entity.enums.RoleName;
import realproject.security.repo.RoleRepository;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
//        Role role = new Role();
//        role.setRoleName(RoleName.ROLE_ADMIN);
//        roleRepository.save(role);
//        Role role1 = new Role();
//        role1.setRoleName(RoleName.ROLE_USER);
//        roleRepository.save(role1);
//

    }
}
