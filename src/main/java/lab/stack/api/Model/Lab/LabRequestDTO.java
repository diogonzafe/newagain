package lab.stack.api.Model.Lab;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lab.stack.api.Model.User.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LabRequestDTO(


        @NotNull
        Long userId,

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


