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
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String problemId = id.toString();
            String sql = "SELECT * FROM \"Problems\" WHERE id=" + problemId + ";";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String text = rs.getString("text");
                String number = rs.getString("number");
                pb = new Problem(Integer.parseInt(number), text);
                pb.setId(id);
                validator.validate(pb);
            }

            stmt.close();
            c.close();
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
                        Problem pb = new Problem(number, text);
                        pb.setId(Long.valueOf(id));
                        return pb;
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
        validator.validate(entity);
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String text = entity.getText();
            int number = entity.getNumber();
            Long problemId = entity.getId();
            String sql = "INSERT INTO \"Problems\" (id, \"number\", text)" +
                    "VALUES(" +
                    problemId + "," +
                    number + ",'" + text + "');";

            stmt.executeUpdate(sql);
            c.commit();
            entity = null;
            stmt.close();
            c.close();
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
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM \"Problems\" WHERE id=" + id + ";";

            ResultSet rs = stmt.executeQuery( sql );

            while ( rs.next() ) {
                String text = rs.getString("text");
                String number = rs.getString("number");
                pb = new Problem(Integer.parseInt(number), text);
                pb.setId(id);
                validator.validate(pb);

            }

            String sql2 = "DELETE FROM \"Problems\" WHERE id=" + id + ";";
            stmt.executeUpdate(sql2);
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {

            System.err.println("My exception in problemDbRepo:\n " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(pb);
    }

    public Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);

            conn = DriverManager.getConnection(this.url, System.getProperty("dbUsername"), System.getProperty("dbPassword"));
            System.out.println("Connected");

        } catch (Exception ex) {
            System.out.println("My exception in problemDbRepository ");
            ex.printStackTrace();
        }
        return conn;
    }
}


