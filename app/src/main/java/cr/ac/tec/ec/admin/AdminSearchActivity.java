package cr.ac.tec.ec.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

import cr.ac.tec.ec.cines35mm.MDetailActivity;
import cr.ac.tec.ec.cines35mm.R;
import cr.ac.tec.ec.cines35mm.SearchActivity;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.utility.Images;

public class AdminSearchActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search);
        setSearchBar();
    }

    private void setSearchBar(){


        SearchView answer = findViewById(R.id.search_svwSearch);
        answer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Película> matches = ListaPelículas.getMoviesByName(s);
                TableLayout matches_table = findViewById(R.id.search_tblMatches);
                if (matches.isEmpty()){
                    matches_table.removeAllViews();
                    Toast.makeText(AdminSearchActivity.this,"No movie found :(",
                            Toast.LENGTH_LONG).show();
                    return false;
                }else{
                    matches_table.removeAllViews();
                    for (int i=0; i<matches.size();i++){
                        //TextView movie = new TextView(SearchActivity.this);
                        Película m = matches.get(i);
                        //matches_table.addView(movie);

                        ImageView new_element = new ImageView(AdminSearchActivity.this);
                        new_element.setId(m.getIdPelícula());

                        new Images.DownloadImageTask(new_element)
                                .execute(m.getPosterURL());
                        new_element.setTag(m.getIdPelícula());

                        new_element.setOnClickListener(AdminSearchActivity.this);
                        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
                        matches_table.addView(new_element);
                    }
                    return false;
                }

            }
        });


    }

    public void searchButtonOnClick(View v){
        Button fav = findViewById(R.id.main_btnFav);
        Intent search_screen = new Intent(this, SearchActivity.class);
        startActivity(search_screen);
    }

    /*public void searchBar(View v){
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(SearchActivity.this,"Yes",Toast.LENGTH_LONG).show();
                    return true;
            }

        });
    }*/

    @Override
    public void onClick(View view) {
        Intent detail_screen = new Intent(this, AdminEditActivity.class);
        System.out.println(String.valueOf(view.getId()));
        //System.out.println(R.id.main_imgReco1);
        int MovieId = (int)findViewById(view.getId()).getTag();
        //System.out.println("TAG " + MovieId);
        detail_screen.putExtra("MovieId", String.valueOf(MovieId));
        startActivity(detail_screen);
    }
}
