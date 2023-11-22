package lab.stack.api.Controller;

import jakarta.validation.Valid;
import lab.stack.api.Model.Lab.Lab;
import lab.stack.api.Model.Lab.LabDTO;
import lab.stack.api.Model.Lab.LabRequestDTO;
import lab.stack.api.Model.Lab.LabResponseDTO;
import lab.stack.api.Repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    @Transactional
    public ResponseEntity postLab(@RequestBody @Valid LabRequestDTO body){
        Lab newLab = new Lab(body);

        this.repository.save(newLab);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllLab(){
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
