package co.edu.umanizales.tads2.model;

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
        identifico que costal esta en la posicion x
        a la posicion x-1 es decir al costal anterior, le digo que se suelte del costal que tenga agarrado,
        a continuacion pego el brazo suelto del anterior costal al nuevocostal,
        el brazo de nuevocostal se pegara al costal que tiene delante
    no
        meto el niño en un costal y lo asigno a la cabeza
     */
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

    /* Eliminar niño por id

        llamo a ayudante
        identifico que costal tiene la id del niño
        al costal anterior, le digo que se suelte del costal con el id del niño,
        al costal con el id del niño le digo que se suelte del costal que tiene adelante
        separo y elimino el costal del niño con id
        uno el costal anterior con el costal de delante

     */
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
}
