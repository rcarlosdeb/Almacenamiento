package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Local extends AppCompatActivity {

    private TextView txtTitulo, path;
    private Button btnCrear;
    private ListView listview;
    private ArrayList<String> archivos;


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
        listview = (ListView) findViewById(R.id.listArchivos);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent registro = new Intent(Local.this, RegistroDatos.class);
                //registro.putExtra("local", archivos.get(position));
                Bundle datos = new Bundle();
                datos.putString("titulo","Almacenamiento Local");
                datos.putString("tipo","local");
                datos.putString("archivo",archivos.get(position));
                registro.putExtras(datos);
                startActivity(registro);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        archivos = new ArrayList<String>();
        File[] files = getFilesDir().listFiles();
        for (File arc : files) {
            archivos.add(arc.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, archivos);
        listview.setAdapter(adapter);
    }


    private View.OnClickListener crearRegistro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent registro = new Intent(Local.this, RegistroDatos.class);
            Bundle datos = new Bundle();
            //registro.putExtra("local", 1);
            datos.putString("titulo","Almacenamiento Local");
            datos.putString("tipo","local");
            datos.putString("archivo",null);
            registro.putExtras(datos);
            startActivity(registro);
        }
    };



}
