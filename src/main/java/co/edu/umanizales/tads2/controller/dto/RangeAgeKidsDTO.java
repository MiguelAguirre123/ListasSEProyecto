package co.edu.umanizales.tads2.controller.dto;

import co.edu.umanizales.tads2.model.RangeAge;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RangeAgeKidsDTO {
    private RangeAge range;
    int quantity;

}
