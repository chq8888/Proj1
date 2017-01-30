package se.newton.flightbooking.webservices;

import java.text.ParseException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import se.newton.flightbooking.HibernateUtil;

import se.newton.flightbooking.Util;
import se.newton.flightbooking.models.*;
import se.newton.flightbooking.services.*;

@Path("/seats")
public class SeatWebService {

    private final SeatService service = new SeatService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seat> getAllSeats() {
        Util.log("Seat", "GET", null, null);
        
        HibernateUtil.openSession();
        List<Seat> seats = service.findAll();
        HibernateUtil.closeSession();
        
        return seats;
    }

    @GET
    @Path("/{seatId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seat getSeatById(@PathParam("seatId") Long sId) {
        Util.log("Seat", "GET", "/{seatId}/", Long.toString(sId));
        
        HibernateUtil.openSession();
        Seat seat = service.findById(sId);
        HibernateUtil.closeSession();
        
        return seat;
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResultSet> search(@BeanParam SearchParam query) throws ParseException {
        Util.log("Seat", "GET", "/search", null);
        
        HibernateUtil.openSession();
        List<ResultSet> result = service.Search(query);
        HibernateUtil.closeSession();
        
        return result;
    }

    @POST
    @Path("/create/{parentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Seat createSeat(Seat s, @PathParam("parentId") int fId) {
        Util.log("Seat", "POST", "/create", "seat");
//        if (ans == null)  throw BadRequestException;
        
        HibernateUtil.openSession();
        Seat seat = service.save(s, fId);
        HibernateUtil.closeSession();
        
        return seat;
    }
 
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFlight(Seat s) {
        Util.log("Seat", "PUT", "/update", "seat");
//        if (ans == null)  throw BadRequestException;
        
        HibernateUtil.openSession();
        service.save(s);
        HibernateUtil.closeSession();
    }
    
//    @PUT
//    @Path("/update/list")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateSeats(List<Seat> s) {
//        Util.log("Seat", "PUT", "/update/list", "seats");
////        if (ans == null)  throw BadRequestException;
//        
//        service.update(s);
//    }
    
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteFlight(Seat s) {
        Util.log("Seat", "DELETE", "/delete", "seat");
        
        HibernateUtil.openSession();
        service.delete(s);
        HibernateUtil.closeSession();
    }
}