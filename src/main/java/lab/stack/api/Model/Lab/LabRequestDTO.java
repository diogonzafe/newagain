package lab.stack.api.Model.Lab;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LabRequestDTO(

        @NotBlank
        String lab,
        @NotNull
        String andar,
        @NotNull
        int is_active,

        @NotNull
        String description


) {
}

