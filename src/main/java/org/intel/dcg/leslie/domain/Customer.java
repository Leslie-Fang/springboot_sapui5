package org.intel.dcg.leslie.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    protected Customer() {}

    public Customer(String Name) {
        this.name = Name;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, Name='%s']",
                id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
