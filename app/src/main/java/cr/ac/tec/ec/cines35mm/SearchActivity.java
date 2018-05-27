package cr.ac.tec.ec.cines35mm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    /*public void searchBar(View v){
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(SearchActivity.this,"Yes",Toast.LENGTH_LONG);
                    return true;
            }

        });
    }*/
}
