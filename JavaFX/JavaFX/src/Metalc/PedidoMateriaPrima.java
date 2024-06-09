package Metalc;
public class PedidoMateriaPrima {
    private int ID;
    private String Nombre;
    private String Materia;
    private String Cantidad;
    private String Monto;
    private String NumeroFactura;
    private String Estado;
    private String Fecha;

    public PedidoMateriaPrima(int ID, String Nombre, String Materia, String Cantidad, String Monto, String NumeroFactura, String Estado, String Fecha) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Materia = Materia;
        this.Cantidad = Cantidad;
        this.Monto = Monto;
        this.NumeroFactura = NumeroFactura;
        this.Estado = Estado;
        this.Fecha = Fecha;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getMateria() {
        return Materia;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public String getMonto() {
        return Monto;
    }

    public String getNumeroFactura() {
        return NumeroFactura;
    }

    public String getEstado() {
        return Estado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setMateria(String Materia) {
        this.Materia = Materia;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public void setNumeroFactura(String NumeroFactura) {
        this.NumeroFactura = NumeroFactura;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    
            
}
