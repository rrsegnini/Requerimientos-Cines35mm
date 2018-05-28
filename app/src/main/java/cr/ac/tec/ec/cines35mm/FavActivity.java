package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import cr.ac.tec.ec.utility.Images.*;

import java.util.Collections;
import java.util.List;

import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.Película;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class FavActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        populateTable();
    }

    private void populateTable(){

        TableLayout favs = findViewById(R.id.fav_tblFavourites);
        TableRow newRow = new TableRow(this);
        List<Película> movies = ListaFavoritas.getFavMovies();
        Collections.reverse(movies);

        int i = 0;
        ImageView new_element1 = null;
        ImageView new_element2 = null;
        ImageView new_element3 = null;
        while (i<movies.size()){
            new_element1 = setNewView(movies, i);
            i++;
            /*if (i+1<movies.size()){
                i++;
                new_element2 = setNewView(movies, i);
            }

            if (i+1<movies.size()){
                i++;
                new_element3 = setNewView(movies, i);
            }*/


            favs.addView(new_element1);

        }
    }

    private ImageView setNewView(List<Película> movies, int i){
        Película m = movies.get(i);
        ImageView new_element = new ImageView(this);
        new_element.setId(m.getIdPelícula());

        new DownloadImageTask(new_element)
                .execute(m.getPosterURL());
        new_element.setTag(m.getIdPelícula());

        new_element.setOnClickListener(this);
        //new_element.setAdjustViewBounds(true);
        //new_element.getLayoutParams().height = 400;
       // new_element.getLayoutParams().width = 300;


        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
        //new_element.setBackgroundColor(Color.BLACK+(i*20));


        return new_element;
    }

    public void buttonOnClick(View v){
        Intent detail_screen = new Intent(this, MDetailActivity.class);
        System.out.println(String.valueOf(v.getId()));
        //System.out.println(R.id.main_imgReco1);
        int MovieId = (int)findViewById(v.getId()).getTag();
        //System.out.println("TAG " + MovieId);
        detail_screen.putExtra("MovieId", String.valueOf(MovieId));
        startActivity(detail_screen);
    }


    @Override
    public void onClick(View view) {
        buttonOnClick(view);
    }
}

