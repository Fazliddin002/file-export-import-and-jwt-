package realproject.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import realproject.security.entity.Role;
import realproject.security.entity.User;
import realproject.security.entity.enums.RoleName;
import realproject.security.repo.RoleRepository;
import realproject.security.repo.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) throws Exception {
       if (ddl.equals("create")) {
           Role role = new Role();
           role.setRoleName(RoleName.ROLE_ADMIN);
           roleRepository.save(role);
           Role role1 = new Role();
           role1.setRoleName(RoleName.ROLE_USER);
           roleRepository.save(role1);

           User user = User.builder()
                   .phone("+9981234567")
                   .firstName("abc")
                   .lastName("def")
                   .password("Root1234")
                   .roles(List.of(role))
                   .build();
           userRepository.save(user);
       }



    }
}
