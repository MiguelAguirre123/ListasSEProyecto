package co.edu.umanizales.tads2.config;

import co.edu.umanizales.tads2.controller.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyNullPointerException extends NullPointerException {
    private List<ErrorDTO> elements;
}
