package co.edu.umanizales.tads2.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovePositionKidDTO {
    @NotEmpty(message = "La identificacion no puede estar vacia")
    private String code;
    @Positive(message = "La posicion debe ser mayor a 0")
    private byte position;
}
