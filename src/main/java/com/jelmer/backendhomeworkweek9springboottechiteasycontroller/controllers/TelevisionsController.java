package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tv") // Set base URL path

public class TelevisionsController {
    private List<String> allTV = new ArrayList<>();
    private int id;

    @PostMapping("/add") // Use plural noun and avoid verb in URI
    public void addTV(@RequestParam String name) { // Use better parameter name
        allTV.add(name);
    }

    @GetMapping("/all") // Use plural noun and avoid verb in URI
    public List<String> getAllTV() {
        return this.allTV;
    }
//    @GetMapping("/{id}") // Use singular noun and resource ID in URI
//    public String getTV(@PathVariable int id) {
//        if (id >= 0 && id < allTV.size()) {
//            return this.allTV.get(id);
//        } else {
//            throw new IndexOutOfBoundsException();
//        }
//    }

//    ZIE HIERONDER HOE JE DOOR TE BEGINNEN MET FOUT DE ELSE STATEMENT ACHTERWEGE KAN LATEN.

    @GetMapping("/{id}") // Use singular noun and resource ID in URI
    public ResponseEntity<String> getTV(@PathVariable int id) throws IndexOutOfBoundsException {
        if (id < 0 ||  id >= allTV.size()) {
            throw new IndexOutOfBoundsException("Id " + id + " does not exist");
        }
        return new ResponseEntity<>(allTV.get(id), HttpStatus.OK);

    }

    @PutMapping("/{id}") // Use singular noun and resource ID in URI
    public ResponseEntity<String> updateTV(@PathVariable int id, @RequestBody String newName) { // Use better parameter name
        if (id >= 0 && id < allTV.size() && newName != null) {
            allTV.set(id, newName);
            return ResponseEntity.ok("Name updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{name}") // Use singular noun and resource name in URI
    public ResponseEntity<String> deleteTV(@PathVariable String name) {
        if (allTV.contains(name)) {
            allTV.remove(name);
            return ResponseEntity.ok("TV item " + name + " deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
