package az.crocusoft.crocusofttask2.services.impl;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dao.entity.Teachers;
import az.crocusoft.crocusofttask2.dao.repository.CoursesRepo;
import az.crocusoft.crocusofttask2.dao.repository.TeachersRepo;
import az.crocusoft.crocusofttask2.dto.request.CoursesRequestDto;
import az.crocusoft.crocusofttask2.dto.request.TeachersRequestDto;
import az.crocusoft.crocusofttask2.dto.response.TeachersResponseDto;
import az.crocusoft.crocusofttask2.exception.CustomNotFoundException;
import az.crocusoft.crocusofttask2.services.TeachersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachersServiceImpl implements TeachersService {

    private final Logger log = LoggerFactory.getLogger(TeachersServiceImpl.class);

    private final TeachersRepo teachersRepo;
    private final CoursesRepo coursesRepo;
    private final ModelMapper modelMapper;

    @Override
    public String saveTeachers(TeachersRequestDto request) {
//        Requestden gelen CourseDto-larin ancaq Id-si bize lazimdi
//        Cunki Studentin yaranmagi yeni bir kursun yaranmagi demek deyil
//        Buna gorede Id-leri yoxluyub Course-leri tapib Teacher-lere Set etmek Ve save etmek lazimdi
        log.info("TeachersServiceImpl: saveTeachers STARTED");

        Teachers teachers = modelMapper.map(request, Teachers.class);
        List<Courses> coursesList = new ArrayList<>();
        for (CoursesRequestDto item : request.getCourses()) {
            coursesList.add(
                    coursesRepo.findById(item.getId())
                    .orElseThrow(() -> new CustomNotFoundException("Course Id is Null")));
        }
        log.info("TeachersServiceImpl: SAVE CoursesList {}",coursesList);

        teachers.setCourses(coursesList);

        log.info("TeachersServiceImpl: SAVE TeachersList {}",teachers);

        teachersRepo.save(teachers);
        return "Teacher Saved!";
    }

    @Override
    public String updateTeachers(TeachersRequestDto request) {
        if (request.getId()==null){
            throw new CustomNotFoundException("Teachers Id is Null!");
        }

        Teachers teachers = modelMapper.map(request, Teachers.class);
        List<Courses> coursesList = new ArrayList<>();

        for (CoursesRequestDto item : request.getCourses()) {
            coursesList.add(
                    coursesRepo.findById(item.getId())
                            .orElseThrow(() -> new CustomNotFoundException("Course Id is Null")));
        }
        log.info("TeachersServiceImpl: UPDATE CoursesList {}",coursesList);

        teachers.setCourses(coursesList);

        log.info("TeachersServiceImpl: UPDATE TeachersList {}",teachers);

        teachersRepo.save(teachers);
        return "Teacher Updated!";

    }


    @Override
    public List<TeachersResponseDto> findAllTeachersByCoursesName(String name){
        List<Teachers> list = teachersRepo.findAllTeachersByCoursesName(name);
        List<TeachersResponseDto> response =new ArrayList<>();

        if (response.isEmpty()){
            throw new CustomNotFoundException("Course of Teachers Not Found");
        }

        for (Teachers item : list) {
            response.add(modelMapper.map(item,TeachersResponseDto.class));
        }
        log.info("TeachersServiceImpl:findAllTeachersByCourseName : {}",response);

        return response;

    }
}
