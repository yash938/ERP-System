package com.Erp_System.service;

import com.Erp_System.dto.DepartmentDto;
import com.Erp_System.response.PaegableResponse;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(int departmentId,DepartmentDto departmentDto);
    DepartmentDto singleDepartment(int departmentId);
    PaegableResponse<DepartmentDto> allDepartment(int pageNumber,int pageSize,String sortBy,String sortDir);

    DepartmentDto createWithUniversity(int universityId,DepartmentDto departmentDto);
    PaegableResponse<DepartmentDto> getAllByUniversity(int universityId,int pageNumber,int pageSize,String sortBy,String sortDir);
    DepartmentDto updateWithUniversity(int universityId,int departmentId);

    void delete(int departmentId);
}
