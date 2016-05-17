package at.ums.luna.sqlitesimple.actividades;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import at.ums.luna.sqlitesimple.R;
import at.ums.luna.sqlitesimple.database.DBHelper;
import at.ums.luna.sqlitesimple.database.DataSourceListaClientes;
import at.ums.luna.sqlitesimple.database.DataSourceListadoProveedores;
import at.ums.luna.sqlitesimple.modelos.Clientes;
import at.ums.luna.sqlitesimple.modelos.Proveedores;

public class FormularioClientes extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private SQLiteDatabase db;
    DataSourceListaClientes mDataSourceListaClientes;
    DataSourceListadoProveedores mDataSourceListadoProveedores;

    private TextView idCliente;
    private EditText nombre;
    private EditText direccion;
    private EditText telefono;

    private Spinner prove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_clientes);


        //Asociamos elementos variables a elementos de la vista
        idCliente = (TextView) findViewById(R.id.tvIdCliente);
        nombre = (EditText) findViewById(R.id.etNombre);
        direccion = (EditText) findViewById(R.id.etDireccion);
        telefono = (EditText) findViewById(R.id.etTelefono);


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

        /**
         * Spinner
         */
        Spinner spinner = (Spinner) findViewById(R.id.spinnerProveeedor);
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_dropdown_item, mDataSourceListadoProveedores.verListadoProveedores(),
            new String[]{DBHelper.ColProveedores.NOMBRE},
            new int[]{android.R.id.text1},
            SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(this);


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



        mDataSourceListaClientes.actualizarCliente(idActual,nombreTxt,direccionTxt,telefonoTxt);

        Toast.makeText(this,"El registro " + idActual + " ha sido actiualizado", Toast.LENGTH_LONG).show();

        Intent a = new Intent(this, ListadoClientes.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);

        finish();

//        //metodo Update
//        ContentValues actualizar = new ContentValues();
//        actualizar.put(DBHelper.ColClientes.NOMBRE, nombreTxt);
//        actualizar.put(DBHelper.ColClientes.DIRECCION, direccionTxt);
//        actualizar.put(DBHelper.ColClientes.TELEFONO,telefonoTxt);
//
//        db.update(DBHelper.Tablas.CLIENTES, actualizar, DBHelper.ColClientes.ID +"="+idActual, null);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),item,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
}
