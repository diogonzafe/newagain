package lab.stack.api.Controller;

import jakarta.validation.Valid;
import lab.stack.api.Model.Lab.Lab;
import lab.stack.api.Model.Lab.LabDTO;
import lab.stack.api.Model.Lab.LabRequestDTO;
import lab.stack.api.Model.Lab.LabResponseDTO;
import lab.stack.api.Model.Lab_Payments.LabPayment;
import lab.stack.api.Model.Payment.Payment;
import lab.stack.api.Repository.LabPaymentRepository;
import lab.stack.api.Repository.LabRepository;
import lab.stack.api.Repository.PaymentRepository;
import lab.stack.api.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "lab")
public class LabController {

    @Autowired
    private LabRepository repository;

    @Autowired
    private PaymentService paymentService;  // Injete o PaymentService

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LabPaymentRepository labPaymentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postLab(@RequestBody @Valid LabRequestDTO body) {
        // Criar uma instância de Lab a partir do LabRequestDTO
        Lab newLab = new Lab(body);

        // Buscar o pagamento a partir do ID do usuário usando o serviço
        Optional<Payment> paymentOptional = paymentRepository.findByUserId(body.userId());

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();

            // Verificar se o pagamento do boleto está aprovado
            if ("approved".equalsIgnoreCase(payment.getStatus())) {
                // Associar o laboratório ao pagamento
                LabPayment labPayment = new LabPayment(newLab, payment);
                newLab.addLabPayment(labPayment);

                // Salvar o laboratório e as associações
                this.repository.save(newLab);
                return ResponseEntity.ok().build();
            } else {
                // Se o pagamento não estiver aprovado, retornar um erro
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O pagamento do boleto não está aprovado.");
            }
        } else {
            // Se não encontrar o pagamento, retornar um erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi encontrado um pagamento para o usuário.");
        }
    }




    @GetMapping
    public ResponseEntity getAllLab() {
        List<LabResponseDTO> labList = this.repository.findAll().stream().map(LabResponseDTO::new).toList();

        return ResponseEntity.ok(labList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lab> updateLab(@PathVariable String id, @RequestBody @Valid LabDTO data) {
        Optional<Lab> existinglab = repository.findById(Long.valueOf(id));

        if (existinglab.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Lab labToUpdate = existinglab.get();

        labToUpdate.setAndar(data.andar());
        labToUpdate.setLab(data.lab());
        labToUpdate.setDescription(data.description());
        labToUpdate.setIs_active(data.is_active());

        Lab updatedLab = repository.save(labToUpdate);

        return ResponseEntity.ok(updatedLab);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLab(@PathVariable String id) {
        Optional<Lab> existingLab = repository.findById(Long.valueOf(id));

        if (existingLab.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(Long.valueOf(id));

        return ResponseEntity.noContent().build();
    }
}
