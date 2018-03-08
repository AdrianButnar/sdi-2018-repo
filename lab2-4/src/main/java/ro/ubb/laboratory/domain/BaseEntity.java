package ro.ubb.laboratory.domain;

/**
 * @author Alexandru Buhai
 * @param <ID> Base entity
 */

public class BaseEntity<ID> {
    private ID id;

    /**
     * Get's the Id of the new Entity
     * @return id - an id of the Entity of type ID
     */

    public ID getId()
    {
        return id;
    }

    /**
     * Sets the id of the new base entity
     * @param id - new id of the Entity
     */
    public void setId(ID id)
    {
        this.id = id;
    }

    /**
     * Override the method toString of an object
     * @return String - a string in a specific format
     */
    @Override
    public String toString()
    {
        return "BaseEntity {" +
                "id = " + id +
                '}';

    }



}
