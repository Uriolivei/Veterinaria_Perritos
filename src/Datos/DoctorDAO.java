package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.DoctorInterface;
import Entidades.Doctores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DoctorDAO implements DoctorInterface<Doctores> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public DoctorDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Doctores> listar(String texto) {
        List<Doctores> registros = new ArrayList<>();
        try {
            ps=CON.Conectar().prepareStatement("SELECT * FROM trabajadores WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Doctores(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),
                        rs.getBoolean(6)));
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
    public boolean insertar(Doctores obj) {
        resp=false;
           try {
            ps=CON.Conectar().prepareStatement("INSERT INTO trabajadores(nombre,DNI,telefono,correo,condicion) VALUES(?,?,?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDNI());
            ps.setString(3, obj.getTelefono());
            ps.setString(4, obj.getCorreo());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar trabajador " + e.getMessage());
        }finally{
               ps=null;
               CON.Desconectar();
           }
           return resp;
    }

    @Override
    public boolean actualizar(Doctores obj) {
        resp=false;
        try {
           ps=CON.Conectar().prepareStatement("UPDATE trabajadores SET nombre=?, DNI=?, telefono=?, correo=? WHERE idtrabajador=?");
           ps.setString(1, obj.getNombre());
           ps.setString(2, obj.getDNI());
           ps.setString(3, obj.getTelefono());
           ps.setString(4, obj.getCorreo());
           ps.setInt(5,obj.getIdtrabajador());
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
            ps=CON.Conectar().prepareStatement("UPDATE trabajadores SET condicion=0 WHERE idtrabajador=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar trabajador" + yeji.getMessage());
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
            ps=CON.Conectar().prepareStatement("UPDATE trabajadores SET condicion=1 WHERE idtrabajador=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar trabajador" + yeji.getMessage());
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
          ps=CON.Conectar().prepareStatement("SELECT COUNT(idtrabajador) FROM trabajadores");
          rs=ps.executeQuery();
          while(rs.next()){
              totalRegistros=rs.getInt("COUNT(idtrabajador)");
          }
          rs.close();
          ps.close();
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null,"No se puede obtener el total de trabajadores" + yeji.getMessage());
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
            ps=CON.Conectar().prepareStatement("SELECT nombre FROM trabajadores WHERE nombre=?");
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
