package lab.stack.api.Repository;

import lab.stack.api.Model.Lab.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, Long> {
}
