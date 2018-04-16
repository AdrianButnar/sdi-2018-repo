package ro.ubb.lab6.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
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

/**
 * @author Alexandru Buhai
 */

public class ProblemDbRepository implements Repository<Long, Problem> {
    private Validator<Problem> validator;
    private String url;

    @Autowired
    private JdbcOperations jdbcOperations;

    public ProblemDbRepository(Validator<Problem> problemValidator, String url) {
        this.validator = problemValidator;
        this.url = url;

    }
    public ProblemDbRepository( String url) {

        this.url = url;

    }

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
    public Optional<Problem> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Problem pb = null;
        try {

            String sql = "SELECT * FROM \"Problems\" WHERE id=" + id.toString();
            List<Problem> pbs = jdbcOperations.query(sql, (rs, i) -> {
                String text = rs.getString("text");
                int number = rs.getInt("number");
                int Id = rs.getInt("id");
                return new Problem(Long.valueOf(id), number, text);
                });

            for(Problem p : pbs)
            {
                if(p.getId().equals(id))
                {
                    pb = p;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return Optional.ofNullable(pb);
    }

    /**
     *
     * @return all Problem entities
     */
    @Override
    public Iterable<Problem> findAll() {
        List<Problem> problems = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"Problems\"";
            return jdbcOperations.query(sql, (rs, i) -> {
                        String text = rs.getString("text");
                        int number = rs.getInt("number");
                        int id = rs.getInt("id");
                        return new Problem(Long.valueOf(id), number, text);

                    });
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return problems;
    }

    /**
     * Saves the given entity.
     *
     * @param entity must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws EntityPresentException if the given entity is Present.
     */
    @Override
    public Optional<Problem> save(Problem entity) throws ValidatorException {
        if (findOne(entity.getId()).isPresent()) {
            throw new EntityPresentException("Entity already in list!\n");
        }
        System.out.println(entity);
       // validator.validate(entity); //TODO Add validator
        try {
            String sql = "INSERT INTO \"Problems\" (id, \"number\", text) values (?,?,?)";
            jdbcOperations.update(sql, entity.getId(), entity.getNumber(), entity.getText());

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
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
    public Optional<Problem> remove(Long id) {
        if (!findOne(id).isPresent()) {
            throw new InexistentEntityException("Entity does not exist in list!\n");
        }
        Problem pb = null;
        try {
            String sql = "DELETE FROM \"Problems\" WHERE id= ?";
            jdbcOperations.update(sql, toIntExact(id));


        } catch (Exception e) {

            System.err.println("My exception in problemDbRepo:\n " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(pb);
    }


}


