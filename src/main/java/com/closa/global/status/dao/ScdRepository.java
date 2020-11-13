package com.closa.global.status.dao;

import com.closa.global.status.model.StatusCodes;
import com.closa.global.status.model.bo.BoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScdRepository extends JpaRepository<StatusCodes, String> {

    @Query("select new com.closa.global.status.model.bo.BoStatus (" +
            " a,  concat(a.statusCode , '.' , b.stsSubCode) , b.stsScdDescription) " +
            "from " +
            "StatusCodes a join StatusSubCode b on a.statusCode = b.belongsTo " )
    List<BoStatus> provideDynamicStatus();


}
