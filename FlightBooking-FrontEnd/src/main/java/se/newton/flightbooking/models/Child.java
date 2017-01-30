
package se.newton.flightbooking.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "value")
public class Child {

    private long value;
    private Parent parent;

    public long getValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Parent getParent() {
        return this.parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

}
