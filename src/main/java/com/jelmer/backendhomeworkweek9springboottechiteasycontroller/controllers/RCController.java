package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.RCInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.RCOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service.RCService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rc")
public class RCController {
    private final RCService rcService;

    public RCController(RCService rcService) {
        this.rcService = rcService;
    }


    @GetMapping()
    public ResponseEntity<List<RCOutputDto>> getAllremoteControllers() {
        List<RCOutputDto> remoteControllerDto = rcService.getAllRCs();
        return ResponseEntity.ok(remoteControllerDto);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<RCOutputDto> getRemoteControllerById(@PathVariable Long id) {
        RCOutputDto remoteControllerDto = rcService.getRCById(id);
        return ResponseEntity.ok(remoteControllerDto);
    }
    @PostMapping("/add")
    public ResponseEntity<RCOutputDto> createRemoteController (@RequestBody RCInputDto rcInputDto) {
        RCOutputDto rcOutputDto = rcService.createRC(rcInputDto);
        return ResponseEntity.ok(rcOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeRemoteController (@PathVariable Long id) {
        boolean isDeleted = rcService.deleteRC(id);
        if(isDeleted) {
            return ResponseEntity.ok().body("Element is deleted");
        } else {
            throw new RecordNotFoundException("No element found with this ID code");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RCOutputDto> updateRC(@PathVariable("id") Long id, @RequestBody RCOutputDto rcOutputDto) {
        rcService.updateRC(id, rcOutputDto);
        return ResponseEntity.ok(rcOutputDto);
    }
}
