package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.TelevisionInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.TelevisionOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.RC;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Television;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Wallbracket;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.RCRepository;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.TelevisionRepository;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.WallbracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RCRepository rcRepository;
    private final WallbracketRepository wallbracketRepository;


    public TelevisionService(TelevisionRepository televisionRepository, RCRepository rcRepository, WallbracketRepository wallbracketRepository) {
        this.televisionRepository = televisionRepository;
        this.rcRepository = rcRepository;
        this.wallbracketRepository = wallbracketRepository;
    }


    public List<TelevisionOutputDto> getAllTVs() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        for (Television television : televisions) {
            //        voor elke tv ook tv dto aanmaken

//        Tv dto toevoegen aan de lijst en die halen we uit transfer methode

            televisionOutputDtos.add(transferTelevisionModelToOutputDto(television));
        }
        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById(Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));
        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        return televisionOutputDto;

    }


    // input dto  want gaat richting database
//    output dto gaat richting client
    public TelevisionOutputDto addTelevision(TelevisionInputDto televisionInputDto) {
        Television television = transferInputDtoToTelevisionModel(televisionInputDto);
        televisionRepository.save((television));
        TelevisionOutputDto televisionOutputDto = transferTelevisionModelToOutputDto(television);
        return televisionOutputDto;
    }




    public TelevisionOutputDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) throws RecordNotFoundException {
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

    public String deleteTelevision(Long id) throws RecordNotFoundException {
        Television television = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television with id " + id + " doesn't exist"));

        televisionRepository.delete(television);

        return "Television successfully removed";
    }

    public TelevisionOutputDto assignRemoteToTelevision(Long id, Long rc_id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RC> optionalRC = rcRepository.findById(rc_id);
        if (optionalTelevision.isEmpty() && optionalRC.isEmpty()) {
            throw new RecordNotFoundException("Remote or television with" + rc_id + " and " + id + "does not exist");
        }
        Television television = optionalTelevision.get();
        RC rc = optionalRC.get();
        television.setRc(rc);
        Television updateTelevision = televisionRepository.save(television);
        return transferTelevisionModelToOutputDto(updateTelevision);
    }


    public String assignWallbracketToTelevision(Long id, Long wallbracket_id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<Wallbracket> optionalWallbracket = wallbracketRepository.findById(wallbracket_id);
        if (optionalTelevision.isEmpty() && optionalWallbracket.isEmpty()) {
            throw new RecordNotFoundException("Wallbracket or television with" + wallbracket_id + " and " + id + "does not exist");
        }
        Television television =optionalTelevision.get();
        Wallbracket wallbracket = optionalWallbracket.get();
        List <Wallbracket> wallbracketList = television.getWallbrackets();
        wallbracketList.add(wallbracket);
        television.setWallbrackets(wallbracketList);
        televisionRepository.save(television);
        return "hoera";

    }

    public TelevisionOutputDto transferTelevisionModelToOutputDto(Television television) {
        TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();

        televisionOutputDto.id = television.getId();
        televisionOutputDto.name = television.getName();
        televisionOutputDto.brand = television.getBrand();
        televisionOutputDto.type = television.getType();
        televisionOutputDto.price = television.getPrice();
        televisionOutputDto.availableSize = television.getAvailableSize();
        televisionOutputDto.refreshRate = television.getRefreshRate();
        televisionOutputDto.screenType = television.getScreenType();
        televisionOutputDto.screenQuality = television.getScreenQuality();
        televisionOutputDto.smartTv = television.getSmartTv();
        televisionOutputDto.wifi = television.getWifi();
        televisionOutputDto.voiceControl = television.getVoiceControl();
        televisionOutputDto.hdr = television.getHdr();
        televisionOutputDto.bluetooth = television.getBluetooth();
        televisionOutputDto.ambiLight = television.getAmbiLight();
        televisionOutputDto.originalStock = television.getOriginalStock();
        televisionOutputDto.sold = television.getSold();
        televisionOutputDto.rc = television.getRc();


        return televisionOutputDto;
    }


    //voor put/post request   input naar data base
    public Television transferInputDtoToTelevisionModel(TelevisionInputDto televisionInputDto) {
        Television television = new Television();

        television.name = televisionInputDto.name;
        television.brand = televisionInputDto.brand;
        television.type = televisionInputDto.type;
        television.price = televisionInputDto.price;
        television.availableSize = televisionInputDto.availableSize;
        television.refreshRate = televisionInputDto.refreshRate;
        television.screenQuality = televisionInputDto.screenQuality;
        television.screenType = televisionInputDto.screenType;
        television.smartTv = televisionInputDto.smartTv;
        television.wifi = televisionInputDto.wifi;
        television.voiceControl = televisionInputDto.voiceControl;
        television.setHdr(televisionInputDto.getHdr());
        television.setBluetooth(televisionInputDto.getBluetooth());
        television.setAmbiLight(televisionInputDto.getAmbiLight());
        television.setOriginalStock(televisionInputDto.getOriginalStock());
        television.setSold(televisionInputDto.getSold());


        return television;

    }


//    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
//        var optionalTelevision = repos.findById(id);
//        var optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);
//
//        if(optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
//            var television = optionalTelevision.get();
//            var remoteController = optionalRemoteController.get();
//
//            television.setRemoteController(remoteController);
//            repos.save(television);
//        } else {
//            throw new RecordNotFoundException();
//        }

}



