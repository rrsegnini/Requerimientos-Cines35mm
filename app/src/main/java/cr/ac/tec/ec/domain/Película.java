package cr.ac.tec.ec.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class Película implements Serializable{
    private int IdPelícula;
    private String Nombre;
    private List<String> Directores;
    private List<String> Guionistas;
    private List<String> Actores;
    private Género Género;
    private int Año;
    private String PosterURL;
    private List<String> Tags;
    private List<List<Object>> Comentarios = new ArrayList<>();

    //For testing only
    private float Calificación = 0;
    private String Comentario;
    //----------------

    public Película(){

    }
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

    public Película(int idPelícula, String nombre, List<String> directores, List<String> guionistas,
                    List<String> actores, Género género, int año) {
        IdPelícula = idPelícula;
        Nombre = nombre;
        Directores = directores;
        Guionistas = guionistas;
        Actores = actores;
        Género = género;
        Año = año;

        ListaPelículas.addSysMovie(this);
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setDirectores(List<String> directores) {
        Directores = directores;
    }

    public void setGuionistas(List<String> guionistas) {
        Guionistas = guionistas;
    }

    public void setActores(List<String> actores) {
        Actores = actores;
    }

    public void setAño(int año) {
        Año = año;
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

    public void setPosterURL(String posterURL) {
        PosterURL = posterURL;
    }

    public List<String> getTags() {
        return Tags;
    }

    public float getCalificación() {
        return Calificación;
    }

    public void setCalificación(float calificación) {
        Calificación = calificación;
    }

    public List<List<Object>> getComentarios() {
        return Comentarios;
    }

    public void addComentario(List<Object> _comentario) {
        Comentarios.add(_comentario);
    }

    public void setGénero(cr.ac.tec.ec.domain.Género género) {
        Género = género;
    }

    public void setIdPelícula(int idPelícula) {
        IdPelícula = idPelícula;
    }
}
