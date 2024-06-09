package Metalc;
public class StockVentas {
    private int ID;
    private String Cliente;
    private String Producto;
    private int Cantidad;
    private String Estado;
    private String Fecha;
    private String FechaPago;
    public StockVentas(int ID, String Cliente, String Producto, int Cantidad, String Estado,String Fecha,String FechaPago){
        this.ID=ID;
        this.Cliente=Cliente;
        this.Producto=Producto;
        this.Cantidad=Cantidad;
        this.Estado=Estado;
        this.Fecha=Fecha;
        this.FechaPago=FechaPago;
    }

    public int getID() {
        return ID;
    }

    public String getCliente() {
        return Cliente;
    }

    public String getProducto() {
        return Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public String getEstado() {
        return Estado;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String FechaPago) {
        this.FechaPago = FechaPago;
    }
    
    
}
