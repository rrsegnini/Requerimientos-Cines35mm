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

import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainScreen();




    }

    private void setMainScreen(){
        ImageView img = findViewById(R.id.main_imgReco1);

        List<Película> sys_movies = ListaPelículas.getSysPelículas();

       Arrays.asList((ImageView) findViewById(R.id.main_imgReco1),
                (ImageView) findViewById(R.id.main_imgRecent1));

        for (int i = 0; i<sys_movies.size(); i++){
            System.out.println(sys_movies.get(i).getNombre());
        }


        new DownloadImageTask((ImageView) findViewById(R.id.main_imgReco1))
                .execute(sys_movies.get(0).getPosterURL());

        new DownloadImageTask((ImageView) findViewById(R.id.main_imgRecent1))
                .execute(sys_movies.get(1).getPosterURL());


    }

    @Override
    protected void onResume() {
        super.onResume();

        //new DownloadImageTask((ImageView) findViewById(R.id.main_imgReco1))
        //        .execute("https://image.ibb.co/kUBeVo/m2001.jpg");

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
        Intent detail_screen = new Intent(this, DetailActivity.class);
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
