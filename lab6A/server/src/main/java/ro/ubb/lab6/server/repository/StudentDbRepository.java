package ro.ubb.lab6.server.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.lab6.common.domain.Student;
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

public class StudentDbRepository implements Repository<Long, Student> {
    private Validator<Student> validator;
    //private Map<Long, Student> entities;
    private String url;


    //JdbcTemplate
    private JdbcTemplate jdbcTemplate;


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public StudentDbRepository(Validator<Student> studentValidator, String url) {
        this.validator = studentValidator;

        this.url = url;

    }

    public Validator<Student> getValidator() {
        return validator;
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
    public Student findOne(Long id) { //Not sure?
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        String query = "SELECT * FROM \"Students\" WHERE id=" + id + ";";
        return jdbcTemplate.queryForObject(query, Student.class);
    }

    /**
     *
     * @return all Student entities
     */
    @Override
    public Iterable<Student> findAll()
    {
        List<Student> students = new ArrayList<>();
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Students\";" );
            while ( rs.next() ) {
                long id = Long.valueOf( rs.getInt("id") );
                String  name = rs.getString("name");
                String  code = rs.getString("code");
                Student st = new Student(code, name);
                st.setId(id);
                students.add(st);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return students;
    }

    /**
     * Saves the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws EntityPresentException
     *             if the given entity is Present.
     */
    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
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

            String name = entity.getName();
            String studentCode = entity.getSerialNumber();
            Long studentId = entity.getId();
            String sql = "INSERT INTO \"Students\" (name, code, id) " +
                    "VALUES('" +
                    name + "','" +
                    studentCode + "'," + studentId +  ");";

            stmt.executeUpdate(sql);
            c.commit();
            entity = null;
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return Optional.ofNullable(entity);
    }

    /**
     * Removes the entity with the given id.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    @Override
    public Optional<Student> remove(Long id) {
        if (!findOne(id).isPresent()) {
            throw new InexistentEntityException("Entity does not exist in list!\n");
        }
        Student st = null;
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM \"Students\" WHERE id=" + id + ";";

            ResultSet rs = stmt.executeQuery( sql );

            while ( rs.next() ) {
                String name = rs.getString("name");
                String code = rs.getString("code");
                st = new Student(code, name);
                st.setId(id);
            }
            String sql2 = "DELETE FROM \"Students\" WHERE id=" + id +";";

            stmt.executeUpdate(sql2);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return Optional.ofNullable(st);
    }

    public Connection getConnection() {
        Connection conn = null;
        try{
            String driver = "org.postgresql.Driver";
            Class.forName(driver);

            conn = DriverManager.getConnection(this.url, System.getProperty("dbUsername"), System.getProperty("dbPassword"));
        }
        catch (Exception ex)
        {
            System.out.println("My exception in StudentDbRepository.");
            ex.printStackTrace();
        }
        return conn;
    }

}
/*
 @Override
    public Optional<Student> findOne(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Student st = null;
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String studentId = id.toString();
            String sql = "SELECT * FROM \"Students\" WHERE id=" + studentId + ";";

            ResultSet rs = stmt.executeQuery( sql );

            while ( rs.next() ) {
                String name = rs.getString("name");
                String code = rs.getString("code");
                st = new Student(code, name);
                st.setId(id);
            }
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return Optional.ofNullable(st);
    }
 */