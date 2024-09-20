package Entidades;


public class Doctores {
    private int idtrabajador;
    private String nombre;
    private String DNI;
    private String telefono;
    private String correo;
    private boolean condicion;

    public Doctores() {
        
    }

    public Doctores(int idtrabajador, String nombre, String DNI, String telefono, String correo, boolean condicion) {
        this.idtrabajador = idtrabajador;
        this.nombre = nombre;
        this.DNI = DNI;
        this.telefono = telefono;
        this.correo = correo;
        this.condicion = condicion;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Doctores{" + "idtrabajador=" + idtrabajador + ", nombre=" + nombre + ", DNI=" + DNI + ", telefono=" + telefono + ", correo=" + correo + ", condicion=" + condicion + '}';
    }
    
    
}
