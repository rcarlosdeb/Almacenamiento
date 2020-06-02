package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegistroDatos extends AppCompatActivity {

    private TextView txtTitulor;
    private TextView txtNombre;
    private TextView txtContenido;
    private Button btnGuardar;
    private Intent intento;
    private File ruta;
    private String nombreNuevo=null;
    private File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);
        Bundle registro = this.getIntent().getExtras();
        txtTitulor=(TextView) findViewById(R.id.txtTituloRegistro);
        txtTitulor.setText(registro.getString("titulo"));
        txtNombre=(TextView) findViewById(R.id.txtNombre);
        txtContenido=(TextView) findViewById(R.id.txtContenido);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(crearArchivo);

        intento = getIntent();

        if (registro.getString("tipo").equals("local")){
            ruta=getFilesDir();
            files = getFilesDir().listFiles();
        }else{
            if (registro.getString("tipo").equals("publico")){
                ruta=getFilesDir();
                files = getFilesDir().listFiles();
            }else {
                ruta = getExternalFilesDir(null);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    files = getExternalFilesDirs(null)[0].listFiles();
                }else{
                    files=null;
                }
            }
        }
        FileInputStream fis = null;
        if (!(registro.getString("archivo")==null)){
            txtContenido.setText("nulo");
        }

        if (!(registro.getString("archivo")==null) && files!=null){
            try {

                for (File arc : files) {
                    if (arc.getName().equals(intento.getStringExtra("archivo"))) {
                        txtNombre.setText(arc.getName());
                        fis = new FileInputStream(arc);
                        nombreNuevo=arc.getName();
                    }
                }


                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                inputStreamReader.close();
                fis.close();

                txtContenido.setText(sb);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private View.OnClickListener crearArchivo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FileOutputStream fos;
            File file;

            file = new File(ruta, txtNombre.getText().toString());
            if (nombreNuevo!=null){
                for (File arc : files) {
                    if (arc.getName().equals(nombreNuevo)) {
                        arc.delete();
                    }
                }
            }
            try {
                fos = new FileOutputStream(file);
                fos.write(txtContenido.getText().toString().getBytes());
                fos.close();
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };
}
