package bidirection;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class UserWithRef {
    public int id;
    public String name;

    @JsonBackReference
    public List<ItemWithRef> items;

    public UserWithRef() {
        super();
    }

    public UserWithRef(final int id, final String name) {
        this.id = id;
        this.name = name;
        items = new ArrayList<ItemWithRef>();
    }

    public void addItem(final ItemWithRef item) {
        items.add(item);
    }
}
