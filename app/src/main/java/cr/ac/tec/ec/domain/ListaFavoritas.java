package cr.ac.tec.ec.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaFavoritas {
    private  List<Película> FavPelículas = new ArrayList<>();

    public  void addFavMovie(Película p){
        FavPelículas.add(p);
    }

    public  List<Película> getFavMovies(){
        return FavPelículas;
    }

    public boolean movieExists(int id){
        for (int i = 0; i<FavPelículas.size();i++){
            if (FavPelículas.get(i).getIdPelícula() == id){
                return true;
            }
        }
        return false;
    }

    public void refreshMovies(){
        List<Película> sys_movies = ListaPelículas.getSysPelículas();

        for (Película m: FavPelículas){
            m = ListaPelículas.getMovieById(m.getIdPelícula());

        }
    }

    public void deleteMovie(int id){
        for (int i = 0; i<FavPelículas.size();i++){
            if (FavPelículas.get(i).getIdPelícula() == id){
                FavPelículas.remove(i);
            }
        }
    }

    public void setFavPelículas(List<Película> favPelículas) {
        FavPelículas = favPelículas;
    }
}
