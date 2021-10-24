package az.crocusoft.crocusofttask2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AcademyRequestDto{

    private Long id;
    private String name;
    private String address;

    private List<CoursesRequestDto> coursesList;
}
