package az.crocusoft.crocusofttask2.services.impl;

import az.crocusoft.crocusofttask2.dao.entity.Courses;
import az.crocusoft.crocusofttask2.dao.entity.Teachers;
import az.crocusoft.crocusofttask2.dao.repository.CoursesRepo;
import az.crocusoft.crocusofttask2.dao.repository.TeachersRepo;
import az.crocusoft.crocusofttask2.dto.request.TeachersRequestDto;
import az.crocusoft.crocusofttask2.dto.response.TeachersResponseDto;
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
    public String saveTeachers(TeachersRequestDto dto) {
        log.info("TeachersServiceImpl: saveTeachers STARTED");

        Teachers teachers = modelMapper.map(dto, Teachers.class);
        List<Courses> list = new ArrayList<>();
        for (Long item : dto.getCoursesId()) {
            list.add(coursesRepo.getById(item));
        }
        log.info("TeachersServiceImpl: CoursesList {}",list);

        teachers.setCourses(list);

        log.info("TeachersServiceImpl: TeachersList {}",teachers);

        teachersRepo.save(teachers);
        return "Teacher Saved!";
    }

    @Override
    public List<TeachersResponseDto> findAllTeachersByCoursesName(String name){
        List<Teachers> list = teachersRepo.findAllTeachersByCoursesName(name);
        List<TeachersResponseDto> response =new ArrayList<>();

        for (Teachers item : list) {
            response.add(modelMapper.map(item,TeachersResponseDto.class));
        }
        log.info("TeachersServiceImpl:findAllTeachersByCourseName : {}",response);

        return response;

    }
}
