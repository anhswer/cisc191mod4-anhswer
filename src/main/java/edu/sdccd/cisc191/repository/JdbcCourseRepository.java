package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseRepository implements CourseRepository {
    private final Connection conn;

    public JdbcCourseRepository(Connection conn){
        this.conn = conn;
    }
    @Override
    public void save(Course course) {

        String sql = "INSERT INTO courses (id, title, student_id) VALUES (?, ?, ?)";

        try(PreparedStatement pstate = conn.prepareStatement(sql)) {

            pstate.setInt(1, course.getId());
            pstate.setString(2, course.getTitle());
            pstate.setInt(3, course.getStudentId());

            pstate.executeUpdate();

        } catch(Exception e) {
            throw new RuntimeException("cannot save course", e);
        }
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        String sql = "SELECT * FROM courses WHERE student_id = ?";
        List<Course> courses = new ArrayList<>();
        try(PreparedStatement pstate = conn.prepareStatement(sql)) {
            pstate.setInt(1, studentId);
            try(ResultSet rset = pstate.executeQuery()) {
                while(rset.next()) {
                    courses.add(new Course(
                            rset.getInt("id"),
                            rset.getString("title"),
                            rset.getInt("student_id")
                    ));
                }
            }
        } catch(Exception e) {
            throw new RuntimeException("not able to find" + studentId, e);
        }
        // TODO query courses by student_id and map to List<Course>
        return courses;
    }

    @Override
    public List<Course> findAll() {
        String sql = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();
        try(PreparedStatement pstate = conn.prepareStatement(sql);
            ResultSet rs = pstate.executeQuery()) {
            while(rs.next()) {

                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("student_id")
                ));
            }
        } catch(Exception e) {
            throw new RuntimeException("Cannot find", e);
        }
        return courses;
    }
}
    }
    }




