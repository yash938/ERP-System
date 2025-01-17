package com.Erp_System.controller;

import com.Erp_System.dto.UniversityDto;
import com.Erp_System.dto.ZoneDto;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.response.Utils;
import com.Erp_System.service.UniversityService;
import com.Erp_System.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/zones")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @Autowired
    private UniversityService universityService;

    @PostMapping("/create")
    public ResponseEntity<ZoneDto> createZones(@RequestBody @Valid ZoneDto zoneDto){
        ZoneDto createdZone = zoneService.createZone(zoneDto);
        return new ResponseEntity<>(createdZone, HttpStatus.CREATED);
    }

    @PutMapping("/{zoneId}")
    public ResponseEntity<ZoneDto> updateZone(@PathVariable int zoneId,@RequestBody @Valid ZoneDto zoneDto){
        ZoneDto updateZone = zoneService.updateZone(zoneId, zoneDto);
        return new ResponseEntity<>(updateZone,HttpStatus.OK);
    }

    @GetMapping("/{zoneId}")
    public ResponseEntity<ZoneDto> singleZone(@PathVariable int zoneId){
        ZoneDto singleZone = zoneService.getSingleZone(zoneId);
        return new ResponseEntity<>(singleZone,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<PaegableResponse<ZoneDto>> allZones(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "20",required = false) int pageSize,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy
    ){
        PaegableResponse<ZoneDto> allZone = zoneService.allZones(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allZone,HttpStatus.OK);
    }

    @DeleteMapping("/{zoneId}")
    public ResponseEntity<Utils> deleteZone(@PathVariable int zoneId){
        zoneService.deleteZone(zoneId);
        Utils deletedZone = Utils.builder().status(HttpStatus.OK).message("zone is deleted successfully").date(LocalDate.now()).build();
        return new ResponseEntity<>(deletedZone,HttpStatus.OK);
    }


    @PostMapping("/{zoneId}/university")
    public ResponseEntity<UniversityDto> createWithZones(@PathVariable int zoneId, @RequestBody UniversityDto universityDto){
        UniversityDto withZone = universityService.createWithZone(zoneId, universityDto);
        return new ResponseEntity<>(withZone,HttpStatus.OK);
    }


}
