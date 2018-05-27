package cr.ac.tec.ec.cines35mm;

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
import android.widget.TextView;

import java.io.InputStream;

import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        Película p = ListaPelículas.getSysPelículas().get(1);
        TextView title = findViewById(R.id.detail_txtTitle);
        TextView directors = findViewById(R.id.detail_txtDirector);
        TextView screenplay = findViewById(R.id.detail_txtScreenplay);
        TextView year = findViewById(R.id.detail_txtYear);
        TextView actors = findViewById(R.id.detail_txtActors);
        ImageView poster = findViewById(R.id.detail_imgPoster);


        title.setText(p.getNombre());
        directors.setText(p.getDirectores().get(0));
        screenplay.setText(p.getGuionistas().get(0));
        year.setText(String.valueOf(p.getAño()));
        actors.setText(p.getActores().get(0));

        new DownloadImageTask((ImageView) poster)
                .execute(p.getPosterURL());



        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
