package at.ums.luna.sqlitesimple.modelos;

/**
 * Created by luna-aleixos on 17.05.2016.
 */
public class Proveedores {
    private int idProveedor;
    private String nombre;
//
//    public Proveedores(int idProveedor, String nombre){
//        this.idProveedor = idProveedor;
//        this.nombre = nombre;
//    }
//
//    public Proveedores(){
//    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
