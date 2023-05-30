package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.WallbracketInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.WallbracketOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Wallbracket;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.TelevisionRepository;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.WallbracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class WallbracketService {

    private final WallbracketRepository wallbracketRepository;
    private final TelevisionRepository televisionRepository;

    public WallbracketService(WallbracketRepository wallbracketRepository, TelevisionRepository televisionRepository){
        this.wallbracketRepository = wallbracketRepository;
        this.televisionRepository = televisionRepository;
    }

    public List<WallbracketOutputDto> getAllWallbrackets() {
        List<Wallbracket> wallbrackets = wallbracketRepository.findAll();
        List<WallbracketOutputDto> wallbracketOutputDtos = new ArrayList<>();
        for (Wallbracket wallbracket: wallbrackets) {
            wallbracketOutputDtos.add(transferWallbracketToOutputDto(wallbracket));
        }
        return wallbracketOutputDtos;
    }



   public WallbracketOutputDto getWallbracketById (long id){
        Wallbracket wallbracket = wallbracketRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("No Wallbracket with id " +  id ));
        return transferWallbracketToOutputDto(wallbracket);
   }


   public WallbracketOutputDto createWallbracket (WallbracketInputDto wallbracketInputDto){
        Wallbracket wallbracket = transferInputDtoToWallbracket(wallbracketInputDto);
        wallbracketRepository.save(wallbracket);
        WallbracketOutputDto wallbracketOutputDto = transferWallbracketToOutputDto(wallbracket);
        return transferWallbracketToOutputDto(wallbracket);
   }

    public void deleteWallbracket(Long id) throws RecordNotFoundException {
        if (!wallbracketRepository.existsById(id)) {
            throw new RecordNotFoundException("No wallbracket found with id " + id);
        }
        wallbracketRepository.deleteById(id);
    }

    public WallbracketOutputDto updateWallBracket(Long id, WallbracketInputDto wallbracketInputDto) throws RecordNotFoundException {
        Wallbracket wallbracket = wallbracketRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Wallbracket with id " + id + " doesn't exist"));
        if (wallbracketInputDto.getSize() != null) {
            wallbracket.setSize(wallbracketInputDto.getSize());
        }
        if (wallbracketInputDto.getAdjustable() != null) {
            wallbracket.setAdjustable(wallbracketInputDto.getAdjustable());
        }
        if (wallbracketInputDto.getName() != null) {
            wallbracket.setName(wallbracketInputDto.getName());
        }
        if (wallbracketInputDto.getPrice() != null) {
            wallbracket.setPrice(wallbracketInputDto.getPrice());
        }

        wallbracketRepository.save(wallbracket);

        return transferWallbracketToOutputDto(wallbracket);
    }

    public Wallbracket transferInputDtoToWallbracket(WallbracketInputDto wallbracketInputDto){
        Wallbracket wallbracket = new Wallbracket();

        wallbracket.setId(wallbracketInputDto.getId());
        wallbracket.setName(wallbracketInputDto.getName());
        wallbracket.setPrice(wallbracketInputDto.getPrice());
        wallbracket.setSize(wallbracketInputDto.getSize());
        wallbracket.setAdjustable(wallbracketInputDto.getAdjustable());

        return wallbracket;
    }
    public WallbracketOutputDto transferWallbracketToOutputDto(Wallbracket wallbracket) {
        WallbracketOutputDto wallbracketOutputDto = new WallbracketOutputDto();

        wallbracketOutputDto.id = wallbracket.getId();
        wallbracketOutputDto.size = wallbracket.getSize();
        wallbracketOutputDto.name = wallbracket.getName();
        wallbracketOutputDto.price = wallbracket.getPrice();
        wallbracketOutputDto.adjustable = wallbracket.getAdjustable();

        return wallbracketOutputDto;
    }


}
