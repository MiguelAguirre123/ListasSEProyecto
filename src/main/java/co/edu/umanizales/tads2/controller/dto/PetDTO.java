package co.edu.umanizales.tads2.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetDTO {
    @NotEmpty(message = "Identificacion no puede estar vacio")
    private String identification;
    @NotEmpty(message = "Nombre no puede estar vacio")
    private String name;
    @Pattern(regexp = "[MF]", message = "El género debe ser M o F")
    @Size(min = 1, max = 1, message = "El género debe ser solo una letra")
    private String gender;
    @Positive(message = "La edad debe ser mayor a 0")
    @Max(value = 14, message = "La edad debe ser menor que 15")
    private byte age;
    @NotEmpty(message = "El tipo de mascota no puede estar vacio")
    private String typePet;
    @NotEmpty(message = "El peso no puede estar vacio")
    private String weight;
    @NotEmpty(message = "La estatura no puede estar vacio")
    private String height;
    @Size(min = 8, max = 8, message = "Codigo invalido")
    private String codeLocation;
}