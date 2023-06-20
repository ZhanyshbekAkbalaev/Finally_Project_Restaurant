package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("select new peaksoft.dto.response.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.role)from User u")
    List<UserResponse> getAllUsers();

    @Query("select new peaksoft.dto.response.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.role)from User u where u.id = ?1")
    UserResponse getUserById(Long userId);

}