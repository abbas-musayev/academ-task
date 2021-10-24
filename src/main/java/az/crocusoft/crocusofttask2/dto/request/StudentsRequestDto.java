package az.crocusoft.crocusofttask2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsRequestDto {
    Long id;
    String name;
    String surname;
    String email;

    @ToString.Exclude
    List<CoursesRequestDto> courses;
}
