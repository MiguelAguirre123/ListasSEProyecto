package co.edu.umanizales.tads2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public List<Pet> getPets() {
        if (head != null) {
            NodeDE temp = head;
            List<Pet> listPets = new ArrayList<>();
            listPets.add(temp.getData());
            while (temp.getNext() != null) {
                temp = temp.getNext();
                listPets.add(temp.getData());
            }

            return listPets;
        }
        return null;
    }

    public void add(Pet pet) {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            NodeDE newNodeDE = new NodeDE(pet);
            temp.setNext(newNodeDE);
            temp.getNext().setPrevious(temp);
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }
}
