package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderParam implements Serializable {
    private Long fId;
    private Long frId;
    private Integer totalPrice;
    private List<Passenger> passengers;
    
    public OrderParam() {
    }

    public OrderParam(Long flightId) {
        this();
        this.fId = flightId;
    }
    
    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFrId() {
        return frId;
    }

    public void setFrId(Long frId) {
        this.frId = frId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}