package lab.stack.api.Model.Payment;

import jakarta.persistence.*;
import lab.stack.api.Model.Lab_Payments.LabPayment;
import lab.stack.api.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "payment")
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String boleto;

    // Alteração para representar o relacionamento ManyToOne com a entidade User
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String paymentDate;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LabPayment> labPayments = new HashSet<>();

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    // Alteração para usar a entidade User diretamente
    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(Long userId) {
        this.user = new User(userId);
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addLabPayment(LabPayment labPayment) {
        labPayments.add(labPayment);
        labPayment.setPayment(this);
    }

    public void removeLabPayment(LabPayment labPayment) {
        labPayments.remove(labPayment);
        labPayment.setPayment(null);
    }
}
