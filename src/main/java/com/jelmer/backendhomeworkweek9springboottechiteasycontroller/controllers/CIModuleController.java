package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import org.springframework.validation.BindingResult;

import org.springframework.validation.FieldError;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.CIModuleInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.CIModuleOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service.CIModuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cimodules")

public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CIModuleOutputDto>> getAllCIModules() {
        List<CIModuleOutputDto> ciModuleOutputDto = ciModuleService.getAllCIModules();
        return ResponseEntity.ok(ciModuleOutputDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CIModuleOutputDto> getCIModule(@PathVariable("id") Long id) {

        CIModuleOutputDto ciModuleOutputDto = ciModuleService.getCIModule(id);

        return ResponseEntity.ok(ciModuleOutputDto);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleOutputDto ciModuleOutputDto = ciModuleService.createCIModule(ciModuleInputDto);
        return ResponseEntity.ok(ciModuleOutputDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("id") Long id) {

        ciModuleService.deleteCIModule(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCIModule(@PathVariable Long id, @Valid @RequestBody CIModuleInputDto ciModuleInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        try {
            CIModuleOutputDto updatedCIModule = ciModuleService.updateCIModule(id, ciModuleInputDto);
            return ResponseEntity.accepted().body(updatedCIModule);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }
}
