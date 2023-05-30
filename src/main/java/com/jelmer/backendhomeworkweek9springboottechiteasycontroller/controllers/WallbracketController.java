package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.WallbracketInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.WallbracketOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service.WallbracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wallbrackets")
public class WallbracketController {
    private final WallbracketService wallbracketService;

    public WallbracketController (WallbracketService wallbracketService) {
        this.wallbracketService = wallbracketService;
    }

    @GetMapping()
    public ResponseEntity<List<WallbracketOutputDto>> getAllWallbrackets() {
        List<WallbracketOutputDto> wallBracketDto = wallbracketService.getAllWallbrackets();
        return ResponseEntity.ok(wallBracketDto);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<WallbracketOutputDto> getWallBracketById(@PathVariable Long id) {
        WallbracketOutputDto wallBracketDto = wallbracketService.getWallbracketById(id);
        return ResponseEntity.ok(wallBracketDto);
    }

    @PostMapping()
    public ResponseEntity<WallbracketOutputDto> createWallBracket (@RequestBody WallbracketInputDto wallbracketInputDto) {
        WallbracketOutputDto wallbracketOutputDto = wallbracketService.createWallbracket(wallbracketInputDto);
        return ResponseEntity.ok(wallbracketOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeWallBracket(@PathVariable Long id) throws RecordNotFoundException {
        wallbracketService.deleteWallbracket(id);
        return ResponseEntity.ok().body("Element is deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable Long id, @RequestBody WallbracketInputDto wallbracketInputDto, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        try{
            WallbracketOutputDto updatedWallbracket = wallbracketService.updateWallBracket(id, wallbracketInputDto);
            return ResponseEntity.accepted().body(updatedWallbracket);
        }catch (RecordNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

}
