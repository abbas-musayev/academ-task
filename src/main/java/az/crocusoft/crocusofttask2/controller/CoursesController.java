package az.crocusoft.crocusofttask2.controller;

import az.crocusoft.crocusofttask2.dto.request.CoursesRequestDto;
import az.crocusoft.crocusofttask2.dto.response.CoursesResponseDto;
import az.crocusoft.crocusofttask2.services.CoursesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final Logger log = LoggerFactory.getLogger(CoursesController.class);
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping
    public ResponseEntity<String> saveCourses(@RequestBody CoursesRequestDto dto){
        return ResponseEntity.ok(coursesService.saveCourses(dto));
    }

    @PutMapping
    public ResponseEntity<String> updateCourse(@RequestBody CoursesRequestDto request) {
        return ResponseEntity.ok(coursesService.updateCourses(request));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCourse(@RequestParam Long id){
        return ResponseEntity.ok(coursesService.deleteCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<CoursesResponseDto>> findAllCourses(){
        return ResponseEntity.ok(coursesService.findAllCourses());
    }

    @GetMapping("/name")
    public ResponseEntity<CoursesResponseDto> findCourseByCourseName(@RequestParam String name){
        log.info("CoursesController: findCourseByCourseName STARTED");
        log.info("Name -> {}",name);
        return ResponseEntity.ok(coursesService.findCourseByCourseName(name));
    }

    @GetMapping("/joinedStudentInCourse")
    public ResponseEntity<String> joinedStudentInCourse(@RequestParam Long courseId,@RequestParam Long[] studentId){
        return ResponseEntity.ok(coursesService.joinedStudentInCourse(courseId,studentId));
    }

    @GetMapping("/findCoursesOfStudentsById")
    public ResponseEntity<List<CoursesResponseDto>> findCoursesOfStudentsById(@RequestParam Long id){
        return ResponseEntity.ok(coursesService.findCoursesOfStudentsById(id));
    }

    @GetMapping("/findCoursesOfTeacherByName")
    public ResponseEntity<List<CoursesResponseDto>> findCoursesOfTeacherById(@RequestParam String name){
        return ResponseEntity.ok(coursesService.findCoursesOfTeacherByName(name));
    }

    @GetMapping("/findCoursesOfAcademyByName")
    public ResponseEntity<List<CoursesResponseDto>> findCoursesOfAcademyByName(@RequestParam String name){
        return ResponseEntity.ok(coursesService.findCoursesOfAcademyByName(name));
    }



}
