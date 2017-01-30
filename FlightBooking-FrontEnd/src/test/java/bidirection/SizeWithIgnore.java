package bidirection;

public class SizeWithIgnore {
    public int id;
    public String sizeName;
    
    public ItemWithIgnore item;

    public SizeWithIgnore() {
        super();
    }

    public SizeWithIgnore(final int id, final String sizeName, final ItemWithIgnore item) {
        this.id = id;
        this.sizeName = sizeName;
        this.item = item;
    }
}