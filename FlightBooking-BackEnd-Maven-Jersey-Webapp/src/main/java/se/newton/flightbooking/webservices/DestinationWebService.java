package se.newton.flightbooking.webservices;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import se.newton.flightbooking.HibernateUtil;

import se.newton.flightbooking.Util;
import se.newton.flightbooking.models.*;
import se.newton.flightbooking.services.*;

@Path("/destinations")
public class DestinationWebService {

    private final DestinationService service = new DestinationService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destination> getAllDestinations() {
        Util.log("Destination", "GET", null, null);

        HibernateUtil.openSession();
        List<Destination> dest = service.findAll();
        HibernateUtil.closeSession();

        return dest;
    }
}
