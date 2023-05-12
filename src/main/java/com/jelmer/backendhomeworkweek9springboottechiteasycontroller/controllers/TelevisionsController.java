package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.TelevisionDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.TelevisionNameTooLongException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Television;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.TelevisionRepository;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tv") // Set base URL path

public class TelevisionsController {
    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;

    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>>getAllTVs(){
        return ResponseEntity.ok().body(televisionService.getAllTVs());
    }


    @PostMapping("/add")
    public ResponseEntity<String> addTelevision(@RequestBody TelevisionDto televisiondto) {

        Television television = televisionService.addTelevision(televisiondto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(television.getId()).toUri();

        return ResponseEntity.created(location).body("television created" );
//
    }
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id")Long id) {

        TelevisionDto television = televisionService.getTelevisionById(id);

        return ResponseEntity.ok().body(television);

    }


//    @PutMapping("/{id}") // alleen voor naam aanpassen werkt dit
//    public ResponseEntity<Television> updateTV(@PathVariable Long id, @RequestBody Television updatedTelevision) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//        if (optionalTelevision.isEmpty()) {
//            throw new RecordNotFoundException("No television found with id: " + id);
//        } else {
//            Television television = optionalTelevision.get();
//            television.setName(updatedTelevision.getName());
//            television.setType(updatedTelevision.getType());
//            televisionRepository.save(television);
//            return ResponseEntity.ok().body(television);
//        }
//    }
//
//    @DeleteMapping("/{id}") // Use singular noun and resource ID in URI
//    public ResponseEntity<String> deleteTV(@PathVariable Long id) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//        if (optionalTelevision.isEmpty()) {
//            throw new RecordNotFoundException("No television found with id: " + id);
//        } else {
//            Television television = optionalTelevision.get();
//            televisionRepository.delete(television);
//            return ResponseEntity.ok("TV item " + television.getName() + " deleted");
//        }
//    }
//


}
