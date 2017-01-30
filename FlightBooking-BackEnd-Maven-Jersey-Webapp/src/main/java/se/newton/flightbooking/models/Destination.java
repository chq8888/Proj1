package se.newton.flightbooking.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Destination")
public class Destination implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dId")
    private Long dId;

    @Column(name = "name", nullable = false, unique = true)
    private String dName;
    
    public Destination() {
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
    
    @Override
    public String toString() {
        return this.dName;
    }
}