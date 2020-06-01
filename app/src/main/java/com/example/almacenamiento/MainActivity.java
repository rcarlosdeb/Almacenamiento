package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLocal, btnExterno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLocal=(Button) findViewById(R.id.local);
        btnExterno=(Button) findViewById(R.id.externo);
        btnLocal.setOnClickListener(abrirLocal);
        btnExterno.setOnClickListener(abriExterno);
    }
    private View.OnClickListener abrirLocal = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent localActivity = new Intent(MainActivity.this, Local.class);
            Bundle datos = new Bundle();
            datos.putString("titulo","Almacenamiento Local");
            localActivity.putExtras(datos);
            startActivity(localActivity);
        }
    };

    private View.OnClickListener abriExterno = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent externoActivity = new Intent(MainActivity.this, Externo.class);
            Bundle datos = new Bundle();
            datos.putString("titulo","Almacenamiento Externo");
            externoActivity.putExtras(datos);
            startActivity(externoActivity);

        }
    };
}
