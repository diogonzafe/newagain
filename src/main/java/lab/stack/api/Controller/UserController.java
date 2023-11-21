package lab.stack.api.Controller;

import jakarta.validation.Valid;
import lab.stack.api.Model.User.*;
import lab.stack.api.Model.User.DTO.*;
import lab.stack.api.Repository.UserRepository;
import lab.stack.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserDTO dados){
        if(this.repository.findByEmail(dados.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.password());
        User newUser = new User(dados.email(), encryptedPassword, dados.role(), dados.name(), dados.is_active(), dados.cpf_cnpj(), dados.phone());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity email(@RequestBody @Valid LoginDTO data){


        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();

        if (!(user.getIs_active() == 0)) {
            // Verifique se o is_active não é igual a "0" e retorne um erro.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não ativado");
        }

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @GetMapping("/users")
    public List<ListUserDTO> getAllUsers() {
        return repository.findAll().stream().map(ListUserDTO::new).toList();
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO data) {
        Optional<User> existingUser = repository.findById(id);

        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userToUpdate = existingUser.get();

        userToUpdate.setEmail(data.email());
        userToUpdate.setPassword(new BCryptPasswordEncoder().encode(data.password()));
        userToUpdate.setRole(data.role());
        userToUpdate.setName(data.name());
        userToUpdate.setIsActive(data.is_active());
        userToUpdate.setCpfCnpj(data.cpf_cnpj());
        userToUpdate.setPhone(data.phone());

        User updatedUser = repository.save(userToUpdate);

        return ResponseEntity.ok(updatedUser);
    }
    }


