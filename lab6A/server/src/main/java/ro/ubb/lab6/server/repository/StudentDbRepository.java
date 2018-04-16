package ro.ubb.lab6.server.repository;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
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
import static java.lang.Math.toIntExact;
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

    public StudentDbRepository( String url) {
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

    public Optional<Student> findOne(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Student st = null;
        try {
            SimpleDriverDataSource dataSource = getDataSource();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String studentId = id.toString();
            List<Student> students = jdbcTemplate.query(
                    "SELECT * FROM \"Students\" WHERE id= ?", new Object[] { Integer.parseInt(studentId) },
                    (rs, rowNum) -> new Student ( rs.getLong("id"), rs.getString("code"), rs.getString("name")));

            for(Student student : students)
            {
                if(student.getId().equals(id))
                {
                    st = student;
                    System.out.println(st.toString());
                }
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return Optional.ofNullable(st);
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
            SimpleDriverDataSource dataSource = getDataSource();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            students= jdbcTemplate.query(
                    "SELECT * FROM \"Students\"", (rs, rowNum) -> new Student ( rs.getLong("id"), rs.getString("code"), rs.getString("name")));

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
            SimpleDriverDataSource dataSource = getDataSource();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            jdbcTemplate.update("INSERT INTO \"Students\"(id, code, name) VALUES (?,?,?)", entity.getId(), entity.getSerialNumber(), entity.getName());

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

            SimpleDriverDataSource dataSource = getDataSource();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            int result = jdbcTemplate.update("DELETE FROM \"Students\" WHERE id=?", toIntExact(id));
//            if(result != 0)
//            {
//                st = new Student("Prov", "Prov")
//            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }


        return Optional.ofNullable(st);
    }

//    public Connection getConnection() {
//        Connection conn = null;
//        try{
//            String driver = "org.postgresql.Driver";
//            Class.forName(driver);
//
//            conn = DriverManager.getConnection(this.url, System.getProperty("dbUsername"), System.getProperty("dbPassword"));
//        }
//        catch (Exception ex)
//        {
//            System.out.println("My exception in StudentDbRepository.");
//            ex.printStackTrace();
//        }
//        return conn;
//    }


    public SimpleDriverDataSource getDataSource()
    {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class); //org.postgresql.Driver.class
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Mppdatabase");
        dataSource.setUsername("postgres");
        dataSource.setPassword("parola12");
        return dataSource;
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