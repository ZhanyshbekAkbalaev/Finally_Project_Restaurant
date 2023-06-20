package peaksoft.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import peaksoft.enums.Role;

import java.time.ZonedDateTime;
@Builder
public record UserResponse(
        Long userId,
        String firstName,
        String lastName,
        ZonedDateTime dateOfBirth,
        @Enumerated(EnumType.STRING)
        Role role
) {
}