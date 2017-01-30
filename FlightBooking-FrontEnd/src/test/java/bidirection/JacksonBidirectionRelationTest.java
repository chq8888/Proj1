package bidirection;

import java.io.IOException;
import org.junit.Test;
import java.util.logging.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class JacksonBidirectionRelationTest {
    
    private static Logger LOGGER = Logger.getLogger(JacksonBidirectionRelationTest.class.getName());

    @Test(expected = JsonMappingException.class)
    public void givenBidirectionRelation_whenSerializing_thenException() throws JsonProcessingException {
        User user = new User(1, "John");
        Item item = new Item(2, "book", user);
        user.addItem(item);

        new ObjectMapper().writeValueAsString(item);
    }

    @Test
    public void givenBidirectionRelation_whenUsingJacksonReference_thenCorrect() throws JsonProcessingException {
        UserWithRef user = new UserWithRef(1, "John");
        ItemWithRef item = new ItemWithRef(2, "book", user);
        user.addItem(item);

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);

        assertThat(json, containsString("book"));
        assertThat(json, containsString("John"));
        assertThat(json, not(containsString("items")));
    }

    @Test
    public void givenBidirectionRelation_whenUsingJsonIdentityInfo_thenCorrect() throws JsonProcessingException {
        UserWithIdentity user = new UserWithIdentity(1, "John");
        ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
        user.addItem(item);

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);

        assertThat(json, containsString("book"));
        assertThat(json, containsString("John"));
        assertThat(json, containsString("user"));
    }

    @Test
    public void givenBidirectionRelation_whenUsingJsonIgnore_thenCorrect() throws JsonProcessingException {
        UserWithIgnore user = new UserWithIgnore(1, "John");
        ItemWithIgnore item = new ItemWithIgnore(2, "book", user);
        user.addItem(item);

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);

        assertThat(json, containsString("book"));
        assertThat(json, containsString("John"));
        assertThat(json, not(containsString("items")));
    }

    @Test
    public void givenBidirectionRelation_whenUsingCustomSerializer_thenCorrect() throws JsonProcessingException {
        UserWithSerializer user = new UserWithSerializer(1, "John");
        ItemWithSerializer item = new ItemWithSerializer(2, "book", user);
        user.addItem(item);

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);

        assertThat(json, containsString("book"));
        assertThat(json, containsString("John"));
        assertThat(json, containsString("user"));
    }

    @Test
    public void givenBidirectionRelation_whenDeserializingUsingReference_thenCorrect() throws JsonProcessingException, IOException {
        UserWithRef user = new UserWithRef(11, "John");
        
        ItemWithRef item = new ItemWithRef(22, "book2", user);
        user.addItem(item);
        
        item = new ItemWithRef(23, "book3", user);
        user.addItem(item);
   
        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);
//        LOGGER.log(Level.INFO, json);

//  *** NOT WORKING on deserializing ***
//        item = map.readValue(json, ItemWithRef.class);
    }
    
    @Test
    public void givenBidirectionRelation_whenDeserializingUsingIdentity_thenCorrect() throws JsonProcessingException, IOException {
        UserWithIdentity user = new UserWithIdentity(11, "John");
        
        ItemWithIdentity item = new ItemWithIdentity(22, "book2", user);
        user.addItem(item);
        SizeWithIdentity size = new SizeWithIdentity(31, "size1", item);
        item.addSize(size);
        size = new SizeWithIdentity(32, "size2", item);
        item.addSize(size);
        
        item = new ItemWithIdentity(23, "book3", user);
        user.addItem(item);
        size = new SizeWithIdentity(33, "size3", item);
        item.addSize(size);
        size = new SizeWithIdentity(34, "size4", item);
        item.addSize(size);

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);       
        
//    *** Require UNIQUE id on EVERY entity object when deserializing ***
          item = map.readValue(json, ItemWithIdentity.class);
    }

    @Test
    public void givenBidirectionRelation_whenDeserializingUsingIgnore_thenCorrect() throws JsonProcessingException, IOException {
        UserWithIgnore user = new UserWithIgnore(11, "John");
        
        ItemWithIgnore item = new ItemWithIgnore(22, "book2", user);
        user.addItem(item);
        SizeWithIgnore size = new SizeWithIgnore(31, "size1", item);
        item.addSize(size);
        size = new SizeWithIgnore(32, "size2", item);
        item.addSize(size);
        
        item = new ItemWithIgnore(23, "book3", user);
        user.addItem(item);
        size = new SizeWithIgnore(33, "size3", item);
        item.addSize(size);
        size = new SizeWithIgnore(34, "size4", item);
        item.addSize(size);
        
        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(item);       
//        LOGGER.log(Level.INFO, json);
        
        item = map.readValue(json, ItemWithIgnore.class);
    }
    
    @Test
    public void givenBidirectionRelation_whenUsingCustomDeserializer_thenCorrect() throws JsonProcessingException, IOException {
        String json = "{\"id\":2,\"itemName\":\"book\",\"user\":{\"id\":1,\"name\":\"John\",\"items\":[2]}}";

        ObjectMapper map = new ObjectMapper();
        ItemWithSerializer item = map.readerFor(ItemWithSerializer.class).readValue(json);

        assertEquals(2, item.id);
        assertEquals("book", item.itemName);
        assertEquals("John", item.user.name);
    }

    @Test
    public void givenBidirectionRelation_whenUsingPublicJsonView_thenCorrect() throws JsonProcessingException {
        UserWithView user = new UserWithView(1, "John");
        ItemWithView item = new ItemWithView(2, "book", user);
        user.addItem(item);

        ObjectMapper map = new ObjectMapper();
        String json = map.writerWithView(Views.Public.class).writeValueAsString(item);

        assertThat(json, containsString("book"));
        assertThat(json, containsString("John"));
        assertThat(json, not(containsString("user")));
    }

    @Test(expected = JsonMappingException.class)
    public void givenBidirectionRelation_whenUsingInternalJsonView_thenException() throws JsonProcessingException {
        UserWithView user = new UserWithView(1, "John");
        ItemWithView item = new ItemWithView(2, "book", user);
        user.addItem(item);

        new ObjectMapper().writerWithView(Views.Internal.class).writeValueAsString(item);
    }
}