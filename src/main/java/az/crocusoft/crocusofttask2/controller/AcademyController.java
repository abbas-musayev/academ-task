package az.crocusoft.crocusofttask2.controller;

import az.crocusoft.crocusofttask2.dto.request.AcademyRequestDto;
import az.crocusoft.crocusofttask2.services.AcademyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/academy")
public class AcademyController {

    private final AcademyService academyService;

    public AcademyController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @PostMapping
    public ResponseEntity<String> saveAcademy(@RequestBody AcademyRequestDto request){
        return ResponseEntity.ok(academyService.saveAcademy(request));
    }
}
