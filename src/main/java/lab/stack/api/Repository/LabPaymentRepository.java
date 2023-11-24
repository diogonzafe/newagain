package lab.stack.api.Repository;

import lab.stack.api.Model.Lab_Payments.LabPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabPaymentRepository extends JpaRepository<LabPayment, Long> {

}
