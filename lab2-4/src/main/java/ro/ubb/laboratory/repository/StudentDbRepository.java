package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.Student;
import ro.ubb.laboratory.domain.validators.EntityNonExistentException;
import ro.ubb.laboratory.domain.validators.EntityPresentException;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;

import java.sql.*;
import java.sql.Connection;
import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexandru Buhai
 */

public class StudentDbRepository implements Repository<Long, Student> {
    private Validator<Student> validator;
    //private Map<Long, Student> entities;
    private String url;
    private String username;
    private String password;

    public StudentDbRepository(Validator<Student> studentValidator, String url, String username, String password) {
        this.validator = studentValidator;

        this.url = url;
        this.username = username;
        this.password = password;
        Optional<Student> st = findOne(17L);
        System.out.println(st.toString());
    //    entities = new HashMap<>();
       //selectAll();

    }

    //done
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
//                System.out.println("Id = " + id);
//                System.out.println("Name = " + name);
//                System.out.println("Code = " + code);
//                System.out.println();
                st = new Student(code, name);
                st.setId(id);
            }
            //System.out.println("Executed successfully " + sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return Optional.ofNullable(st);
    }

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
//                System.out.println( "Id = " + id );
//                System.out.println( "Name = " + name );
//                System.out.println( "Code = " + code );
//                System.out.println();
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
        //System.out.println("Operation done successfully");
    }

    @Override
    public Optional<Student> save(Student entity) throws ValidatorException {
        if (findOne(entity.getId()).isPresent()) {
            throw new EntityPresentException("Entity already in list!\n");
        }
        //Student st = null;
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String name = entity.getName();
            String studentCode = entity.getSerialNumber();

            //Just a normal variable, my database is serializable
            String sql = "INSERT INTO \"Students\" (name, code, id) " +
                    "VALUES('" +
                    name + "','" +
                    studentCode + "');";

            stmt.executeUpdate(sql);
            c.commit();
            //System.out.println(stmt.executeUpdate(sql));
            //System.out.println("Executed successfully " + sql);
            entity = null;
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Student> remove(Long id) {
//        if (!findOne(id).isPresent()) {
            throw new EntityNonExistentException("Entity does not exist in list!\n");
//        }
//        try {
//            Connection c = getConnection();
//            Statement stmt = null;
//
//            Class.forName("org.postgresql.Driver");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//
//            System.out.println("SELECT * FROM \"Student\";");
//            String sql = "INSERT INTO \"Students\" " +
//                    "VALUES('" +
//                    name + "','" +
//                    studentCode + "');";
//
//            stmt.execute(sql);
//
//            stmt.close();
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//            System.exit(0);
//        }
//
//        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
//        return Optional.ofNullable(entities.remove(id));
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


    private void selectAll() {
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");

            stmt = c.createStatement();
            //System.out.println("SELECT * FROM \"Student\";");
            ResultSet rs = stmt.executeQuery( "SELECT * FROM \"Students\";" );
            while ( rs.next() ) {
                long id = Long.valueOf( rs.getInt("id") );
                String  name = rs.getString("name");
                String  code = rs.getString("code");
                System.out.println( "Id = " + id );
                System.out.println( "Name = " + name );
                System.out.println( "Code = " + code );
                System.out.println();
                Student st = new Student(code, name);
                st.setId(id);
                //entities.put(id, st);

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
