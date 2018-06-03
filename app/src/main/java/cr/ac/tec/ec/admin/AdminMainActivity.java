package cr.ac.tec.ec.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cr.ac.tec.ec.cines35mm.R;
import cr.ac.tec.ec.cines35mm.SearchActivity;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        findViewById(R.id.admin_btnAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_intent = new Intent(AdminMainActivity.this,
                        AdminMovieActivity.class);

                startActivity(new_intent);

            }
        });

        findViewById(R.id.admin_btnBlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_intent = new Intent(AdminMainActivity.this,
                        AdminBlockActivity.class);

                startActivity(new_intent);

            }
        });

        findViewById(R.id.admin_btnEditMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_intent = new Intent(AdminMainActivity.this,
                        AdminSearchActivity.class);

                startActivity(new_intent);

            }
        });
    }
}
