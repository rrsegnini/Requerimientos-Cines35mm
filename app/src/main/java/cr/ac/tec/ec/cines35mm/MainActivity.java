package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.ListaRecomendaciones;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.domain.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainScreen();




    }

    @Override
    protected void onResume(){
        super.onResume();
        setMainScreen();
    }

    private void setMainScreen(){
        ImageView img = findViewById(R.id.main_imgReco1);

        List<Película> sys_movies = ListaPelículas.getSysPelículas();
        List<Película> fav_movies = Usuario.getInstance().getListaFavoritas().getFavMovies();

       Arrays.asList((ImageView) findViewById(R.id.main_imgReco1),
                (ImageView) findViewById(R.id.main_imgRecent1));

        for (int i = 0; i<sys_movies.size(); i++){
            System.out.println(sys_movies.get(i).getNombre());
        }


        List<Película> recos = ListaRecomendaciones.getRecos();

        for (int i=0;i<recos.size();i++) {

            //////////RECOMMENDATIONS
            Película p1 = recos.get(i);

            if (i == 0) {
                new DownloadImageTask((ImageView) findViewById(R.id.main_imgReco1))
                        .execute(p1.getPosterURL());
                findViewById(R.id.main_imgReco1).setTag(p1.getIdPelícula());
            }
            if (i == 1) {
                new DownloadImageTask((ImageView) findViewById(R.id.main_imgNextReco))
                        .execute(p1.getPosterURL());
                findViewById(R.id.main_imgNextReco).setTag(p1.getIdPelícula());
            }
            if (i == 2) {
                new DownloadImageTask((ImageView) findViewById(R.id.main_imgPrevReco))
                        .execute(p1.getPosterURL());
                findViewById(R.id.main_imgPrevReco).setTag(p1.getIdPelícula());
            }

            if (i==2){
                break;
            }
        }


        ////////RECENTLY ADDED
        for (int i=0;i<fav_movies.size();i++) {
            Película rp1 = fav_movies.get(i);

            if (i == 0) {
                new DownloadImageTask((ImageView) findViewById(R.id.main_imgRecent1))
                        .execute(rp1.getPosterURL());
                findViewById(R.id.main_imgRecent1).setTag(rp1.getIdPelícula());
            }
            if (i == 1) {
                new DownloadImageTask((ImageView) findViewById(R.id.main_imgPrevRecent))
                        .execute(rp1.getPosterURL());
                findViewById(R.id.main_imgPrevRecent).setTag(rp1.getIdPelícula());
            }
            if (i == 2) {
                new DownloadImageTask((ImageView) findViewById(R.id.main_imgNextRecent))
                        .execute(rp1.getPosterURL());
                findViewById(R.id.main_imgNextRecent).setTag(rp1.getIdPelícula());
            }

            if (i==2){
                break;
            }
        }







    }


    public void buttonOnClick(View v){
        Button fav = findViewById(R.id.main_btnFav);
        Intent fav_screen = new Intent(this, FavActivity.class);
        startActivity(fav_screen);
    }

    public void searchButtonOnClick(View v){
        Button fav = findViewById(R.id.main_btnFav);
        Intent search_screen = new Intent(this, SearchActivity.class);
        startActivity(search_screen);
    }

    public void imageOnClick(View v){
        Intent detail_screen = new Intent(this, MDetailActivity.class);
        System.out.println(String.valueOf(v.getId()));
        //System.out.println(R.id.main_imgReco1);
        int MovieId = (int)findViewById(v.getId()).getTag();
        System.out.println("TAG " + MovieId);
        detail_screen.putExtra("MovieId", String.valueOf(MovieId));
        startActivity(detail_screen);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                //in.reset();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }








}
