package com.unamis.REST;

import com.google.gson.Gson;
import com.unamis.Controladores.ControladorAdmin;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Marvin
 */
@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginREST {
    
    private JSONParser parser = new JSONParser();
    private ControladorAdmin admin = new ControladorAdmin();
    private Gson gson = new Gson();
    
    @GET
    @Path("/iniciarSesion/{nombre}/{contrasena}")
    public String iniciarSesion(@PathParam("nombre") String nombre, @PathParam("contrasena") String contrasena){
       
        admin.iniciarSesion(nombre, contrasena);
        String jsonArray = gson.toJson(admin.datosSesion());
        
        return jsonArray;
        
    }//Fin método login.
    
    @POST
    @Path("/registrarse")
    public Response registrarse(String datos){
        
        try{
            
            JSONObject json = (JSONObject) parser.parse(datos);
            admin.nuevoRegistro(json);
            admin.registrar();
            
        }catch(ParseException ex){
            
            ex.printStackTrace();
            
        }
        
        return Response.ok("Usuario Registrado", MediaType.APPLICATION_JSON).build();
        
    }//Fin método registrarse.
    
}
