package az.crocusoft.crocusofttask2.services;

import az.crocusoft.crocusofttask2.dto.request.CoursesRequestDto;
import az.crocusoft.crocusofttask2.dto.response.CoursesResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoursesService {


    String saveCourses(CoursesRequestDto dto);
    CoursesResponseDto findCourseByCourseName(String name);
    List<CoursesResponseDto> findAllCourses();
    String updateCourses(CoursesRequestDto dto);
    String joinedStudentInCourse(Long courseId,Long[] studentId);
    String deleteCourse(Long id);
}
