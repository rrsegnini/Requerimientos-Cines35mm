package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.domain.Usuario;

public class MDetailActivity extends AppCompatActivity {
    private int _MovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add to favourite movies?", Snackbar.LENGTH_LONG)
                        .setAction("ADD", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Película p = ListaPelículas.getMovieById(_MovieId);
                                ListaFavoritas.addFavMovie(p);
                            }
                        }).show();
            }
        });

        setDetails();
        RatingBar rating = findViewById(R.id.detail_barRating);
        Película p = ListaPelículas.getMovieById(_MovieId);
        rating.setRating(p.getCalificación());

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println(ratingBar.getRating());
                Película p = ListaPelículas.getMovieById(_MovieId);
                p.setCalificación(ratingBar.getRating());

            }
        });





    }



    protected void setDetails(){
        Intent intent = getIntent();
        int MovieId = (Integer.parseInt(intent.getStringExtra("MovieId")));
        _MovieId = MovieId;

        setReviews(MovieId);


        Película p = ListaPelículas.getMovieById(MovieId);
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

        new MDetailActivity.DownloadImageTask((ImageView) poster)
                .execute(p.getPosterURL());


    }

    private void setReviews(int MovieId){

        Película p = ListaPelículas.getMovieById(MovieId);
        TableLayout table_reviews = findViewById(R.id.detail_tblReviews);
        List<List<Object>> list_reviews = p.getComentarios();


        int i = 0;
        for (List<Object> m: list_reviews){
            TextView username = new TextView(this);
            username.setTextSize(20);
            username.setGravity(View.TEXT_ALIGNMENT_CENTER);//.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView rev = new TextView(this);
            rev.setGravity(View.TEXT_ALIGNMENT_CENTER);
            rev.setTextSize(30);


            Usuario u = (Usuario)m.get(0);
            String s = (String)m.get(1);
            TableRow newRow = new TableRow(this);
            newRow.setId(i + 1);
            i++;


            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            //trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            newRow.setPadding(20,20,20,20);
            newRow.setLayoutParams(trParams);


            username.setText(u.getUsername());
            rev.setText(s);

            if(newRow.getParent()!=null) {
                ((TableLayout) newRow.getParent()).removeView(newRow);
            }
            //newRow.getParent().removeView(newRow);


            newRow.addView(username);
            newRow.addView(rev);
            table_reviews.addView(newRow);
            /*
            table_reviews.addView(username);
            table_reviews.addView(rev);*/
        }


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
