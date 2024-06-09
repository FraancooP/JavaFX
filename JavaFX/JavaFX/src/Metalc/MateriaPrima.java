package Metalc;
public class MateriaPrima {
    private int ID;
    private String Nombre;
    private String Cantidad;

    public MateriaPrima(int ID, String Nombre, String Cantidad) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }
    
}
