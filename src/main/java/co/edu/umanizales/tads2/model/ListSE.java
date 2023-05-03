package co.edu.umanizales.tads2.model;

import co.edu.umanizales.tads2.config.MyNullPointerException;
import co.edu.umanizales.tads2.controller.dto.ErrorDTO;
import co.edu.umanizales.tads2.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads2.controller.dto.QuantityKidByLocationDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    private Node head;

    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
    }

    /* Adicionar en posicion x
    si hay datos
    si
        llamo a ayudante
        creo nuevo costal con el niño dentro
        identifico que costal esta en la posicion x
        posicion x es menor o igual a 1?
        Si es asi
            nuevo costal se colocara detras de cabeza
            FIN CODIGO
        Si no es asi
            ayudante se pone a recorrer la lista para llegar a la posicion x-1
            en algun momento mientras ayudante recorre la lista el costal siguiente esta vacio?
            Si es asi
                nuevo costal se colocara al final de la lista
                FIN CODIGO
            Si no es asi
                al costal donde esta posicionado ayudante le digo que suelte el costal que tenga agarrado,
                a continuacion pego el brazo suelto del costal donde esta posicionado ayudante al nuevocostal,
                el brazo de nuevocostal se pegara al costal que tiene delante
    no
        meto el niño en un costal y lo asigno a la cabeza
     */
    public void addToXPosition(Kid kid, byte position) {
        List<ErrorDTO> listErrors = new ArrayList<>();
        if (head != null) {
            Node temp = head;
            Node newNode = new Node(kid);
            int positionTemp = 1;

            if (position <= 1) {
                newNode.setNext(head);
                head = newNode;
            } else {
                for (byte i = 1; i < position - 1; i++) {
                    if (temp.getNext() == null) {
                        if(positionTemp == position-1) {
                            temp.setNext(newNode);
                            return;
                        }
                        else {
                            listErrors.add(new ErrorDTO(400, "La posicion no corresponde con el tamaño de la lista de niños"));
                            throw new MyNullPointerException(listErrors);
                        }
                    }
                    temp = temp.getNext();
                    positionTemp++;
                }

                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            }
        } else {
            if (position != 1) {
                listErrors.add(new ErrorDTO(400, "La posicion no corresponde con el tamaño de la lista de niños"));
                throw new MyNullPointerException(listErrors);
            }
            head = new Node(kid);
        }
    }
    /*
    public void addToXPosition(Kid kid, byte position){
        if(head !=null)
        {
            Node temp = head;
            Node temp2 = head;
            for(byte i = 0; i < position-1; i++){
                if(temp.getNext() == null){
                    temp = temp.getNext();
                    break;
                }
                else{
                    temp = temp.getNext();
                }
            }

            for(byte i = 0; i < position-2; i++){
                if(temp2.getNext() == null){
                    break;
                }
                else{
                    temp2 = temp2.getNext();
                }
            }

            Node newNode = new Node(kid);
            newNode.setNext(temp);

            if(position > 1) {
                temp2.setNext(newNode);
            }
            else{
                head = newNode;
            }
        }
        else {
            head = new Node(kid);
        }
    }
     */

    /* Eliminar niño por id

        si hay datos
        si
            llamo a ayudante
            ayudante se posiciona en cabeza
            el niño del costal en el que se encuentra ayudante tiene la misma id que la ingresada?
            si es asi
                despego el brazo del costal donde esta ayudante, asi elimino el costal
                FIN CODIGO
            si no es asi
                el costal siguiente esta vacio?
                si es asi
                    FIN CODIGO
                si no es asi
                    el niño del costal siguiente del que se encuentra ayudante tiene la misma id que la ingresada?
                    si es asi
                        despego el brazo del costal donde esta ayudante,
                        al costal con el id del niño le digo que se suelte del costal que tiene adelante
                        quedando asi suelto y uniendo el costal donde esta situado ayudante con el costal
                        de adelante del costal de la id del niño
                        FIN CODIGO
                    si no es asi
                        ayudante recorre la lista buscando a un costal con un niño con la misma id
                        en algun momento mientras recorre la lista costal siguiente esta vacio?
                        si es asi
                            FIN CODIGO
                        si no es asi
                            sigue recorriendo la lista buscando a un niño que coincida con el id ingresado
        no
            no se hace nada
     */

    public void deleteKidByIdentification(String identification) {
        if (head != null) {
            Node temp = head;

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                temp = temp.getNext();
                head = temp;
            } else {
                while (temp.getNext() != null) {
                    if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                        temp.setNext(temp.getNext().getNext());
                        return;
                    }
                    temp = temp.getNext();
                }
            }
        }
    }

    /*
    public void deleteKidByIdentification(String identification){
        if(head !=null)
        {
            Node temp = head;
            Node temp2 = head;

            if(temp.getNext() == null && temp.getData().getIdentification() != identification){
                return;
            }

            while(temp.getData().getIdentification() != identification){
                temp = temp.getNext();
                if(temp.getNext() == null){
                    break;
                }
            }

            if(temp.getData().getIdentification() == identification) {
                temp = temp.getNext();
            }
            else{
                return;
            }

            if(temp2.getNext() == null){
                head = null;
                return;
            }

            while(temp2.getNext().getData().getIdentification() != identification){
                temp2 = temp2.getNext();
                if(temp2.getNext() == null){
                    break;
                }
            }

            if(temp2.getNext() != null) {
                temp2.setNext(temp);
            }
            else{
                head = temp;
            }

        }
    }
    */
    public void deleteKidsByAge(byte age) {
        if (head != null) {
            ListSE newList = new ListSE();
            Node temp = head;

            while (temp.getData() != null) {
                if (temp.getData().getAge() != age) {
                    newList.add(temp.getData());
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

    /*
    public void moveKidByPosition(String identification, byte position) {
        if (head != null) {
            byte positionTemp = 1;
            Node temp = head;
            Kid kidById = null;

            if (temp.getNext() == null || position == 0) {
                return;
            }

            if (position < 0 && (temp.getData().getIdentification().equalsIgnoreCase(identification))) {
                kidById = temp.getData();
                temp = temp.getNext();
                head = temp;
                positionTemp = 0;
            } else {
                while (temp.getNext() != null) {
                    if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                        kidById = temp.getNext().getData();
                        temp.setNext(temp.getNext().getNext());
                        break;
                    } else {
                        temp = temp.getNext();
                        positionTemp++;
                        if (temp.getNext() == null) {
                            return;
                        }
                    }
                }
            }

            byte restaPosition = (byte) (positionTemp - position);

            if (restaPosition < 1) {
                Node newNode = new Node(kidById);
                newNode.setNext(head);
                head = newNode;
            } else {
                temp = head;
                for (byte i = 1; i < restaPosition; i++) {
                    if (temp.getNext() == null) {
                        break;
                    }
                    temp = temp.getNext();
                }
                Node newNode = new Node(kidById);
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            }
        }
    }
     */

    public void goUpKidByIdentification(String identification, byte position) {
        if (head != null) {
            byte positionKid = 1;
            Node temp = head;
            Kid kidByPosition = null;
            List<ErrorDTO> listErrors = new ArrayList<>();

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                listErrors.add(new ErrorDTO(400, "El niño esta en cabeza por lo que no se puede desplazar hacia arriba"));
            }
            if (temp.getNext() == null) {
                listErrors.add(new ErrorDTO(400, "Hay un solo dato en la lista por lo que no se puede desplazar hacia arriba"));
            }
            if (listErrors.size() != 0) {
                throw new MyNullPointerException(listErrors);
            }

            while (temp.getNext() != null) {
                if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                    positionKid++;
                    if (positionKid <= position) {
                        listErrors.add(new ErrorDTO(400, "La posicion del niño no concuerda con las posiciones que se desean desplazarse"));
                        throw new MyNullPointerException(listErrors);
                    }
                    kidByPosition = temp.getNext().getData();
                    temp.setNext(temp.getNext().getNext());
                    break;
                }
                temp = temp.getNext();
                positionKid++;
            }

            byte positionFinal = (byte)(positionKid - position);
            addToXPosition(kidByPosition, positionFinal);
        }
    }

    public void goDownKidByIdentification(String identification, byte position) {
        if (head != null) {
            byte positionKid = 1;
            int sizeList = 1;
            Node temp = head;
            Kid kidByPosition = null;
            List<ErrorDTO> listErrors = new ArrayList<>();

            if (temp.getNext() == null) {
                listErrors.add(new ErrorDTO(400, "Hay un solo dato en la lista por lo que no se puede desplazar hacia abajo"));
                throw new MyNullPointerException(listErrors);
            }
            else {
                while (temp.getNext() != null) {
                    sizeList++;
                    temp = temp.getNext();
                }
                temp = head;
            }

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                if ((sizeList - positionKid) < (position)) {
                    listErrors.add(new ErrorDTO(400, "La posicion del niño no concuerda con las posiciones que se desean desplazarse"));
                    throw new MyNullPointerException(listErrors);
                }
                kidByPosition = temp.getData();
                head = temp.getNext();
            }
            else {
                while (temp.getNext() != null) {
                    if (temp.getNext().getNext() == null) {
                        listErrors.add(new ErrorDTO(400, "El niño esta en el ultimo nodo por lo que no se puede desplazar hacia abajo"));
                        throw new MyNullPointerException(listErrors);
                    }
                    if (temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)) {
                        positionKid++;
                        if ((sizeList - positionKid) < (position)) {
                            listErrors.add(new ErrorDTO(400, "La posicion del niño no concuerda con las posiciones que se desean desplazarse"));
                            throw new MyNullPointerException(listErrors);
                        }
                        kidByPosition = temp.getNext().getData();
                        temp.setNext(temp.getNext().getNext());
                        break;
                    }
                    temp = temp.getNext();
                    positionKid++;
                }
            }

            byte positionFinal = (byte)(positionKid + position);
            addToXPosition(kidByPosition, positionFinal);
        }
    }

    public String compareIdKids(String identification) {
        if (head != null) {
            Node temp = head;

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

    public String compareAgeKids(byte age) {
        if (head != null) {
            Node temp = head;

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
            Node temp = head;
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
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void changeExtremes() {
        if (this.head != null && this.head.getNext() != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }

    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
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
            Node temp = head;
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

    public List<QuantityKidByLocationDTO> countKidByLocationAndAge(byte age, String code) {
        if (this.head != null) {
            Node temp = head;
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

    public void intercaleBoysAndGirls() {
        if (head != null) {
            Node temp = head;
            ListSE listBoys = new ListSE();
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
            Node temp2 = listBoys.head;

            while (quantityFemale != 0) {
                if (temp.getData().getGender() == 'F') {
                    Node newNode = new Node(temp.getData());
                    newNode.setNext(temp2.getNext());
                    temp2.setNext(newNode);
                    if (temp2.getNext().getNext() == null) {
                        temp2 = temp2.getNext();
                    } else {
                        temp2 = temp2.getNext().getNext();
                    }
                    quantityFemale--;
                }
                temp = temp.getNext();
            }

            head = listBoys.getHead();
        }
    }

    public int countKidsByAge(int min, int max) {
        Node temp = head;
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
