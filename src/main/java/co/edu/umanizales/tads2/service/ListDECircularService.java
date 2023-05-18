package co.edu.umanizales.tads2.service;

import co.edu.umanizales.tads2.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads2.controller.dto.QuantityKidByLocationDTO;
import co.edu.umanizales.tads2.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListDECircularService {
    private ListDECircular pets;

    public ListDECircularService() {
        pets = new ListDECircular();

        /*
        pets.add(new Pet("123","Carlos"));
        pets.add(new Pet("256","Mariana"));
        pets.add(new Pet("789","Daniel"));
        pets.add(new Pet("156","Paola"));
        pets.add(new Pet("196","Lina"));
        pets.add(new Pet("678","Sofia"));
        pets.add(new Pet("934","Juan"));
        pets.add(new Pet("177","Rodrigo"));
        pets.add(new Pet("481","Sergio"));
         */
    }

    public List<Pet> getPets() { return pets.getPets(); }
    public List<Pet> getPetsByPrevious() { return pets.getPetsByPrevious(); }
    public void addPet(Pet pet) { pets.add(pet); }
    public void addToStartPet(Pet pet) { pets.addToStart(pet); }
    public void addToXPositionPet(Pet pet, byte position, char direction) { pets.addToXPosition(pet, position, direction); }
    public Pet bathePetRandomList(char direction) { return pets.bathePetRandom(direction); }
    public String compareId(String identification) { return pets.compareIdPets(identification); }
}
