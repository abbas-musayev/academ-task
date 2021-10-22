package az.crocusoft.crocusofttask2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeachersResponseDto {

    Long id;
    String name;
    String surname;
    String email;

    List<CoursesResponseDto> courses;

}
