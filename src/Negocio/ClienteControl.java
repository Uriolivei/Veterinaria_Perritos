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
        
        String[] titulos={"IdCliente","Nombre Cliente","DNI Cliente","Teléfono","Edad","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[6];
        
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
            registro[4]=Integer.toString(item.getEdad());
            registro[5]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados + 1;
        }
        return this.modeloTabla;
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
