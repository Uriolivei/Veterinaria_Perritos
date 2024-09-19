package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.ClienteInterface;
import Entidades.Clientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO implements ClienteInterface<Clientes>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public ClienteDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Clientes> listar(String texto) {
        List<Clientes> registros = new ArrayList<>();
        try {
            ps=CON.Conectar().prepareStatement("SELECT * FROM clientes WHERE nombre_cliente LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                rs.getInt(5),rs.getBoolean(6)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No se puede mostrar datos en la tabla " + e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.Desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Clientes obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Clientes obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean activar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
       int totalRegistros = 0;
        try {
          ps=CON.Conectar().prepareStatement("SELECT COUNT(idcliente) FROM clientes");
          rs=ps.executeQuery();
          while(rs.next()){
              totalRegistros=rs.getInt("COUNT(idcliente)");
          }
          rs.close();
          ps.close();
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null,"No se puede obtener el total de categorias" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.Desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
