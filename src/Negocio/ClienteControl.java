package Negocio;

import Datos.ClienteDAO;
import Entidades.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteControl {
    private final ClienteDAO datos;
    private Clientes obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados=0;
    
    public ClienteControl(){
        this.datos=new ClienteDAO();
        this.obj=new Clientes();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Clientes> lista = new ArrayList();
        lista.addAll(datos.listar(texto));
        
        String[] titulos={"IdCliente","Nombre Cliente","DNI","Teléfono","Edad","Dirección", "Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[7];
        
        this.registrosMostrados=0;
        
        for(Clientes item:lista){
            if(item.isCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdcliente());
            registro[1]=item.getNombre_cliente();
            registro[2]=item.getDNI();
            registro[3]=item.getTelefono();
            registro[4]=item.getEdad();
            registro[5]=item.getEdad();
            registro[6]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre, String DNI, String telefono, String edad, String direccion){
        if(datos.existe(nombre)){
            return "El nombre del cliente se encuentra en nuestra BD";
        }else{
            obj.setNombre_cliente(nombre);
            obj.setDNI(DNI);
            obj.setTelefono(telefono);
            obj.setEdad(edad);
            obj.setDireccion(direccion);
            if(datos.insertar(obj)){
                return "OK";
            }else{
                return "Error al registar Cliente";
            }
        }
    }
    
    public String actualizar(int id,String nombre, String nombreAnt,String DNI,String telefono, String edad, String direccion){
        if(nombre.equals(nombreAnt)){
            obj.setIdcliente(id);
            obj.setNombre_cliente(nombre);
            obj.setDNI(DNI);
            obj.setTelefono(telefono);
            obj.setEdad(edad);
            obj.setDireccion(direccion);
            if(datos.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización";
            }
        }else{
            if(datos.existe(nombre)){
                return "El cliente ya existe";
            }else{
                obj.setNombre_cliente(nombre);
                obj.setDNI(DNI);
                obj.setTelefono(telefono);
                obj.setEdad(edad);
                if(datos.actualizar(obj)){
                    return "OK";
                }else{
                    return "ERROR en la actualización";
                }
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
