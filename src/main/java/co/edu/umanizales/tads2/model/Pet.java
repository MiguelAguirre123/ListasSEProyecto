package co.edu.umanizales.tads2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private String identification;
    private String name;
    private byte age;
    private char gender;
    private String typePet;
    private String weight;
    private String height;
    private Location location;
}
