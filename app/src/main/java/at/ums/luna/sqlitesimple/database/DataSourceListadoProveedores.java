package at.ums.luna.sqlitesimple.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import at.ums.luna.sqlitesimple.modelos.Clientes;
import at.ums.luna.sqlitesimple.modelos.Proveedores;

/**
 * Created by luna-aleixos on 17.05.2016.
 */
public class DataSourceListadoProveedores {
    DBHelper ayudaDb;
    SQLiteDatabase db;


    public DataSourceListadoProveedores (Context context){
        ayudaDb = new DBHelper(context);}

    public void abrir(){db = ayudaDb.getWritableDatabase();
    }

    public void cerrar(){ ayudaDb.close();}

    public Cursor verListadoProveedores(){
        return db.rawQuery("SELECT * FROM " + DBHelper.Tablas.PROVEEDORES, null);
    }

    public List<Proveedores> getAll(){
        SQLiteDatabase db = ayudaDb.getReadableDatabase();
        String selectQuery = "SELECT " +
                DBHelper.ColProveedores.ID + "," +
                DBHelper.ColProveedores.NOMBRE  +
                " FROM " + DBHelper.Tablas.PROVEEDORES;

        List<Proveedores> listaProveedores = new ArrayList<Proveedores>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Proveedores proveedor = new Proveedores();
                proveedor.setIdProveedor(cursor.getInt(cursor.getColumnIndex(DBHelper.ColProveedores.ID)));
                proveedor.setNombre(cursor.getString(cursor.getColumnIndex(DBHelper.ColProveedores.NOMBRE)));
                listaProveedores.add(proveedor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaProveedores;

    }

    public List<String> getAll_Simple(){
        SQLiteDatabase db = ayudaDb.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DBHelper.Tablas.PROVEEDORES;

        List<String> listaProveedores = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery,null);

        Integer i = 0;
        if (cursor.moveToFirst()){
            do{
                listaProveedores.add(i,cursor.getString(cursor.getColumnIndex(DBHelper.ColProveedores.ID)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaProveedores;




    }



}
