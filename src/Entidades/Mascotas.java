package Entidades;

import java.util.Date;

public class Mascotas {
    private int idmascota;
    private String nombre_mascota;
    private String raza;
    private String color;
    private double peso;
    private String edad;
    private java.sql.Date fecha_nacimiento;
    private boolean condicion;

    public Mascotas() {
    }

    public Mascotas(int idmascota, String nombre_mascota, String raza, String color, double peso, String edad, java.sql.Date fecha_nacimiento, boolean condicion) {
        this.idmascota = idmascota;
        this.nombre_mascota = nombre_mascota;
        this.raza = raza;
        this.color = color;
        this.peso = peso;
        this.edad = edad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.condicion = condicion;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public java.sql.Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(java.sql.Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Mascotas{" + "idmascota=" + idmascota + ", nombre_mascota=" + nombre_mascota + ", raza=" + raza + ", color=" + color + ", peso=" + peso + ", edad=" + edad + ", fecha_nacimiento=" + fecha_nacimiento + ", condicion=" + condicion + '}';
    }
    
}
