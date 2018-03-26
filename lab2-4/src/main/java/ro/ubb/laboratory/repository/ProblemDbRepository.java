package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.Problem;
import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.EntityNonExistentException;
import ro.ubb.laboratory.domain.validators.EntityPresentException;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;

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
    private String username;
    private String password;

    public ProblemDbRepository(Validator<Problem> problemValidator, String url, String username, String password) {
        this.validator = problemValidator;

        this.url = url;
        this.username = username;
        this.password = password;
    }

    //done
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
            }
            //System.out.println("Executed successfully " + sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return Optional.ofNullable(pb);
    }

    @Override
    public Iterable<Problem> findAll() {
        List<Problem> problems = new ArrayList<>();
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Problems\";");

            while (rs.next()) {
                long id = Long.valueOf(rs.getInt("id"));
                String text = rs.getString("text");
                String number = rs.getString("number");

                Problem pb = new Problem(Integer.parseInt(number), text);
                pb.setId(id);
                problems.add(pb);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return problems;
        //System.out.println("Operation done successfully");
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

        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String text = entity.getText();
            int number = entity.getNumber();
            Long problemId = entity.getId();
            //Just a normal variable, my database is serializable
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
            throw new EntityNonExistentException("Entity does not exist in list!\n");
        }
        Problem pb = null;
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

//            System.out.println("SELECT * FROM \"Student\";");
            String sql = "DELETE FROM \"Problems\" WHERE id=" + id + ";";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return Optional.ofNullable(pb);
    }

    public Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);

            conn = DriverManager.getConnection(this.url, this.username, this.password);
            System.out.println("Connected");

        } catch (Exception ex) {
            System.out.println("My exception");
            ex.printStackTrace();
        }
        return conn;
    }


    private void selectAll() {
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");

            stmt = c.createStatement();
            //System.out.println("SELECT * FROM \"Student\";");
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Problems\";");
            while (rs.next()) {
                long id = Long.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String code = rs.getString("code");
                System.out.println("Id = " + id);
                System.out.println("Name = " + name);
                System.out.println("Code = " + code);
                System.out.println();
                Student st = new Student(code, name);
                st.setId(id);
                //entities.put(id, st);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Operation done successfully");
    }
}


//    @Override
//    public Optional<Student> update(Student entity) throws ValidatorException {
//        throw new RuntimeException("not yet implemented");
//    }

