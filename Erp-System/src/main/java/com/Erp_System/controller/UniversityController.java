package com.Erp_System.controller;

import com.Erp_System.dto.UniversityDto;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/create")
    public ResponseEntity<UniversityDto> createUniversities(@RequestBody @Valid  UniversityDto universityDto){
        UniversityDto university = universityService.createUniversity(universityDto);
        return new ResponseEntity<>(university, HttpStatus.CREATED);
    }

    @PutMapping("/{universityId}")
    public ResponseEntity<UniversityDto> updateUniversity(@PathVariable int universityId,@RequestBody @Valid UniversityDto universityDto){
        UniversityDto updateUniversity = universityService.updateUniversity(universityId, universityDto);
        return new ResponseEntity<>(updateUniversity,HttpStatus.OK);
    }

    @GetMapping("/{universityId}")
    public ResponseEntity<UniversityDto> getSingle(@PathVariable int universityId){
        UniversityDto universityDto = universityService.singleUniversity(universityId);
        return new ResponseEntity<>(universityDto,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<PaegableResponse<UniversityDto>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "20",required = false) int pageSize,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy
    ){
        PaegableResponse<UniversityDto> university = universityService.allUniversity(pageNumber, pageSize, sortBy, sortDir);
        return  new ResponseEntity<>(university,HttpStatus.OK);
    }


}
