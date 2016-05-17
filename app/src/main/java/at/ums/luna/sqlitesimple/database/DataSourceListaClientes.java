package at.ums.luna.sqlitesimple.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import at.ums.luna.sqlitesimple.modelos.Clientes;

/**
 * Created by luna-aleixos on 17.05.2016.
 */
public class DataSourceListaClientes {

    DBHelper ayudaDb;
    SQLiteDatabase db;

    public static final String [] todasColumnasListaClientes = {
            DBHelper.ColClientes.ID,
            DBHelper.ColClientes.NOMBRE,
            DBHelper.ColClientes.DIRECCION,
            DBHelper.ColClientes.TELEFONO
    };

    public DataSourceListaClientes (Context context){
        ayudaDb = new DBHelper(context);}

    public void abrir(){db = ayudaDb.getWritableDatabase();
    }

    public void cerrar(){ ayudaDb.close();}

    public List<Clientes> verListadoClientes(){
        Cursor cursor = db.query(DBHelper.Tablas.CLIENTES, todasColumnasListaClientes, null, null, null, null, null);

        List<Clientes> listaClientes = new ArrayList<>();
        while (cursor.moveToNext()){
            Clientes clientes = new Clientes();
            clientes.setIdCliente(cursor.getInt(cursor.getColumnIndex(DBHelper.ColClientes.ID)));
            clientes.setNombre(cursor.getString(cursor.getColumnIndex(DBHelper.ColClientes.NOMBRE)));
            clientes.setDireccion(cursor.getString(cursor.getColumnIndex(DBHelper.ColClientes.DIRECCION)));
            clientes.setTelefono(cursor.getString(cursor.getColumnIndex(DBHelper.ColClientes.TELEFONO)));
            listaClientes.add(clientes);
        }


        return  listaClientes;

    }


    public int verIdMaximo(){
        int resultado;
        Cursor cursor = db.rawQuery("SELECT Max(_id) As _id From Clientes",null);
        cursor.moveToFirst();
        resultado = cursor.getInt(cursor.getColumnIndex(DBHelper.ColClientes.ID));

        Log.i("MSN", "El valor max es " + resultado);
        return resultado;

    }

    public void nuevoCliente(int nuevoRegistro){
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.ColClientes.ID, nuevoRegistro);

        int idNuevo = (int) db.insert(DBHelper.Tablas.CLIENTES, null, valores);

        Log.i("MSG", "Creado el registro con id " + Integer.toString(idNuevo));

    }

    public void eliminarCliente(CharSequence idCliente){
        db.delete(DBHelper.Tablas.CLIENTES,DBHelper.ColClientes.ID + "= " + idCliente,null);
    }

    public void actualizarCliente(CharSequence idCliente, String nombre, String direccion, String telefono){
        ContentValues actualizar = new ContentValues();
        actualizar.put(DBHelper.ColClientes.NOMBRE, nombre);
        actualizar.put(DBHelper.ColClientes.DIRECCION, direccion);
        actualizar.put(DBHelper.ColClientes.TELEFONO,telefono);

        db.update(DBHelper.Tablas.CLIENTES, actualizar, DBHelper.ColClientes.ID +"="+idCliente, null);
    }

}
