package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



}
