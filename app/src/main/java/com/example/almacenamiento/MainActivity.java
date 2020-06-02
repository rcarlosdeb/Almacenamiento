package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLocal, btnExterno, btnExternoPrivado;
    String[] permiso = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLocal=(Button) findViewById(R.id.local);
        btnExterno=(Button) findViewById(R.id.externo);
        btnExternoPrivado=(Button) findViewById(R.id.externoPrivado);
        btnLocal.setOnClickListener(abrirLocal);
        btnExterno.setOnClickListener(abriExterno);
        btnExternoPrivado.setOnClickListener(abriExternoPrivado);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
    private View.OnClickListener abrirLocal = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent localActivity = new Intent(MainActivity.this, Local.class);
            Bundle datos = new Bundle();
            datos.putString("titulo","Almacenamiento Local");
            datos.putString("tipo","local");
            localActivity.putExtras(datos);
            startActivity(localActivity);
        }
    };

    private View.OnClickListener abriExterno = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent externoActivity = new Intent(MainActivity.this, Local.class);
            Bundle datos = new Bundle();
            datos.putString("titulo","Almacenamiento Externo Público");
            datos.putString("tipo","publico");
            externoActivity.putExtras(datos);
            startActivity(externoActivity);

        }
    };

    private View.OnClickListener abriExternoPrivado = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent externoActivity = new Intent(MainActivity.this, Local.class);
            Bundle datos = new Bundle();
            datos.putString("titulo","Almacenamiento Externo Privado");
            datos.putString("tipo","privado");
            externoActivity.putExtras(datos);
            startActivity(externoActivity);

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (!(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED )){
                    Toast.makeText(MainActivity.this, "Permission denied to access your external storage.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
