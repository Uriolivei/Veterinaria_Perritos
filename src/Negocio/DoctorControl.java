package Negocio;

import Datos.DoctorDAO;
import Entidades.Doctores;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DoctorControl {
    private final DoctorDAO datos;
    private Doctores obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados=0;
    
    public DoctorControl(){
        this.datos=new DoctorDAO();
        this.obj=new Doctores();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Doctores> lista = new ArrayList();
        lista.addAll(datos.listar(texto));
        
        String[] titulos={"IdCliente","Nombre Tabajador","DNI","Teléfono","Correo","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[6];
        
        this.registrosMostrados=0;
        
        for(Doctores item:lista){
            if(item.isCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdtrabajador());
            registro[1]=item.getNombre();
            registro[2]=item.getDNI();
            registro[3]=item.getTelefono();
            registro[4]=item.getCorreo();
            registro[5]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre, String DNI, String telefono, String correo){
        if(datos.existe(nombre)){
            return "El nombre del cliente se encuentra en nuestra BD";
        }else{
            obj.setNombre(nombre);
            obj.setDNI(DNI);
            obj.setTelefono(telefono);
            obj.setCorreo(correo);
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
            return "No se puede desactivar el cliente";
        }
    }
    
    //metodo para activar
    public String activar(int id){
        if(datos.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el cliente";
        }
    }
}
