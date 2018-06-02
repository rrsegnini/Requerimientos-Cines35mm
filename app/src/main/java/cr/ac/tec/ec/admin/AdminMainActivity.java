package cr.ac.tec.ec.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cr.ac.tec.ec.cines35mm.R;

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
    }
}
