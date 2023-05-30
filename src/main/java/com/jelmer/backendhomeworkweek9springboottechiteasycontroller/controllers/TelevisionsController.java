package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.TelevisionInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.WallbracketInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.TelevisionOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tv") // Set base URL path

public class TelevisionsController {
//    @Autowired
    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;

    }

    @GetMapping
    public ResponseEntity<List<TelevisionOutputDto>>getAllTVs(){
        return ResponseEntity.ok().body(televisionService.getAllTVs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> getTelevisionById(@PathVariable Long id) {
        return new ResponseEntity<>(televisionService.getTelevisionById(id), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        TelevisionOutputDto televisionOutputDto = televisionService.addTelevision(televisionInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + televisionOutputDto).toUriString());
        return ResponseEntity.created(uri).body(televisionOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return ResponseEntity.badRequest().body(errorToStringHandling(bindingResult));
        }
        return new ResponseEntity<>(televisionService.updateTelevision(id, televisionInputDto), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        String message = televisionService.deleteTelevision(id);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/{id}/rc/{rc_id}")
    public ResponseEntity<TelevisionOutputDto> assignRemoteToTelevision(@PathVariable Long id, @PathVariable Long rc_id) {
        return ResponseEntity.ok(televisionService.assignRemoteToTelevision(id, rc_id));
    }

    @PutMapping("/{id}/wallbracket/{wallbracket_id}")
    public ResponseEntity<String> assignWallbracketToTelevision(@PathVariable Long id, @PathVariable Long wallbracket_id  ){
        return ResponseEntity.ok(televisionService.assignWallbracketToTelevision(id, wallbracket_id));
    }


    public String errorToStringHandling (BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : bindingResult.getFieldErrors()){
            sb.append(fe.getField() + ": ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return sb.toString();
    }



}
