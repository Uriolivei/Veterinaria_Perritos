package DataBase;

import javax.swing.JOptionPane;

public class MainConectar {


    public static void main(String[] args) {
        Conexion conexion = Conexion.getInstancia();
        conexion.Conectar();
        if(conexion.cadena!=null){
            JOptionPane.showMessageDialog(null, "Conectado");
        }else{
            System.out.println("Desconectado");
        }
    }
    
}
