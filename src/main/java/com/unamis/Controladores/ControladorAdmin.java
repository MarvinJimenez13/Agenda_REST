package com.unamis.Controladores;

import com.unamis.modelos.Admin;
import com.unamis.mysql.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;

/**
 *
 * @author Marvin
 */
public class ControladorAdmin {
    
    private Admin admin = new Admin();
    private ConexionMySQL mysql = new ConexionMySQL();
    private Connection conectar;
    private boolean adminRegistrado = false;
    
    public void iniciarSesion(String nombre, String contrasena){

        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement consulta  = conectar.prepareStatement("SELECT * FROM admins WHERE usuario = ? AND contrasena = ?");
                consulta.setString(1 , nombre);
                consulta.setString(2, contrasena);
                ResultSet resultado = consulta.executeQuery();
                
                if(resultado.next()){
                    
                    admin.setIdAdmin(resultado.getInt("idadmins"));
                    admin.setNombre(resultado.getString("usuario"));
                    admin.setTipoAdmin(resultado.getInt("tipoAdmin"));
                    adminRegistrado = true;
                    
                }else{
                    
                    conectar.close();
                    adminRegistrado = false;
                    
                }
                
                conectar.close();
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                
            }
            
        }else{
            
            System.out.println("Error en iniciarSesion.");
            
        }
        
    }//Fin método nuevaSesion
    
    public Admin datosSesion(){
        
        if(adminRegistrado){
            
            return admin;
            
        }else{
            
            return null;
            
        }
        
    }//Fin método iniciarSesion.
    
    public void nuevoRegistro(JSONObject json){

            admin.setNombre(json.get("nombre").toString());
            admin.setContrasena(json.get("contrasena").toString());
            admin.setTipoAdmin(Integer.parseInt(json.get("tipoAdmin").toString()));
        
    }//Fin método nuevoRegistro.
    
    public void registrar(){
        
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement registroConsulta = conectar.prepareStatement("INSERT INTO admins (usuario, contrasena, tipoAdmin) VALUES (?, ?, ?)");
                registroConsulta.setString(1 , admin.getNombre());
                registroConsulta.setString(2 , admin.getContrasena());
                registroConsulta.setInt(3 , admin.getTipoAdmin());
                registroConsulta.execute();
                
                conectar.close();
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                
            }
            
        }else{
            
            System.out.println("Error en registrar.");
            
        }
        
    }//Fin método registrar.
    
}
