package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
@Entity
@Table (name= "televisions")
public class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Dit is de owner kan van de relatie. Er staat een foreign key in de database
    @OneToOne
    @JsonIgnore
    private RC rc;

    @OneToMany(mappedBy = "television")
    private List<CIModule> ciModule;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "television_wallbracket",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "wallbracket_id")
    )
    private List<Wallbracket> wallbrackets;



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
