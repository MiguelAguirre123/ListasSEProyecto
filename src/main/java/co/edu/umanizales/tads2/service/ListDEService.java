package co.edu.umanizales.tads2.service;

import co.edu.umanizales.tads2.model.ListDE;
import co.edu.umanizales.tads2.model.NodeDE;
import co.edu.umanizales.tads2.model.Pet;
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

        pets.add(new Pet("123","Carlos"));
        pets.add(new Pet("256","Mariana"));
        pets.add(new Pet("789","Daniel"));
        pets.add(new Pet("156","Paola"));
        pets.add(new Pet("196","Lina"));
        pets.add(new Pet("678","Sofia"));
        pets.add(new Pet("934","Juan"));
        pets.add(new Pet("177","Rodrigo"));
        pets.add(new Pet("481","Sergio"));
    }

    public List<Pet> getPets() { return pets.getPets(); }
}
