package co.edu.umanizales.tads2.model;

import co.edu.umanizales.tads2.config.MyNullPointerException;
import co.edu.umanizales.tads2.controller.dto.ErrorDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ListDECircular {
    private NodeDE head;
    private int size;

            /*
    Algoritmo de mostrar lista circular
    si hay datos
    si
        recorro la lista doblemente circular guardando cada mascota en una lista normal
        la lista se recorrera por los brazos siguientes de la lista circular
        cuando el nodo siguiente sea igual a cabeza devuelvo la lista
    no
        No hacemos nada
     */

    public List<Pet> getPets() {
        if (head != null) {
            NodeDE temp = head;
            List<Pet> listPets = new ArrayList<>();
            listPets.add(temp.getData());
            while (temp.getNext() != head) {
                temp = temp.getNext();
                listPets.add(temp.getData());
            }
            listPets.add(temp.getNext().getData());

            return listPets;
        }
        return null;
    }

                /*
    Algoritmo de mostrar lista circular por previous
    si hay datos
    si
        recorro la lista doblemente circular guardando cada mascota en una lista normal
        la lista se recorrera por los brazos anteriores de la lista circular
        cuando el nodo siguiente sea igual a cabeza devuelvo la lista
    no
        No hacemos nada
     */

    public List<Pet> getPetsByPrevious() {
        if (head != null) {
            NodeDE temp = head;
            List<Pet> listPets = new ArrayList<>();

            listPets.add(temp.getData());
            while (temp.getPrevious() != head) {
                temp = temp.getPrevious();
                listPets.add(temp.getData());
            }
            listPets.add(temp.getPrevious().getData());

            return listPets;
        }
        return null;
    }

        /*
    Algoritmo de adicionar al final
    Entrada
        una mascota
    si hay datos
    si
        guardamos a mascota en un nuevo saco
        le digo al nuevo nodo que su brazo anterior se pegue al nodo anterior a cabeza
        el brazo anterior de cabeza se pega a nuevo nodo
        el nodo anterior de cabeza que ahora es el nuevo nodo pega su brazo siguiente a cabeza
        el nodo anterior de nuevo nodo pega su brazo siguiente a nuevo nodo
    no
        Metemos a la mascota en un nodo que sera cabeza y el brazo siguiente y anterior se pegan a cabeza
     */

    public void add(Pet pet) {
        if (head != null) {
            NodeDE newNodeDECircular = new NodeDE(pet);
            newNodeDECircular.setPrevious(head.getPrevious());
            head.setPrevious(newNodeDECircular);
            head.getPrevious().setNext(head);
            head.getPrevious().getPrevious().setNext(head.getPrevious());
        } else {
            head = new NodeDE(pet);
            head.setNext(head);
            head.setPrevious(head);
        }
        size++;
    }

            /*
    Algoritmo de adicionar al Inicio
    Entrada
        una mascota
    si hay datos
    si
        guardamos a mascota en un nuevo saco
        le digo al nuevo nodo que su brazo anterior se pegue al nodo anterior a cabeza
        el brazo anterior de cabeza se pega a nuevo nodo
        el nodo anterior de cabeza que ahora es el nuevo nodo pega su brazo siguiente a cabeza
        el nodo anterior de nuevo nodo pega su brazo siguiente a nuevo nodo
        le digo al nodo anterior a cabeza que es nuevo nodo que sea ahora la nueva cabeza
    no
        Metemos a la mascota en un nodo que sera cabeza y el brazo siguiente y anterior se pegan a cabeza
     */

    public void addToStart(Pet pet) {
        if (head != null) {
            NodeDE newNodeDECircular = new NodeDE(pet);
            newNodeDECircular.setPrevious(head.getPrevious());
            head.setPrevious(newNodeDECircular);
            head.getPrevious().setNext(head);
            head.getPrevious().getPrevious().setNext(head.getPrevious());
            head = head.getPrevious();
        } else {
            head = new NodeDE(pet);
            head.setNext(head);
            head.setPrevious(head);
        }
        size++;
    }

                /*
    Algoritmo de adicionar en X position
    Entrada
        una mascota
        posicion en la que se desea agregar la mascota
        direccion en la que se va a recorrer la lista
    si hay datos
    llamo a un ayudante y le digo que se posicione en la cabeza
    si
        posicion es igual o menor a 1?
        si es asi
            guardamos a mascota en un nuevo saco
            le digo al nuevo nodo que su brazo anterior se pegue al nodo anterior a cabeza
            el brazo anterior de cabeza se pega a nuevo nodo
            el nodo anterior de cabeza que ahora es el nuevo nodo pega su brazo siguiente a cabeza
            el nodo anterior de nuevo nodo pega su brazo siguiente a nuevo nodo
            le digo al nodo anterior a cabeza que es nuevo nodo que sea ahora la nueva cabeza
        si no es asi
            recorremos la lista con ayudante hasta llegar una posicion antes de la posicion de entrada
            tenemos en cuenta si la direccion en la que se quiere mover la lista es derecha o izquierda
            la direccion en la que se quiere recorrer la lista es la derecha?
            si es asi
                guardamos a mascota en un nuevo saco
                le digo al nuevo nodo que su brazo siguiente se pegue al nodo siguiente a temp
                el brazo siguiente de temp se pega a nuevo nodo
                el nodo siguiente de temp que ahora es el nuevo nodo pega su brazo anterior a temp
                el nodo siguiente de nuevo nodo pega su brazo anterior a nuevo nodo
            si no es asi
                guardamos a mascota en un nuevo saco
                le digo al nuevo nodo que su brazo anterior se pegue al nodo anterior a temp
                el brazo anterior de temp se pega a nuevo nodo
                el nodo anterior de temp que ahora es el nuevo nodo pega su brazo siguiente a temp
                el nodo anterior de nuevo nodo pega su brazo siguiente a nuevo nodo
    no
        Metemos a la mascota en un nodo que sera cabeza y el brazo siguiente y anterior se pegan a cabeza
     */

    public void addToXPosition(Pet pet, byte position, char direction) {
        if (head != null) {
            NodeDE temp = head;
            NodeDE newNodeDECircular = new NodeDE(pet);
            if (position <= 1) {
                newNodeDECircular.setPrevious(head.getPrevious());
                head.setPrevious(newNodeDECircular);
                head.getPrevious().setNext(head);
                head.getPrevious().getPrevious().setNext(head.getPrevious());
                head = head.getPrevious();
            }
            else {
                position--;
                while (position != 1) {
                    if (direction == 'R') {
                        temp = temp.getNext();
                    }
                    else{
                        temp = temp.getPrevious();
                    }
                    position--;
                }

                if (direction == 'R') {
                    newNodeDECircular.setNext(temp.getNext());
                    temp.setNext(newNodeDECircular);
                    temp.getNext().setPrevious(temp);
                    temp.getNext().getNext().setPrevious(temp.getNext());
                }
                else{
                    newNodeDECircular.setPrevious(temp.getPrevious());
                    temp.setPrevious(newNodeDECircular);
                    temp.getPrevious().setNext(temp);
                    temp.getPrevious().getPrevious().setNext(temp.getPrevious());
                }
            }
        } else {
            head = new NodeDE(pet);
            head.setNext(head);
            head.setPrevious(head);
        }
        size++;
    }

                /*
    Algoritmo de comparar si existe una id repetida
    entrada
    id
    si hay datos
    si
        recorro la lista doblemente circular comparando con cada mascota si la id de entrada es igual a alguna id de las mascotas
        si es asi
            retornamos nada
        si no es asi
            retornamos un string que dice "confirmado" el cual me indica que no hay ninguna id igual
    no
        retornamos un string que dice "confirmado" el cual me indica que no hay ninguna id igual
     */

    public String compareIdPets(String identification) {
        if (head != null) {
            NodeDE temp = head;

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                return null;
            } else {
                while (temp.getNext() != head) {
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

                /*
    Algoritmo de bañar mascota aleatoriamente
    Entrada
        direccion
    si hay datos
    si
        llamamos un ayudante
        se genera un numero aleatorio
        recorremos la lista la cantidad de veces que el numero aleatorio
        si la direccion es L recorremos la lista por medio de ayudante hacia la izquierda, y si es R hacia la derecha
        cuando ayudante este posicionado en el nodo que indico el numero aleatorio se cambia el estado del perro de no bañado a bañado
        devolvemos la mascota en la que esta posicionado ayudante para dar un mensaje mas personalizado
        si el perro ya estaba bañado no se hace nada y se hace saber con un mensaje
    no
        devolvemos nada
     */

    public Pet bathePetRandom(char direction) {
        if (head != null) {
            NodeDE temp = head;
            Random random = new Random();
            int position = random.nextInt(100) + 1;
            while (position != 1) {
                if (direction == 'R') {
                    temp = temp.getNext();
                }
                else{
                    temp = temp.getPrevious();
                }
                position--;
            }

            if (temp.getData().isStateBathePet() == false) {
                temp.getData().setStateBathePet(true);
                return temp.getData();
            }
            else {
                List<ErrorDTO> listErrors = new ArrayList<>();
                listErrors.add(new ErrorDTO(422, "La mascota escogida aleatoriamente ya esta bañada"));
                throw new MyNullPointerException(listErrors);
            }
        }
        return null;
    }
}
