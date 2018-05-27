package cr.ac.tec.ec.domain;

import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class Película {
    private int IdPelícula;
    private String Nombre;
    private List<String> Directores;
    private List<String> Guionistas;
    private List<String> Actores;
    private Género Género;
    private int Año;
    private String PosterURL;
    private List<String> Tags;

    //For testing only
    private int Calificación;
    private String Comentario;
    //----------------

    public Película(int idPelícula, String nombre, List<String> directores, List<String> guionistas,
                    List<String> actores, Género género, int año, String posterURL, List<String> tags) {
        IdPelícula = idPelícula;
        Nombre = nombre;
        Directores = directores;
        Guionistas = guionistas;
        Actores = actores;
        Género = género;
        Año = año;
        PosterURL = posterURL;
        Tags = tags;

        ListaPelículas.addSysMovie(this);
    }

    public int getIdPelícula() {
        return IdPelícula;
    }

    public String getNombre() {
        return Nombre;
    }

    public List<String> getDirectores() {
        return Directores;
    }

    public List<String> getGuionistas() {
        return Guionistas;
    }

    public List<String> getActores() {
        return Actores;
    }

    public cr.ac.tec.ec.domain.Género getGénero() {
        return Género;
    }

    public int getAño() {
        return Año;
    }

    public String getPosterURL() {
        return PosterURL;
    }

    public List<String> getTags() {
        return Tags;
    }

    public int getCalificación() {
        return Calificación;
    }

    public void setCalificación(int calificación) {
        Calificación = calificación;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }
}
