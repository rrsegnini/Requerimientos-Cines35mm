package cr.ac.tec.ec.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaUsuarios {
    private static List<Usuario> ListaUsuarios = new ArrayList<>();

    public static void addUser(Usuario u){
        ListaUsuarios.add(u);
    }

    public static boolean validUser(String _username){
        for (int i=0; i<ListaUsuarios.size(); i++){
            if (ListaUsuarios.get(i).getUsername().equals(_username)){
                return true;
            }
        }
        return false;
    }

    public static Usuario getUser(String username){
        for (Usuario u: ListaUsuarios){
            System.out.println(u.getUsername());
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

}
