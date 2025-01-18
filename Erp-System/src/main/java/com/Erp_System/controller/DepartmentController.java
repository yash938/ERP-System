package com.Erp_System.controller;

import com.Erp_System.dto.DepartmentDto;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.response.Utils;
import com.Erp_System.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        DepartmentDto department = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable int departmentId,@RequestBody DepartmentDto departmentDto){
        DepartmentDto updateDepartment = departmentService.updateDepartment(departmentId, departmentDto);
        return new ResponseEntity<>(updateDepartment,HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> singleDepartment(@PathVariable int departmentId){
        DepartmentDto departmentDto = departmentService.singleDepartment(departmentId);
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<PaegableResponse<DepartmentDto>> allDepartments(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "20",required = false) int pageSize,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy
    ){
        PaegableResponse<DepartmentDto> department = departmentService.allDepartment(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(department,HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Utils> deleteDepartments(@PathVariable int departmentId){
        departmentService.delete(departmentId);
        Utils delete = Utils.builder().message("department deleted !! ").status(HttpStatus.OK).date(LocalDate.now()).build();
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }
}
