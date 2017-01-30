package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Company")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cId")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGenerator")
    @TableGenerator(name = "seqGenerator", table = "sequencegenerator", pkColumnName = "seqName", valueColumnName = "seqValue", pkColumnValue = "counter_1", initialValue = 1, allocationSize = 1)
    @Column(name = "cId")
    private Long cId;

    @Column(name = "name", nullable = false)
    private String cName;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company")
    private List<AirPlane> airPlanes;
    
    public Company() {
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public List<AirPlane> getAirPlanes() {
        return airPlanes;
    }

    public void setAirPlanes(List<AirPlane> airPlanes) {
        this.airPlanes = airPlanes;
    }
}