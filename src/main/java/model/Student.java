package model;

import model.Department;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    private  String name;
    private  String id;
    private Department department;
    private  LocalDate birth;
    private int credit = 0;
    private ArrayList<Course> courses = new ArrayList<>();

    public Student(String name, String id, Department department, LocalDate birth) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public static Student getStudentByName(String name){
        for (Student student : DataBase.getInstance().getStudents()) {
            if (student.getName().equals(name)){
                return student;
            }
        }
        return null;
    }
}
