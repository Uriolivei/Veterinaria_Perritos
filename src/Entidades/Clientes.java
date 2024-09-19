package Entidades;

public class Clientes {
    private int idcliente;
    private String nombre_cliente;
    private String DNI;
    private String telefono;
    private int edad;
    private boolean condicion;

    public Clientes() {
    }

    public Clientes(int idcliente, String nombre_cliente, String DNI, String telefono, int edad, boolean condicion) {
        this.idcliente = idcliente;
        this.nombre_cliente = nombre_cliente;
        this.DNI = DNI;
        this.telefono = telefono;
        this.edad = edad;
        this.condicion = condicion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Clientes{" + "idcliente=" + idcliente + ", nombre_cliente=" + nombre_cliente + ", DNI=" + DNI + ", telefono=" + telefono + ", edad=" + edad + ", condicion=" + condicion + '}';
    }
    
    
}
