package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Booking")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="bId")
public class Booking implements Serializable {
    @Transient
    public static final String[] PAYMENT = {"Internetbank", "Mastercard", "Visa"};
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGenerator")
    @TableGenerator(name = "seqGenerator", table = "sequencegenerator", pkColumnName = "seqName", valueColumnName = "seqValue", pkColumnValue = "counter_1", initialValue = 1, allocationSize = 1)
    @Column(name = "bId")
    private Long bId;
    
    @Column(name = "fId", nullable = false)
    private Long fId;
    
    @Column(name = "frId")
    private Long frId;

    @Column(name = "payment", nullable = false)
    private String payment;
    
    @Transient
    private Integer totalPrice;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "booking")
    private List<Passenger> passengers;
            
    public Booking() {
    }
    
    public Booking(Long flightId, String payment) {
        this();
        this.fId = flightId;
        this.payment = payment;
    }

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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