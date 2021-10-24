package az.crocusoft.crocusofttask2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeachersRequestDto implements Serializable {
    Long id;
    String name;
    String surname;
    String email;

    @ToString.Exclude
    List<CoursesRequestDto> courses;
}

