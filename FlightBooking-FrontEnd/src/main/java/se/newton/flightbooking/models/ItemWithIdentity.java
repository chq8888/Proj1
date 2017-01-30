package se.newton.flightbooking.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ItemWithIdentity {
    public int id;
    public String itemName;
    public UserWithIdentity user;

    public List<SizeWithIdentity> sizes;
        
    public ItemWithIdentity() {
        super();
    }

    public ItemWithIdentity(final int id, final String itemName, final UserWithIdentity user) {
        this.id = id;
        this.itemName = itemName;
        this.user = user;
        sizes = new ArrayList<SizeWithIdentity>();
    }
    
    public void addSize(final SizeWithIdentity size) {
        sizes.add(size);
    }
}
