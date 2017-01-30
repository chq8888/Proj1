package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="aId")
@Table(name = "AirPlane")
public class AirPlane implements Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGenerator")
    @TableGenerator(name = "seqGenerator", table = "sequencegenerator", pkColumnName = "seqName", valueColumnName = "seqValue", pkColumnValue = "counter_1", initialValue = 1, allocationSize = 1)
    @Column(name = "aId")
    private Long aId;

    @Column(name = "name", nullable = false)
    private String aName;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @ManyToOne
    @JoinColumn(name = "cId", nullable=false)
    private Company company;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "airPlane")
    private List<Flight> flights; 
    
    public AirPlane() {
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public String getaName() {
        return aName;
    }

    public void setsName(String aName) {
        this.aName = aName;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}