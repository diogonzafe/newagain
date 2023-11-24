package lab.stack.api.Repository;

import java.util.Optional;

import lab.stack.api.Model.Payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByUserId(Long aLong);
}



