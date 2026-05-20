package edu.sdccd.cisc191.model;

public class Course {
    private int id;
    private String title;
    private int studentId;

    public Course(int id, String title, int studentId) {
        // TODO validate fields and assign them
        if(id <= 0){
            throw new IllegalArgumentException("Id cannot be below 0");
        }
        if(title == null || title.trim().isEmpty() ){
            throw new IllegalArgumentException("Title should not be null or empty");
        }
        if(studentId <= 0){
            throw new IllegalArgumentException("StudentID cannot be below 0");
        }
        this.id = id;
        this.title = title;
        this.studentId = studentId;
    }

    public int getId() {
        // TODO
        return id;
    }

    public String getTitle() {
        // TODO
        return title;
    }

    public int getStudentId() {
        // TODO
        return studentId;
    }

    @Override
    public String toString() {
        // TODO
        return "course id: " + id + ", title: " + title + "/" +
                ", studentId=" + studentId;
    }
}
