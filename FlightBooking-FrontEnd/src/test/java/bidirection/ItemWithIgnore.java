package bidirection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class ItemWithIgnore {
    public int id;
    public String itemName;
    public UserWithIgnore user;
    
    @JsonIgnore
    public List<SizeWithIgnore> sizes;

    public ItemWithIgnore() {
        super();
    }

    public ItemWithIgnore(final int id, final String itemName, final UserWithIgnore user) {
        this.id = id;
        this.itemName = itemName;
        this.user = user;
        sizes = new ArrayList<SizeWithIgnore>();
    }
    
    public void addSize(final SizeWithIgnore size) {
        sizes.add(size);
    }
}
