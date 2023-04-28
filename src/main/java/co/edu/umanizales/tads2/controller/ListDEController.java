package co.edu.umanizales.tads2.controller;

import co.edu.umanizales.tads2.controller.dto.ResponseDTO;
import co.edu.umanizales.tads2.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPetsList(){
        if(listDEService.getPets() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets(),null), HttpStatus.OK);
    }
}
