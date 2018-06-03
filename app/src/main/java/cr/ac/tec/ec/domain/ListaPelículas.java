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
        if (!movieExists(p.getIdPelícula())){
                SysPelículas.add(p);
        }
    }

    public static Película getMovieById(int id){
        for (int i = 0; i<SysPelículas.size();i++){
            if (SysPelículas.get(i).getIdPelícula() == id){
                return SysPelículas.get(i);
            }
        }
        return null;
    }

    public static boolean movieExists(int id){
        for (int i = 0; i<SysPelículas.size();i++){
            if (SysPelículas.get(i).getIdPelícula() == id){
                return true;
            }
        }
        return false;
    }

    public static void deleteMovie(int id){
        for (int i = 0; i<SysPelículas.size();i++){
            if (SysPelículas.get(i).getIdPelícula() == id){
                SysPelículas.remove(i);
            }
        }
    }

    public static List<Película> getMoviesByName(String name){
        List<Película> matches = new ArrayList<>();
        if (name.isEmpty()){
            return matches;
        }
        for (int i = 0; i<SysPelículas.size();i++){
            if (SysPelículas.get(i).getNombre().toUpperCase().contains(name.toUpperCase())){
                matches.add((SysPelículas.get(i)));
            }
        }
        return matches;
    }

    public void editMovie(int id, Película p){
        for (int i = 0; i<SysPelículas.size();i++){
            if (SysPelículas.get(i).getIdPelícula() == id){
                deleteMovie(SysPelículas.get(i).getIdPelícula());
                addSysMovie(p);
                break;
            }
        }
    }


}
