package se.newton.flightbooking.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import se.newton.flightbooking.HibernateUtil;

import se.newton.flightbooking.Util;
import se.newton.flightbooking.models.*;
import se.newton.flightbooking.services.*;

@Path("/flights")
public class FlightWebService {

    private final FlightService service = new FlightService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getAllFlights() {
        Util.log("Flight", "GET", null, null);

        HibernateUtil.openSession();
        List<Flight> flights = service.findAll();
        HibernateUtil.closeSession();

        return flights;
    }
    
//    @GET
//    @Path("/{flightId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Child getChild() {
//
//        Child child = new Child();
//        Parent parent = new Parent();
//        child.setValue(1L);
//        parent.setValue(2L);
//        child.setParent(parent);
//        parent.getChilds().add(child);
//        return child;
//    }
    
    @GET
    @Path("/{flightId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getFlightById(@PathParam("flightId") Long fId) {
        Util.log("Flight", "GET", "/{flightId}/", Long.toString(fId));
        
        HibernateUtil.openSession();
        Flight flight = service.findById(fId);
        HibernateUtil.closeSession();

        return flight;
    }

    @POST
    @Path("/create/{parentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Flight createFlight(Flight f, @PathParam("parentId") int aId) {
        Util.log("Flight", "POST", "/create", "flight");
//        if (ans == null)  throw BadRequestException;
        
        HibernateUtil.openSession();
        Flight flight = service.save(f, aId);
        HibernateUtil.closeSession();
        
        return flight;
    }
 
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFlight(Flight f) {
        Util.log("Flight", "PUT", "/update", "flight");
//        if (ans == null)  throw BadRequestException;

        HibernateUtil.openSession();
        service.save(f);
        HibernateUtil.closeSession();
    }
    
//    @PUT
//    @Path("/update/list")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateFlights(List<Flight> f) {
//        Util.log("Flight", "PUT", "/update/list", "flights");
////        if (ans == null)  throw BadRequestException;
//        
//        service.update(f);
//    }
    
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteFlight(Flight f) {
        Util.log("Flight", "DELETE", "/delete", "flight");
        
        HibernateUtil.openSession();
        service.delete(f);
        HibernateUtil.closeSession();
    }
}