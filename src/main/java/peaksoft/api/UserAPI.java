package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.AuthenticationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public AuthenticationResponse saveUser(UserRequest userRequest) {
        return userService.register(userRequest);
    }

    @GetMapping("/getAll")
    @PermitAll
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long userId) {
        return userService.deleteUserById(userId);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','CHEF')")
    public UserResponse findUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/{restaurantId}/{userId}/{word}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse answer(@PathVariable Long restaurantId, @PathVariable Long userId, @PathVariable String word) {
        return userService.answer(restaurantId, userId, word);
    }
}