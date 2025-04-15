package realproject.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import realproject.security.entity.User;
import realproject.security.repo.UserRepository;
import realproject.security.service.JwtUtils;


@RestController
@RequestMapping("/api/token/refresh")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    @GetMapping
    public String refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = (String) authentication.getPrincipal();
        User user = userRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("Phone not found"));
        return jwtUtils.generateToken(user);
    }
}
