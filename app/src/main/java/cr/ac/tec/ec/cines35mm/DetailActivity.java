package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.common.collect.Table;

import java.io.InputStream;
import java.util.List;

import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.domain.Usuario;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);



        setDetails();

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    protected void setDetails(){
        Intent intent = getIntent();
        int MovieId = (Integer.parseInt(intent.getStringExtra("MovieId")));


        Película p = ListaPelículas.getMovieById(MovieId);
        //Película p = ListaPelículas.getSysPelículas().get(0);
        TextView title = findViewById(R.id.detail_txtTitle);
        TextView directors = findViewById(R.id.detail_txtDirector);
        TextView screenplay = findViewById(R.id.detail_txtScreenplay);
        TextView year = findViewById(R.id.detail_txtYear);
        TextView actors = findViewById(R.id.detail_txtActors);
        ImageView poster = findViewById(R.id.detail_imgPoster);


        title.setText("Title: " + p.getNombre());
        directors.setText("Director(s): " + toString(p.getDirectores()));
        screenplay.setText("Screenplay by: " + toString(p.getGuionistas()));
        year.setText("Year: " + String.valueOf(p.getAño()));
        actors.setText("Actors: " + toString(p.getActores()));

        new DownloadImageTask((ImageView) poster)
                .execute(p.getPosterURL());


    }

    private String toString(List<String> s){
        String result = "";
        for (int i=0;i<s.size();i++){
            if (i==s.size()-1){
                result = result + s.get(i);
            }else {
                result = result + s.get(i) + ", ";
            }
        }
        return result;
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
