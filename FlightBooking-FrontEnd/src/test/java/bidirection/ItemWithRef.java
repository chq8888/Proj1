package bidirection;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ItemWithRef {
    public int id;
    public String itemName;

    @JsonManagedReference
    public UserWithRef user;
   
    public ItemWithRef() {
        super();
    }

    public ItemWithRef(final int id, final String itemName, final UserWithRef user) {
        this.id = id;
        this.itemName = itemName;
        this.user = user;
    }
}
