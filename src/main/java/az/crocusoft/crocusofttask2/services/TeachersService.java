package az.crocusoft.crocusofttask2.services;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dto.request.TeachersRequestDto;
import az.crocusoft.crocusofttask2.dto.response.TeachersResponseDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeachersService {

    String saveTeachers(TeachersRequestDto request);
    String updateTeachers(TeachersRequestDto request);
    TeachersResponseDto findTeachersByCoursesName(String name);
}
