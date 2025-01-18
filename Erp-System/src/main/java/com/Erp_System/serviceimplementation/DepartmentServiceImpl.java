package com.Erp_System.serviceimplementation;

import com.Erp_System.Entity.Department;
import com.Erp_System.Entity.University;
import com.Erp_System.Helper.Helper;
import com.Erp_System.dto.DepartmentDto;
import com.Erp_System.exception.ResourceNotFoundException;
import com.Erp_System.repository.DepartmentRepo;
import com.Erp_System.repository.UniversityRepo;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UniversityRepo universityRepo;


    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department create = modelMapper.map(departmentDto, Department.class);
        Department save = departmentRepo.save(create);
        DepartmentDto createDepartment = modelMapper.map(save, DepartmentDto.class);
        return createDepartment;
    }

    @Override
    public DepartmentDto updateDepartment(int departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department is not found"));
        department.setDescription(departmentDto.getDescription());
        department.setTitle(departmentDto.getTitle());
        Department saveDepart = departmentRepo.save(department);
        DepartmentDto updateDepartment = modelMapper.map(saveDepart, DepartmentDto.class);
        return updateDepartment;
    }

    @Override
    public DepartmentDto singleDepartment(int departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department is not found"));
        DepartmentDto findDepart = modelMapper.map(department, DepartmentDto.class);
        return findDepart;
    }

    @Override
    public PaegableResponse<DepartmentDto> allDepartment(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()) :(Sort.by(sortBy).descending()) ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Department> all = departmentRepo.findAll(pageable);
        return Helper.getPaegable(all,DepartmentDto.class);
    }

    @Override
    public DepartmentDto createWithUniversity(int universityId, DepartmentDto departmentDto) {
        University university = universityRepo.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University is not found"));
        Department department = modelMapper.map(departmentDto, Department.class);
        department.setUniversity(university);
        Department saveUniversity = departmentRepo.save(department);
        
        return modelMapper.map(saveUniversity,DepartmentDto.class);
    }

    @Override
    public PaegableResponse<DepartmentDto> getAllByUniversity(int universityId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        University university = universityRepo.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University is not found"));
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Department> byUniversity = departmentRepo.findByUniversity(university.getUniversityId(), pageable);

        return Helper.getPaegable(byUniversity, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateWithUniversity(int universityId, int departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department is not found"));
        University university = universityRepo.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("university is not found"));

        department.setUniversity(university);
        Department save = departmentRepo.save(department);

        return modelMapper.map(save,DepartmentDto.class);
    }

    @Override
    public void delete(int departmentId) {
        Department departmentNotFound = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department not found"));
        departmentRepo.delete(departmentNotFound);
    }
}
