package Entidades;

public class Citas {
    private int idcita;
    private String codigo;
    private String cliente_id;
    private String mascota_id;
    private String trabajador_id;
    private String motivo;
    private boolean condicion;

    public Citas() {
        
    }

    public Citas(int idcita, String codigo, String cliente_id, String mascota_id, String trabajador_id, String motivo, boolean condicion) {
        this.idcita = idcita;
        this.codigo = codigo;
        this.cliente_id = cliente_id;
        this.mascota_id = mascota_id;
        this.trabajador_id = trabajador_id;
        this.motivo = motivo;
        this.condicion = condicion;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Citas{" + "idcita=" + idcita + ", codigo=" + codigo + ", cliente_id=" + cliente_id + ", mascota_id=" + mascota_id + ", trabajador_id=" + trabajador_id + ", motivo=" + motivo + ", condicion=" + condicion + '}';
    }

    

    
    
}
