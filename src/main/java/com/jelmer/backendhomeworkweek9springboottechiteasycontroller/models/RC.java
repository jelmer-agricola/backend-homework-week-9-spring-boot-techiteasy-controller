package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "remotecontroller")
@Entity
public class RC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @OneToOne(mappedBy = "rc")
//    Deze misschien niet nodig houd evt info tegen.
    @JsonIgnore
    Television television;


}