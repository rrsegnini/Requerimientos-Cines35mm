package cr.ac.tec.ec.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

import cr.ac.tec.ec.cines35mm.MDetailActivity;
import cr.ac.tec.ec.cines35mm.R;
import cr.ac.tec.ec.cines35mm.SearchActivity;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.ListaUsuarios;
import cr.ac.tec.ec.domain.Usuario;

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
                /*Intent new_intent = new Intent(AdminMainActivity.this,
                        AdminBlockActivity.class);

                startActivity(new_intent);*/

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminMainActivity.this);
                builder.setTitle("User to block or unblock (username)");

                final EditText input = new EditText(AdminMainActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Usuario.getInstance();
                        String user = input.getText().toString();
                        if (ListaUsuarios.isBlocked(user)){
                            if (ListaUsuarios.unblockUser(user)) {
                                cr.ac.tec.ec.data.Database.createBlockedUsersData(AdminMainActivity.this);
                                Toast.makeText(AdminMainActivity.this, "User UNblocked!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AdminMainActivity.this, "Username not found",
                                        Toast.LENGTH_LONG).show();
                            }

                        }else {
                            if (ListaUsuarios.blockUser(user)) {
                                cr.ac.tec.ec.data.Database.createBlockedUsersData(AdminMainActivity.this);
                                Toast.makeText(AdminMainActivity.this, "User blocked!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AdminMainActivity.this, "Username not found",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

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

    //private class MyWebViewClient extends Web
}
