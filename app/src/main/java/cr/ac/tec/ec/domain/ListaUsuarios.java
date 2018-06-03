package cr.ac.tec.ec.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CASA on 5/26/2018.
 */

public class ListaUsuarios {
    private static List<Usuario> ListaUsuarios = new ArrayList<>();
    private static List<Usuario> ListaUsuariosBloqueados = new ArrayList<>();

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
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    public static boolean isBlocked(String username){
        for (Usuario u: ListaUsuariosBloqueados){
            if (u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean isBlocked(Usuario user){
        return ListaUsuariosBloqueados.contains(user);
    }

    public static boolean blockUser(String username){
        Usuario u = getUser(username);
        if (u != null){
            ListaUsuariosBloqueados.add(u);
            return true;
        }
        return false;
    }

    public static boolean unblockUser(String username){
        for (Usuario u: ListaUsuariosBloqueados){
            if (u.getUsername().equals(username)){
                ListaUsuariosBloqueados.remove(u);
                return true;
            }
        }
        return false;
    }



}
