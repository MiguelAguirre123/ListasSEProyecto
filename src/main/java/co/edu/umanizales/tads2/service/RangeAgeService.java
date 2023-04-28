package co.edu.umanizales.tads2.service;

import co.edu.umanizales.tads2.model.Location;
import co.edu.umanizales.tads2.model.RangeAge;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeAgeService {
    private List<RangeAge> ranges ;

    public RangeAgeService() {
        //Conectar a una base de datos
        ranges= new ArrayList<>();
        ranges.add(new RangeAge(1,3));
        ranges.add(new RangeAge(4,6));
        ranges.add(new RangeAge(7,9));
        ranges.add(new RangeAge(10,12));
        ranges.add(new RangeAge(13,15));

    }


}