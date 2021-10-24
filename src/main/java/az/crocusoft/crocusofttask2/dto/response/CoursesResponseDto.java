package az.crocusoft.crocusofttask2.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoursesResponseDto {

    String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate expirationDate;

    @ToString.Exclude
    @JsonBackReference
    TeachersResponseDto teachers;

    @ToString.Exclude
    @JsonBackReference
    List<StudentsResponseDto> students;

    @ToString.Exclude
    @JsonBackReference
    AcademyResponseDto academy;
}
