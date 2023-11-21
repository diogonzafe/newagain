package lab.stack.api.Controller;

import lab.stack.api.Model.Payment.BoletoRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class PaymentController {


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

        return ResponseEntity.ok(response.getBody());
    }
}

