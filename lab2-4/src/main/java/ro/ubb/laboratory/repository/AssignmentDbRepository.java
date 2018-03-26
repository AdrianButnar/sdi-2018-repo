package ro.ubb.laboratory.repository;

import ro.ubb.laboratory.domain.Assignment;
import ro.ubb.laboratory.domain.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignmentDbRepository {

    private String url;
    private String username;
    private String password;

    public AssignmentDbRepository() {
        this.url = "jdbc:postgresql://localhost:5432/Mppdatabase";
        this.username =  "postgres";
        this.password = "parola12";
    }

    public void AssignStudentToProblem(Student st, long pb)
    {
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            Long stId = st.getId();
            //Long pbId = pb.getId();
//            System.out.println("SELECT * FROM \"Student\";");
            String sql = "INSERT INTO \"Assigned\" (\"studentId\", \"problemId\"7) VALUES" + "(" + stId + ", " + pb + ")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

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


    public Assignment findOne(Long id) {

        Assignment as = null;

        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "SELECT * FROM \"Assigned\" WHERE \"studentId\"=" + id + ";";
            ResultSet rs = stmt.executeQuery( sql );

            while ( rs.next() ) {
                String stId = rs.getString("studentId");
                String pbId = rs.getString("problemId");

                as = new Assignment(Long.parseLong(stId), Long.parseLong(pbId));
            }
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return as;
    }


    public Assignment findAll() {

        Assignment as = null;
        List<Assignment> assignmentList = new ArrayList<>();
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "SELECT * FROM \"Assigned\";";
            ResultSet rs = stmt.executeQuery( sql );

            while ( rs.next() ) {

                String stId = rs.getString("studentId");
                String pbId = rs.getString("problemId");

                as = new Assignment(Long.parseLong(stId), Long.parseLong(pbId));
                assignmentList.add(as);
            }
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }


        return as;
    }
}
