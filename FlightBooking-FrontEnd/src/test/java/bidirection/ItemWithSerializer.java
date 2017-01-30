package bidirection;

public class ItemWithSerializer {
    public int id;
    public String itemName;
    public UserWithSerializer user;

    public ItemWithSerializer() {
        super();
    }

    public ItemWithSerializer(final int id, final String itemName, final UserWithSerializer user) {
        this.id = id;
        this.itemName = itemName;
        this.user = user;
    }
}
