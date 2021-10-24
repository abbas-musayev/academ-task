package az.crocusoft.crocusofttask2.controller;

import az.crocusoft.crocusofttask2.dto.request.StudentsRequestDto;
import az.crocusoft.crocusofttask2.dto.response.StudentsResponseDto;
import az.crocusoft.crocusofttask2.services.StudentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentsController {

    private final Logger log = LoggerFactory.getLogger(StudentsController.class);
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity<String> saveStudents(@RequestBody StudentsRequestDto dto){
        log.info("StudentsController: saveStudents STARTED");
        log.info("StudentsRequestDto -> {}",dto);
        return ResponseEntity.ok(studentsService.saveStudents(dto));
    }

    @PutMapping
    public ResponseEntity<String> updateStudents(@RequestBody StudentsRequestDto request){
        return ResponseEntity.ok(studentsService.updateStudents(request));
    }

    @GetMapping("/findbynameandsurname")
    public ResponseEntity<StudentsResponseDto> findStudentByNameAndSurname(
            @RequestParam String name, @RequestParam String surname){
        log.info("StudentsController: findStudentByNameAndSurname STARTED");
        log.info("Name: "+ name+" surname: "+surname);
        return ResponseEntity.ok(studentsService.findStudentByNameAndSurname(name,surname));
    }

    @GetMapping("/findStudentsOfCourseById")
    public ResponseEntity<List<StudentsResponseDto>> findStudentsOfCourseById(@RequestParam Long id){
        return ResponseEntity.ok(studentsService.findStudentsOfCourseById(id));
    }
}
