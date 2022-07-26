package model;

import java.util.ArrayList;

public class Course {
    private  String name;
    private int credit;
    private Department department;
    private Professor professor;
    private ArrayList<Student> students = new ArrayList<>();

    public Course(String name, int credit, Department department, Professor professor) {
        this.name = name;
        this.credit = credit;
        this.department = department;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public Department getDepartment() {
        return department;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public static Course getCourseByName(String name){
        for (Course course : DataBase.getInstance().getCourses()) {
            if (course.getName().equals(name)){
                return course;
            }
        }
        return null;
    }
}
