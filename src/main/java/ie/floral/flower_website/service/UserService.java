package ie.floral.flower_website.service;

import ie.floral.flower_website.entity.User;
import ie.floral.flower_website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String fullName, String email, String rawPassword) {
        userRepository.findByEmail(email).ifPresent(u -> {
            throw new IllegalArgumentException("Email already in use");
        });

        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .build();

        return userRepository.save(user);
    }
}

