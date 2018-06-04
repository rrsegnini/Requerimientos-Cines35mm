package cr.ac.tec.ec.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by CASA on 5/26/2018.
 */

public class Usuario implements Serializable{
    private int IdUsuario;
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private boolean admin;
    private static Usuario currentUser;
    private ListaFavoritas listaFavoritas = new ListaFavoritas();


    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String password) {
        IdUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.admin = false;
        this.listaFavoritas = new ListaFavoritas();
    }

    public Usuario(int idUsuario, String username, String password, boolean admin) {
        IdUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.listaFavoritas = new ListaFavoritas();
    }

    public static Usuario getInstance() {
        if (currentUser == null)
            currentUser = new Usuario();

        return currentUser;
    }

    public void logUser(Usuario u){
        currentUser = u;
    }

    public boolean isAdmin(){
        return this.admin;
    }



    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public ListaFavoritas getListaFavoritas() {
        return listaFavoritas;
    }

    public void setListaFavoritas(ListaFavoritas listaFavoritas) {
        this.listaFavoritas = listaFavoritas;
    }
}
