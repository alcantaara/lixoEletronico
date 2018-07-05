package br.com.lixoeletronico.lixoeletronico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LixoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lixo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
