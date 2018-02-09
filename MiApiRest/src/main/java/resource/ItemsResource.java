/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.Alumno;

/**
 * REST Web Service
 *
 * @author oscar
 */
@Path("/Items")
public class ItemsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ItemsResource
     */
    public ItemsResource() {
    }

    /**
     * Retrieves representation of an instance of resource.ItemsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno getXml() {
        //TODO return proper representation object
        return new Alumno();
    }

    /**
     * Sub-resource locator method for {name}
     */
    @Path("{name}")
    public ItemResource getItemResource(@PathParam("name") String name) {
        return ItemResource.getInstance(name);
    }
}
