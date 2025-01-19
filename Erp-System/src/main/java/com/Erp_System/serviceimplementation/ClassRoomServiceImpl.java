package com.Erp_System.serviceimplementation;

import com.Erp_System.Entity.Classroom;
import com.Erp_System.Entity.Department;
import com.Erp_System.Helper.Helper;
import com.Erp_System.dto.ClassroomDto;
import com.Erp_System.exception.ResourceNotFoundException;
import com.Erp_System.repository.ClassroomRepo;
import com.Erp_System.repository.DepartmentRepo;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.service.ClassroomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepo classroomRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public ClassroomDto create(ClassroomDto classroomDto) {
        Classroom map = modelMapper.map(classroomDto, Classroom.class);
        Classroom save = classroomRepo.save(map);
        return modelMapper.map(save,ClassroomDto.class);
    }

    @Override
    public ClassroomDto update(ClassroomDto classroomDto, int classroomId) {
        Classroom classroom = classroomRepo.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("classroom is not found"));
        classroom.setDescription(classroomDto.getDescription());
        classroom.setTitle(classroomDto.getTitle());
        return null;
    }

    @Override
    public void delete(int classroomId) {
        Classroom classroom = classroomRepo.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("classroom is not found"));
        classroomRepo.delete(classroom);
    }

    @Override
    public ClassroomDto get(int classroomId) {
        Classroom classrom = classroomRepo.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("classroom is not found"));
        ClassroomDto find = modelMapper.map(classrom, ClassroomDto.class);
        return find;
    }

    @Override
    public PaegableResponse<ClassroomDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortBy.equalsIgnoreCase("asc"))?(Sort.by(sortDir).ascending()) :(Sort.by(sortDir).descending());

        Pageable pageable = PageRequest.of(pageNumber,pageSize ,sort );
        Page<Classroom> all = classroomRepo.findAll(pageable);
        return Helper.getPaegable(all,ClassroomDto.class);
    }

    @Override
    public PaegableResponse<ClassroomDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Classroom> byTitleContaining = classroomRepo.findByTitleContaining(subTitle, pageable);
        return Helper.getPaegable(byTitleContaining,ClassroomDto.class);
    }

    @Override
    public ClassroomDto createWithDepartment(ClassroomDto classroomDto, int departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department not found "));
        Classroom clasroom = modelMapper.map(classroomDto, Classroom.class);

        clasroom.setDepartment(department);
        Classroom save = classroomRepo.save(clasroom);
        return modelMapper.map(save,ClassroomDto.class);
    }

    @Override
    public ClassroomDto updateClassroom(int classroomId, int departmentId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department not found "));
        Classroom classrom = classroomRepo.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("classroom is not found"));

        classrom.setDepartment(department);
        Classroom save = classroomRepo.save(classrom);

        return modelMapper.map(save,ClassroomDto.class);
    }

    @Override
    public PaegableResponse<ClassroomDto> getAllOfClassroom(int departmentId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department not found "));
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Classroom> byDepartment = classroomRepo.findByDepartment(department,pageable);
        return Helper.getPaegable(byDepartment,ClassroomDto.class);
    }
}
