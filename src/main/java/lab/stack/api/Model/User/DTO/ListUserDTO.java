package lab.stack.api.Model.User.DTO;

import lab.stack.api.Model.User.User;
import lab.stack.api.Model.User.UserRole;

import java.util.concurrent.atomic.LongAccumulator;

public record ListUserDTO(Long id, String email, String password, UserRole role, int is_active, String cpf_cnpj, String phone ) {

    public ListUserDTO(User user){
        this(user.getId(), user.getEmail(), user.getPassword(), user.getRole(), user.getIs_active(), user.getCpf_cnpj(), user.getPhone());
    }

}
