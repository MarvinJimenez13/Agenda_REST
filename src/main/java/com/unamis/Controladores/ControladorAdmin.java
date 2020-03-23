package com.unamis.Controladores;

import com.google.gson.Gson;
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
    private Admin admins[];
    private ConexionMySQL mysql = new ConexionMySQL();
    private Connection conectar;
    private Gson gson = new Gson();
    private boolean adminRegistrado = false;
    
    public void iniciarSesion(String nombre, String contrasena){

        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement consulta  = conectar.prepareStatement("SELECT * FROM admins WHERE BINARY usuario = ? AND BINARY contrasena = ?");
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
    
    public void actualizar(JSONObject jsonDatos){
        
        String query = "";
        
        admin = new Admin();
        admin.setIdAdmin(Integer.parseInt(jsonDatos.get("idAdmin").toString()));
        admin.setNombre(jsonDatos.get("nombre").toString());
        admin.setTipoAdmin(Integer.parseInt(jsonDatos.get("tipoAdmin").toString()));
        if(jsonDatos.containsKey("contrasena")){
            
            admin.setContrasena(jsonDatos.get("contrasena").toString());
            query = "UPDATE admins SET usuario = '" + admin.getNombre() + "', tipoAdmin = " + admin.getTipoAdmin() + ", "
                    + "contrasena = '" + admin.getContrasena() + "' WHERE idadmins = " + admin.getIdAdmin();
            
        }else{
            
            query = "UPDATE admins SET usuario = '" + admin.getNombre() + "', tipoAdmin = " + admin.getTipoAdmin()
                    + "  WHERE idadmins = " + admin.getIdAdmin();
            
            
        }
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement update = conectar.prepareStatement(query);
                update.execute();
                conectar.close();
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                
            }
            
        }else{
            
            System.out.println("Error en actualizar.");
            
        }
        
    }//Fin método actualizar.
    
    public String obtenerUsuarios(){
        
        String arrayJSON = "";
        
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement consulta = conectar.prepareStatement("SELECT COUNT(*) AS registros FROM admins");
                ResultSet resultado = consulta.executeQuery();
                
                if(resultado.next()){
                    
                    int registros = resultado.getInt("registros");
                    admins = new Admin[registros];
                    
                    int aux = 0;
                    
                    PreparedStatement consulta2 = conectar.prepareStatement("SELECT * FROM admins");
                    ResultSet resultado2 = consulta2.executeQuery();
                    
                    while(resultado2.next()){
                        
                        admin = new Admin();
                        admin.setIdAdmin(resultado2.getInt("idadmins"));
                        admin.setNombre(resultado2.getString("usuario"));
                        admin.setTipoAdmin(resultado2.getInt("tipoAdmin"));
                        admins[aux] = admin;
                        aux++;
                        
                    }
                    
                }
                
                conectar.close();
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                
            }
            
        }else{
            
            System.out.println("Error en obtenerUsuarios.");
            
        }
        
        arrayJSON = gson.toJson(admins);
        
        return arrayJSON;
        
    }//Fin método obtenerUsuarios.
    
    public boolean eliminar(JSONObject jsonDatos){
        
        boolean eliminado = false;
        admin = new Admin();
        admin.setIdAdmin(Integer.parseInt(jsonDatos.get("idAdmin").toString()));
        
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement eliminar = conectar.prepareStatement("DELETE FROM admins WHERE idadmins = ?");
                eliminar.setInt(1 , admin.getIdAdmin());
                eliminar.execute();
                conectar.close();
                eliminado = true;
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                eliminado = false;
                
            }
            
        }else{
            
            System.out.println("Error en la conexion en Eliminar.");
            
        }
        
        return eliminado;
        
    }//Fin método eliminar.
    
}
