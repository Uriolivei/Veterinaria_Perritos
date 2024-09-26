package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.CitaInterface;
import Entidades.Citas;
import Entidades.Clientes;
import Entidades.Doctores;
import Entidades.Mascotas;
import java.security.Timestamp;
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
            ps=CON.Conectar().prepareStatement("SELECT * FROM citas WHERE cliente_id LIKE ?"
            
            );
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                java.sql.Date fechaCita = rs.getDate(7); 
                registros.add(new Citas(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                rs.getString(5),rs.getString(6),fechaCita,rs.getBoolean(8)));
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
            ps=CON.Conectar().prepareStatement("INSERT INTO citas(cliente_id,mascota_id,trabajador_id,motivo,descripcion,fecha_cita,condicion) VALUES(?,?,?,?,?,?,1)");
            ps.setString(1, obj.getCliente_id());
            ps.setString(2, obj.getMascota_id());
            ps.setString(3, obj.getTrabajador_id());
            ps.setString(4, obj.getMotivo());
            ps.setString(5, obj.getDescripcion());
            java.sql.Date fechaCita = new java.sql.Date(obj.getFecha_cita().getTime());
            ps.setDate(6, fechaCita);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar cita " + e.getMessage());
        }finally{
               ps=null;
               CON.Desconectar();
           }
           return resp;
    }

    @Override
    public boolean actualizar(Citas obj) {
        resp=false;
        try {
           ps=CON.Conectar().prepareStatement("UPDATE citas SET cliente_id=?, mascota_id=?, trabajador_id=?, motivo=?, fecha_cita=? descripcion=? WHERE idcita=?");
           ps.setString(1, obj.getCliente_id());
           ps.setString(2, obj.getMascota_id());
           ps.setString(3, obj.getTrabajador_id());
           ps.setString(4, obj.getMotivo());
           ps.setString(5, obj.getDescripcion());
           java.sql.Date fechaCita = new java.sql.Date(obj.getFecha_cita().getTime());
           ps.setDate(6, fechaCita);
           ps.setInt(7,obj.getIdcita());
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
    public boolean existe(String mascota_id) {
        resp = false;
        try{
            ps=CON.Conectar().prepareStatement("SELECT COUNT(*) FROM citas WHERE cliente_id = ?");
            //ps.setString(1,cliente_id);
            ps.setString(1,mascota_id);
            rs = ps.executeQuery(); 
            if(rs.next() && rs.getInt(1) > 0){
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
    
    public List<Clientes> seleccionar() {
    List<Clientes> registros = new ArrayList<>();
    try {
        // Consulta SQL para obtener id y nombre de los clientes
        ps = CON.Conectar().prepareStatement("SELECT idcliente, nombre_cliente, DNI, telefono, edad, direccion condicion FROM clientes ORDER BY nombre_cliente ASC");
        rs = ps.executeQuery();
        while (rs.next()) {
            registros.add(new Clientes(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getBoolean(7)
            ));
        }
        ps.close();
        rs.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pueden cargar los clientes: " + e.getMessage());
        } finally {
        ps = null;
        rs = null;
        CON.Desconectar();
        }
        return registros;
    }
    
    
    
    public List<Mascotas> seleccionarPaciente() {
    List<Mascotas> registros = new ArrayList<>();
    try {
        // Consulta SQL para obtener id y nombre de los clientes
        ps = CON.Conectar().prepareStatement("SELECT idmascota, nombre_mascota, raza, color, peso, edad, fecha_nacimiento, condicion FROM mascotas ORDER BY nombre_mascota ASC");
        rs = ps.executeQuery();
        while (rs.next()) {
            java.sql.Date fechaNacimiento = rs.getDate(7);
            registros.add(new Mascotas(
                rs.getInt(1),      
                rs.getString(2),   
                rs.getString(3),    
                rs.getString(4),    
                rs.getDouble(5),    
                rs.getString(6),      
                fechaNacimiento,      
                rs.getBoolean(8)
            ));
        }
        ps.close();
        rs.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pueden cargar los Pacientes: " + e.getMessage());
        } finally {
        ps = null;
        rs = null;
        CON.Desconectar();
        }
        return registros;
    }  
    
    public List<Doctores> seleccionarTrabajador() {
    List<Doctores> registros = new ArrayList<>();
    try {
        // Consulta SQL para obtener id y nombre de los clientes
        ps = CON.Conectar().prepareStatement("SELECT idtrabajador, nombre, DNI, telefono, correo, condicion FROM trabajadores ORDER BY nombre ASC");
        rs = ps.executeQuery();
        while (rs.next()) {
            registros.add(new Doctores(
                rs.getInt(1),      
                rs.getString(2),   
                rs.getString(3),    
                rs.getString(4), 
                rs.getString(5),
                rs.getBoolean(6)
            ));
        }
        ps.close();
        rs.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pueden cargar los Trabajadores: " + e.getMessage());
        } finally {
        ps = null;
        rs = null;
        CON.Desconectar();
        }
        return registros;
    } 

}

