package infraestructure.resources;

import application.ApplicationService;
import application.dto.CustomerDTO;
import application.dto.ProfilePhotoDTO;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.NoSuchElementException;

@Path("customers")
public class CustomerResource {

    private final ApplicationService service;

    public CustomerResource(ApplicationService service){
        this.service = service;
    }

    @GET
    public List<CustomerDTO> searchCustomers(){
        return service.searchCustomers();
    }

    @GET
    @Path("/{id}")
    public CustomerDTO getCustomer(@PathParam("id") String id){
        try{
            return service.getCustomer(id);
        } catch(NoSuchElementException exception){
            throw new NotFoundException();
        }

    }


    @POST
    @Path("/{id}")
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    public void persistProfilePhoto(@PathParam("id") String id, ProfilePhotoDTO profilePhoto){
        service.persistProfilePhoto(id, profilePhoto);
    }
}
