package lab.stack.api.Model.User.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lab.stack.api.Model.User.UserRole;

public record UserDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,

        UserRole role,
        @NotBlank
        String name,
        @NotNull
        @Max(1)
        int is_active,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf_cnpj,
        @NotBlank
        @Pattern(regexp = "\\d{11,15}")
        String phone
) {
}
