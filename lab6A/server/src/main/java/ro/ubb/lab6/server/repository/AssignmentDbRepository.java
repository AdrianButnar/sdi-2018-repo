package ro.ubb.lab6.server.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.lab6.common.domain.Assignment;
import ro.ubb.lab6.common.domain.Problem;
import ro.ubb.lab6.common.domain.validators.EntityPresentException;
import ro.ubb.lab6.common.domain.validators.InexistentEntityException;
import ro.ubb.lab6.common.domain.validators.Validator;
import ro.ubb.lab6.common.domain.validators.ValidatorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;

//anotatii de repo..
public class AssignmentDbRepository implements Repository<Long, Assignment> {
    private Validator<Assignment> validator;

    private String url;

    @Autowired
    private JdbcOperations jdbcOperations;


    public AssignmentDbRepository(Validator<Assignment> assignmentValidator, String url) {
        validator = assignmentValidator;
        this.url = url;

    }

    public AssignmentDbRepository( String url) {
        this.url = url;

    }

    //id studentId problemId

    /**
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    @Override
    public Optional<Assignment> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Assignment as = null;
        try {

            String sql = "SELECT * FROM \"Assigned\" WHERE id=" + id.toString();
            List<Assignment> pbs = jdbcOperations.query(sql, (rs, i) -> {
                long problemId = rs.getInt("problemId");
                long studentId = rs.getInt("studentId");
                long Id = rs.getInt("id");
                return new Assignment(Id, studentId, problemId);
            });

            for(Assignment p : pbs)
            {
                if(p.getId().equals(id))
                {
                    as = p;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return Optional.ofNullable(as);
    }

    /**
     *
     * @return all Problem entities
     */
    @Override
    public Iterable<Assignment> findAll() {
        List<Assignment> assignmentList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"Assigned\"";
            return jdbcOperations.query(sql, (rs, i) -> {
                long problemId = rs.getInt("problemId");
                long studentId = rs.getInt("studentId");
                long Id = rs.getInt("id");
                return new Assignment(Id, studentId, problemId);

            });
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return assignmentList;
    }

    /**
     * Saves the given entity.
     *
     * @param entity must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws EntityPresentException if the given entity is Present.
     */
    @Override
    public Optional<Assignment> save(Assignment entity) throws ValidatorException {
        if (findOne(entity.getId()).isPresent()) {
            throw new EntityPresentException("Entity already in list!\n");
        }
        validator.validate(entity);
        try {
            String sql = "INSERT INTO \"Assigned\" (id, \"studentId\", \"problemId\") values (?,?,?)";
            jdbcOperations.update(sql, entity.getId(), entity.getStudentID(), entity.getProblemID());

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
        }

        return Optional.ofNullable(entity);
    }

    /**
     * Removes the entity with the given id.
     *
     * @param id must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException if the given id is null.
     */
    @Override
    public Optional<Assignment> remove(Long id) {
        if (!findOne(id).isPresent()) {
            throw new InexistentEntityException("Entity does not exist in list!\n");
        }
        Assignment as = null;
        try {
            String sql = "DELETE FROM \"Assigned\" WHERE id= ?";
            jdbcOperations.update(sql, toIntExact(id));

        } catch (Exception e) {

            System.err.println("My exception in problemDbRepo:\n " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(as);
    }



}
