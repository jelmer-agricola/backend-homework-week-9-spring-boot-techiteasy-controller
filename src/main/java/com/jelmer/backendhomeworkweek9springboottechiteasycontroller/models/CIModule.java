package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name= "cimodules")
public class CIModule {

    @Id
//    voor sql file id genereren
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "tv_id")
    private Television television;


}
