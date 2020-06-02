package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Externo extends AppCompatActivity {
    private TextView txtTitulo, pathext;
    private Button btnPublico, btnPrivado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externo);
        txtTitulo=(TextView)findViewById(R.id.txtTitulo);
        btnPrivado=(Button)findViewById(R.id.privado);
        btnPublico=(Button)findViewById(R.id.publico);
        pathext= (TextView) findViewById(R.id.pathext);
        Bundle datosPrincipal = this.getIntent().getExtras();
        txtTitulo.setText(datosPrincipal.getString("titulo"));
        pathext.setText(getExternalFilesDir(null).getAbsolutePath());
        btnPublico.setOnClickListener(registro);
        btnPrivado.setOnClickListener(registro2);
    }

    private View.OnClickListener registro= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent abrirRegistro = new Intent(Externo.this, RegistroDatos.class);
            Bundle datos= new Bundle();
            //abrirRegistro.putExtra("publico", 1);
            datos.putString("tipo","publico");
            datos.putString("titulo","Almacenamiento externo Público");
            abrirRegistro.putExtras(datos);
            startActivity(abrirRegistro);
        }
    };

    private View.OnClickListener registro2= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent abrirRegistro = new Intent(Externo.this, RegistroDatos.class);
            Bundle datos= new Bundle();
            //abrirRegistro.putExtra("privado", 1);
            datos.putString("tipo","privado");
            datos.putString("titulo","Almacenamiento externo Privado");
            abrirRegistro.putExtras(datos);
            startActivity(abrirRegistro);
        }
    };


}
