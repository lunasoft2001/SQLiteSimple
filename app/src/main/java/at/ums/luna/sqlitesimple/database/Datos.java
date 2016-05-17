package at.ums.luna.sqlitesimple.database;

import java.util.ArrayList;
import java.util.List;

import at.ums.luna.sqlitesimple.modelos.Clientes;

/**
 * Created by luna-aleixos on 21.04.2016.
 */
public class Datos {
    List items = new ArrayList<>();

    public Datos(){
        items.add(new Clientes(1,"Cliente 1", "Direccion 1", "Telefono 1"));
        items.add(new Clientes(2,"Cliente 2", "Direccion 2", "Telefono 2"));
        items.add(new Clientes(3,"Cliente 3", "Direccion 3", "Telefono 3"));
        items.add(new Clientes(4,"Cliente 4", "Direccion 4", "Telefono 4"));
    }

    public List getItems(){
        return items;
    }

}
