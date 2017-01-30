package se.newton.flightbooking.models;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Passenger")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="pId")
public class Passenger implements Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGenerator")
    @TableGenerator(name = "seqGenerator", table = "sequencegenerator", pkColumnName = "seqName", valueColumnName = "seqValue", pkColumnValue = "counter_1", initialValue = 1, allocationSize = 1)
    @Column(name = "pId")
    private Long pId;

    @Column(name = "fname", nullable = false)
    private String pfName;

    @Column(name = "lname", nullable = false)
    private String plName;
    
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "age", nullable = false)
    private Integer age;
    
    @ManyToOne
    @JoinColumn(name = "bId", nullable = false)
    private Booking booking;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "passenger")
    private List<Ticket> tickets;
    
    public Passenger() {
    }

    public Passenger(String fname, String lname, int age, String email, String phone) {
        this();
        this.pfName = fname;
        this.plName = lname;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getPfName() {
        return pfName;
    }

    public void setPfName(String fName) {
        this.pfName = fName;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String lName) {
        this.plName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}