package cr.ac.tec.ec.domain;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cr.ac.tec.ec.utility.MString;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaPelículas implements Serializable{
    private static List<Película> SysPelículas = new ArrayList<>();

    public static List<Película> getSysPelículas() {
        return SysPelículas;
    }

    public static void addSysMovie(Película p){
        if (!movieExists(p.getIdPelícula())){
                SysPelículas.add(p);
        }
    }

    public static void setSysPelículas(List<Película> data){
        SysPelículas = data;
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


    public static List<Película> searchMoviesByValue(String value){
        List<Película> matches = new ArrayList<>();
        if (value.isEmpty()){
            return matches;
        }

        ///List<Película> favs = ListaFavoritas.getFavMovies();
        List<Película> syspelículas = SysPelículas;


            for (Película sm: syspelículas){
                if (sm.getNombre().toUpperCase().contains(value.toUpperCase())){
                    if (!matches.contains(sm)) {
                        matches.add(sm);

                    }
                }else {
                    if (MString.contains(sm.getDirectores(), value)){
                        if (!matches.contains(sm)) {
                            matches.add(sm);
                        }
                    }else{
                        if (MString.contains(sm.getActores(), value)){
                            if (!matches.contains(sm)) {
                                matches.add(sm);
                            }
                        }
                    }
                }
            }

        //Collections.shuffle(recos);
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
