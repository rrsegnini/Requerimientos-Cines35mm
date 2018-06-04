package cr.ac.tec.ec.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cr.ac.tec.ec.cines35mm.MainActivity;
import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.ListaUsuarios;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.domain.Usuario;

/**
 * Created by CASA on 6/2/2018.
 */

public class Database {
    private static MobileServiceClient mClient;

    public static void connect(Activity context) {
        try {
            mClient = new MobileServiceClient(
                    "https://requerimientos-cines35mm.azurewebsites.net",
                    context
            );
        } catch (Exception e) {
        }
    }

    public static MobileServiceList<Género> getGens() {
        try {
            final MobileServiceTable<Género> mGens = mClient.getTable(Género.class);
            //return mGens.execute().get();
            MobileServiceList<Género> results;

            new AsyncTask<MobileServiceList<Género>, MobileServiceList<Género>, MobileServiceList<Género>>() {

                @Override
                protected MobileServiceList<Género> doInBackground(MobileServiceList<Género>... params) {
                    try {
                        return mGens.execute().get();
                        //Log.d("debug","data success");
                        //String str="";
                    } catch (Exception e) {

                    }
                    return null;
                }
            }.execute();

            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public static void initialize(Activity context) {

        Película p2001 = new Película(1, "2001: A Space Odyssey",
                Arrays.asList("Stanley Kubrick"),
                Arrays.asList("Stanley Kubrick", "Arthur C. Clarke"),
                Arrays.asList("Keir Dullea"),
                cr.ac.tec.ec.domain.Género.SCIENCE_FICTION,
                1968,
                "https://image.ibb.co/cSc58J/m2001.jpg",
                Arrays.asList("classic", "cult classic", "cult movie"));

        Película pAlien = new Película(2, "Alien",
                Arrays.asList("Ridley Scott"),
                Arrays.asList("Dan O'Bannon"),
                Arrays.asList("Sigourney Weaver", "John Hurt"),
                cr.ac.tec.ec.domain.Género.SCIENCE_FICTION,
                1979,
                "https://image.ibb.co/gX4P2d/alien.jpg",
                Arrays.asList("classic", "cult", "cult movie", "xenomorph"));

        Película pXtro = new Película(3, "Xtro",
                Arrays.asList("Harry Bromley Davenport"),
                Arrays.asList("Harry Bromley Davenport", "Iain Cassie", "Robert Smith"),
                Arrays.asList("Maryam d'Abo", "Philip Sayer"),
                cr.ac.tec.ec.domain.Género.HORROR,
                1982,
                "https://image.ibb.co/kzY1hd/xtro.jpg",
                Arrays.asList("bad movie", "funny", "cult", "cult movie", "redlettermedia"));

        Película pBarbarella = new Película(4, "Barbarella",
                Arrays.asList("Roger Vadim"),
                Arrays.asList("Terry Southern"),
                Arrays.asList("Jane Fonda", "John Phillip Law"),
                cr.ac.tec.ec.domain.Género.SCIENCE_FICTION,
                1968,
                "https://image.ibb.co/bUqxNd/barbarella.jpg",
                Arrays.asList("italian cult", "cult movie"));

        Película pTHX = new Película(5, "THX 1138",
                Arrays.asList("George Lucas"),
                Arrays.asList("George Lucas", "Walter Murch", "Matthew Robbins"),
                Arrays.asList("Robert Duvall", "Maggie McOmie"),
                cr.ac.tec.ec.domain.Género.SCIENCE_FICTION,
                1971,
                "https://image.ibb.co/b6NGFy/thxii83.jpg",
                Arrays.asList("old movie", "cult movie", "star wars"));

        Película pAnnihilation = new Película(6, "Annihilation",
                Arrays.asList("Alex Garland"),
                Arrays.asList("Alex Garland"),
                Arrays.asList("Natalie Portman", "Gina Rodriguez", "Oscar Isaac"),
                cr.ac.tec.ec.domain.Género.SCIENCE_FICTION,
                2018,
                "https://image.ibb.co/irYv8J/annihilation.jpg",
                Arrays.asList("ex_machina", "ex machina", "netflix"));

        Usuario u1 = new Usuario(1, "r", "roberto");
        Usuario u2 = new Usuario(2, "admin", "admin", true);

        u1.getListaFavoritas().addFavMovie(pAlien);
        u1.getListaFavoritas().addFavMovie(pBarbarella);
        u1.getListaFavoritas().addFavMovie(pTHX);

        ListaUsuarios.addUser(u1);
        ListaUsuarios.addUser(u2);


        ListaPelículas.addSysMovie(p2001);
        ListaPelículas.addSysMovie(pAlien);
        ListaPelículas.addSysMovie(pXtro);
        ListaPelículas.addSysMovie(pBarbarella);
        ListaPelículas.addSysMovie(pTHX);
        ListaPelículas.addSysMovie(pAnnihilation);



        pAlien.addComentario(Arrays.asList((Object) u1, "Classic horror/scifi movie."));
        pAlien.addComentario(Arrays.asList((Object) u2, "Awesome movie."));
        p2001.addComentario(Arrays.asList((Object) u2, "A masterpiece."));


        createMoviesData(context);
        createUsersData(context);
        createBlockedUsersData(context);
        createFavMoviesData(context);

        /*
        https://image.ibb.co/iZpVay/bladerunner.jpg
        https://image.ibb.co/j6EwFy/logo.png
        https://image.ibb.co/cSc58J/m2001.jpg
        https://image.ibb.co/j1uVay/metropolis.jpg
        https://image.ibb.co/karXoJ/oldboy.jpg
        https://image.ibb.co/b6NGFy/thxii83.jpg
        https://image.ibb.co/kzY1hd/xtro.jpg
        https://image.ibb.co/gX4P2d/alien.jpg
        https://image.ibb.co/irYv8J/annihilation.jpg
        https://image.ibb.co/bUqxNd/barbarella.jpg
         */

        //---------------------
    }

    public static void createMoviesData(Activity context) {
        try {
            //ArrayList al = new ArrayList();
            //do something with your ArrayList
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), "moviesdata.txt"));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);
            oos.writeObject(ListaPelículas.getSysPelículas());
            oos.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void createUsersData(Activity context) {
        try {
            //ArrayList al = new ArrayList();
            //do something with your ArrayList
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), "usersdata.txt"));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);
            oos.writeObject(ListaUsuarios.getListaUsuarios());
            oos.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void createBlockedUsersData(Activity context) {
        try {
            //ArrayList al = new ArrayList();
            //do something with your ArrayList
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), "blockedusersdata.txt"));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);
            oos.writeObject(ListaUsuarios.getListaUsuariosBloqueados());
            oos.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void createFavMoviesData(Activity context) {
        try {
            //ArrayList al = new ArrayList();
            //do something with your ArrayList
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), "favmoviesdata.txt"));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);
            oos.writeObject(Usuario.getInstance().getListaFavoritas().getFavMovies());
            oos.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }





    public static void loadData(Activity context){
        try {
            //ArrayList al = new ArrayList();
            //do something with your ArrayList
            FileInputStream fos =
                    new FileInputStream(new File(context.getFilesDir(), "moviesdata.txt"));
            ObjectInputStream oos =
                    new ObjectInputStream(fos);

            List<Película> movieslist = (ArrayList<Película>) oos.readObject();
            ListaPelículas.setSysPelículas(movieslist);
            oos.close();


            FileInputStream fos2 =
                    new FileInputStream(new File(context.getFilesDir(), "favmoviesdata.txt"));
            ObjectInputStream oos2 =
                    new ObjectInputStream(fos2);

            List<Película> favmovieslist = (ArrayList<Película>) oos2.readObject();
            Usuario.getInstance().getListaFavoritas().setFavPelículas(favmovieslist);
            oos2.close();



            FileInputStream fos3 =
                    new FileInputStream(new File(context.getFilesDir(), "usersdata.txt"));
            ObjectInputStream oos3 =
                    new ObjectInputStream(fos3);

            List<Usuario> userslist = (ArrayList<Usuario>) oos3.readObject();
            ListaUsuarios.setListaUsuarios(userslist);
            oos3.close();






            FileInputStream fos4 =
                    new FileInputStream(new File(context.getFilesDir(), "blockedusersdata.txt"));
            ObjectInputStream oos4 =
                    new ObjectInputStream(fos4);

            List<Usuario> blockeduserslist = (ArrayList<Usuario>) oos4.readObject();
            ListaUsuarios.setListaUsuariosBloqueados(blockeduserslist);
            oos4.close();









        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }


}
