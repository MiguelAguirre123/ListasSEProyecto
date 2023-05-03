package co.edu.umanizales.tads2.controller;

import co.edu.umanizales.tads2.config.MyNullPointerException;
import co.edu.umanizales.tads2.controller.dto.*;
import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.model.Location;
import co.edu.umanizales.tads2.model.RangeAge;
import co.edu.umanizales.tads2.service.ListSEService;
import co.edu.umanizales.tads2.service.LocationService;
import co.edu.umanizales.tads2.service.RangeAgeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RangeAgeService rangeAgeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKidsList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addKidList(@Valid @RequestBody KidDTO kidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        try {
            location.toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listSEService.compareId(kidDTO.getIdentification()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(400, "Ya hay un niño con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.addKid(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender().charAt(0), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño Agregado",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStartKidList(@Valid @RequestBody KidDTO kidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        try {
            location.toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listSEService.compareId(kidDTO.getIdentification()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(400, "Ya hay un niño con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.addToStartKid(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender().charAt(0), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño agregado al inicio",null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoxposition")
    public ResponseEntity<ResponseDTO> addToXPositionKidList(@Valid @RequestBody ChangePositionKidDTO changePositionKidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(changePositionKidDTO.getCodeLocation());
        try {
            location.toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listSEService.compareId(changePositionKidDTO.getIdentification()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(400, "Ya hay un niño con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.addToXPositionKid(
                new Kid(changePositionKidDTO.getIdentification(),
                        changePositionKidDTO.getName(), changePositionKidDTO.getAge(),
                        changePositionKidDTO.getGender().charAt(0), location), changePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño agregado en la posicion especificada",null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyidentification/{code}")
    public ResponseEntity<ResponseDTO> deleteKidByIdentificationList(@PathVariable String code){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listSEService.compareId(code);
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(400, "No hay un niño con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.deleteByIdentification(code);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Niño Eliminado", null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deleteKidByAgeList(@PathVariable byte age){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.compareAge(age).toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(400, "No hay una ningun niño con esa edad"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Niños que tienen " + age + " años eliminados", null), HttpStatus.OK);
    }

    @GetMapping(path = "/goupkidbyposition")
    public ResponseEntity<ResponseDTO> goUpKidByPositionList(@Valid @RequestBody MovePositionKidDTO movePositionKidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listSEService.compareId(movePositionKidDTO.getCode());
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(400, "No hay un niño con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.goUpByPosition(
                movePositionKidDTO.getCode(), movePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño subido en la posicion especificada",null), HttpStatus.OK);
    }

    @GetMapping(path = "/godownkidbyposition")
    public ResponseEntity<ResponseDTO> goDownKidByPositionList(@Valid @RequestBody MovePositionKidDTO movePositionKidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listSEService.compareId(movePositionKidDTO.getCode());
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(400, "No hay un niño con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.goDownByPosition(
                movePositionKidDTO.getCode(), movePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niño bajado en la posicion especificada",null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidslocationcityinform")
    public ResponseEntity<ResponseDTO> getKidsLocationCityInform(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsCity = locationService.getLocationsByCodeSize(8);
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getLocationInform(locationsCity, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidslocationdepartmentsinform")
    public ResponseEntity<ResponseDTO> getKidsLocationDepartmentsInform(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsDepartments = locationService.getLocationsByCodeSize(5);
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getLocationInform(locationsDepartments, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidslocationcountriesinform")
    public ResponseEntity<ResponseDTO> getKidsLocationCountriesInform(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsCountries = locationService.getLocationsByCodeSize(3);
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getLocationInform(locationsCountries, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.invertKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.changeExtremesKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/orderboystostart")
    public ResponseEntity<ResponseDTO> orderBoysToStart() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.orderBoysToStartKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han puesto los niños masculinos al inicio",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/getaverage")
    public ResponseEntity<ResponseDTO> getAverage() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,"El promedio de edad de los niños es: " + listSEService.getAverageKids(),
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/getinformkidsbylocationandage/{age}")
    public ResponseEntity<ResponseDTO> getInformKidsByLocation(@PathVariable byte age) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

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
    @GetMapping(path = "/intercaleboysandgirls")
    public ResponseEntity<ResponseDTO> intercaleBoysAndGirlsList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listSEService.intercaleBoysAndGirlsKids();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Niños Intercalados",null), HttpStatus.OK);
    }

    @GetMapping(path = "/rangebyage")
    public ResponseEntity<ResponseDTO> getRangeByKidsInform() {
        List<RangeAgeKidsDTO>  kidsRangeDTOList = new ArrayList<>();
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listSEService.getKids();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun niño agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        for (RangeAge rangeAge : rangeAgeService.getRanges()) {
            int quantity = listSEService.countKidsByAges(rangeAge.getFrom(), rangeAge.getTo());
            kidsRangeDTOList.add(new RangeAgeKidsDTO(rangeAge, quantity));
        }

        return new ResponseEntity<>(new ResponseDTO(200, kidsRangeDTOList, null), HttpStatus.OK);
    }
}
