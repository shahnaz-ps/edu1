package model;

import java.util.ArrayList;

public class Department {
    private String name;
    private String id;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public Department(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public static Department getDepartmentByName(String name){
        for (Department department : DataBase.getInstance().getDepartments()) {
            if (department.getName().equals(name)){
                return department;
            }
        }
        return null;
    }
}
