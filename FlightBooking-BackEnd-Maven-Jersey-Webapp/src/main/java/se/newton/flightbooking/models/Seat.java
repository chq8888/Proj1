package se.newton.flightbooking.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Seat")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="sId")
public class Seat implements Serializable {
    
    @Transient
    public static String[] SCLASS = {"Business", "Ekonomi"};

    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGenerator")
    @TableGenerator(name = "seqGenerator", table = "sequencegenerator", pkColumnName = "seqName", valueColumnName = "seqValue", pkColumnValue = "counter_1", initialValue = 1, allocationSize = 1)
    @Column(name = "sId")
    private Long sId;

    @Column(name = "sClass", nullable = false)
    private String sClass ;

    @Column(name = "available", nullable = false, columnDefinition = "boolean default true")
    private Boolean available ;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "fId", nullable=false)
    private Flight flight; 
    
    @OneToOne
    @JoinColumn(name = "tId")
    private Ticket ticket;
        
    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
    
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
