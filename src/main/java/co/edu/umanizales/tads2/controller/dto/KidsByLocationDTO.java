package co.edu.umanizales.tads2.controller.dto;

import co.edu.umanizales.tads2.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidsByLocationDTO {
    private Location location;
    private int quantity;
}
