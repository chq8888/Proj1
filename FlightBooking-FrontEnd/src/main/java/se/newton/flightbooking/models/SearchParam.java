package se.newton.flightbooking.models;

import java.util.StringJoiner;
import javax.persistence.Transient;
import javax.ws.rs.MatrixParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchParam {
    @MatrixParam("fromDest") 
    public String fromDest;
    @MatrixParam("toDest") 
    public String toDest;
    @MatrixParam("date")
    public String date;
    @MatrixParam("sClass")
    public String sClass;
    @MatrixParam("adult")
    public Integer adult;
    @MatrixParam("child")
    public Integer child;
    @MatrixParam("orderBy")
    public String orderBy;
        
    public SearchParam() { 
        this.adult = 1;
        this.child = 0;
        this.sClass = Seat.SCLASS[1];
        this.orderBy = "price";
    }
    
    public SearchParam(String fromDest, String toDest, String date, String sClass) { 
        this();
        this.fromDest = fromDest;
        this.toDest = toDest;
        this.date = date;
        this.sClass = sClass;
    }
    
    @Transient
    public int getNrOfPassenger() {
        return this.adult + this.child;
    }
    
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(";");
        
        joiner.add("fromDest=" + fromDest);
        joiner.add("toDest=" + toDest);
        joiner.add("date=" + date);
        joiner.add("sClass=" + sClass);
        joiner.add("adult=" + Integer.toString(adult));
        joiner.add("child=" + Integer.toString(child));
        joiner.add("orderBy=" + orderBy);
        
        return joiner.toString();
    }
}
