package edu.sdccd.cisc191.service;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.CourseRepository;
import edu.sdccd.cisc191.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    public void addStudent(Student student) {
        // TODO delegate to repository

        repository.save(student);
    }
    public Student getStudent(int id) {
        // TODO
        return repository.findById(id);
    }
    public List<Student> getAllStudents() {
        // TODO
        return repository.findAll();
    }
    public void changeGpa(int id, double newGpa) {
        // TODO
        repository.updateGpa(id,newGpa);
    }
    public void removeStudent(int id) {
        // TODO
        repository.deleteById(id);
    }
}