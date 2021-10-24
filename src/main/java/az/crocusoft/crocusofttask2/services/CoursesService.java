package az.crocusoft.crocusofttask2.services;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dto.request.CoursesRequestDto;
import az.crocusoft.crocusofttask2.dto.response.CoursesResponseDto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CoursesService {


    String saveCourses(CoursesRequestDto dto);
    String updateCourses(CoursesRequestDto dto);
    CoursesResponseDto findCourseByCourseName(String name);
    List<CoursesResponseDto> findAllCourses();
    List<CoursesResponseDto> findCoursesOfStudentsById(Long id);
    List<CoursesResponseDto> findCoursesOfTeacherByName(String name);
    List<CoursesResponseDto> findCoursesOfAcademyByName(String name);

    String joinedStudentInCourse(Long courseId,Long[] studentId);
    String deleteCourse(Long id);
}
