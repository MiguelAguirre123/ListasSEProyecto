package co.edu.umanizales.tads2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePositionKidDTO {
    private KidDTO kidDTO;
    private byte position;
}
