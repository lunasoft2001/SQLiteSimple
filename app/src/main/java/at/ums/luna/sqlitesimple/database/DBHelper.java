package at.ums.luna.sqlitesimple.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by luna-aleixos on 21.04.2016.
 */
public class DBHelper extends SQLiteAssetHelper{
    private static int version = 1;
    private static String nombreDb = "ums_global.db";

    public DBHelper(Context contexto){
        super(contexto,nombreDb,null,version);
    }

    /*
    Interface de las tablas para facilitar su entrada
     */
    public interface Tablas{
        String CLIENTES = "Clientes";
        String PROVEEDORES = "Proveedores";
    }

    /*
    Interfaces de las columnas
     */
    public interface ColClientes{
        String ID = "_id";
        String NOMBRE = "nombre";
        String DIRECCION = "direccion";
        String TELEFONO = "telefono";
    }

    public interface ColProveedores{
        String ID = "_id";
        String NOMBRE = "nombre";
    }



}
