package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service;


import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.InputDto.CIModuleInputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto.CIModuleOutputDto;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions.RecordNotFoundException;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.CIModule;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CIModuleService {
    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }


    public List<CIModuleOutputDto> getAllCIModules() {
        List<CIModule> ciModules = ciModuleRepository.findAll();
        List<CIModuleOutputDto>  ciModulesOutputDtos = new ArrayList<>();
        for (CIModule ciModule: ciModules) {

            ciModulesOutputDtos.add(transferCIModuleToModelOutputDto(ciModule));
        }
        return ciModulesOutputDtos;
    }


    public CIModuleOutputDto getCIModule(long id) {
        CIModule ciModule = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No ci-module found"));
        return transferCIModuleToModelOutputDto(ciModule);
    }

    public CIModuleOutputDto createCIModule (CIModuleInputDto ciModuleInputDto) {
        CIModule ciModule = transferInputDtoToCIModuleModel(ciModuleInputDto);
        ciModuleRepository.save(ciModule);
        CIModuleOutputDto ciModuleOutputDto = transferCIModuleToModelOutputDto(ciModule);
        return transferCIModuleToModelOutputDto(ciModule);
    }

    public void deleteCIModule(Long id) {
        if (!ciModuleRepository.existsById(id)) {
            throw new RecordNotFoundException("No ci-module found");
        }
        ciModuleRepository.deleteById(id);
    }

    public CIModuleOutputDto updateCIModule(Long id, CIModuleInputDto ciModuleInputDto) throws RecordNotFoundException {
        CIModule ciModule = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("CIModule with id " + id + " doesn't exist"));

        if (ciModuleInputDto.getType() != null) {
            ciModule.setType(ciModuleInputDto.getType());
        }
        if (ciModuleInputDto.getName() != null) {
            ciModule.setName(ciModuleInputDto.getName());
        }
        if (ciModuleInputDto.getPrice() != null) {
            ciModule.setPrice(ciModuleInputDto.getPrice());
        }

        ciModuleRepository.save(ciModule);

        return transferCIModuleToModelOutputDto(ciModule);
    }


    public CIModule transferInputDtoToCIModuleModel(CIModuleInputDto ciModuleInputDto){
        CIModule ciModule = new CIModule();

        ciModule.setId(ciModuleInputDto.getId());
        ciModule.setName(ciModuleInputDto.getName());
        ciModule.setType(ciModuleInputDto.getType());
        ciModule.setPrice(ciModuleInputDto.getPrice());

        return ciModule;
    }
    public CIModuleOutputDto transferCIModuleToModelOutputDto(CIModule ciModule) {
        CIModuleOutputDto ciModuleOutputDto = new CIModuleOutputDto();

        ciModuleOutputDto.id = ciModule.getId();
        ciModuleOutputDto.type = ciModule.getType();
        ciModuleOutputDto.name = ciModule.getName();
        ciModuleOutputDto.price = ciModule.getPrice();

        return ciModuleOutputDto;
    }


}



