package home.solmaz.JavaPersistenceWithHibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public   class BillingDetails {

    @Id
    @GeneratedValue()
    protected Long id;
    @NotNull
    protected String owner;

    public Long getId() {
        return id;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
