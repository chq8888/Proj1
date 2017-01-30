package se.newton.flightbooking.services;

import java.util.List;
import org.hibernate.Session;
import se.newton.flightbooking.models.*;

public class FlightService extends AbstractService<Flight, Long> {
    
    public Flight save(Flight flight, Integer parentId) {
        Session session = getSession();
        AirPlane airplane = (AirPlane) session.get(AirPlane.class, parentId);
        
        if (session.contains(airplane)) {
            flight.setAirPlane(airplane);
            airplane.getFlights().add(flight);
            
            super.save(flight);   
        }
        
        return flight;
    }
}
