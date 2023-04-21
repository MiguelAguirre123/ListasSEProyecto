package co.edu.umanizales.tads2.controller;

import co.edu.umanizales.tads2.controller.dto.*;
import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.model.Location;
import co.edu.umanizales.tads2.service.ListSEService;
import co.edu.umanizales.tads2.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKidsList(){
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addKidList(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(listSEService.compareId(kidDTO.getIdentification()) != null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya hay ningun niño con esa ID", null), HttpStatus.OK);
        }
        listSEService.addKid(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño Agregado",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStartKidList(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(listSEService.compareId(kidDTO.getIdentification()) != null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya hay ningun niño con esa ID", null), HttpStatus.OK);
        }
        listSEService.addToStartKid(
                new Kid(kidDTO.getIdentification(),
                kidDTO.getName(), kidDTO.getAge(),
                kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño agregado al inicio",null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoxposition")
    public ResponseEntity<ResponseDTO> addToXPositionKidList(@RequestBody ChangePositionKidDTO changePositionKidDTO){
        Location location = locationService.getLocationByCode(changePositionKidDTO.getKidDTO().getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        if(listSEService.compareId(changePositionKidDTO.getKidDTO().getIdentification()) != null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "Ya hay ningun niño con esa ID", null), HttpStatus.OK);
        }
        listSEService.addToXPositionKid(
                new Kid(changePositionKidDTO.getKidDTO().getIdentification(),
                        changePositionKidDTO.getKidDTO().getName(), changePositionKidDTO.getKidDTO().getAge(),
                        changePositionKidDTO.getKidDTO().getGender(), location), changePositionKidDTO.getPosition());
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

    @GetMapping(path = "/kidslocationcityinform")
    public ResponseEntity<ResponseDTO> getKidsLocationCityInform(){
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        List<Location> locationsCity = locationService.getLocationsByCodeSize(8);
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getLocationInform(locationsCity, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidslocationdepartmentsinform")
    public ResponseEntity<ResponseDTO> getKidsLocationDepartmentsInform(){
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        List<Location> locationsDepartments = locationService.getLocationsByCodeSize(5);
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getLocationInform(locationsDepartments, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidslocationcountriesinform")
    public ResponseEntity<ResponseDTO> getKidsLocationCountriesInform(){
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        List<Location> locationsCountries = locationService.getLocationsByCodeSize(3);
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getLocationInform(locationsCountries, (byte) -1),null), HttpStatus.OK);
    }
    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        listSEService.invertKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        listSEService.changeExtremesKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/orderboystostart")
    public ResponseEntity<ResponseDTO> orderBoysToStart() {
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        listSEService.orderBoysToStartKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han puesto los niños masculinos al inicio",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/getaverage")
    public ResponseEntity<ResponseDTO> getAverage() {
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,"El promedio de edad de los niños es: " + listSEService.getAverageKids(),
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/getinformkidsbylocationandage/{age}")
    public ResponseEntity<ResponseDTO> getInformKidsByLocation(@PathVariable byte age) {
        if(listSEService.getKids() == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No hay ningun niño agregado", null), HttpStatus.OK);
        }

        List<Location> locationsCity = locationService.getLocationsByCodeSize(8);
        List<InformLocationAgeTotalDTO> informLocationAgeTotalDTOList = new ArrayList<>();

        for (KidsByLocationDTO kidsByLocationDTO:listSEService.getLocationInform(locationsCity, age)) {
            InformLocationAgeTotalDTO informLocationAgeTotalDTO = new InformLocationAgeTotalDTO(kidsByLocationDTO.getLocation().getName(), listSEService.countKidByLocationAndAgeList(age, kidsByLocationDTO.getLocation().getCode()), kidsByLocationDTO.getQuantity());
            informLocationAgeTotalDTOList.add(informLocationAgeTotalDTO);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,informLocationAgeTotalDTOList,
                null), HttpStatus.OK);
    }
}
