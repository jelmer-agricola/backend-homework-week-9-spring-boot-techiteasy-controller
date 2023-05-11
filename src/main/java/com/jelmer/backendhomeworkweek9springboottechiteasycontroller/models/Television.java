package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name= "televisions")
public class Television {

    @Id
    @GeneratedValue
// in eindopdracht primary key maken van kenteken
    private Long id;

    public String type;
    public String brand;
    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;



        // Een default constructor
    public Television() {}

    // Een constructor met alle gevraagde variable
    public Television(
            Long id,
            String type,
            String brand,
            String name,
            Double price,
            Double availableSize,
            Double refreshRate,
            String screenType,
            String screenQuality,
            Boolean smartTv,
            Boolean wifi,
            Boolean voiceControl,
            Boolean hdr,
            Boolean bluetooth,
            Boolean ambiLight,
            Integer originalStock,
            Integer sold ) {
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSize = availableSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
    }

}
