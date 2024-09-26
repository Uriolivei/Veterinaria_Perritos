package Datos;

import DataBase.Conexion;
import Entidades.Clientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Datos.CrudInterface.PacienteInterface;
import Entidades.Mascotas;

public class PacienteDAO implements PacienteInterface<Mascotas> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PacienteDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Mascotas> listar(String texto) {
        List<Mascotas> registros = new ArrayList<>();
        try {
            ps=CON.Conectar().prepareStatement("SELECT * FROM mascotas WHERE nombre_mascota LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                java.sql.Date fechaNacimiento = rs.getDate(7); 
                registros.add(new Mascotas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getDouble(5),
                rs.getString(6),fechaNacimiento,rs.getBoolean(8)));
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
    public boolean insertar(Mascotas obj) {
        resp=false;
           try {
            ps=CON.Conectar().prepareStatement("INSERT INTO mascotas(nombre_mascota,raza,color,peso,edad,fecha_nacimiento,condicion) "
                    + "VALUES(?,?,?,?,?,?,1)");
            ps.setString(1, obj.getNombre_mascota());
            ps.setString(2, obj.getRaza());
            ps.setString(3, obj.getColor());
            ps.setDouble(4, obj.getPeso());
            ps.setString(5, obj.getEdad());
            java.sql.Date fechaNacimiento = new java.sql.Date(obj.getFecha_nacimiento().getTime());
            ps.setDate(6, fechaNacimiento);
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
    public boolean actualizar(Mascotas obj) {
        resp=false;
        try {
           ps=CON.Conectar().prepareStatement("UPDATE mascotas SET nombre_mascota=?, raza=?,color=?, peso=?, edad=?, fecha_nacimiento=? "
                   + "WHERE idmascota=?");
           ps.setString(1, obj.getNombre_mascota());
           ps.setString(2, obj.getRaza());
           ps.setString(3, obj.getColor());
           ps.setDouble(4, obj.getPeso());
           ps.setString(5,obj.getEdad());
           java.sql.Date fechaNacimiento = new java.sql.Date(obj.getFecha_nacimiento().getTime());
           ps.setDate(6, fechaNacimiento);
           ps.setInt(7, obj.getIdmascota());
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
            ps=CON.Conectar().prepareStatement("UPDATE mascotas SET condicion=0 WHERE idmascota=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar paciente" + yeji.getMessage());
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
            ps=CON.Conectar().prepareStatement("UPDATE mascotas SET condicion=1 WHERE idmascota=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar paciente" + yeji.getMessage());
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
          ps=CON.Conectar().prepareStatement("SELECT COUNT(idmascota) FROM mascotas");
          rs=ps.executeQuery();
          while(rs.next()){
              totalRegistros=rs.getInt("COUNT(idmascota)");
          }
          rs.close();
          ps.close();
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null,"No se puede obtener el total de pacientes" + yeji.getMessage());
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
            ps=CON.Conectar().prepareStatement("SELECT nombre_mascota FROM mascotas WHERE nombre_mascota=?");
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
