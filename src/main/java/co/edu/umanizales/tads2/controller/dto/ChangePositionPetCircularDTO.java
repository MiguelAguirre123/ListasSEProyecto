package co.edu.umanizales.tads2.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePositionPetCircularDTO {
    @NotEmpty(message = "La identificacion no puede estar vacia")
    private String identification;
    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(max = 30, message = "El nombre no puede ser mayor a 30 caracteres")
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
    @Size(min = 8, max = 8, message = "El codigo es invalido")
    private String codeLocation;
    @NotNull(message = "El estado de baño de la mascota no puede estar vacio")
    private boolean stateBathePet;
    @Positive(message = "La posicion debe ser mayor a 0")
    private byte position;
    @Pattern(regexp = "[LR]", message = "La direccion debe ser L(left) o R(Right)")
    @Size(min = 1, max = 1, message = "La direccion debe ser solo una letra")
    private String direction;
}