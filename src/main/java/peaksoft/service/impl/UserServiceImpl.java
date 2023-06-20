package peaksoft.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.AuthenticationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new EntityExistsException(String.format(
                    "User with email: %s already exists!", userRequest.email()));
        }
        if (userRequest.role().equals(Role.ADMIN)) {
            SimpleResponse.builder()
                    .status(HttpStatus.FORBIDDEN)
                    .message("Forbidden")
                    .build();
        }
        if (userRequest.role().equals(Role.CHEF)) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            if (zonedDateTime.getYear() > 45 || zonedDateTime.getYear() < 25) {
                SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("The cook must be between 25 and 45 years of age")
                        .build();
            }
        }
        if (userRequest.role().equals(Role.WAITER)) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            if (zonedDateTime.getYear() > 30 || zonedDateTime.getYear() < 18) {
                SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Waiter must be between 18 and 30 years of age")
                        .build();
            }
        }
        if (userRequest.role().equals(Role.WAITER)) {
            if (userRequest.expirense() < 1 || userRequest.expirense() > 10) {
                SimpleResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Waiter experience must be between 1 and 10")
                        .build();
            }
        }

        User user = User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .dateOfBirth(ZonedDateTime.now())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .phoneNumber(userRequest.phoneNumber())
                .role(userRequest.role())
                .expirense(userRequest.expirense())
                .build();
        if (!user.getRole().equals(Role.ADMIN)) {
            userRepository.save(user);
        } else {
            throw new BadRequestException("User with role yes ADMIN!");
        }
        String jwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwToken)
                .email(user.getEmail())
                .build();
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public SimpleResponse deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("User with id %s is deleted!", userId))
                .build();
    }

    @Override
    public SimpleResponse updateUser(Long userId, UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("user with email yes!!!" + userRequest.email())
                    .build();
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " is not found!"));
        if (!user.getRole().equals(Role.ADMIN)) {
            user.setFirstName(userRequest.firstName());
            user.setLastName(userRequest.lastName());
            user.setDateOfBirth(userRequest.dateOfBirth());
            user.setEmail(userRequest.email());
            user.setPassword(userRequest.password());
            user.setPhoneNumber(userRequest.phoneNumber());
            user.setRole(userRequest.role());
            user.setExpirense(userRequest.expirense());
            userRepository.save(user);
        } else {
            throw new BadRequestException("User with role ADMIN");
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("User with name %s is successfully updated.", user.getFirstName()))
                .build();
    }

    @Override
    public UserResponse findById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public SimpleResponse answer(Long restaurantId, Long userId, String word) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Restaurant with id " + restaurantId + " is not found!"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id " + userId + " is not found!"));
        if (word.equals("ok")){
            restaurant.setUsers(List.of(user));
            user.setRestaurant(restaurant);
            userRepository.save(user);
        } else if (word.equals("no")) {
            userRepository.delete(user);
            return SimpleResponse.builder()
                    .status(HttpStatus.NO_CONTENT)
                    .message(String.format("User with name %s is successfully delete...",user.getFirstName()))
                    .build();
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name %s and User with name %s is successfully answer...",restaurant.getName(),user.getFirstName()))
                .build();
    }
}