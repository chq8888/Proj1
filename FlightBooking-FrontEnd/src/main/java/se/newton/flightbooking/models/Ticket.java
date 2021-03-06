package se.newton.flightbooking.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="tId")
@Table(name = "Ticket")
class Ticket implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tId")
    private Long tId;
    
    @Column(name = "sId", nullable=false)
    private Long sId;
    
    @ManyToOne
    @JoinColumn(name = "pId", nullable=false)
    private Passenger passenger;
    
    @OneToOne(mappedBy = "ticket")
    private Seat seat;
    
    public Ticket() {
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
