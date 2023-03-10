package co.edu.umanizales.tads2.service;

import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.model.ListSE;
import co.edu.umanizales.tads2.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
        kids.add(new Kid("123","Carlos",(byte)4));
        kids.add(new Kid("256","Mariana",(byte)3));
        kids.add(new Kid("789","Daniel",(byte)2));
        kids.add(new Kid("156","Paola",(byte)5));
        kids.add(new Kid("196","Lina",(byte)2));
        kids.add(new Kid("678","Sofia",(byte)2));
        kids.add(new Kid("934","Juan",(byte)5));
        kids.add(new Kid("177","Rodrigo",(byte)6));
        kids.add(new Kid("481","Sergio",(byte)4));

        kids.deleteKidsByAge((byte)2);

        kids.moveKidByPosition("934", (byte)2);

        //kids.addToStart(new Kid("967","Estefania",(byte)6));

        //kids.addToXPosition(new Kid("456","Pepe",(byte)7), (byte)3);

        //ids.deleteKidByIdentification("123");

    }

    public Node getKids()
    {
        return kids.getHead();
    }
}
