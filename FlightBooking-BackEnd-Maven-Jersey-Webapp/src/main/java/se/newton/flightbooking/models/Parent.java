package se.newton.flightbooking.models;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.List;

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Parent {

    private long value;
    
    @JsonIgnore
    private List<Child> childs;

    public Parent() {
        this.childs = new ArrayList<>();
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public List<Child> getChilds() {
        return this.childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }
}
