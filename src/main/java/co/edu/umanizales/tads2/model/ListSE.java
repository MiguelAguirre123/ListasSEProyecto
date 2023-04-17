package co.edu.umanizales.tads2.model;

import co.edu.umanizales.tads2.controller.dto.KidsByLocationDTO;
import lombok.Data;

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
    public void add(Kid kid){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
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
    public void addToStart(Kid kid){
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
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
    public void addToXPosition(Kid kid, byte position){
        if(head != null) {
            Node temp = head;
            Node newNode = new Node(kid);

            if (position <= 1) {
                newNode.setNext(head);
                head = newNode;
            } else {
                for (byte i = 1; i < position - 1; i++) {
                    if (temp.getNext() == null) {
                        temp.setNext(newNode);
                        return;
                    }
                    temp = temp.getNext();
                }

                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            }
        }
        else{
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
    public void deleteKidByIdentification(String identification){
        if(head !=null){
            Node temp = head;

            if(temp.getData().getIdentification().equalsIgnoreCase(identification)){
                temp = temp.getNext();
                head = temp;
            }
            else{
                if(temp.getNext() != null){
                    while(!temp.getNext().getData().getIdentification().equalsIgnoreCase(identification)){
                        temp = temp.getNext();
                        if(temp.getNext() == null){
                            return;
                        }
                    }
                    temp.setNext(temp.getNext().getNext());
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
    public void deleteKidsByAge(byte age){
        if(head != null){
             ListSE newList = new ListSE();
            Node temp = head;

            while(temp.getData() != null){
                if(temp.getData().getAge() != age){
                    newList.add(temp.getData());
                }
                if(temp.getNext() == null){
                    break;
                }
                else{
                    temp = temp.getNext();
                }
            }
            head = newList.getHead();
        }
    }
    public void moveKidByPosition(String identification, byte position) {
        if (head != null){
            byte positionTemp = 1;
            Node temp = head;
            Kid kidById = null;

            if(temp.getNext() == null || position == 0){
                return;
            }

            if(position < 0 && (temp.getData().getIdentification().equalsIgnoreCase(identification))){
                kidById = temp.getData();
                temp = temp.getNext();
                head = temp;
                positionTemp = 0;
            }
            else {
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

            byte restaPosition = (byte)(positionTemp - position);

            if(restaPosition < 1){
                Node newNode = new Node(kidById);
                newNode.setNext(head);
                head = newNode;
            }
            else{
                temp = head;
                for(byte i = 1; i < restaPosition; i++){
                    if(temp.getNext() == null){
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

    public String compareIdKids(String identification) {
        if (head != null) {
            Node temp = head;

            if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                return "confirmado";
            } else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().getIdentification().equalsIgnoreCase(identification)) {
                        return "confirmado";
                    }
                }
            }

            return null;
        }
        else{
            return  null;
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
        }
        else{
            return null;
        }
    }

    public KidsByLocationDTO addLocationListSE(Location location) {
        if (head != null) {
            Node temp = head;
            int quantity = 0;
            KidsByLocationDTO kidByLocationDTO = new KidsByLocationDTO(location, 0);

            if (temp.getData().getLocation().getCode().substring(0, location.getCode().length()).equals(location.getCode())) {
                quantity++;
            }

            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getData().getLocation().getCode().substring(0, location.getCode().length()).equals(location.getCode())) {
                    quantity++;
                }
            }

            kidByLocationDTO.setQuantity(quantity);

            return kidByLocationDTO;
        }

        return null;
    }

    public void invert(){
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void changeExtremes(){
        if(this.head !=null && this.head.getNext() !=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            //temp está en el último
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }

    }
}
