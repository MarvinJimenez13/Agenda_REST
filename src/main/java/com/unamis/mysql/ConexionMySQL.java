package com.unamis.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Marvin
 */
public class ConexionMySQL {
    
    public String db, url, user, pass;
    
    public ConexionMySQL(){
        
        this.db = "agenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City";
        this.url = "jdbc:mysql://localhost:3306/" +db;
        this.user = "root";
        this.pass = "";
        
    }
    
    public Connection conectar(){
        
        Connection link = null;
        
        try{
            
            String textoConn = db + url + url + pass;
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            
            
        }catch(Exception ex){
            
            ex.printStackTrace();
            
        }
        
        return link;
        
    }//Fin método conectar.
    
}
