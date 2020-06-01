package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RegistroDatos extends AppCompatActivity {

    private TextView txtTitulor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);
        Bundle registro = this.getIntent().getExtras();
        txtTitulor=(TextView) findViewById(R.id.txtTituloRegistro);
        txtTitulor.setText(registro.getString("titulo"));
    }
}
