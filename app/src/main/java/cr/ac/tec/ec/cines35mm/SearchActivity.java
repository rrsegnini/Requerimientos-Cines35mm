package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchButtonOnClick(View v){
        Button fav = findViewById(R.id.main_btnFav);
        Intent search_screen = new Intent(this, SearchActivity.class);
        startActivity(search_screen);
    }
}
