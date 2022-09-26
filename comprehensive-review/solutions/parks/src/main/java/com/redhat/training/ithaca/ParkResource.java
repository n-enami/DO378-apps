package com.redhat.training.ithaca;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import io.quarkus.logging.Log;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.BadRequestException;

@Path("/parks")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// @RequestScoped
public class ParkResource {

    @Inject
    public ParkService parkService;

    @GET
    @Operation(
        summary = "List parks",
        description = "List all the parks registered in the system"
    )
    public Set<Park> list() {
        return parkService.list();
    }

    @Transactional
    @POST
    public Park create(Park park) {
        return parkService.create(park);
    }

    @Transactional
    @DELETE
    // @RolesAllowed({ "Admin" })
    @Path("{uuid}")
    public Set<Park> delete(@PathParam("uuid") String uuid) {
        if (!parkService.delete(uuid)) {
            throw new NotFoundException();
        }
        return parkService.list();
    }

    // @Transactional
    // @PUT
    // @Path("/{id}")
    // public void update(Park park) {
    //     parkService.update(park);
    // }

    @Transactional
    @PUT
    @Path("")
    @RolesAllowed({"Admin"})
    public void update(Park park) {
        if (park.getUuid() == null) {
            throw new NotFoundException();
        }

        Log.info("New park size " + park.getSize());

        parkService.update(park);
    }
}
