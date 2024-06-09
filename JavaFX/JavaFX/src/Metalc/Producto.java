package Metalc;
public class Producto {
    private int ID;
    private String Nombre;
    private String Precio;
    private int Cantidad;

    public Producto(int ID, String Nombre, String Precio, int Cantidad) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    
    
}
