package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultSet implements Serializable {

    private Long fId;
    private String companyName;
    private String flightName;
    private String fromDest;
    private String toDest;
    private Calendar departure;
    private Calendar arrival;
    private Integer price;
    private Long seatCount;
    
    public ResultSet() {
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }   

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFromDest() {
        return fromDest;
    }

    public void setFromDest(String fromDest) {
        this.fromDest = fromDest;
    }

    public String getToDest() {
        return toDest;
    }

    public void setToDest(String toDest) {
        this.toDest = toDest;
    }

    public Calendar getDeparture() {
        return departure;
    }

    public void setDeparture(Calendar departure) {
        this.departure = departure;
    }

    public Calendar getArrival() {
        return arrival;
    }

    public void setArrival(Calendar arrival) {
        this.arrival = arrival;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Long seatCount) {
        this.seatCount = seatCount;
    }
}
