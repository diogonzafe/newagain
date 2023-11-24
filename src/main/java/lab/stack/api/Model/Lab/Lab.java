package lab.stack.api.Model.Lab;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lab.stack.api.Model.Lab_Payments.LabPayment;
import lab.stack.api.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "labs")
@Entity(name = "Lab")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String andar;
    private String lab;
    private String description;
    private int is_active;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LabPayment> labPayments = new HashSet<>();

    public Lab(@Valid LabRequestDTO body) {
        this.lab = body.lab();
        this.andar = body.andar();
        this.description = body.description();
        this.is_active = body.is_active();
        this.user = new User(body.userId());  // Criar uma instância de User usando o ID
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addLabPayment(LabPayment labPayment) {
        labPayments.add(labPayment);
        labPayment.setLab(this);
    }

    public void removeLabPayment(LabPayment labPayment) {
        labPayments.remove(labPayment);
        labPayment.setLab(null);
    }
}
