package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name= "wallbracket")

public class Wallbracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;



    @ManyToMany(mappedBy = "wallbrackets")
    private List<Television> televisions;
}

