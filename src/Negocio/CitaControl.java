package Negocio;

import Datos.CitaDAO;
import Entidades.Citas;
import java.util.ArrayList;
import java.util.List;
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
    
    public DefaultTableModel listar(String texto){
        List<Citas> lista = new ArrayList();
        lista.addAll(datos.listar(texto));
        
        String[] titulos={"IdCita","Nombre Cliente","Nombre Paciente","Nombre Trabajador","Motivo","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[6];
        
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
            registro[5]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String cliente_id, String mascota_id, String trabajador_id, String motivo){
        if(datos.existe(cliente_id)){
            return "El nombre del cliente se encuentra en nuestra BD";
        }else{
            obj.setCliente_id(cliente_id);
            obj.setMascota_id(mascota_id);
            obj.setTrabajador_id(trabajador_id);
            obj.setMotivo(motivo);
            if(datos.insertar(obj)){
                return "OK";
            }else{
                return "Error al registar Cliente";
            }
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
