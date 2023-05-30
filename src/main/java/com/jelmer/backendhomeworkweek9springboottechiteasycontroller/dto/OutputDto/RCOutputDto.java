package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.dto.OutputDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RCOutputDto {


    public Long id;
    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;

    public RCOutputDto() {
        this.id = id;
        this.compatibleWith = compatibleWith;
        this.batteryType = batteryType;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.originalStock = originalStock;
    }


}