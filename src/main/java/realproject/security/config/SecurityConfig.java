package realproject.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import realproject.security.service.CustomUserDetailsService;
import realproject.security.service.Filter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, Filter filter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/api/auth/**",
                        "/api/token/refresh",
                        "/css/**", "/js/**", "/images/**",
                        "/login.html",
                        "/favicon.ico"
                ).permitAll()

                // 🟢 Barcha foydalanuvchilar ko‘rishi mumkin bo‘lgan endpointlar
                .requestMatchers(HttpMethod.GET,
                        "/api/program_link",
                        "/api/video_link",
                        "/api/files",
                        "/api/files/download/**"
                ).permitAll()

                // 🔴 Faqat ADMIN huquqli foydalanuvchilar bajara oladigan endpointlar
                .requestMatchers(
                        "/api/program_link",        // POST (link qo‘shish)
                        "/api/video_link",          // POST (link qo‘shish)
                        "/api/program_link/**",     // DELETE
                        "/api/video_link/**",       // DELETE
                        "/api/files/upload",        // fayl yuklash
                        "/api/files/delete/**"      // fayl o‘chirish
                ).hasRole("ADMIN")

//                 🔒 Qolgan barcha requestlar login talab qiladi
                .anyRequest().permitAll()

        );

        http.sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.userDetailsService(customUserDetailsService);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider providerManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(providerManager());
    }
}
