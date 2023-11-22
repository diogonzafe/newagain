package lab.stack.api.Model.Lab;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "labs")
@Entity(name = "Lab")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String andar;
    private String lab;
    private String description;
    private int is_active;

    public Lab(LabRequestDTO body) {
        this.lab = body.lab();
        this.andar = body.andar();
        this.description = body.description();
        this.is_active = body.is_active();
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}

