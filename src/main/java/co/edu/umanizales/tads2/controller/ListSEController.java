package co.edu.umanizales.tads2.controller;

import co.edu.umanizales.tads2.controller.dto.ChangePositionKidDTO;
import co.edu.umanizales.tads2.controller.dto.MovePositionKidDTO;
import co.edu.umanizales.tads2.controller.dto.ResponseDTO;
import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKidsList(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addKidList(@RequestBody Kid kid){
        listSEService.addKid(kid);
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño agregado",null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStartKidList(@RequestBody Kid kid){
        listSEService.addToStartKid(kid);
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño agregado al inicio",null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoxposition")
    public ResponseEntity<ResponseDTO> addToXPositionKidList(@RequestBody ChangePositionKidDTO changePositionKidDTO){
        listSEService.addToXPositionKid(
                changePositionKidDTO.getKid(), changePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño agregado en la posicion especificada",null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyidentification/{code}")
    public ResponseEntity<ResponseDTO> deleteKidByIdentificationList(@PathVariable String code){
        if(listSEService.compareId(code) == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño con esa ID", null), HttpStatus.BAD_REQUEST);
        }
        listSEService.deletebyIdentification(code);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Niño Eliminado", null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteKidByAgeList(@PathVariable byte age){
        if(listSEService.compareAge(age) == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño con esa edad", null), HttpStatus.BAD_REQUEST);
        }
        listSEService.deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Niños que tienen " + age + " años eliminados", null), HttpStatus.OK);
    }

    @GetMapping(path = "/movekidbyposition")
    public ResponseEntity<ResponseDTO> moveKidByPositionList(@RequestBody MovePositionKidDTO movePositionKidDTO){
        if(listSEService.compareId(movePositionKidDTO.getCode()) == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño con esa ID", null), HttpStatus.BAD_REQUEST);
        }
        listSEService.moveByPosition(
                movePositionKidDTO.getCode(), movePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño movido en la posicion especificada",null), HttpStatus.OK);
    }

}
