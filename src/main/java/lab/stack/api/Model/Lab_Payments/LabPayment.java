package lab.stack.api.Model.Lab_Payments;

import jakarta.persistence.*;
import lab.stack.api.Model.Lab.Lab;
import lab.stack.api.Model.Payment.Payment;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lab_payments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LabPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public LabPayment(Lab lab, Payment payment) {
        this.lab = lab;
        this.payment = payment;
    }

    // getters e setters...



    public void setLab(Lab lab) {
    }

    public void setPayment(Payment payment) {
    }

    // getters e setters...
}


