package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto;


import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.RC;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models.Television;

public class TelevisionOutputDto {

    public Long id;
    public String type;
    public String brand;
    public String name;
    public double price;
    public double availableSize;
    public double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Boolean wifi;
    public int originalStock;
    public int sold;

    public RC rc;
    private CIModuleOutputDto ciModuleOutputDto;
    private WallbracketOutputDto wallbracketOutputDto;


}