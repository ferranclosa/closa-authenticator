package com.closa.global.functions;

import com.closa.global.dto.GlobaliDTO;
import com.closa.global.dto.StandardQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
public class PagingHelper {
    public Pageable buildThePageable(StandardQuery iDto){
        Pageable pageable = null ;

        try {
            if(iDto.getPageNo() == null ){
                iDto.setPageNo(0);
            }
            if(iDto.getPageSize() == null ){
                iDto.setPageSize(Integer.MAX_VALUE);

            }
            if (iDto.getSortBy() == null ){
                return pageable = PageRequest.of(iDto.getPageNo(), iDto.getPageSize());
            } else if (iDto.getSortDirection()  == null || iDto.getSortDirection().toString().equalsIgnoreCase("asc") ){
                 return pageable = PageRequest.of(iDto.getPageNo(), iDto.getPageSize(), Sort.by(iDto.getSortBy()).ascending());
            } else
                return pageable = PageRequest.of(iDto.getPageNo(), iDto.getPageSize(), Sort.by(iDto.getSortBy()).descending());
        } catch (Exception e) {
            return null;
        }

    }
    public Pageable buildThePageable(GlobaliDTO iDto) {
        Pageable pageable = null;
        try {
            if (iDto.getPageNo() == null) {
                iDto.setPageNo(0);
            }
            if (iDto.getSortBy() == null) {
                iDto.setPageSize(Integer.MAX_VALUE);
            }
            if (iDto.getSortBy() == null) {
                return pageable = PageRequest.of(iDto.getPageNo(), iDto.getPageSize());
            } else if (iDto.getSortDirection() == null || (iDto.getSortDirection().toString().equalsIgnoreCase("asc"))) {
                return pageable = PageRequest.of(iDto.getPageNo(), iDto.getPageSize(), Sort.by(iDto.getSortBy()).ascending());
            } else
                return pageable = PageRequest.of(iDto.getPageNo(), iDto.getPageSize(), Sort.by(iDto.getSortBy()).descending());
        } catch (Exception e) {
            return null;
        }

    }
}
