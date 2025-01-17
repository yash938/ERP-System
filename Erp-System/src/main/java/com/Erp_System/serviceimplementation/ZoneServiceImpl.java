package com.Erp_System.serviceimplementation;

import com.Erp_System.Entity.Zone;
import com.Erp_System.Helper.Helper;
import com.Erp_System.dto.ZoneDto;
import com.Erp_System.exception.ResourceNotFoundException;
import com.Erp_System.repository.ZoneRepo;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.service.ZoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ZoneServiceImpl implements ZoneService {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ZoneRepo zoneRepo;
    
    @Override
    public ZoneDto createZone(ZoneDto zoneDto) {
        Zone createZone = modelMapper.map(zoneDto, Zone.class);
        Zone save = zoneRepo.save(createZone);
        ZoneDto createdZone = modelMapper.map(save, ZoneDto.class);
        return createdZone;
    }

    @Override
    public ZoneDto updateZone(int zoneId, ZoneDto zoneDto) {
        Zone zone = zoneRepo.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone is not found with given id " + zoneId));
        zone.setDescription(zoneDto.getDescription());
        zone.setTitle(zoneDto.getTitle());
        Zone updatedZone = zoneRepo.save(zone);
        ZoneDto updateZone = modelMapper.map(updatedZone, ZoneDto.class);
        return updateZone;
    }

    @Override
    public ZoneDto getSingleZone(int zoneId) {
        Zone zone = zoneRepo.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone is not found "));
        ZoneDto findZone = modelMapper.map(zone, ZoneDto.class);
        return findZone;
    }

    @Override
    public PaegableResponse<ZoneDto> allZones(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending()) ;
       Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Zone> all = zoneRepo.findAll(pageable);
        PaegableResponse<ZoneDto> paegable = Helper.getPaegable(all, ZoneDto.class);
        return paegable;
    }

    @Override
    public void deleteZone(int zoneId) {
        Zone zone = zoneRepo.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone is not found"));
        zoneRepo.delete(zone);
    }
}
