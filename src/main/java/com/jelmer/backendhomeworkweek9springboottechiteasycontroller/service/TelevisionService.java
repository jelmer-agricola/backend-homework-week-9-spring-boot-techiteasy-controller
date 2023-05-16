package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.TelevisionDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.TelevisionNameTooLongException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Television;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

            televisionDtos.add(transferTelevisionModelToOutputDto(television));
        }
        return televisionDtos;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> televisionOptional = televisionRepository.findById(id);
        if (televisionOptional.isPresent()) {
            Television television = televisionOptional.get();
            return transferTelevisionModelToOutputDto(television);
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



    public TelevisionDto updateTelevision(Long id, TelevisionDto televisionInputDto)  throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        // als de variabelen niet ingevuld worden in client side, dan worden deze variabelen gevuld met de volgende waardes: 0, 0.0, null, of false (=default boolean) (afhankelijk van type variabele). Daarom eerst checken of de variabelen wel meegegeven worden in de body.
        //Bij de inputDto wordt momenteel een aantal variabelen gecheckt op notBank etc, maar sommige niet. Die kunnen voor null vervangen worden zonder onderstaande checks.
        if (televisionInputDto.type != null) {
            television.setType(televisionInputDto.type);
        }
        if (televisionInputDto.brand != null) {
            television.setBrand(televisionInputDto.brand);
        }
        if (televisionInputDto.name != null) {
            television.setName(televisionInputDto.name);
        }
        if (televisionInputDto.price != 0.0) {
            television.setPrice(televisionInputDto.price);
        }
        if (televisionInputDto.availableSize != 0.0) {
            television.setAvailableSize(televisionInputDto.availableSize);
        }
        if (televisionInputDto.refreshRate != 0.0) {
            television.setRefreshRate(televisionInputDto.refreshRate);
        }
        if (televisionInputDto.screenType != null) {
            television.setScreenType(televisionInputDto.screenType);
        }
        if (televisionInputDto.screenQuality != null) {
            television.setScreenQuality(televisionInputDto.screenQuality);
        }
        // Door in de klasse niet boolean met kleine letter (=primitieve variabele die alleen true of false kan teruggeven), maar Boolean met hoofdletter (die kan ook null zijn) te gebruiken, kan je checken of die in de body zit.
        if (televisionInputDto.smartTv != null) {
            television.setSmartTv(televisionInputDto.smartTv);
        }
        if (televisionInputDto.voiceControl != null) {
            television.setVoiceControl(televisionInputDto.voiceControl);
        }
        if (televisionInputDto.hdr != null) {
            television.setHdr(televisionInputDto.hdr);
        }
        if (televisionInputDto.bluetooth != null) {
            television.setBluetooth(televisionInputDto.bluetooth);
        }
        if (televisionInputDto.ambiLight != null) {
            television.setAmbiLight(televisionInputDto.ambiLight);
        }
        if (televisionInputDto.originalStock != 0) {
            television.setOriginalStock(televisionInputDto.originalStock);
        }
        if (televisionInputDto.sold != 0) {
            television.setSold(televisionInputDto.sold);
        }
        televisionRepository.save(television);

        return transferTelevisionModelToOutputDto(television);
    }

    public String deleteTelevision (Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));

        televisionRepository.delete(television);

        return "Television successfully removed";
    }




    public TelevisionDto transferTelevisionModelToOutputDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.id = television.getId();
        televisionDto.name = television.getName();
        televisionDto.brand = television.getBrand();
        televisionDto.type =television.getType();
        televisionDto.price = television.getPrice();
        televisionDto.availableSize = television.getAvailableSize();
        televisionDto.refreshRate = television.getRefreshRate();
        televisionDto.screenType = television.getScreenType();
        televisionDto.screenQuality =television.getScreenQuality();
        televisionDto.smartTv = television.getSmartTv();
        televisionDto.wifi = television.getWifi();
        televisionDto.voiceControl = television.getVoiceControl();
        televisionDto.hdr = television.getHdr();
        televisionDto.bluetooth = television.getBluetooth();
        televisionDto.ambiLight = television.getAmbiLight();
        televisionDto.originalStock = television.getOriginalStock();
        televisionDto.sold = television.getSold();



        return televisionDto;
    }

    //voor put/post request   input naar data base
    public Television transferDtoToTelevision(TelevisionDto televisionDto) {
        Television television = new Television();

        television.name = televisionDto.name;
        television.brand = televisionDto.brand;
        television.type =televisionDto.type;
        television.price =televisionDto.price;
        television.availableSize =televisionDto.availableSize;
        television.refreshRate =televisionDto.refreshRate;
        television.screenQuality =televisionDto.screenQuality;
        television.screenType =televisionDto.screenType;
        television.smartTv =televisionDto.smartTv;
        television.wifi =televisionDto.wifi;
        television.voiceControl =televisionDto.voiceControl;
        television.setHdr(televisionDto.getHdr());
        television.setBluetooth(televisionDto.getBluetooth());
        television.setAmbiLight(televisionDto.getAmbiLight());
        television.setOriginalStock(televisionDto.getOriginalStock());
        television.setSold(televisionDto.getSold());


        return television;

    }


}
