package az.crocusoft.crocusofttask2.services.impl;

import az.crocusoft.crocusofttask2.dao.entity.Academy;
import az.crocusoft.crocusofttask2.dao.repository.AcademyRepo;
import az.crocusoft.crocusofttask2.dto.request.AcademyRequestDto;
import az.crocusoft.crocusofttask2.services.AcademyService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AcademyServiceImpl implements AcademyService {

    private final Logger log = LoggerFactory.getLogger(AcademyServiceImpl.class);

    private final AcademyRepo academyRepo;
    private final ModelMapper modelMapper;

    public AcademyServiceImpl(AcademyRepo academyRepo, ModelMapper modelMapper) {
        this.academyRepo = academyRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveAcademy(AcademyRequestDto request) {
        log.info("AcademyServiceImpl: SaveAcademy Started");
        academyRepo.save(modelMapper.map(request,Academy.class));
        return "Academy Saved";
    }

}
