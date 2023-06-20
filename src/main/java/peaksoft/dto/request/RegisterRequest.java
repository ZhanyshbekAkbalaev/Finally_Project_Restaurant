package peaksoft.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int experience;
    private String phoneNumber;

    public RegisterRequest(String firstName, String lastName, String email, String password, int experience, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.experience = experience;
        this.phoneNumber = phoneNumber;
    }
}
