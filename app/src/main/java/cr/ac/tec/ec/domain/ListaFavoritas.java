package cr.ac.tec.ec.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaFavoritas {
    private static List<Película> FavPelículas = new ArrayList<>();

    public static void addFavMovie(Película p){
        FavPelículas.add(p);
    }

    public static List<Película> getFavMovies(){
        return FavPelículas;
    }
}
