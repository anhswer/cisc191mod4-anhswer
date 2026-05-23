package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {
    private final Connection conn;
    public JdbcStudentRepository(Connection conn){
        this.conn = conn;
    }
    @Override
    public void save(Student student) {
        String sql = "Insert into students(id, name, gpa ) VALUES (?,?,?)";
        // TODO use PreparedStatement INSERT
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setDouble(3, student.getGpa());
            ps.executeUpdate();
        } catch(Exception e) {
            throw new RuntimeException("failed to save", e);
        }

    }

    @Override
    public Student findById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try(PreparedStatement pstate = conn.prepareStatement(sql)) {

            pstate.setInt(1, id);

            try(ResultSet rs = pstate.executeQuery()) {

                if(rs.next()) {

                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("gpa")
                    );
                }
            }

        } catch(Exception e) {
            throw new RuntimeException("Cant find student " + id, e);
        }
        // TODO use PreparedStatement SELECT by id
        return null;
    }

    @Override
    public List<Student> findAll() {

        String sql = "SELECT * FROM students";

        List<Student> students = new ArrayList<>();

        try(PreparedStatement pstate = conn.prepareStatement(sql);
            ResultSet rs = pstate.executeQuery()) {

            while(rs.next()) {

                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("gpa")
                ));
            }

        } catch(Exception e) {
            throw new RuntimeException("Cannot find all", e);
        }
        // TODO query all rows and map to List<Student>
        return students;

    }

    @Override
    public void updateGpa(int id, double gpa) {

        String sql = "UPDATE students SET gpa = ? WHERE id = ?";

        try(PreparedStatement pstate = conn.prepareStatement(sql)) {

            pstate.setDouble(1, gpa);
            pstate.setInt(2, id);

            pstate.executeUpdate();

        } catch(Exception e) {
            throw new RuntimeException("cannot update", e);
        }

        // TODO use PreparedStatement UPDATE
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException("Cannot delete", e);
        }
        // TODO use PreparedStatement DELETE
    }
}