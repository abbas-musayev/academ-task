package az.crocusoft.crocusofttask2.dto.request;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StudentsRequestDto {
    Long id;
    String name;
    String surname;
    String email;

    Long[] courseId;

    public StudentsRequestDto() {
    }

    public StudentsRequestDto(Long id, String name, String surname, String email, Long[] courseId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.courseId = courseId;
    }
}
