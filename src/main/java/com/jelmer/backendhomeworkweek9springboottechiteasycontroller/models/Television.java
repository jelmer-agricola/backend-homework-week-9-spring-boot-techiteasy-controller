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

    public String name;

    public String type;

    public String brand;

    public double price;

    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;


}
