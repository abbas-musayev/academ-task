package az.crocusoft.crocusofttask2.services.impl;

import az.crocusoft.crocusofttask2.dao.entity.Academy;
import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dao.entity.Students;
import az.crocusoft.crocusofttask2.dao.entity.Teachers;
import az.crocusoft.crocusofttask2.dao.repository.AcademyRepo;
import az.crocusoft.crocusofttask2.dao.repository.CoursesRepo;
import az.crocusoft.crocusofttask2.dao.repository.StudentsRepo;
import az.crocusoft.crocusofttask2.dao.repository.TeachersRepo;
import az.crocusoft.crocusofttask2.dto.request.CoursesRequestDto;
import az.crocusoft.crocusofttask2.dto.response.CoursesResponseDto;
import az.crocusoft.crocusofttask2.exception.CustomNotFoundException;
import az.crocusoft.crocusofttask2.services.CoursesService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    private final Logger log = LoggerFactory.getLogger(CoursesServiceImpl.class);

    private final CoursesRepo coursesRepo;
    private final StudentsRepo studentsRepo;
    private final TeachersRepo teachersRepo;
    private final AcademyRepo academyRepo;
    private final ModelMapper modelMapper;


    public CoursesServiceImpl(CoursesRepo coursesRepo,
                              StudentsRepo studentsRepo,
                              TeachersRepo teachersRepo,
                              AcademyRepo academyRepo, ModelMapper modelMapper) {
        this.coursesRepo = coursesRepo;
        this.studentsRepo = studentsRepo;
        this.teachersRepo = teachersRepo;
        this.academyRepo = academyRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public String saveCourses(CoursesRequestDto request) {
        List<Students> studentsList= new ArrayList<>();
        for (Long item : request.getStudentsId()) {
            studentsList.add(studentsRepo.getById(item));
        }
        Teachers teachers = teachersRepo.getById(request.getId());
        Academy academy = academyRepo.getById(request.getAcademyId());
        Courses courses = modelMapper.map(request, Courses.class);

        courses.setTeachers(teachers);
        courses.setStudents(studentsList);
        courses.setAcademy(academy);

        coursesRepo.save(courses);
        return "Courses Saved!";

    }

    @Override
    public CoursesResponseDto findCourseByCourseName(String name) {
        log.info("CoursesServiceImpl: findCourseByCourseName STARTED");
        Courses coursesByName = coursesRepo.findCoursesByName(name);
        log.info("CoursesServiceImpl: coursesByName -> {}",coursesByName);
        return modelMapper.map(coursesByName,CoursesResponseDto.class);
    }

    @Override
    public List<CoursesResponseDto> findAllCourses() {
        List<Courses> all = coursesRepo.findAll();
        log.info("FindAllCourses : {}",all);
        List<CoursesResponseDto> responseList = new ArrayList<>();
        for (Courses courses : all) {
            var map = modelMapper.map(courses, CoursesResponseDto.class);
            responseList.add(map);
        }
        return responseList;
    }

    @Override
    public String updateCourses(CoursesRequestDto request) {
        if (request.getId()==null){
            return "Id is Null!";
        }
        List<Students> studentsList= new ArrayList<>();
        for (Long item : request.getStudentsId()) {
            studentsList.add(studentsRepo.getById(item));
        }
        Teachers teachers = teachersRepo.getById(request.getId());
        Academy academy = academyRepo.getById(request.getAcademyId());
        Courses courses = modelMapper.map(request, Courses.class);

        courses.setTeachers(teachers);
        courses.setStudents(studentsList);
        courses.setAcademy(academy);

        coursesRepo.save(courses);

        return "Courses Saved!";
    }

    @Override
    public String joinedStudentInCourse(Long courseId, Long[] studentId) {

        Courses courses = coursesRepo.findById(courseId).orElseThrow(
                ()-> new CustomNotFoundException("Course Not Found ID: "+courseId));

        List<Students> list = new ArrayList<>();

        for (Long item : studentId) {
            Students students = studentsRepo.findById(item).orElseThrow(
                    () -> new CustomNotFoundException("Course Not Found ID: " + item));
            list.add(students);
        }

        courses.setStudents(list);
        log.info("CourseServiceImpl: joinedStudentInCourse : CourseName,StudentName {},{}",courses.getName(),list);

        return "Successfull joinedStudentInCourse";
    }

    @Override
    public String deleteCourse(Long id) {
        if (id==null){
            return "Id is Null!";
        }
        coursesRepo.deleteById(id);
        return "Course Deleted";
    }


}
