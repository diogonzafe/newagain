package lab.stack.api.Model.Reserva;

import jakarta.persistence.*;
import lab.stack.api.Model.User.User;
import lombok.*;

public class Reserva {
    @Entity(name = "reserva")
    @Table(name = "reserva")
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
    }
}
