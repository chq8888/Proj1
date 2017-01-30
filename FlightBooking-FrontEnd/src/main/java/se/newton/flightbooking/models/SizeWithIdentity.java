package se.newton.flightbooking.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SizeWithIdentity {
    public int id;
    public String sizeName;
    public ItemWithIdentity item;

    public SizeWithIdentity() {
        super();
    }

    public SizeWithIdentity(final int id, final String sizeName, final ItemWithIdentity item) {
        this.id = id;
        this.sizeName = sizeName;
        this.item = item;
    }
}
