package co.edu.umanizales.tads2.controller.dto;

import co.edu.umanizales.tads2.model.Kid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePositionKidDTO {
    private Kid kid;
    private byte position;
}
