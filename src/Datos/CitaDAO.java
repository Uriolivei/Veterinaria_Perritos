package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.CitaInterface;
import Entidades.Citas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CitaDAO implements CitaInterface<Citas>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public CitaDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Citas> listar(String texto) {
        List<Citas> registros = new ArrayList<>();
        try {
            ps=CON.Conectar().prepareStatement(
                    "SELECT c.idcita AS Idcita, " +
             "c.codigo, " + // Aquí falta la coma
             "cl.nombre_cliente AS Nombre_Cliente, " + 
             "m.nombre_mascota AS Nombre_Paciente, " + 
             "t.nombre AS Nombre_Trabajador, " + 
             "c.motivo, " + 
             "c.condicion " + 
             "FROM citas c " +
             "INNER JOIN clientes cl ON c.cliente_id = cl.idcliente " + 
             "INNER JOIN mascotas m ON c.mascota_id = m.idmascota " + 
             "INNER JOIN trabajadores t ON c.trabajador_id = t.idtrabajador " + 
             "WHERE c.cliente_id LIKE ?"
            
            );
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Citas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
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
    public boolean insertar(Citas obj) {
        resp=false;
           try {
            ps=CON.Conectar().prepareStatement("INSERT INTO citas(codigo,cliente_id,mascota_id,trabajador_id,motivo,condicion) VALUES(?,?,?,?,?,1)");
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getCliente_id());
            ps.setString(3, obj.getMascota_id());
            ps.setString(4, obj.getTrabajador_id());
            ps.setString(5, obj.getMotivo());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar categoria " + e.getMessage());
        }finally{
               ps=null;
               CON.Desconectar();
           }
           return resp;
    }

    @Override
    public boolean actualizar(Citas obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps=CON.Conectar().prepareStatement("UPDATE citas SET condicion=0 WHERE idcita=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar cita" + yeji.getMessage());
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
            ps=CON.Conectar().prepareStatement("UPDATE citas SET condicion=1 WHERE idcita=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar cita" + yeji.getMessage());
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
          ps=CON.Conectar().prepareStatement("SELECT COUNT(idcita) FROM citas");
          rs=ps.executeQuery();
          while(rs.next()){
              totalRegistros=rs.getInt("COUNT(idcita)");
          }
          rs.close();
          ps.close();
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null,"No se puede obtener el total de citas" + yeji.getMessage());
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
            ps=CON.Conectar().prepareStatement("SELECT codigo FROM citas WHERE codigo=?");
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
