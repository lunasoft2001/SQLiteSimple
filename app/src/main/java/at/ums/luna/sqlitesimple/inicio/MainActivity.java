package at.ums.luna.sqlitesimple.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import at.ums.luna.sqlitesimple.R;
import at.ums.luna.sqlitesimple.actividades.ListaClientes;
import at.ums.luna.sqlitesimple.actividades.ListadoClientes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void botonListaClientes(View v){
        Intent intento = new Intent(this, ListaClientes.class);
        startActivity(intento);

    }


    public void botonListadoClientes(View v){
        Intent intento = new Intent(this, ListadoClientes.class);
        startActivity(intento);

    }


}
