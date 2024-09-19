package DataBase;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //variables
    private final String DRIVER="com.mysql.jdbc.Driver";
    private final String URL="jdbc:mysql://localhost:3306/";
    private final String DB="veterinariabd";
    private final String USER="root";
    private final String PASSWORD="";
    
    public Connection cadena;
    public static Conexion instancia;

    public Conexion() {
        this.cadena = null;
    }
    
    public Connection Conectar(){
        try{
            Class.forName(DRIVER);  
            this.cadena=DriverManager.getConnection(URL+DB,USER,PASSWORD);
            System.out.println("Conexion establecida");
        }catch(ClassNotFoundException | SQLException yeji){
            JOptionPane.showMessageDialog(null,"ERROR de conexión a la BD" + yeji.getMessage());
            System.out.println("ERROR de conexion a la BD");
            System.exit(0);
        }
        return this.cadena;
    }
    
    public void Desconectar(){
        try{
            this.cadena.close();
        }catch(SQLException yeji){
            JOptionPane.showMessageDialog(null,"No se pude cerrar la consulta Statement" + yeji.getMessage());
        }
    }
    
    public synchronized static Conexion getInstancia(){
        if(instancia==null){
            instancia = new Conexion();
        }
        return instancia;
    }
    
    
}
