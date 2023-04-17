package co.edu.umanizales.tads2.service;

import co.edu.umanizales.tads2.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads2.model.Kid;
import co.edu.umanizales.tads2.model.ListSE;
import co.edu.umanizales.tads2.model.Location;
import co.edu.umanizales.tads2.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
        //kids.add(new Kid("123","Carlos",(byte)4));
        //kids.add(new Kid("256","Mariana",(byte)3));
        //kids.add(new Kid("789","Daniel",(byte)2));
        //kids.add(new Kid("156","Paola",(byte)5));
        //kids.add(new Kid("196","Lina",(byte)2));
        //kids.add(new Kid("678","Sofia",(byte)2));
        //kids.add(new Kid("934","Juan",(byte)5));
        //kids.add(new Kid("177","Rodrigo",(byte)6));
        //kids.add(new Kid("481","Sergio",(byte)4));

        //kids.deleteKidsByAge((byte)2);

        //kids.moveKidByPosition("934", (byte)2);

        //kids.addToStart(new Kid("967","Estefania",(byte)6));

        //kids.addToXPosition(new Kid("456","Pepe",(byte)7), (byte)3);

        //kids.deleteKidByIdentification("256");

    }

    public Node getKids() { return kids.getHead(); }
    public void addKid(Kid kid) { kids.add(kid); }
    public void addToStartKid(Kid kid) { kids.addToStart(kid); }
    public void addToXPositionKid(Kid kid, byte position) { kids.addToXPosition(kid, position); }
    public void deletebyIdentification(String identification) { kids.deleteKidByIdentification(identification); }
    public void deleteByAge(byte age) { kids.deleteKidsByAge(age); }
    public void moveByPosition(String identification, byte position) {
        kids.moveKidByPosition(identification, position); }
    public String compareId(String identification) { return kids.compareIdKids(identification); }
    public String compareAge(byte age) { return kids.compareAgeKids(age); }
    public void invertKids(){
        kids.invert();
    }
    public void changeExtremesKids(){ kids.changeExtremes(); }

    public List<KidsByLocationDTO> getLocationInform(List<Location> locations) {

        if (kids.getHead() == null) {
            return null;
        }

        List<KidsByLocationDTO> listKidsLocation = new ArrayList<>();

        for (Location location:locations)
        {
            listKidsLocation.add(kids.addLocationListSE(location));
        }

        return listKidsLocation;
    }
}
