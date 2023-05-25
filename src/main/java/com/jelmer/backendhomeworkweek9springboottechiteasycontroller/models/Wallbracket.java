package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    // Dit is de target kant van de relatie. Er staat niks in de database
//    @OneToMany(mappedBy = "wallBracket")
//    @JsonIgnore
//    List<TelevisionWallBracket> televisionWallBrackets;

}
