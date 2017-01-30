package se.newton.flightbooking.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "value")
public class Parent {

    private long value;
    private List<Child> childs = new ArrayList<Child>(0);

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
