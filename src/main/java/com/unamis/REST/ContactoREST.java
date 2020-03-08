package com.unamis.REST;

import com.unamis.Controladores.ControladorContacto;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
    
}
