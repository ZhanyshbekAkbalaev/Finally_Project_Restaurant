package peaksoft.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import peaksoft.enums.Role;

import java.time.ZonedDateTime;

public record UserRequest(
        String firstName,
        String lastName,
        ZonedDateTime dateOfBirth,
        String email,
        String password,
        String phoneNumber,
        @Enumerated(EnumType.STRING)
        Role role,
        int expirense
) {
}
