package com.example.almacenamiento;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
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
    private File ruta;
    Bundle registro;
    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        btnCrear= (Button)findViewById(R.id.btnCrear);
        path=(TextView)findViewById(R.id.path);
        registro = this.getIntent().getExtras();
        txtTitulo.setText(registro.getString("titulo"));
        //path.setText(getFilesDir().getAbsolutePath());
        btnCrear.setOnClickListener(crearRegistro);
        listview = (ListView) findViewById(R.id.listArchivos);


        //Evento clic para la lista de archivos donde pasa el archivo que se va a editar y el tipo de almacenamiento para obtner la ruta
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent registro = new Intent(Local.this, RegistroDatos.class);
                //registro.putExtra("local", archivos.get(position));
                Bundle datos = new Bundle();
                datos.putString("titulo",registro.getStringExtra("titulo"));
                datos.putString("tipo",tipo);
                datos.putString("archivo",archivos.get(position));
                registro.putExtras(datos);
                startActivity(registro);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart()
    {
        super.onStart();
        //Se obtienen los archivos en diferente ruta dependiendo del tipo de almacenamiento

        //CREO QUE ES NECECESARIO CREAR UNA CARPETA(FILE) DE ALMACENAMIENTO PUBLICO Y GUARDARLO EN LA VARIABLE RUTA, NO SE xd
        archivos = new ArrayList<String>();
        File[] files;
        if (registro.getString("tipo").equals("local")){
            ruta=getFilesDir();
            files = getFilesDir().listFiles();
            tipo="local";
        }else{
            if (registro.getString("tipo").equals("publico")){
                //CAMBIAR LA RUTA PARA EL DIRECTORIO PUBLICO
                ruta=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                files = ruta.listFiles();
                tipo="publico";
            }else {
                ruta = getExternalFilesDir(null);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    files = getExternalFilesDirs(null)[0].listFiles();
                    tipo="privado";
                }else{
                    files=null;
                }
            }
        }
        path.setText(ruta.toString());
        for (File arc : files) {
            archivos.add(arc.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, archivos);
        listview.setAdapter(adapter);

    }

//Se le pasa el tipo de almacenamiento para obtner la ruta de donde esta el archivo
    private View.OnClickListener crearRegistro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent registro = new Intent(Local.this, RegistroDatos.class);
            Bundle datos = new Bundle();
            //registro.putExtra("local", 1);
            datos.putString("titulo","Almacenamiento Local");
            datos.putString("tipo",tipo);
            datos.putString("archivo",null);
            registro.putExtras(datos);
            startActivity(registro);
        }
    };



}
