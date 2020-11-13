package com.closa.global.status.util;

import com.closa.global.status.dao.ScdRepository;
import com.closa.global.status.dao.SscdRepository;
import com.closa.global.status.model.ItemStatus;
import com.closa.global.status.model.StatusCodes;
import com.closa.global.status.model.StatusSubCode;
import com.closa.global.status.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusHelper {

    @Autowired
    ScdRepository scdRepository;

    @Autowired
    SscdRepository sscdRepository;

    public StatusHelper() {
    }

    public StatusHelper(ScdRepository scdRepository, SscdRepository sscdRepository) {
        this.scdRepository = scdRepository;
        this.sscdRepository = sscdRepository;
    }

    public ItemStatus setItemStatus(Status status){
        ItemStatus is = new ItemStatus();
        String sc = status.getValue().substring(0,1);
        String scd = status.getValue().substring(2,5);
        Optional<StatusCodes> osc = scdRepository.findById(sc);
        Optional<StatusSubCode> ossc = sscdRepository.findById(scd);
        is.setStatusCode(osc.get().getStatusCode());
        is.setStatusSubCode(ossc.get().getStsSubCode());
        is.setStatusSetWhen();
        return is;
    }
    public ItemStatus setItemStatus(String status){
        ItemStatus is = new ItemStatus();
        String sc = status.substring(0,1);
        String scd = status.substring(2,5);
        Optional<StatusCodes> osc = scdRepository.findById(sc);
        Optional<StatusSubCode> ossc = sscdRepository.findById(scd);
        is.setStatusCode(osc.get().getStatusCode());
        is.setStatusSubCode(ossc.get().getStsSubCode());
        is.setStatusSetWhen();
        return is;
    }
    public Boolean isActive(ItemStatus itemStatus){
        return  itemStatus.getActive();
    }

}
