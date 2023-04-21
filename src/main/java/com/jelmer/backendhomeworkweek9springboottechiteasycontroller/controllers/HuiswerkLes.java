package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HuiswerkLes {

    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions() {
        // ok is  eigenlijk code 200
        return ResponseEntity.ok("All Televisions");
    }

    @GetMapping("/television/{id}")
//    path variable om pad op te zoeken
    public ResponseEntity<Object> getAllTelevision(@PathVariable long id) {
        return ResponseEntity.ok("Television " + id);
    }

    @PostMapping("/addTelevision")
    public ResponseEntity<Object> addTelevision(@RequestBody String television) {
        return ResponseEntity.created(null).body(television);
    }

//    @PutMapping("update")
//    public ResponseEntity<Object> updateTelevision(@PathVariable long id, @RequestBody String newTelevision){
//
//    }
}
