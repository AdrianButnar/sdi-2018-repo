package ro.ubb.lab11.core.repository;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Getter;
import ro.ubb.lab11.core.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by radu.
 */
@Getter
public abstract class CustomRepositorySupport<T extends BaseEntity<ID>, ID extends Serializable> {
    @PersistenceContext
    private EntityManager entityManager;
}
