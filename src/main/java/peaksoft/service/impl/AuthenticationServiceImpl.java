package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.request.AuthenticationRequest;
import peaksoft.dto.response.AuthenticationResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setDateOfBirth(ZonedDateTime.of(2004, 2, 27, 10, 2, 23, 3, ZoneId.systemDefault()));
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setPhoneNumber("+996505550574");
        user.setRole(Role.ADMIN);
        user.setExpirense(1);
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email: " + authenticationRequest.getEmail() + " not found"
                ));
        if (authenticationRequest.getEmail().isBlank()) {
            throw new BadCredentialsException("Email doesn't exist");
        }
        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }
        String jwToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .token(jwToken)
                .build();
    }
}
