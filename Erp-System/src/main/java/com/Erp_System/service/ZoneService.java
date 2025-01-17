package com.Erp_System.service;

import com.Erp_System.dto.ZoneDto;
import com.Erp_System.response.PaegableResponse;



public interface ZoneService {
    ZoneDto createZone(ZoneDto zoneDto);
    ZoneDto updateZone(int zoneId,ZoneDto zoneDto);
    ZoneDto getSingleZone(int zoneId);
    PaegableResponse<ZoneDto> allZones(int pageNumber, int pageSize, String sortBy, String sortDir);
    void deleteZone(int zoneId);
}
