package az.crocusoft.crocusofttask2.services;

import az.crocusoft.crocusofttask2.dto.request.StudentsRequestDto;
import az.crocusoft.crocusofttask2.dto.response.StudentsResponseDto;

import java.util.List;

public interface StudentsService {

    String saveStudents(StudentsRequestDto dto);
    String updateStudents(StudentsRequestDto dto);
    StudentsResponseDto findStudentByNameAndSurname(String name, String surname);
    List<StudentsResponseDto> findStudentsOfCourseById(Long id);
}
