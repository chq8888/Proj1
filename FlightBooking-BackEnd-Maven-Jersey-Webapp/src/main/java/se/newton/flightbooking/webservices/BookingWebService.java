package se.newton.flightbooking.webservices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import se.newton.flightbooking.HibernateUtil;

import se.newton.flightbooking.Util;
import se.newton.flightbooking.models.*;
import se.newton.flightbooking.services.*;

@Path("/bookings")
public class BookingWebService {
    
    private final BookingService service = new BookingService();
    
    @GET
    @Path("/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking getBookingById(@PathParam("bookingId") Long bId) {
        Util.log("Flight", "GET", "/{bookingId}/", Long.toString(bId));
        
        HibernateUtil.openSession();
        Booking booking = service.findById(bId);
        HibernateUtil.closeSession();

        return booking;
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Booking createBooking(Booking b) {
        Util.log("Booking", "POST", "/create", "booking");
//        if (ans == null)  throw BadRequestException;
        
        HibernateUtil.openSession();
        //Booking booking = service.save(b);
        HibernateUtil.closeSession();
     b.setbId(123L);
        return b;//booking;
    }
}
