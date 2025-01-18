package com.Erp_System.service;

import com.Erp_System.dto.UniversityDto;
import com.Erp_System.response.PaegableResponse;

public interface UniversityService {

    UniversityDto createUniversity(UniversityDto universityDto);
    UniversityDto updateUniversity(int universityId,UniversityDto universityDto);
    PaegableResponse<UniversityDto> allUniversity(int pageNumber, int pageSize, String sortBy, String sortDir);
    UniversityDto singleUniversity(int universityId);

    UniversityDto createWithZone(int zoneId,UniversityDto universityDto);
    PaegableResponse<UniversityDto> findByZone(int zoneId,int pageNumber,int pageSize,String sortDir,String sortBy);
    UniversityDto updateZoneUniversity(int zoneId,int universityId);
}
