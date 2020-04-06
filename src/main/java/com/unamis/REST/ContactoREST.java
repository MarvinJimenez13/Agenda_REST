package com.unamis.REST;

import com.unamis.Controladores.ControladorContacto;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Marvin
 */

@Path("/Contacto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactoREST {
    
    private ControladorContacto contacto = new ControladorContacto();
    
    @POST
    @Path("/registrarContacto")
    public Response registrarContacto(JSONObject jsonContacto){
        
        contacto.nuevoContacto(jsonContacto);
        contacto.registrarContacto();
        
        return Response.ok("Contacto Registrado.", MediaType.APPLICATION_JSON).build();
        
    }//Fin método registrarCOntacto.
    
    @POST
    @Path("eliminarContacto")
    public Response eliminarContacto(JSONObject jsonDatos){
        
        contacto.eliminarContacto(jsonDatos);
        
        return Response.ok("Eliminado correctamente.", MediaType.APPLICATION_JSON).build();
        
    }//Fin método eliminarContacto.
    
    @GET
    @Path("/obtenerContactos")
    public String obtenerContactos(){
        
        String jsonArray = "{\"contactos\":" + contacto.obtenerContactos() + "}";
        
        return jsonArray;
        
    }//Fin método obtenerContactos.
    
    @PUT
    @Path("/actualizarContacto")
    public Response actualizarContacto(JSONObject jsonDatos){
        
        contacto.actualizarContacto(jsonDatos);
        
        return Response.ok("Actualización exitosa.", MediaType.APPLICATION_JSON).build();
        
    }//Fin método actualizarContacto.
    
}
