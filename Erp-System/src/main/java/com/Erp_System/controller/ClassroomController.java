package com.Erp_System.controller;

import com.Erp_System.dto.ClassroomDto;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/create")
    public ResponseEntity<ClassroomDto> createClassroom(@RequestBody @Valid ClassroomDto classroomDto){
        ClassroomDto classroomDto1 = classroomService.create(classroomDto);
        return new ResponseEntity<>(classroomDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{classroomId}")
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable int classroomId,@RequestBody @Valid ClassroomDto classroomDto){
        ClassroomDto update = classroomService.update(classroomDto, classroomId);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @GetMapping("/{classroomId}")
    public ResponseEntity<ClassroomDto> singleClassroom(@PathVariable int classroomId){
        ClassroomDto classroomDto = classroomService.get(classroomId);
        return new ResponseEntity<>(classroomDto,HttpStatus.OK);
    }

    @GetMapping("/allClassroom")
    public ResponseEntity<PaegableResponse<ClassroomDto>> allClassroom(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "20",required = false) int pageSize,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy
    ){
        PaegableResponse<ClassroomDto> all = classroomService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<PaegableResponse<ClassroomDto>> searchClassrooms(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PaegableResponse<ClassroomDto> classroomDtoPaegableResponse = classroomService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(classroomDtoPaegableResponse,HttpStatus.OK);
    }
}
