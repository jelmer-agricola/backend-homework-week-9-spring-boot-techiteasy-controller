package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.TelevisionDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.TelevisionNameTooLongException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Television;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    @Autowired
    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    public List<TelevisionDto> getAllTVs() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();
        for (Television television : televisions) {
            //        voor elke tv ook tv dto aanmaken

//        Tv dto toevoegen aan de lijst en die halen we uit transfer methode

            televisionDtos.add(transferTelevisionToDto(television));
        }
        return televisionDtos;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> televisionOptional = televisionRepository.findById(id);
        if (televisionOptional.isPresent()) {
            Television television = televisionOptional.get();
            return transferTelevisionToDto(television);
        } else {
            throw new RecordNotFoundException("Televisie niet gevonden met ID: " + id);
        }
    }




    // input dto  want gaat richting database
//    output dto gaat richting client
    public Television addTelevision(TelevisionDto televisionDto) {
        if (televisionDto.brand.length() > 20) {
            throw new TelevisionNameTooLongException("Mag niet langer dan 20 letters zijn");
        }
        Television television = transferDtoToTelevision(televisionDto);
        television = televisionRepository.save(television);

        return television;



    }



    public TelevisionDto transferTelevisionToDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.id = television.getId();
        televisionDto.name = television.getName();
        televisionDto.brand = television.getBrand();


        return televisionDto;
    }

    //voor put/post request
    public Television transferDtoToTelevision(TelevisionDto televisionDto) {
        Television television = new Television();


        television.name = televisionDto.name;
        television.brand = televisionDto.brand;

        return television;

    }


}
