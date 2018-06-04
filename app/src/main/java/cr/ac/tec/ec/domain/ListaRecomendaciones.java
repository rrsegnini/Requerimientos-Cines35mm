package cr.ac.tec.ec.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cr.ac.tec.ec.utility.MString;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaRecomendaciones {
    private static List<Película> RecoPelículas = new ArrayList<>();

    public static void addFavMovie(Película p){
        RecoPelículas.add(p);
    }

    public static List<Película> getFavMovies(){
        return RecoPelículas;
    }

    public static boolean movieExists(int id){
        for (int i = 0; i<RecoPelículas.size();i++){
            if (RecoPelículas.get(i).getIdPelícula() == id){
                return true;
            }
        }
        return false;
    }

    public static void refreshMovies(){
        List<Película> sys_movies = ListaPelículas.getSysPelículas();

        for (Película m: RecoPelículas){
            m = ListaPelículas.getMovieById(m.getIdPelícula());

        }
    }

    public static void deleteMovie(int id){
        for (int i = 0; i<RecoPelículas.size();i++){
            if (RecoPelículas.get(i).getIdPelícula() == id){
                RecoPelículas.remove(i);
            }
        }
    }

    public static void setFavPelículas(List<Película> recoPelículas) {
        RecoPelículas = recoPelículas;
    }

    public static List<Película> getRecos(){
        ListaFavoritas lf = Usuario.getInstance().getListaFavoritas();
        lf.refreshMovies();
        List<Película> favs = Usuario.getInstance().getListaFavoritas().getFavMovies();

        List<Película> syspelículas = ListaPelículas.getSysPelículas();
        List<Película> recos = new ArrayList<>();

        for (Película m: favs){
            List<String> directors = m.getDirectores();
            List<String> actors = m.getActores();
            Género gen = m.getGénero();

            for (Película sm: syspelículas){
                if (sm.getGénero() == m.getGénero()){
                    if (!recos.contains(ListaPelículas.getMovieById(sm.getIdPelícula())) && !lf.movieExists(sm.getIdPelícula())) {
                        Log.d("APP", "MOVIE: " + sm.getNombre());
                        //System.out.println("MOVIE: " + sm.getNombre());
                        recos.add(sm);

                    }
                }else {
                    for (String d: directors){
                        if (sm.getDirectores().contains(d)){
                            if (!recos.contains(ListaPelículas.getMovieById(sm.getIdPelícula())) && !lf.movieExists(sm.getIdPelícula())) {
                                recos.add(sm);

                            }
                        }else{
                            for (String a: actors){
                                if (sm.getActores().contains(a)){
                                    if (!recos.contains(ListaPelículas.getMovieById(sm.getIdPelícula())) && !lf.movieExists(sm.getIdPelícula())) {
                                        recos.add(sm);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Collections.shuffle(recos);
        return recos;
    }
}
