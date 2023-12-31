package peaksoft.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;

    private String password;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
