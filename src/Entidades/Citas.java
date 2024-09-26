package Entidades;

import java.sql.Date;

public class Citas {
    private int idcita;
    private String cliente_id;
    private String mascota_id;
    private String trabajador_id;
    private String motivo;
    private String descripcion;
    private java.sql.Date fecha_cita;
    private boolean condicion;


    public Citas() {
        
    }

    public Citas(int idcita, String cliente_id, String mascota_id, String trabajador_id, String motivo, String descripcion, Date fecha_cita, boolean condicion) {
        this.idcita = idcita;
        this.cliente_id = cliente_id;
        this.mascota_id = mascota_id;
        this.trabajador_id = trabajador_id;
        this.motivo = motivo;
        this.descripcion = descripcion;
        this.fecha_cita = fecha_cita;
        this.condicion = condicion;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getMascota_id() {
        return mascota_id;
    }

    public void setMascota_id(String mascota_id) {
        this.mascota_id = mascota_id;
    }

    public String getTrabajador_id() {
        return trabajador_id;
    }

    public void setTrabajador_id(String trabajador_id) {
        this.trabajador_id = trabajador_id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Citas{" + "idcita=" + idcita + ", cliente_id=" + cliente_id + ", mascota_id=" + mascota_id + ", trabajador_id=" + trabajador_id + ", motivo=" + motivo + ", descripcion=" + descripcion + ", fecha_cita=" + fecha_cita + ", condicion=" + condicion + '}';
    }
}
