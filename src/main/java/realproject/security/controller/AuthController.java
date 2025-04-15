package realproject.security.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import realproject.security.dto.LoginRequestDto;
import realproject.security.dto.TokenDto;
import realproject.security.dto.UserForm;
import realproject.security.entity.Attachment;
import realproject.security.entity.Role;
import realproject.security.entity.User;
import realproject.security.entity.enums.RoleName;
import realproject.security.repo.AttachmentRepository;
import realproject.security.repo.RoleRepository;
import realproject.security.repo.UserRepository;
import realproject.security.service.JwtUtils;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    public final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getPhone(), loginRequestDto.getPassword())
        );
        String accessToken = jwtUtils.generateToken((UserDetails) authenticate.getPrincipal());
        String refreshToken = jwtUtils.generateRefreshToken((UserDetails) authenticate.getPrincipal());
        return new TokenDto(accessToken, refreshToken);
    }


    @PostMapping( "/register")
    public HttpEntity<?> register(
            @Valid @ModelAttribute UserForm userForm) throws IOException {
        String phone = userForm.getUserDto().getPhone().trim();

        if (!phone.startsWith("+998")) {
            phone = "+998" + phone;
        }

        String phonePattern = "^\\+998\\d{9}$";
        if (!userForm.getUserDto().getPhone().matches(phonePattern)) {
            return ResponseEntity.badRequest().body("Telefon raqami +998 bilan boshlanib, 12 ta raqam bo‘lishi kerak!");
        }

        if (!userForm.getUserDto().getPassword().equals(userForm.getUserDto().getPasswordRepeat())) {
            return ResponseEntity.badRequest().body("Parollar mos kelmadi!");
        }

        if (userForm.getUserDto().getPassword().length() < 8) {
            return ResponseEntity.badRequest().body("Parol kamida 8 ta belgidan iborat bo‘lishi kerak!");
        }

        if (userForm.getImage() == null || userForm.getImage().isEmpty()) {
            return ResponseEntity.badRequest().body("Profil rasmi yuklanishi shart!");
        }

            Attachment attachment = Attachment.builder().image(userForm.getImage().getBytes()).build();
            attachmentRepository.save(attachment);

        Role role = roleRepository.findAllByRoleName(RoleName.ROLE_USER);
        User user = User.builder()
                .firstName(userForm.getUserDto().getFirstName())
                .phone(userForm.getUserDto().getPhone())
                .password(passwordEncoder.encode(userForm.getUserDto().getPassword()))
                .attachment(attachment)
                .roles(List.of(role))
                .build();
        User saved = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Foydalanuvchi muvaffaqiyatli ro‘yxatdan o‘tdi!");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Agar JWT tokenni ishlatgan bo'lsangiz, tokenni invalidate qilish kerak
        // Bu yerda HttpSession yordamida foydalanuvchini log out qilamiz

        // Sessionni invalid qilish
        request.getSession().invalidate();

        // Agar JWT token ishlatilayotgan bo'lsa, tokenni serverda invalid qilishni ta'minlash kerak
        // Bu uchun JWT tokenning expiry vaqtini yoki blacklisting qilish imkoniyatlari bo'lishi mumkin

        return ResponseEntity.ok("Foydalanuvchi muvaffaqiyatli tizimdan chiqdi!");
    }

}
