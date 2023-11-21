package lab.stack.api.Controller;

import jakarta.validation.Valid;
import lab.stack.api.Model.Lab.Lab;
import lab.stack.api.Model.Lab.LabRequestDTO;
import lab.stack.api.Repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "lab")
public class LabController {
    @Autowired
    private LabRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postLab(@RequestBody @Valid LabRequestDTO body){
        Lab newLab = new Lab(body);

        this.repository.save(newLab);
        return ResponseEntity.ok().build();
    }
}
