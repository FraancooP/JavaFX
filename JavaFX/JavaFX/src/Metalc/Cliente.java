package Metalc;
public class Cliente {
    private int ID;
    private String Nombre;
    private String Apellido;
    private String DNI;
    private String Direccion;
    private String Email;
    private String Telefono;
    public Cliente(int ID, String Nombre, String Apellido, String DNI, String Direccion, String Email, String Telefono) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Direccion = Direccion;
        this.Email = Email;
        this.Telefono = Telefono;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getEmail() {
        return Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
}
