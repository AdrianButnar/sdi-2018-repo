package ro.ubb.lab7.core.repository;

import ro.ubb.laboratory.domain.Assignment;
import ro.ubb.laboratory.domain.validators.EntityPresentException;
import ro.ubb.laboratory.domain.validators.InexistentEntityException;
import ro.ubb.laboratory.domain.validators.Validator;
import ro.ubb.laboratory.domain.validators.ValidatorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignmentDbRepository implements Repository<Long, Assignment> {
    private Validator<Assignment> validator;

    private String url;


    public AssignmentDbRepository(Validator<Assignment> assignmentValidator, String url) {
        validator = assignmentValidator;
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
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String searchId = id.toString();
            String sql = "SELECT * FROM \"Assigned\" WHERE id=" + searchId + ";";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String studentId = rs.getString("studentId");
                String problemId = rs.getString("problemId");
                as = new Assignment(Long.parseLong(studentId),Long.parseLong(problemId));
                as.setId(id);
                validator.validate(as);
            }

            stmt.close();
            c.close();
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
        Assignment as = null;
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Assigned\";");

            while (rs.next()) {
                long id = Long.valueOf(rs.getInt("id"));
                String studentId = rs.getString("studentId");
                String problemId = rs.getString("problemId");
                as = new Assignment(Long.parseLong(studentId),Long.parseLong(problemId));
                as.setId(id);
                validator.validate(as);

                assignmentList.add(as);

            }

            rs.close();
            stmt.close();
            c.close();
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
        Assignment as = null;
        try {

            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String studentId = entity.getStudentID().toString();
            String problemId = entity.getProblemID().toString();
            Long assignmentId = entity.getId();
            String sql = "INSERT INTO \"Assigned\" (id, \"studentId\", \"problemId\")" +
                    "VALUES(" +
                    assignmentId + "," +
                    studentId + ",'" + problemId + "');";

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
    public Optional<Assignment> remove(Long id) {
        if (!findOne(id).isPresent()) {
            throw new InexistentEntityException("Entity does not exist in list!\n");
        }
        Assignment as = null;
        try {
            Connection c = getConnection();
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM \"Assigned\" WHERE id=" + id + ";";

            ResultSet rs = stmt.executeQuery( sql );

            while ( rs.next() ) {
                String studentId = rs.getString("studentId");
                String problemId = rs.getString("problemId");
                as = new Assignment(Long.parseLong(studentId),Long.parseLong(problemId));
                as.setId(id);
                validator.validate(as);

            }

            String sql2 = "DELETE FROM \"Assigned\" WHERE id=" + id + ";";
            stmt.executeUpdate(sql2);
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {

            System.err.println("My exception in problemDbRepo:\n " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(as);
    }

//    public void AssignStudentToProblem(Student st, long pb)
//    {
//        try {
//            Connection c = getConnection();
//            Statement stmt = null;
//            Class.forName("org.postgresql.Driver");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//            Long stId = st.getId();
//            //Long pbId = pb.getId();
////            System.out.println("SELECT * FROM \"Student\";");
//            String sql = "INSERT INTO \"Assigned\" (\"studentId\", \"problemId\") VALUES" + "(" + stId + ", " + pb + ")";
////            System.out.println(sql);
//            stmt.executeUpdate(sql);
//            c.commit();
//            stmt.close();
//            c.close();
//
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//
//    }


    public Connection getConnection() {
        Connection conn = null;
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);

            conn = DriverManager.getConnection(this.url,  System.getProperty("dbUsername"), System.getProperty("dbPassword"));
            //System.out.println("Connected");

        } catch (Exception ex) {
            System.out.println("My exception in AssignementDBRepo");
            ex.printStackTrace();
        }
        return conn;
    }

}


//    public Assignment findOne(Long id) {
//
//        Assignment as = null;
//
//        try {
//            Connection c = getConnection();
//            Statement stmt = null;
//            Class.forName("org.postgresql.Driver");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//
//            String sql = "SELECT * FROM \"Assigned\" WHERE \"studentId\"=" + id + ";";
//            ResultSet rs = stmt.executeQuery( sql );
//
//            while ( rs.next() ) {
//                String stId = rs.getString("studentId");
//                String pbId = rs.getString("problemId");
//
//                as = new Assignment(Long.parseLong(stId), Long.parseLong(pbId));
//            }
//            stmt.close();
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//            System.exit(0);
//        }
//        return as;
//    }
//
//    public Long findAll() {
//
//        Assignment as = null;
//        List<Long> assignmentList = new ArrayList<>();
//        Long num = 0L;
//        try {
//            Connection c = getConnection();
//            Statement stmt = null;
//            Class.forName("org.postgresql.Driver");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//
//            String sql = "SELECT * FROM \"Assigned\";";
//            ResultSet rs = stmt.executeQuery( sql );
//
//            while ( rs.next() ) {
//
//                String stId = rs.getString("studentId");
//                String pbId = rs.getString("problemId");
//
//                as = new Assignment(Long.parseLong(stId), Long.parseLong(pbId));
//                assignmentList.add(as.getProblemID());
//            }
//            num = Long.valueOf(Collections.frequency(assignmentList, assignmentList.get(0)));
//            stmt.close();
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//            System.exit(0);
//        }
//
//
//        return num;
//    }