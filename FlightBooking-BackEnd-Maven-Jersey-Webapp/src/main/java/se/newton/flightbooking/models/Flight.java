package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Flight")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="fId")
public class Flight implements Serializable {

    @Transient
    public static String[] FTYPE = {"Enkel", "Tur & Retur"};
        
    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGenerator")
    @TableGenerator(name = "seqGenerator", table = "sequencegenerator", pkColumnName = "seqName", valueColumnName = "seqValue", pkColumnValue = "counter_1", initialValue = 1, allocationSize = 1)
    @Column(name = "fId")
    private Long fId;

    @Column(name = "name", nullable = false)
    private String fName;
    
    @Column(name = "fromDest", nullable = false)
    private String fromDest;

    @Column(name = "toDest", nullable = false)
    private String toDest;
    
    @Column(name = "departure", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar departure;

    @Column(name = "arrival")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar arrival;
        
    @ManyToOne
    @JoinColumn(name = "aId", nullable=false)
    private AirPlane airPlane; 

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Seat> seats;

    public Flight() {
    }

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
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

    public AirPlane getAirPlane() {
        return airPlane;
    }

    public void setAirPlane(AirPlane airPlane) {
        this.airPlane = airPlane;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}