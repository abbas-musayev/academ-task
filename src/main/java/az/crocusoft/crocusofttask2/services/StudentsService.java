package az.crocusoft.crocusofttask2.services;

import az.crocusoft.crocusofttask2.dto.request.StudentsRequestDto;
import az.crocusoft.crocusofttask2.dto.response.StudentsResponseDto;

public interface StudentsService {

    String saveStudents(StudentsRequestDto dto);
    String updateStudents(StudentsRequestDto dto);
    StudentsResponseDto findStudentByNameAndSurname(String name, String surname);
}
