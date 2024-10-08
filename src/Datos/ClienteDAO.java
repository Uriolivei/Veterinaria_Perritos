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
                rs.getString(5),rs.getString(6),rs.getBoolean(7)));
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
        resp=false;
           try {
            ps=CON.Conectar().prepareStatement("INSERT INTO clientes(nombre_cliente,DNI,telefono,edad,direccion,condicion) "
                    + "VALUES(?,?,?,?,?,1)");
            ps.setString(1, obj.getNombre_cliente());
            ps.setString(2, obj.getDNI());
            ps.setString(3, obj.getTelefono());
            ps.setString(4, obj.getEdad());
            ps.setString(5, obj.getDireccion());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar cliente " + e.getMessage());
        }finally{
               ps=null;
               CON.Desconectar();
           }
           return resp;
    }

    @Override
    public boolean actualizar(Clientes obj) {
        resp=false;
        try {
           ps=CON.Conectar().prepareStatement("UPDATE clientes SET nombre_cliente=?, DNI=?, telefono=?, edad=?, direccion=? "
                   + "WHERE idcliente=?");
           ps.setString(1, obj.getNombre_cliente());
           ps.setString(2, obj.getDNI());
           ps.setString(3, obj.getTelefono());
           ps.setString(4, obj.getEdad());
           ps.setString(5, obj.getDireccion());
           ps.setInt(6,obj.getIdcliente());
           if(ps.executeUpdate()>0){
               resp = true;
           }
           ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede actualizar los datos" + yeji.getMessage());
        }finally{
            ps=null;
            CON.Desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps=CON.Conectar().prepareStatement("UPDATE clientes SET condicion=0 WHERE idcliente=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar cliente" + yeji.getMessage());
        }finally{
            ps=null;
            CON.Desconectar();
        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp = false;
        try {
            ps=CON.Conectar().prepareStatement("UPDATE clientes SET condicion=1 WHERE idcliente=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar cliente" + yeji.getMessage());
        }finally{
            ps=null;
            CON.Desconectar();
        }
        return resp;
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
            JOptionPane.showMessageDialog(null,"No se puede obtener el total de clientes" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.Desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        try{
            ps=CON.Conectar().prepareStatement("SELECT nombre_cliente FROM clientes WHERE nombre_cliente=?");
            ps.setString(1,texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }
            rs.close();
            ps.close();
        }catch(SQLException yeji){
            JOptionPane.showMessageDialog(null,"No se puede validar datos" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.Desconectar();
        }
        return resp;
    }    

}
