package lab.stack.api.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab.stack.api.Model.Payment.BoletoRequest;
import lab.stack.api.Model.Payment.Payment;
import lab.stack.api.Model.User.User;
import lab.stack.api.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    private final String apiUrl = "https://api-go-wash-efc9c9582687.herokuapp.com/api/pay-boleto";
    private final String apiAuthorization = "Vf9WSyYqnwxXODjiExToZCT9ByWb3FVsjr";
    private final String apiCookie = "gowash_session=m6Y5t4HwextNyZIPR4uCOD97ebOoYusUfmRMwt06";

    @PostMapping("/pay-boleto")
    public ResponseEntity<String> payBoleto(@RequestBody BoletoRequest boletoRequest) {
        // Configurar os headers necessários
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiAuthorization);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Cookie", apiCookie);

        // Configurar a requisição HTTP com os dados do boletoRequest
        HttpEntity<BoletoRequest> requestEntity = new HttpEntity<>(boletoRequest, headers);

        // Realizar a chamada POST à API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // Processar a resposta da API e salvar os dados
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            // Usar a biblioteca Jackson para processar o JSON
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                // Verificar se a resposta contém o nó 'data'
                if (jsonNode.has("data")) {
                    JsonNode dataNode = jsonNode.get("data");

                    // Extrair os dados necessários
                    String boleto = dataNode.get("boleto").asText();
                    Long userId = dataNode.get("user_id").asLong();  // Supondo que o ID seja um número longo
                    String paymentDate = dataNode.get("payment_date").asText();
                    String status = dataNode.get("status").asText();

// Criar uma instância da entidade Payment
                    Payment payment = new Payment();
                    payment.setBoleto(boleto);
                    payment.setUser(userId);
                    payment.setPaymentDate(paymentDate);
                    payment.setStatus(status);

                    // Salvar a entidade no banco de dados
                    paymentRepository.save(payment);
                } else {
                    // Lidar com casos em que a resposta não contém o nó 'data'
                    System.out.println("Resposta da API não contém o nó 'data'.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Lidar com erros de parsing
            }
        }

        return ResponseEntity.ok(response.getBody());
    }
}
