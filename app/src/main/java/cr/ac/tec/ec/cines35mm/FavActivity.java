package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cr.ac.tec.ec.domain.Usuario;
import cr.ac.tec.ec.utility.Images.*;

import java.util.Collections;
import java.util.List;

import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.utility.MString;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class FavActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        populateTable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateTable();

    }

    private void populateTable(){
        TableLayout favs = findViewById(R.id.fav_tblFavourites);
        //CardView favs_crd = findViewById(R.id.fav_crdFavourites);
        //favs_crd.removeAllViews();
        favs.removeAllViews();
        TableRow newRow = new TableRow(this);
        List<Película> movies = Usuario.getInstance().getListaFavoritas().getFavMovies();
        Collections.reverse(movies);

        int i = 0;
        CardView new_element1 = null;
        while (i<movies.size()){
            new_element1 = setNewView(movies, i);
            i++;
            //favs_crd.addView(new_element1);
            favs.addView(new_element1);

        }
    }
    private CardView setNewView(List<Película> movies, int i){
        Película m = movies.get(i);
        LinearLayout layout = new LinearLayout(this);
        ImageView new_element = new ImageView(this);
        CardView cardview = new CardView(this);
        TextView textview = new TextView(this);
        TextView directors = new TextView(this);
        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layout.setOrientation(LinearLayout.VERTICAL);
        new_element.setId(m.getIdPelícula());

        new DownloadImageTask(new_element)
                .execute(m.getPosterURL());
        new_element.setTag(m.getIdPelícula());

        //cardview.setPreventCornerOverlap(false);

        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
        new_element.setOnClickListener(this);


        //new_text.setText(m.getNombre());
        cardview.setLayoutParams(layoutparams);
        //cardview.setRadius(35);
        //cardview.setPadding(25, 25, 25, 25);
        cardview.setCardBackgroundColor(Color.TRANSPARENT);
        //cardview.setMaxCardElevation(30);
        //cardview.setMaxCardElevation(16);
        textview = new TextView(this);
        textview.setLayoutParams(layoutparams);
        textview.setText(m.getNombre());// + "\n" + "Directed by " + m.getDirectores());
        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
        textview.setTextColor(Color.BLACK);
        textview.setPadding(25,10,25,5);
        textview.setGravity(Gravity.CENTER);

        directors.setText(MString.toString(m.getDirectores()));
        directors.setTextSize(20);

        layout.addView(new_element);
        layout.addView(textview);
        layout.addView(directors);
        cardview.addView(layout);
        /*cardview.addView(textview);
        cardview.addView(new_element);*/
        //new_card.addView(new_text);

        return cardview;
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

