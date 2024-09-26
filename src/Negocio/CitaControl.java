package Negocio;

import Datos.CitaDAO;
import Entidades.Citas;
import Entidades.Clientes;
import Entidades.Doctores;
import Entidades.Mascotas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class CitaControl {
    private final CitaDAO datos;
    private Citas obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados=0;
    
    public CitaControl(){
        this.datos=new CitaDAO();
        this.obj=new Citas();
        this.registrosMostrados=0;
    }
    
    public DefaultComboBoxModel seleccionar() {
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    List<Clientes> listaClientes = datos.seleccionar();
    
    if (listaClientes.isEmpty()) {
        System.out.println("No hay clientes disponibles."); // Para depuración
    } else {
        for (Clientes cliente : listaClientes) {
            comboModel.addElement(cliente.getNombre_cliente());
        }
    }
    return comboModel;
    }
    
    public DefaultComboBoxModel seleccionarPaciente() {
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    List<Mascotas> listaMascotas = datos.seleccionarPaciente();
    
    if (listaMascotas.isEmpty()) {
        System.out.println("No hay pacientes disponibles."); 
    } else {
        for (Mascotas mascota : listaMascotas) {
            comboModel.addElement(mascota.getNombre_mascota());
        }
    }
    return comboModel;
    }
    
    public DefaultComboBoxModel seleccionarTrabajador() {
    DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
    List<Doctores> listaDoctores = datos.seleccionarTrabajador();
    
    if (listaDoctores.isEmpty()) {
        System.out.println("No hay Trabajadores disponibles.");
    } else {
        for (Doctores doctor : listaDoctores) {
            comboModel.addElement(doctor.getNombre());
        }
    }
    return comboModel;
    }
    
    public DefaultTableModel listar(String texto){
        List<Citas> lista = new ArrayList();
        lista.addAll(datos.listar(texto));
        
        String[] titulos={"IdCita","Cliente","Paciente","Trabajador","Motivo","Descripción","Fecha de la Cita","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[8];
        
        this.registrosMostrados=0;
        
        for(Citas item:lista){
            if(item.isCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdcita());
            registro[1]=item.getCliente_id();
            registro[2]=item.getMascota_id();
            registro[3]=item.getTrabajador_id();
            registro[4]=item.getMotivo();
            registro[5]=item.getDescripcion();
            java.sql.Date fechaCita = item.getFecha_cita();
            registro[6] = (fechaCita != null) ? fechaCita.toString() : "";
            registro[7]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
 
    
    
    public String insertar(String cliente_id, String mascota_id, String trabajador_id, String motivo, String descripcion, String fecha_cita) {
    if(datos.existe(mascota_id)) {
        return "El nombre de la mascota ya existe en nuestra BD.";
    } else {
        obj.setCliente_id(cliente_id);
        obj.setMascota_id(mascota_id);
        obj.setTrabajador_id(trabajador_id);
        obj.setMotivo(motivo);
        obj.setDescripcion(descripcion);
        try {
            java.sql.Date fechaSQL = java.sql.Date.valueOf(fecha_cita);
            obj.setFecha_cita(fechaSQL);
        } catch (IllegalArgumentException e) {
            return "Formato de fecha inválido";
        }
        if (datos.insertar(obj)) {
            return "OK";
        } else {
            return "Error al registrar la cita.";
        }
    }
}


    
    public String actualizar(int id, String cliente_id, String nombreAnt, String mascota_id, String trabajador_id, String motivo, String descripcion, String fecha_cita) {
    obj.setIdcita(id);
    obj.setCliente_id(cliente_id);
    obj.setMascota_id(mascota_id);
    obj.setTrabajador_id(trabajador_id);
    obj.setMotivo(motivo);
    obj.setDescripcion(descripcion);
    try {
            java.sql.Date fechaSQL = java.sql.Date.valueOf(fecha_cita); // formato de fecha: "yyyy-mm-dd"
            obj.setFecha_cita(fechaSQL);
        } catch (IllegalArgumentException e) {
            return "Formato de fecha inválido";
        }
        
    if (!cliente_id.equals(nombreAnt)) {
        if (datos.existe(mascota_id)) { 
            return "El cliente ya existe";
        }
    }

    if (datos.actualizar(obj)) {
        return "OK";
    } else {
        return "Error en la actualización";
    }
}
    
    public int total(){
        return datos.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }
    
    public String desactivar(int id){
        if(datos.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar la cita";
        }
    }
    
    //metodo para activar
    public String activar(int id){
        if(datos.activar(id)){
            return "OK";
        }else{
            return "No se puede activar la cita";
        }
    }
}
