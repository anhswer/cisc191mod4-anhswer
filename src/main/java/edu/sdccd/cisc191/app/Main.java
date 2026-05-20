package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.JdbcCourseRepository;
import edu.sdccd.cisc191.repository.JdbcStudentRepository;
import edu.sdccd.cisc191.repository.StudentRepository;
import edu.sdccd.cisc191.service.StudentService;

import edu.sdccd.cisc191.util.DatabaseConfig;
import edu.sdccd.cisc191.util.DatabaseInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // TODO initialize database
        DatabaseInitializer.initialize();
        try(Connection conn = DatabaseConfig.getConnection()){
            System.out.println("connected");
            // TODO create student service and repositories
            var StudentRepo = new JdbcStudentRepository(conn);
            var courseRepo = new JdbcCourseRepository(conn);
            var studentService = new StudentService(StudentRepo);

            // TODO add at least 3 students
            studentService.addStudent(new Student(1,"Bob", 3.7));
            studentService.addStudent(new Student(2,"bob1", 3.4));
            studentService.addStudent(new Student(3,"bob2", 3.9));
            // TODO add at least 3 courses linked to students
            courseRepo.save(new Course(101,"AP US History",3));
            courseRepo.save(new Course(102,"Calculus",2));
            courseRepo.save(new Course(103,"English",1));
            // TODO print all students
            System.out.println("All students:");
            studentService.getAllStudents().forEach(System.out::println);
            // TODO find one student by ID
            System.out.println("\nStudent with ID 2:");
            System.out.println(studentService.getStudent(2));
            // TODO print courses for a student
            System.out.println("\nCourses for student 1:");
            courseRepo.findByStudentId(1).forEach(System.out::println);
            // TODO update one GPA
            System.out.println("\nUpdating gpa for student 3");
            studentService.changeGpa(3,4.0);
            // TODO delete one student
            System.out.println("\nRemaining students:");
            studentService.getAllStudents().forEach(System.out::println);

            System.out.println("\nRemaining courses:");
            courseRepo.findAll().forEach(System.out::println);
            // TODO print remaining students and courses

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
