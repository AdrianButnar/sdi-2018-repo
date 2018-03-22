package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;

import java.sql.*;
import java.sql.Connection;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Buhai
 */

public class StudentDbRepository implements Repository<Long, Student> {
    private Validator<Student> validator;
    private String url;
    private String username;
    private String password;

    public StudentDbRepository(Validator<Student> studentValidator, String url, String username, String password) {
        this.validator = studentValidator;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Student> findOne(Long id) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public Iterable<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");

            stmt = c.createStatement();
            System.out.println("SELECT * FROM \"Student\";");
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Students\";" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String  code = rs.getString("code");
                System.out.println( "Id = " + id );
                System.out.println( "Name = " + name );
                System.out.println( "Code = " + code );
                System.out.println();
                Student st = new Student(code, name);
                st.setId(Long.valueOf(id));
                students.add(st);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return students;
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public Optional<Student> remove(Long id) {
        throw new RuntimeException("not yet implemented");
    }

    public Connection getConnection() throws Exception {
        Connection conn = null;
        try{
            String driver = "org.postgresql.Driver";
            System.out.println(this.url);
            System.out.println(this.username);
            System.out.println(this.password);
            Class.forName(driver);

            conn = DriverManager.getConnection(this.url, this.username, this.password);
            System.out.println("Connected");

        }
        catch (Exception ex)
        {
            System.out.println("My exception");
            ex.printStackTrace();
        }
        return conn;
    }
    public void selectAll() {

        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");

            stmt = c.createStatement();
            System.out.println("SELECT * FROM \"Student\";");
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Students\";" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String  code = rs.getString("code");
                System.out.println( "Id = " + id );
                System.out.println( "Name = " + name );
                System.out.println( "Code = " + code );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }




//    @Override
//    public Optional<Student> update(Student entity) throws ValidatorException {
//        throw new RuntimeException("not yet implemented");
//    }

}
