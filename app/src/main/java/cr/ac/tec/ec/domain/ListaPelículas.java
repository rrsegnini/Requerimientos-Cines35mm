package cr.ac.tec.ec.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaPelículas {
    private static List<Película> SysPelículas = new ArrayList<>();

    public static List<Película> getSysPelículas() {
        return SysPelículas;
    }

    public static void addSysMovie(Película p){
        SysPelículas.add(p);
    }
}
