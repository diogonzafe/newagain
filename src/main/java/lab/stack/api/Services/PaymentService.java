package lab.stack.api.Services;

import lab.stack.api.Model.Payment.Payment;
import lab.stack.api.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Método para buscar o boleto pelo ID do pagamento
    public String buscarBoletoPorId(Long paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        return paymentOptional.map(Payment::getBoleto).orElse(null);
    }
}
