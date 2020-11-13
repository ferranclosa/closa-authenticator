package com.closa.global.status.util;

import com.closa.global.status.dao.SUseCaseRepo;
import com.closa.global.status.dao.ScdRepository;
import com.closa.global.status.dao.SscdRepository;
import com.closa.global.status.model.StatusCodes;
import com.closa.global.status.model.StatusSubCode;
import com.closa.global.status.model.StatusUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Order(1)
public class DataLoader implements CommandLineRunner {

    @Autowired
    ScdRepository scdRepository;
    @Autowired
    SscdRepository sscdRepository;

    @Autowired
    SUseCaseRepo uscRepo;

    @Autowired
    StatusHelper sh ;

    @Override
    //@Transactional
    public void run(String... args) throws Exception { StatusSubCode sscd = null;
        StatusCodes scd = null;
    Long usc = uscRepo.count();
    if(usc == 0 )
    {
        StatusUseCase useCase = new StatusUseCase();
        useCase.setUscCode("C");
        useCase.setUscDescription("CREATE");
        uscRepo.save(useCase);

        useCase = new StatusUseCase();
        useCase.setUscCode("U");
        useCase.setUscDescription("UPDATE");
        uscRepo.save(useCase);

        useCase = new StatusUseCase();
        useCase.setUscCode("D");
        useCase.setUscDescription("DELETE");
        uscRepo.save(useCase);

        useCase = new StatusUseCase();
        useCase.setUscCode("A");
        useCase.setUscDescription("APPROVE");
        uscRepo.save(useCase);

        useCase = new StatusUseCase();
        useCase.setUscCode("A");
        useCase.setUscDescription("APPROVE");
        uscRepo.save(useCase);

    }

        Long sts = scdRepository.count();
        if (sts == 0 ){
            scd = new StatusCodes("A", "ACTIVATED");
            scd.addUseCase(uscRepo.getOne("C"));
            scd.addUseCase(uscRepo.getOne("U"));

            scdRepository.save(scd);
            scd = new StatusCodes("S", "CLOSED");
            scd.addUseCase(uscRepo.getOne("D"));
            scd.addUseCase(uscRepo.getOne("U"));
            scdRepository.save(scd);
            scd = new StatusCodes("Z", "AVENANT");
            scdRepository.save(scd);
            scd = new StatusCodes("K", "SUSPENDED");
            scd.addUseCase(uscRepo.getOne("U"));

            scdRepository.save(scd);
            scd = new StatusCodes("T", "IN TRANSIT");
            scd.addUseCase(uscRepo.getOne("C"));
            scd.addUseCase(uscRepo.getOne("U"));
            scdRepository.save(scd);
        }

        sts = sscdRepository.count();
        if (sts == 0){
            Optional<StatusCodes> osc = scdRepository.findById("A");
            sscd = new StatusSubCode("ACT", "FULLY OPERATIONAL", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("DOR", "DORMANT OR INACTIVE", osc.get());
            sscdRepository.save(sscd);
            osc = scdRepository.findById("S");
            sscd = new StatusSubCode("CLO", "CLOSED", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("DEL", "DELETED", osc.get());
            sscdRepository.save(sscd);

            osc = scdRepository.findById("T");
            sscd = new StatusSubCode("WUV", "WAITING USER VALIDATION", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("WSV", "WAITING STAFF VALIDATION", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("PRC", "PROCESSING", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("VAL", "VALIDATED BUT NOT YET ACTIVE", osc.get());
            sscdRepository.save(sscd);
            osc = scdRepository.findById("K");
            sscd = new StatusSubCode("EXP", "EXPIRED", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("BLK", "BLOCKED", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("DES", "DESACTIVATED", osc.get());
            sscdRepository.save(sscd);
            sscd = new StatusSubCode("REJ", "REJECTED", osc.get());
            sscdRepository.save(sscd);

        }

        List<StatusCodes> list = scdRepository.findAll();
        for (StatusCodes  one : list ){
            one.setStatus(sh.setItemStatus("A.ACT"));
            scdRepository.save(one);
        }
        List<StatusSubCode> list2 = sscdRepository.findAll();
        for (StatusSubCode  one : list2 ){
            one.setStatus(sh.setItemStatus("A.ACT"));
            sscdRepository.save(one);
        }

    }
}
