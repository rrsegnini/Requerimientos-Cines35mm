package cr.ac.tec.ec.admin;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import cr.ac.tec.ec.cines35mm.MDetailActivity;
import cr.ac.tec.ec.cines35mm.R;
import cr.ac.tec.ec.cines35mm.SearchActivity;
import cr.ac.tec.ec.domain.ListaFavoritas;
import cr.ac.tec.ec.domain.ListaPelículas;
import cr.ac.tec.ec.domain.Película;
import cr.ac.tec.ec.utility.Images;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.EasyImageConfig;

public class AdminMovieActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Save movie?", Snackbar.LENGTH_LONG)
                        .setAction("SAVE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try{
                                    Película p = new Película();

                                    TextView title = findViewById(R.id.detail_txtTitle);
                                    TextView directors = findViewById(R.id.detail_txtDirector);
                                    TextView screenplay = findViewById(R.id.detail_txtScreenplay);
                                    TextView year = findViewById(R.id.detail_txtYear);
                                    TextView actors = findViewById(R.id.detail_txtActors);
                                    TextView poster = findViewById(R.id.admin_txtURL);

                                    p.setNombre(String.valueOf(title.getText()));
                                    p.setDirectores(Arrays.asList(String
                                            .valueOf(directors.getText()).split("\\,")));
                                    p.setGuionistas(Arrays.asList(String
                                            .valueOf(screenplay.getText()).split("\\,")));
                                    p.setAño(Integer.parseInt(String.valueOf(year.getText())));
                                    p.setActores(Arrays.asList(String
                                            .valueOf(actors.getText()).split("\\,")));
                                    p.setPosterURL(String.valueOf(poster.getText()));

                                    ListaPelículas.addSysMovie(p);


                                    Toast.makeText(AdminMovieActivity.this, "Movie saved!",
                                            Toast.LENGTH_LONG).show();
                                }catch(Exception e){
                                    Toast.makeText(AdminMovieActivity.this, e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                        }).show();
            }
        });

        //findViewById(R.id.admin_webWeb);


        WebView web = findViewById(R.id.admin_webWeb);
        WebSettings mWebSettings = web.getSettings();
        /*web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setAllowContentAccess(true);

        if (Build.VERSION.SDK_INT > 15) {
            web.getSettings().setAllowUniversalAccessFromFileURLs(true);
            web.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);*/
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowContentAccess(true);


        web.loadUrl("https://imgbb.com/");
        web.setWebChromeClient(new WebChromeClient() {

            //WebView mWebView;
            private ValueCallback<Uri> mUploadMessage;
            public ValueCallback<Uri[]> uploadMessage;
            public static final int REQUEST_SELECT_FILE = 100;
            private final static int FILECHOOSER_RESULTCODE = 1;

            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }


            // For Lollipop 5.0+ Devices
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;
                Intent intent = new Intent();


                if (Build.VERSION.SDK_INT > 20) {
                    System.out.println("EL API ES " + Build.VERSION.SDK_INT);
                    intent = fileChooserParams.createIntent();
                }
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(AdminMovieActivity.this, "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;

            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });


        final EditText url = findViewById(R.id.admin_txtURL);
        final ImageView img = findViewById(R.id.detail_imgPoster);
        url.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                try {
                    String _url = url.getText().toString();




                    new Images.DownloadImageTask(img)
                            .execute(_url);

                }catch(Exception e){

                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.print(url.getText());
            }
        });








        findViewById(R.id.detail_imgPoster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openGallery(AdminMovieActivity.this,
                        EasyImageConfig.REQ_PICK_PICTURE_FROM_GALLERY);
            }
        });

    }





    //flipscreen not loading again
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }



}
