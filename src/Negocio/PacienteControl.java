package Negocio;

import Datos.PacienteDAO;
import Entidades.Clientes;
import Entidades.Mascotas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PacienteControl {
    private final PacienteDAO datos;
    private Mascotas obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados=0;
    
    public PacienteControl(){
        this.datos=new PacienteDAO();
        this.obj=new Mascotas();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Mascotas> lista = new ArrayList();
        lista.addAll(datos.listar(texto));
        
        String[] titulos={"IdPaciente","Nombre Paciente","Raza","Color","Peso","Edad","Fecha de Nacimiento","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[8];
        
        this.registrosMostrados=0;
        
        for(Mascotas item:lista){
            if(item.isCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdmascota());
            registro[1]=item.getNombre_mascota();
            registro[2]=item.getRaza();
            registro[3]=item.getColor();
            registro[4]=Double.toString(item.getPeso());
            registro[5]=item.getEdad();
            registro[6]=item.getFecha_nacimiento();
            registro[7]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre, String raza, String color,double peso, String edad,String fecha_nacimiento){
        if(datos.existe(nombre)){
            return "El nombre del paciente se encuentra en nuestra BD";
        }else{
            obj.setNombre_mascota(nombre);
            obj.setRaza(raza);
            obj.setColor(color);
            obj.setPeso(peso);
            obj.setEdad(edad);
            obj.setFecha_nacimiento(fecha_nacimiento);
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
            return "No se puede desactivar el paciente";
        }
    }
    
    //metodo para activar
    public String activar(int id){
        if(datos.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el paciente";
        }
    }
}
