package caoh29.OMS.auth_server.dto;
import caoh29.OMS.auth_server.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Role role;
}
