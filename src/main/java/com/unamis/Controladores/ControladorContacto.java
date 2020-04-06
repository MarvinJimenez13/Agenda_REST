package com.unamis.Controladores;

import com.google.gson.Gson;
import com.unamis.modelos.Contacto;
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
public class ControladorContacto {
    
    private ConexionMySQL mysql = new ConexionMySQL();
    private Connection conectar;
    private Gson gson = new Gson();
    private Contacto contacto = new Contacto();
    private Contacto contactos[];
    
    public void nuevoContacto(JSONObject jsonContacto){
        
        contacto.setIdAdmin(Integer.parseInt(jsonContacto.get("idAdmin").toString()));
        contacto.setNombre(jsonContacto.get("nombre").toString());
        contacto.setApellidos(jsonContacto.get("apellidos").toString());
        contacto.setNumeroCelular(jsonContacto.get("numeroCelular").toString());
        contacto.setLugarComun(jsonContacto.get("lugarComun").toString());
        contacto.setAvenida(jsonContacto.get("avenida").toString());
        contacto.setColonia(jsonContacto.get("colonia").toString());
        contacto.setEstado(jsonContacto.get("estado").toString());
        contacto.setPais(jsonContacto.get("pais").toString());
        contacto.setComentario(jsonContacto.get("comentarios").toString());
        contacto.setFechaRegistro(java.sql.Date.valueOf(jsonContacto.get("fechaRegistro").toString()));
        
    }//Fin método nuevoContacto.
    
    public void registrarContacto(){
        
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement registro = conectar.prepareStatement("INSERT INTO contactos (admins_idadmins, nombre, apellidos, numeroCelular,"
                        + "avenida, colonia, estado, pais, comentario, fechaRegistro, lugarComun) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                registro.setInt(1 , contacto.getIdAdmin());
                registro.setString(2 , contacto.getNombre());
                registro.setString(3 , contacto.getApellidos());
                registro.setString(4 , contacto.getNumeroCelular());
                registro.setString(5 , contacto.getAvenida());
                registro.setString(6 , contacto.getColonia());
                registro.setString(7 , contacto.getEstado());
                registro.setString(8 , contacto.getPais());
                registro.setString(9 , contacto.getComentario());
                registro.setDate(10, contacto.getFechaRegistro());
                registro.setString(11, contacto.getLugarComun());
                registro.execute();
                conectar.close();
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                
            }
            
        }else{
            
            System.out.println("Error en registrarContacto.");
            
        }
        
    }//Fin método registrarContacto.
    
    public String obtenerContactos(){
        
        String jsonArray = "";
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement obtenerRegistros = conectar.prepareStatement("SELECT COUNT(*) AS registros FROM contactos");
                ResultSet resultadoRegistros = obtenerRegistros.executeQuery();
                
                if(resultadoRegistros.next()){
                    
                    contactos = new Contacto[resultadoRegistros.getInt("registros")];
                    PreparedStatement obtenerContactos = conectar.prepareStatement("SELECT * FROM contactos INNER JOIN admins WHERE admins.idadmins = contactos.admins_idadmins");
                    ResultSet resultadoContactos = obtenerContactos.executeQuery();
                    
                    int aux = 0;
                    
                    while(resultadoContactos.next()){
                        
                        Contacto contacto = new Contacto();
                        contacto.setIdContacto(resultadoContactos.getInt("idcontactos"));
                        contacto.setIdAdmin(resultadoContactos.getInt("admins_idadmins"));
                        contacto.setAdminRegistro(resultadoContactos.getString("usuario"));
                        contacto.setNombre(resultadoContactos.getString("nombre"));
                        contacto.setApellidos(resultadoContactos.getString("apellidos"));
                        contacto.setNumeroCelular(resultadoContactos.getString("numeroCelular"));
                        contacto.setAvenida(resultadoContactos.getString("avenida"));
                        contacto.setColonia(resultadoContactos.getString("colonia"));
                        contacto.setEstado(resultadoContactos.getString("estado"));
                        contacto.setPais(resultadoContactos.getString("pais"));
                        contacto.setComentario(resultadoContactos.getString("comentario"));
                        contacto.setFechaRegistro(resultadoContactos.getDate("fechaRegistro"));
                        contacto.setLugarComun(resultadoContactos.getString("lugarComun"));
                        contactos[aux] = contacto;
                        aux++;
                        
                    }
                    
                }
                
                conectar.close();
                
            }catch(SQLException ex){
                
                ex.printStackTrace();
                
            }
            
        }else{
            
            System.out.println("Error en obtenerContactos.");
            
        }
        
        jsonArray = gson.toJson(contactos);
        
        return jsonArray;
        
    }//Fin método obtenerContactos.
    
    public void actualizarContacto(JSONObject jsonDatos){
        
        conectar = mysql.conectar();
        if(conectar != null){
            
            try{          
                
                PreparedStatement actualizar = conectar.prepareStatement("UPDATE contactos SET nombre = ?, apellidos = ?, numeroCelular = ?, avenida = ?, colonia = ?, estado = ?, "
                        + "pais = ?, comentario = ?, lugarComun = ? WHERE idcontactos = ?");
                actualizar.setString(1 , jsonDatos.get("nombre").toString());
                actualizar.setString(2 , jsonDatos.get("apellidos").toString());
                actualizar.setString(3 , jsonDatos.get("numeroCelular").toString());
                actualizar.setString(4 , jsonDatos.get("avenida").toString());
                actualizar.setString(5 , jsonDatos.get("colonia").toString());
                actualizar.setString(6 , jsonDatos.get("estado").toString());
                actualizar.setString(7 , jsonDatos.get("pais").toString());
                actualizar.setString(8 , jsonDatos.get("comentario").toString());
                actualizar.setString(9 , jsonDatos.get("lugarComun").toString());
                actualizar.setInt(10 , Integer.parseInt(jsonDatos.get("idContacto").toString()));
                actualizar.execute();
                conectar.close();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            
        }else{
            
            System.out.println("Error en actualizarContacto.");
            
        }
        
    }//Fin método actualizarContacto.
    
    public void eliminarContacto(JSONObject jsonDatos){
        
        conectar = mysql.conectar();
        
        if(conectar != null){
            
            try{
                
                PreparedStatement eliminar = conectar.prepareStatement("DELETE FROM contactos WHERE idcontactos = ?");
                eliminar.setInt(1, Integer.parseInt(jsonDatos.get("idContacto").toString()));
                eliminar.execute();
                conectar.close();
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            
        }else{
            
            System.out.println("Error en eliminarContacto.");
            
        }
        
    }//Fin método eliminarContacto.
    
}
