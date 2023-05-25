package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service;

import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.RCInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.RCOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.RC;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.RCRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RCService {


    private final RCRepository rcRepository;

    public RCService(RCRepository rcRepository) {
        this.rcRepository = rcRepository;
    }

    public List<RCOutputDto> getAllRCs() {
        List<RC> rcs = rcRepository.findAll();
        List<RCOutputDto> rcOutputDtos = new ArrayList<>();

        for (RC rc : rcs) {
            rcOutputDtos.add(transferRCToOutputDto(rc));
        }
        return rcOutputDtos;
    }

    public RCOutputDto getRCById(Long id) {
        RC rc = rcRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("RC with id " + id + " doesn't exist"));

        return transferRCToOutputDto(rc);
    }

    public RCOutputDto createRC(RCInputDto rcInputDto) {
        RC rc = transferInputDtoToRCModel(rcInputDto);
        rcRepository.save(rc);
        RCOutputDto rcOutputDto = transferRCToOutputDto(rc);
        return transferRCToOutputDto(rc);
    }


    public boolean deleteRC(Long id) {
        if (rcRepository.existsById(id)) {
            rcRepository.deleteById(id);
            return true;
        } else {
            throw new RecordNotFoundException("RC with id " + id + " doesn't exist");
        }
    }
    public void updateRC(Long id, RCOutputDto rcOutputDto) {
        if(!rcRepository.existsById(id)) {
            throw new RecordNotFoundException("No remotecontroller found");
        }
        RC rc = rcRepository.findById(id).orElse(null);
        rc.setId(rcOutputDto.getId());
        rc.setCompatibleWith(rcOutputDto.getCompatibleWith());
        rc.setBatteryType(rcOutputDto.getBatteryType());
        rc.setName(rcOutputDto.getName());
        rc.setPrice(rcOutputDto.getPrice());
        rc.setBrand(rcOutputDto.getBrand());
        rc.setOriginalStock(rcOutputDto.getOriginalStock());
        rcRepository.save(rc);
    }


    public RC transferInputDtoToRCModel(RCInputDto rcInputDto) {
        RC rc = new RC();

        rc.setId(rcInputDto.getId());
        rc.setCompatibleWith(rcInputDto.getCompatibleWith());
        rc.setBatteryType(rcInputDto.getBatteryType());
        rc.setBrand(rcInputDto.getBrand());
        rc.setOriginalStock(rcInputDto.getOriginalStock());
        rc.setName(rcInputDto.getName());
        rc.setPrice(rcInputDto.getPrice());

        return rc;
    }

    public RCOutputDto transferRCToOutputDto(RC rc) {
        RCOutputDto rcOutputDto = new RCOutputDto();

        rcOutputDto.id  = rc.getId();
        rcOutputDto.compatibleWith = rc.getCompatibleWith();
        rcOutputDto.batteryType = rc.getBatteryType();
        rcOutputDto.name = rc.getName();
        rcOutputDto.brand = rc.getBrand();
        rcOutputDto.price = rc.getPrice();
        rcOutputDto.originalStock = rc.getOriginalStock();


        return rcOutputDto;

    }
}