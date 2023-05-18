package co.edu.umanizales.tads2.controller;

import co.edu.umanizales.tads2.config.MyNullPointerException;
import co.edu.umanizales.tads2.controller.dto.*;
import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.model.Location;
import co.edu.umanizales.tads2.model.Pet;
import co.edu.umanizales.tads2.model.RangeAge;
import co.edu.umanizales.tads2.service.ListDEService;
import co.edu.umanizales.tads2.service.LocationService;
import co.edu.umanizales.tads2.service.RangeAgeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RangeAgeService rangeAgeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPetsList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPets(),null), HttpStatus.OK);
    }

    @GetMapping(path = "/byprevious")
    public ResponseEntity<ResponseDTO> getPetsByPreviousList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getPetsByPrevious(),null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addPetList(@Valid @RequestBody PetDTO petDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        try {
            location.toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listDEService.compareId(petDTO.getIdentification()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.addPet(new Pet(petDTO.getIdentification(),
                petDTO.getName(), petDTO.getAge(),
                petDTO.getGender().charAt(0), petDTO.getTypePet(),
                petDTO.getWeight(), petDTO.getHeight(), petDTO.isStateBathePet(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Mascota Agregada",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStartKidList(@Valid @RequestBody PetDTO petDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        try {
            location.toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listDEService.compareId(petDTO.getIdentification()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.addToStartPet(
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender().charAt(0), petDTO.getTypePet(),
                        petDTO.getWeight(), petDTO.getHeight(), petDTO.isStateBathePet(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Mascota agregada al inicio",null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoxposition")
    public ResponseEntity<ResponseDTO> addToXPositionPetList(@Valid @RequestBody ChangePositionPetDTO changePositionPetDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(changePositionPetDTO.getCodeLocation());
        try {
            location.toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listDEService.compareId(changePositionPetDTO.getIdentification()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay una mascota con esa ID"));
        }

        try {
            listDEService.compareSizeWithPositionPet(changePositionPetDTO.getPosition()).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "La posicion no corresponde con el tamaño de la lista de mascotas"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.addToXPositionPet(
                new Pet(changePositionPetDTO.getIdentification(),
                        changePositionPetDTO.getName(), changePositionPetDTO.getAge(),
                        changePositionPetDTO.getGender().charAt(0), changePositionPetDTO.getTypePet(),
                        changePositionPetDTO.getWeight(), changePositionPetDTO.getHeight(),
                        changePositionPetDTO.isStateBathePet(), location), changePositionPetDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Mascota agregada en la posicion especificada",null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyidentification/{code}")
    public ResponseEntity<ResponseDTO> deletePetByIdentificationList(@PathVariable String code) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listDEService.compareId(code);
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(404, "No hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.deleteByIdentification(code);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota Eliminada", null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> deletePetByAgeList(@PathVariable byte age){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.compareAge(age).toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay una ninguna mascota con esa edad"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascotas que tienen " + age + " años eliminados", null), HttpStatus.OK);
    }

    @GetMapping(path = "/gouppetbyposition")
    public ResponseEntity<ResponseDTO> goUpPetByPositionList(@Valid @RequestBody MovePositionKidDTO movePositionKidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listDEService.compareId(movePositionKidDTO.getCode());
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(404, "No hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.goUpByPosition(
                movePositionKidDTO.getCode(), movePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Mascota subida en la posicion especificada",null), HttpStatus.OK);
    }

    @GetMapping(path = "/godownpetbyposition")
    public ResponseEntity<ResponseDTO> goDownPetByPositionList(@Valid @RequestBody MovePositionKidDTO movePositionKidDTO){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listDEService.compareId(movePositionKidDTO.getCode());
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(404, "No hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.goDownByPosition(
                movePositionKidDTO.getCode(), movePositionKidDTO.getPosition());
        return new ResponseEntity<>(new ResponseDTO(
                200,"Mascota bajada en la posicion especificada",null), HttpStatus.OK);
    }

    @GetMapping(path = "/petslocationcityinform")
    public ResponseEntity<ResponseDTO> getPetsLocationCityInform(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsCity = locationService.getLocationsByCodeSize(8);
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLocationInform(locationsCity, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping(path = "/petslocationdepartmentsinform")
    public ResponseEntity<ResponseDTO> getPetsLocationDepartmentsInform(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsDepartments = locationService.getLocationsByCodeSize(5);
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLocationInform(locationsDepartments, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping(path = "/petslocationcountriesinform")
    public ResponseEntity<ResponseDTO> getPetsLocationCountriesInform(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsCountries = locationService.getLocationsByCodeSize(3);
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLocationInform(locationsCountries, (byte) -1),null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.invertPets();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.changeExtremesPets();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/orderpetsmalestostart")
    public ResponseEntity<ResponseDTO> orderPetsMalesStart() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.orderPetsMalesToStartList();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han puesto las mascotas macho al inicio",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/getaverage")
    public ResponseEntity<ResponseDTO> getAverage() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,"El promedio de edad de las mascotas es: " + listDEService.getAveragePets(),
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/getinformpetsbylocationandage/{age}")
    public ResponseEntity<ResponseDTO> getInformPetsByLocation(@PathVariable byte age) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        List<Location> locationsCity = locationService.getLocationsByCodeSize(8);
        List<InformLocationAgeTotalDTO> informLocationAgeTotalDTOList = new ArrayList<>();

        for (KidsByLocationDTO petsByLocationDTO : listDEService.getLocationInform(locationsCity, age)) {
            InformLocationAgeTotalDTO informLocationAgeTotalDTO = new InformLocationAgeTotalDTO(petsByLocationDTO.getLocation().getName(), listDEService.countPetByLocationAndAgeList(age, petsByLocationDTO.getLocation().getCode()), petsByLocationDTO.getQuantity());
            informLocationAgeTotalDTOList.add(informLocationAgeTotalDTO);
        }

        return new ResponseEntity<>(new ResponseDTO(
                200,informLocationAgeTotalDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/intercalemalesandfemalespets")
    public ResponseEntity<ResponseDTO> intercaleMaleandFemaleList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.intercaleMalesAndFemalesPets();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Mascotas Intercaladas",null), HttpStatus.OK);
    }

    @GetMapping(path = "/rangebyage")
    public ResponseEntity<ResponseDTO> getRangeByPetsInform() {
        List<RangeAgeKidsDTO>  petsRangeDTOList = new ArrayList<>();
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getPets().size();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        for (RangeAge rangeAge : rangeAgeService.getRanges()) {
            int quantity = listDEService.countPetsByAges(rangeAge.getFrom(), rangeAge.getTo());
            petsRangeDTOList.add(new RangeAgeKidsDTO(rangeAge, quantity));
        }

        return new ResponseEntity<>(new ResponseDTO(200, petsRangeDTOList, null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletebyidentificationv2/{code}")
    public ResponseEntity<ResponseDTO> deletePetByIdentificationListV2(@PathVariable String code) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            Object str = listDEService.compareId(code);
            Integer i = (Integer) str;
        } catch (ClassCastException ex) {
            errorsList.add(new ErrorDTO(404, "No hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.deleteByIdentificationV2(code);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota Eliminada", null), HttpStatus.OK);
    }
}