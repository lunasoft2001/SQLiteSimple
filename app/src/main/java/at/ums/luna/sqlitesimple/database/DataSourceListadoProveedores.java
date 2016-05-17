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



}
