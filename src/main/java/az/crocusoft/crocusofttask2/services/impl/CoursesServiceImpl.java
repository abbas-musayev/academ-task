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
import az.crocusoft.crocusofttask2.dto.request.StudentsRequestDto;
import az.crocusoft.crocusofttask2.dto.response.CoursesResponseDto;
import az.crocusoft.crocusofttask2.exception.CustomNotFoundException;
import az.crocusoft.crocusofttask2.services.CoursesService;
import org.hibernate.Hibernate;
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

        Courses map = modelMapper.map(request, Courses.class);
        coursesRepo.save(map);
        return "Course Saved!";
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
    public List<CoursesResponseDto> findCoursesOfStudentsById(Long id) {
        List<Courses> coursesOfStudents = coursesRepo.findCoursesOfStudentsById(id);

        List<CoursesResponseDto> list = new ArrayList<>();
        for (Courses item : coursesOfStudents) {
            list.add(modelMapper.map(item,CoursesResponseDto.class));
        }
        return list;
    }

    @Override
    public List<CoursesResponseDto> findCoursesOfTeacherByName(String name) {
        List<Courses> coursesOfTeachers = coursesRepo.findCoursesOfTeachersByName(name);
        List<CoursesResponseDto> list = new ArrayList<>();
        for (Courses item : coursesOfTeachers) {
            list.add(modelMapper.map(item,CoursesResponseDto.class));
        }
        return list;
    }

    @Override
    public List<CoursesResponseDto> findCoursesOfAcademyByName(String name) {

        List<Courses> courses = coursesRepo.findCoursesOfAcademyByName(name);
        if (courses.isEmpty())
            throw new CustomNotFoundException("Course Not Found for Academy Name:"+name);

        List<CoursesResponseDto> list = new ArrayList<>();
        for (Courses item : courses) {
            list.add(modelMapper.map(item,CoursesResponseDto.class));
        }
        return list;
    }

    @Override
    public String updateCourses(CoursesRequestDto request) {

        if (request.getId()==null || request.getId().equals(0L))
            throw new CustomNotFoundException("Course Id is Null!");

        List<Students> studentsOfCourse = studentsRepo.findStudentsOfCourseById(request.getId());

        Courses map = modelMapper.map(request, Courses.class);

        for (Students item : map.getStudents()) {
            if (item.getId()!=null){
                Students byId = (Students) Hibernate.unproxy(studentsRepo.getById(item.getId()));
                studentsOfCourse.add(byId);
            }
        }
        if (map.getTeachers().getId()!=null){
            Teachers teachers = teachersRepo.findById(map.getTeachers().getId()).get();
            map.setTeachers(teachers);
        }
        if (map.getAcademy().getId()!=null || map.getAcademy().getId()==0){
            Academy academy = academyRepo.findById(map.getAcademy().getId()).orElse(new Academy());
            map.setAcademy(academy);
        }

        log.info("CoursesServiceImpl: SaveCourses: Courses Entity {}",map);
        coursesRepo.save(map);

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
