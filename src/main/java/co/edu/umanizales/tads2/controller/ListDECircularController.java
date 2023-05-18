package co.edu.umanizales.tads2.controller;

import co.edu.umanizales.tads2.config.MyNullPointerException;
import co.edu.umanizales.tads2.controller.dto.*;
import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.model.Location;
import co.edu.umanizales.tads2.model.Pet;
import co.edu.umanizales.tads2.model.RangeAge;
import co.edu.umanizales.tads2.service.ListDECircularService;
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
@RequestMapping(path = "/listdecircular")
public class ListDECircularController {
    @Autowired
    private ListDECircularService listDECircularService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPetsList() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDECircularService.getPets().size();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200, listDECircularService.getPets(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/byprevious")
    public ResponseEntity<ResponseDTO> getPetsByPreviousList() {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDECircularService.getPets().size();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200, listDECircularService.getPetsByPrevious(), null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addPetList(@Valid @RequestBody PetDTO petDTO) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        try {
            location.toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listDECircularService.compareId(petDTO.getIdentification()).toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDECircularService.addPet(new Pet(petDTO.getIdentification(),
                petDTO.getName(), petDTO.getAge(),
                petDTO.getGender().charAt(0), petDTO.getTypePet(),
                petDTO.getWeight(), petDTO.getHeight(), petDTO.isStateBathePet(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota Agregada",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStartKidList(@Valid @RequestBody PetDTO petDTO) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        try {
            location.toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listDECircularService.compareId(petDTO.getIdentification()).toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDECircularService.addToStartPet(
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender().charAt(0), petDTO.getTypePet(),
                        petDTO.getWeight(), petDTO.getHeight(), petDTO.isStateBathePet(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota agregada al inicio", null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtoxposition")
    public ResponseEntity<ResponseDTO> addToXPositionPetList(@Valid @RequestBody ChangePositionPetCircularDTO changePositionPetCircularDTO) {
        List<ErrorDTO> errorsList = new ArrayList<>();
        Location location = locationService.getLocationByCode(changePositionPetCircularDTO.getCodeLocation());
        try {
            location.toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "La ubicación no existe"));
        }

        try {
            listDECircularService.compareId(changePositionPetCircularDTO.getIdentification()).toString();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay una mascota con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDECircularService.addToXPositionPet(
                new Pet(changePositionPetCircularDTO.getIdentification(),
                        changePositionPetCircularDTO.getName(), changePositionPetCircularDTO.getAge(),
                        changePositionPetCircularDTO.getGender().charAt(0), changePositionPetCircularDTO.getTypePet(),
                        changePositionPetCircularDTO.getWeight(), changePositionPetCircularDTO.getHeight(),
                        changePositionPetCircularDTO.isStateBathePet(), location),
                changePositionPetCircularDTO.getPosition(), changePositionPetCircularDTO.getDirection().charAt(0));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Mascota agregada en la posicion especificada", null), HttpStatus.OK);
    }

    @GetMapping(path = "/bathepetrandom/{direction}")
    public ResponseEntity<ResponseDTO> bathePetRandom(@PathVariable char direction){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDECircularService.getPets().size();
        } catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ninguna mascota agregada"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        Pet pet = listDECircularService.bathePetRandomList(direction);
        return new ResponseEntity<>(new ResponseDTO(
                200, "La Mascota con la ID " + pet.getIdentification() + " fue bañada", null), HttpStatus.OK);
    }
}
