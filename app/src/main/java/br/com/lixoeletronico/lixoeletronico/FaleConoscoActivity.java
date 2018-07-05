package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FaleConoscoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fale_conosco);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button enviar = (Button) findViewById(R.id.faleEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FaleConoscoActivity.this,"Mensagem enviada!", Toast.LENGTH_LONG).show();
                Intent enviar = new Intent(FaleConoscoActivity.this, MenuActivity.class);
                startActivity(enviar);
            }
        });

    }
}
