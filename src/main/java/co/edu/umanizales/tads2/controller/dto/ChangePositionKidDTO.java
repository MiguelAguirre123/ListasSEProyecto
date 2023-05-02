package co.edu.umanizales.tads2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePositionKidDTO {
    private String identification;
    private String name;
    private byte age;
    private char gender;
    private String codeLocation;
    private byte position;
}
