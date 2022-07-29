package model;

public class GradeReport {

    private Student student;
    private Course course;
    private Double grade;

    public GradeReport(Student student, Course course, Double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Double getGrade() {
        return grade;
    }
}
