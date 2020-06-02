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

    private Button btnLocal, btnExterno;
    String[] permiso = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLocal=(Button) findViewById(R.id.local);
        btnExterno=(Button) findViewById(R.id.externo);
        btnLocal.setOnClickListener(abrirLocal);
        btnExterno.setOnClickListener(abriExterno);
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
