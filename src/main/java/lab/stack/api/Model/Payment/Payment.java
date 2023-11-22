package lab.stack.api.Model.Payment;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "payment")
@Entity(name = "payment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boleto;
    private String userId;
    private String paymentDate;
    private String status;

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

