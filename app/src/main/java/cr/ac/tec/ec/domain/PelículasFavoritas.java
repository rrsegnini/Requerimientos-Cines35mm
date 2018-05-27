package cr.ac.tec.ec.domain;

import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class PelículasFavoritas {
    private List<Película> FavPelícula;

    public void addFavMovie(Película p){
        this.FavPelícula.add(p);
    }
}
