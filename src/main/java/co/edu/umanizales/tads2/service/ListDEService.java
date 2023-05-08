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
public class ListDEService {
    private ListDE pets;

    public ListDEService() {
        pets = new ListDE();

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
    public void addToXPositionPet(Pet pet, byte position) { pets.addToXPosition(pet, position); }
    public void deleteByIdentification(String identification) { pets.deletePetByIdentification(identification); }
    public void deleteByAge(byte age) { pets.deletePetsByAge(age); }
    public void goUpByPosition(String identification, byte position) {
        pets.goUpPetByIdentification(identification, position); }
    public void goDownByPosition(String identification, byte position) {
        pets.goDownPetByIdentification(identification, position); }
    public String compareSizeWithPositionPet(byte position) { return pets.compareSizeWithPosition(position); }
    public String compareId(String identification) { return pets.compareIdPets(identification); }
    public String compareAge(byte age) { return pets.compareAgePets(age); }
    public void invertPets(){
        pets.invert();
    }
    public void changeExtremesPets(){ pets.changeExtremes(); }
    public void orderPetsMalesToStartList(){ pets.orderPetsMalesToStart(); }
    public float getAveragePets(){ return pets.getAverage(); }
    public List<QuantityKidByLocationDTO> countPetByLocationAndAgeList(byte age, String code) { return pets.countPetByLocationAndAge(age, code); }
    public void intercaleMalesAndFemalesPets() { pets.intercalePetsMalesAndPetsFemales(); }
    public int countPetsByAges(int min, int max) { return pets.countPetsByAge(min, max); }
    public void deleteByIdentificationV2(String identification) { pets.deletePetByIdentificationV2(identification); }
    public List<KidsByLocationDTO> getLocationInform(List<Location> locations, byte age) {
        List<KidsByLocationDTO> listPetsLocation = new ArrayList<>();

        for (Location location:locations)
        {
            listPetsLocation.add(pets.addLocationListSE(location, age));
        }

        return listPetsLocation;
    }
}
