package at.ums.luna.sqlitesimple.actividades;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import at.ums.luna.sqlitesimple.R;
import at.ums.luna.sqlitesimple.adaptadores.ProveedoresSpinnerAdapter;
import at.ums.luna.sqlitesimple.database.DataSourceListaClientes;
import at.ums.luna.sqlitesimple.database.DataSourceListadoProveedores;
import at.ums.luna.sqlitesimple.modelos.Proveedores;

public class FormularioClientes extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private SQLiteDatabase db;
    DataSourceListaClientes mDataSourceListaClientes;
    DataSourceListadoProveedores mDataSourceListadoProveedores;

    private TextView idCliente;
    private EditText nombre;
    private EditText direccion;
    private EditText telefono;

    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_clientes);


        //Asociamos elementos variables a elementos de la vista
        idCliente = (TextView) findViewById(R.id.tvIdCliente);
        nombre = (EditText) findViewById(R.id.etNombre);
        direccion = (EditText) findViewById(R.id.etDireccion);
        telefono = (EditText) findViewById(R.id.etTelefono);

        spinner = (Spinner) findViewById(R.id.spinnerProveeedor);


        spinner.setOnItemSelectedListener(this);



        //Rellenamos los campos
        Intent intento = getIntent();

        Bundle bundle = intento.getExtras();
        if(bundle != null){
            idCliente.setText(bundle.getString("idCliente"));
            nombre.setText(bundle.getString("nombre"));
            direccion.setText(bundle.getString("direccion"));
            telefono.setText(bundle.getString("telefono"));

        }




        //Abrimos la BD en modo escritura
        mDataSourceListaClientes = new DataSourceListaClientes(this);
        mDataSourceListadoProveedores = new DataSourceListadoProveedores(this);
        mDataSourceListaClientes.abrir();
        mDataSourceListadoProveedores.abrir();



//        cargaSpinnerSimple();
        cargaSpinner();

        //Codigo para el onClickListener
        findViewById(R.id.btCancelar).setOnClickListener(mGlobal_onClickListener);
        findViewById(R.id.btEliminar).setOnClickListener(mGlobal_onClickListener);
        findViewById(R.id.btGuardar).setOnClickListener(mGlobal_onClickListener);

    }

    //Intents para cualquier botón de la actividad
    final View.OnClickListener mGlobal_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btCancelar:
                    finish();
                    break;
                case R.id.btEliminar:
                    Eliminar();
                    break;
                case R.id.btGuardar:
                    Actualizar();
                    break;
            }
        }
    };

    public void Eliminar() {

        CharSequence idActual = idCliente.getText();
        mDataSourceListaClientes.eliminarCliente(idActual);

        Toast.makeText(this,"El registro " + idActual + " ha sido eliminado", Toast.LENGTH_LONG).show();

        Intent a = new Intent(this, ListadoClientes.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);



        finish();

    }

    public void Actualizar() {
        //Recuperamos los campos de texto
        CharSequence idActual = idCliente.getText();
        String nombreTxt = nombre.getText().toString();
        String direccionTxt = direccion.getText().toString();
        String telefonoTxt = telefono.getText().toString();


        mDataSourceListaClientes.actualizarCliente(idActual, nombreTxt, direccionTxt, telefonoTxt);

        Toast.makeText(this, "El registro " + idActual + " ha sido actiualizado", Toast.LENGTH_LONG).show();

        Intent a = new Intent(this, ListadoClientes.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);

        finish();
    }



    private void cargaSpinner(){
        ProveedoresSpinnerAdapter proveedorAdapter;
        DataSourceListadoProveedores db = new DataSourceListadoProveedores(getApplicationContext());
        List<Proveedores> proveedores = db.getAll();
        proveedorAdapter = new ProveedoresSpinnerAdapter(FormularioClientes.this,
                android.R.layout.simple_spinner_item, proveedores);
        spinner.setAdapter(proveedorAdapter);

        proveedorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Proveedores selected = (Proveedores) parent.getItemAtPosition(position);
                String item = selected.getNombre();

                Toast.makeText(FormularioClientes.this,item,Toast.LENGTH_LONG).show();
                nombre.setText(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void cargaSpinnerSimple(){
        ArrayAdapter<String> spinnerAdapter;
        DataSourceListadoProveedores db = new DataSourceListadoProveedores(getApplicationContext());
        List<String> proveedores = db.getAll_Simple();
        spinnerAdapter = new ArrayAdapter<String>(FormularioClientes.this, android.R.layout.simple_spinner_item,proveedores);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                Proveedores selected = (Proveedores) parent.getItemAtPosition(position);
//                String item = selected.getNombre();

                String item = (String) ((TextView) view).getText();



                Toast.makeText(FormularioClientes.this,item,Toast.LENGTH_LONG).show();
                nombre.setText(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent a = new Intent(this, ListadoClientes.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Proveedores selected = (Proveedores) parent.getItemAtPosition(position);
        String item = selected.getNombre();

        Toast.makeText(FormularioClientes.this, item, Toast.LENGTH_LONG).show();
        nombre.setText(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        nombre.setText( "");
        direccion.setText( "");
    }
}
