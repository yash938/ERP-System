package com.Erp_System.service;

import com.Erp_System.dto.ClassroomDto;
import com.Erp_System.response.PaegableResponse;

public interface ClassroomService {
    ClassroomDto create(ClassroomDto classroomDto);

    //update
    ClassroomDto update(ClassroomDto classroomDto, int classroomId);

    //delete
    void delete(int classroomId);

    //get single

    ClassroomDto get(int classroomId);

    //get all
    PaegableResponse<ClassroomDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search Department
    PaegableResponse<ClassroomDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir);


    //create Department with University
    ClassroomDto createWithDepartment(ClassroomDto classroomDto,int departmentId);


    //update University of Department
    ClassroomDto updateClassroom(int classroomId,int departmentId);

    PaegableResponse<ClassroomDto> getAllOfClassroom(int departmentId,int pageNumber,int pageSize,String sortBy, String sortDir);
}
