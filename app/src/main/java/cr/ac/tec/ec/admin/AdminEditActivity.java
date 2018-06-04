package cr.ac.tec.ec.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import cr.ac.tec.ec.cines35mm.MDetailActivity;
import cr.ac.tec.ec.cines35mm.R;
import cr.ac.tec.ec.domain.Género;
import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.domain.Usuario;


public class AdminEditActivity extends AppCompatActivity {
    private int _MovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Spinner mySpinner = findViewById(R.id.detail_spnGen2);
        mySpinner.setAdapter(new ArrayAdapter<Género>(this, android.R.layout.simple_list_item_1, Género.values()));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setListeners();
    }

    @Override
    protected void onResume(){
        super.onResume();
        ListaFavoritas.refreshMovies();
        setReviews(_MovieId);
    }


    protected void setListeners(){

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Save changes?", Snackbar.LENGTH_LONG)
                        .setAction("SAVE", new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                try {
                                    //ListaFavoritas.deleteMovie(_MovieId);

                                    Película p = ListaPelículas.getMovieById(_MovieId);

                                    TextView title = findViewById(R.id.detail_txtTitle);
                                    TextView directors = findViewById(R.id.detail_txtDirector);
                                    TextView screenplay = findViewById(R.id.detail_txtScreenplay);
                                    TextView year = findViewById(R.id.detail_txtYear);
                                    TextView actors = findViewById(R.id.detail_txtActors);
                                    ImageView poster = findViewById(R.id.detail_imgPoster);
                                    Spinner gen = findViewById(R.id.detail_spnGen2);

                                    p.setNombre(String.valueOf(title.getText()));

                                    p.setDirectores(Arrays.asList(String
                                            .valueOf(directors.getText()).split("\\,")));

                                    p.setGuionistas(Arrays.asList(String
                                            .valueOf(screenplay.getText()).split("\\,")));

                                    p.setAño(Integer.parseInt(String.valueOf(year.getText())));

                                    p.setActores(Arrays.asList(String
                                            .valueOf(actors.getText()).split("\\,")));

                                    p.setGénero(Género.valueOf(gen.getSelectedItem().toString()));

                                    cr.ac.tec.ec.data.Database.createMoviesData(AdminEditActivity.this);


                                    Toast.makeText(AdminEditActivity.this, "Saved!",
                                            Toast.LENGTH_LONG).show();


                                }catch(Exception e){
                                    Toast.makeText(AdminEditActivity.this, e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        }).show();
            }
        });



        setDetails();


        /*rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println(ratingBar.getRating());
                Película p = ListaPelículas.getMovieById(_MovieId);
                p.setCalificación(ratingBar.getRating());

            }
        });*/
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
        Spinner gen = findViewById(R.id.detail_spnGen2);


        title.setText(p.getNombre());
        directors.setText(toString(p.getDirectores()));
        screenplay.setText(toString(p.getGuionistas()));
        year.setText(String.valueOf(p.getAño()));
        actors.setText(toString(p.getActores()));

        int spinsize = gen.getCount();
        int igen = 0;
        for (int i=0;i<spinsize;i++){
            if (gen.getItemAtPosition(i).toString().equals(p.getGénero().toString())){
                igen = i;
            }
        }
        gen.setSelection(igen);

        new cr.ac.tec.ec.utility.Images.DownloadImageTask((ImageView) poster)
                .execute(p.getPosterURL());


    }

    private void setReviews(int MovieId){

        Película p = ListaPelículas.getMovieById(MovieId);
        TableLayout table_reviews = findViewById(R.id.detail_tblReviews);
        table_reviews.removeAllViews();
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

}
