package co.edu.umanizales.tads2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InformLocationAgeTotalDTO {

    private String city;
    private List<QuantityKidByLocationDTO> genders;
    private int total;
}
