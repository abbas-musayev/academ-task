package az.crocusoft.crocusofttask2.dto.request;

import az.crocusoft.crocusofttask2.dto.response.AcademyResponseDto;
import az.crocusoft.crocusofttask2.dto.response.StudentsResponseDto;
import az.crocusoft.crocusofttask2.dto.response.TeachersResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoursesRequestDto  implements Serializable {

    Long id;
    String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate expirationDate;

    TeachersRequestDto teachers;

    List<StudentsRequestDto> students;

    AcademyRequestDto academy;

//    Long teachersId;
//
//    List<Long> studentsId;
//
//    Long academyId;



}
