package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Local extends AppCompatActivity {

    private TextView txtTitulo, path;
    private Button btnCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        btnCrear= (Button)findViewById(R.id.btnCrear);
        path=(TextView)findViewById(R.id.path);
        Bundle datosPrincipal = this.getIntent().getExtras();
        txtTitulo.setText(datosPrincipal.getString("titulo"));
        path.setText(getFilesDir().getAbsolutePath());
        btnCrear.setOnClickListener(crearRegistro);
    }

    private View.OnClickListener crearRegistro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent registro = new Intent(Local.this, RegistroDatos.class);
            Bundle datos = new Bundle();
            datos.putString("titulo","Almacenamiento Local");
            registro.putExtras(datos);
            startActivity(registro);
        }
    };
}
