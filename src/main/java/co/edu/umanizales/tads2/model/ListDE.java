package co.edu.umanizales.tads2.model;

import co.edu.umanizales.tads2.config.MyNullPointerException;
import co.edu.umanizales.tads2.controller.dto.ErrorDTO;
import co.edu.umanizales.tads2.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads2.controller.dto.QuantityKidByLocationDTO;
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

    public List<Pet> getPetsByPrevious() {
        if (head != null) {
            NodeDE temp = head;
            List<Pet> listPets = new ArrayList<>();
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            listPets.add(temp.getData());
            while (temp.getPrevious() != null) {
                temp = temp.getPrevious();
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

    public void addToStart(Pet pet) {
        if (head != null) {
            NodeDE newNode = new NodeDE(pet);
            newNode.setNext(head);
            head = newNode;
            head.getNext().setPrevious(head);
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }

    public void addToXPosition(Pet pet, byte position) {
        if (head != null) {
            NodeDE temp = head;
            NodeDE newNodeDE = new NodeDE(pet);

            if (position <= 1) {
                newNodeDE.setNext(head);
                head = newNodeDE;
                head.getNext().setPrevious(head);
            } else {
                for (byte i = 1; i < position - 1; i++) {
                    if (temp.getNext() == null) {
                        temp.setNext(newNodeDE);
                        temp.getNext().setPrevious(temp);
                        size++;
                        return;
                    }
                    temp = temp.getNext();
                }

                newNodeDE.setNext(temp.getNext());
                temp.setNext(newNodeDE);
                temp.getNext().setPrevious(temp);
                if (temp.getNext().getNext() != null) {
                    temp.getNext().getNext().setPrevious(temp.getNext());
                }
            }
        } else {
            head = new NodeDE(pet);
        }
        size++;
    }

    public void deletePetByIdentification(String identification) {
        if (head != null) {
            NodeDE temp = head;

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                temp = temp.getNext();
                head = temp;
                if (size != 1) {
                    head.setPrevious(null);
                }
            } else {
                while (temp.getNext() != null) {
                    if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                        if (temp.getNext().getNext() != null) {
                            temp.getNext().getNext().setPrevious(temp);
                        }
                        temp.setNext(temp.getNext().getNext());
                        size--;
                        return;
                    }
                    temp = temp.getNext();
                }
            }
            size--;
        }
    }

    public void deletePetsByAge(byte age) {
        if (head != null) {
            ListDE newList = new ListDE();
            NodeDE temp = head;

            while (temp.getData() != null) {
                if (temp.getData().getAge() != age) {
                    newList.add(temp.getData());
                }
                else {
                    size--;
                }
                if (temp.getNext() == null) {
                    break;
                } else {
                    temp = temp.getNext();
                }
            }
            head = newList.getHead();
        }
    }

    public void goUpPetByIdentification(String identification, byte position) {
        if (head != null) {
            byte positionPet = 1;
            NodeDE temp = head;
            Pet petByPosition = null;
            List<ErrorDTO> listErrors = new ArrayList<>();

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                listErrors.add(new ErrorDTO(422, "La mascota esta en cabeza por lo que no se puede desplazar hacia arriba"));
            }
            if (temp.getNext() == null) {
                listErrors.add(new ErrorDTO(422, "Hay un solo dato en la lista por lo que no se puede desplazar hacia arriba"));
            }
            if (listErrors.size() != 0) {
                throw new MyNullPointerException(listErrors);
            }

            while (temp.getNext() != null) {
                if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                    positionPet++;
                    if (positionPet <= position) {
                        listErrors.add(new ErrorDTO(422, "La posicion de la mascota no concuerda con las posiciones que se desean desplazarse"));
                        throw new MyNullPointerException(listErrors);
                    }
                    petByPosition = temp.getNext().getData();
                    if (temp.getNext().getNext() != null) {
                        temp.getNext().getNext().setPrevious(temp);
                    }
                    temp.setNext(temp.getNext().getNext());
                    size--;
                    break;
                }
                temp = temp.getNext();
                positionPet++;
            }

            byte positionFinal = (byte)(positionPet - position);
            addToXPosition(petByPosition, positionFinal);
        }
    }

    public void goDownPetByIdentification(String identification, byte position) {
        if (head != null) {
            byte positionPet = 1;
            NodeDE temp = head;
            Pet petByPosition = null;
            List<ErrorDTO> listErrors = new ArrayList<>();

            if (temp.getNext() == null) {
                listErrors.add(new ErrorDTO(422, "Hay un solo dato en la lista por lo que no se puede desplazar hacia abajo"));
                throw new MyNullPointerException(listErrors);
            }

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                if ((size - positionPet) < (position)) {
                    listErrors.add(new ErrorDTO(422, "La posicion de la mascota no concuerda con las posiciones que se desean desplazarse"));
                    throw new MyNullPointerException(listErrors);
                }
                petByPosition = temp.getData();
                head = temp.getNext();
                head.setPrevious(null);
            }
            else {
                while (temp.getNext() != null) {
                    if (temp.getNext().getNext() == null) {
                        listErrors.add(new ErrorDTO(422, "La mascota esta en el ultimo nodo por lo que no se puede desplazar hacia abajo"));
                        throw new MyNullPointerException(listErrors);
                    }
                    if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                        positionPet++;
                        if ((size - positionPet) < (position)) {
                            listErrors.add(new ErrorDTO(422, "La posicion de la mascota no concuerda con las posiciones que se desean desplazarse"));
                            throw new MyNullPointerException(listErrors);
                        }
                        petByPosition = temp.getNext().getData();
                        if (temp.getNext().getNext() != null) {
                            temp.getNext().getNext().setPrevious(temp);
                        }
                        temp.setNext(temp.getNext().getNext());
                        break;
                    }
                    temp = temp.getNext();
                    positionPet++;
                }
            }

            size--;
            byte positionFinal = (byte)(positionPet + position);
            addToXPosition(petByPosition, positionFinal);
        }
    }

    public String compareSizeWithPosition(byte position) {
        if (size > 0) {
            if (position > (size + 1)) {
                return null;
            } else {
                return "confirmado";
            }
        }
        else if (position == 1) {
            return "confirmado";
        }
        return null;
    }

    public String compareIdPets(String identification) {
        if (head != null) {
            NodeDE temp = head;

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                return null;
            } else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                        return null;
                    }
                }
            }

            return "confirmado";
        } else {
            return "confirmado";
        }
    }

    public String compareAgePets(byte age) {
        if (head != null) {
            NodeDE temp = head;

            if (temp.getData().getAge() == age) {
                return "confirmado";
            } else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().getAge() == age) {
                        return "confirmado";
                    }
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public KidsByLocationDTO addLocationListSE(Location location, byte age) {
        if (head != null) {
            NodeDE temp = head;
            int quantity = 0;
            KidsByLocationDTO kidByLocationDTO = new KidsByLocationDTO(location, 0);

            if (temp.getData().getLocation().getCode().substring(0, location.getCode().length()).equals(location.getCode()) && temp.getData().getAge() > age) {
                quantity++;
            }

            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getData().getLocation().getCode().substring(0, location.getCode().length()).equals(location.getCode()) && temp.getData().getAge() > age) {
                    quantity++;
                }
            }

            kidByLocationDTO.setQuantity(quantity);

            return kidByLocationDTO;
        }

        return null;
    }

    public void invert() {
        if (this.head != null) {
            ListDE listCp = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void changeExtremes() {
        if (this.head != null && this.head.getNext() != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Pet copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }

    public void orderPetsMalesToStart() {
        if (this.head != null) {
            ListDE listCp = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public float getAverage() {
        float average = 0;
        if (head != null) {
            NodeDE temp = head;
            float quantity = temp.getData().getAge();
            float position = 1;

            while (temp.getNext() != null) {
                temp = temp.getNext();
                position++;
                quantity += temp.getData().getAge();
            }

            return average = quantity / position;
        }

        return average;
    }

    public List<QuantityKidByLocationDTO> countPetByLocationAndAge(byte age, String code) {
        if (this.head != null) {
            NodeDE temp = head;
            List<QuantityKidByLocationDTO> listQuantityKidByLocationDTO = new ArrayList<>();
            int quantityMales = 0;
            int quantityFemales = 0;

            if (temp.getData().getLocation().getCode().substring(0, code.length()).equals(code)
                    && temp.getData().getAge() > age) {
                if (temp.getData().getGender() == 'M') {
                    quantityMales++;
                } else {
                    quantityFemales++;
                }
            }

            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getData().getLocation().getCode().substring(0, code.length()).equals(code)
                        && temp.getData().getAge() > age) {
                    if (temp.getData().getGender() == 'M') {
                        quantityMales++;
                    } else {
                        quantityFemales++;
                    }
                }
            }

            QuantityKidByLocationDTO quantityKidByLocationDTOMales = new QuantityKidByLocationDTO('M', quantityMales);
            listQuantityKidByLocationDTO.add(quantityKidByLocationDTOMales);
            QuantityKidByLocationDTO quantityKidByLocationDTOFemales = new QuantityKidByLocationDTO('F', quantityFemales);
            listQuantityKidByLocationDTO.add(quantityKidByLocationDTOFemales);


            return listQuantityKidByLocationDTO;
        }

        return null;

    }

    public void intercalePetsMalesAndPetsFemales() {
        if (head != null) {
            NodeDE temp = head;
            ListDE listBoys = new ListDE();
            int quantityFemale = 0;

            if (temp.getData().getGender() == 'M') {
                listBoys.add(temp.getData());
            } else {
                quantityFemale++;
            }

            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getData().getGender() == 'M') {
                    listBoys.add(temp.getData());
                } else {
                    quantityFemale++;
                }
            }

            if (quantityFemale == 0 || listBoys.head == null) {
                return;
            }

            temp = head;
            NodeDE temp2 = listBoys.head;

            while (quantityFemale != 0) {
                if (temp.getData().getGender() == 'F') {
                    NodeDE newNodeDE = new NodeDE(temp.getData());
                    newNodeDE.setNext(temp2.getNext());
                    temp2.setNext(newNodeDE);
                    temp2.getNext().setPrevious(temp2);
                    if (temp2.getNext().getNext() == null) {
                        temp2 = temp2.getNext();
                    } else {
                        temp2.getNext().getNext().setPrevious(temp2.getNext());
                        temp2 = temp2.getNext().getNext();
                    }
                    quantityFemale--;
                }
                temp = temp.getNext();
            }

            head = listBoys.getHead();
        }
    }

    public int countPetsByAge(int min, int max) {
        NodeDE temp = head;
        int counter = 0;
        while (temp != null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                counter++;
            }
            temp = temp.getNext();
        }

        return counter;
    }
}
