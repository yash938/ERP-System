package com.Erp_System.serviceimplementation;

import com.Erp_System.Entity.University;
import com.Erp_System.Entity.Zone;
import com.Erp_System.Helper.Helper;
import com.Erp_System.dto.UniversityDto;
import com.Erp_System.exception.ResourceNotFoundException;
import com.Erp_System.repository.UniversityRepo;
import com.Erp_System.repository.ZoneRepo;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.service.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityRepo universityRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ZoneRepo zoneRepo;
    @Override
    public UniversityDto createUniversity(UniversityDto universityDto) {
        University createUniversity = modelMapper.map(universityDto, University.class);
        University save = universityRepo.save(createUniversity);
        UniversityDto created = modelMapper.map(save, UniversityDto.class);
        return created;
    }

    @Override
    public UniversityDto updateUniversity(int universityId, UniversityDto universityDto) {
        University university = universityRepo.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University is not found"));
        university.setAddress(universityDto.getAddress());
        university.setTitle(universityDto.getTitle());
        university.setDescription(universityDto.getDescription());
        University save = universityRepo.save(university);
        UniversityDto updated = modelMapper.map(save, UniversityDto.class);
        return updated;
    }

    @Override
    public PaegableResponse<UniversityDto> allUniversity(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortDir).ascending()) ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<University> all = universityRepo.findAll(pageable);
        return Helper.getPaegable(all,UniversityDto.class);
    }

    @Override
    public UniversityDto singleUniversity(int universityId) {
        University university = universityRepo.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University is not found"));
        UniversityDto findUniversity = modelMapper.map(university, UniversityDto.class);
        return findUniversity;
    }

    @Override
    public UniversityDto createWithZone(int zoneId, UniversityDto universityDto) {
        Zone zone = zoneRepo.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone is not find"));
        University university = modelMapper.map(universityDto, University.class);
        university.setZone(zone);
        University createZone = universityRepo.save(university);
        UniversityDto updateUniversity = modelMapper.map(createZone, UniversityDto.class);
        return updateUniversity;
    }

    @Override
    public PaegableResponse<UniversityDto> findByZone(int zoneId,int pageNumber,int pageSize,String sortDir,String sortBy) {
        Zone zone = zoneRepo.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone is not found"));
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ?(Sort.by(sortBy).ascending()) :(Sort.by(sortBy).descending());
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<University> byZone = universityRepo.findByZone(zone, pageRequest);
        return Helper.getPaegable(byZone,UniversityDto.class);
    }

    public UniversityDto updateZoneOfUniversity(int zoneId,int universityId){
        University university = universityRepo.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("university is not found"));
        Zone zone = zoneRepo.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("zone is not found"));
        
        university.setZone(zone);
        University save = universityRepo.save(university);
        return modelMapper.map(save,UniversityDto.class);
    }
}
