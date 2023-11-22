package lab.stack.api.Model.Lab;

public record LabResponseDTO(Long id, String andar, String lab, String description, int is_active) {
    public  LabResponseDTO(Lab lab){
        this(lab.getId(), lab.getLab(), lab.getAndar(), lab.getDescription(), lab.getIs_active());
    }
}
