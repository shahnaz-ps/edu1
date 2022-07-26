package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Professor {
    private String name;
    private LocalDate birth;
    private ProfessorRank rank;
    private Department department;
    private ArrayList<Course> courses = new ArrayList<>();

    public Professor(String name, LocalDate birth, ProfessorRank rank, Department department) {
        this.name = name;
        this.birth = birth;
        this.rank = rank;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public ProfessorRank getRank() {
        return rank;
    }

    public Department getDepartment() {
        return department;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public static Professor getProfessorByName(String name){
        for (Professor professor : DataBase.getInstance().getProfessors()) {
            if (professor.getName().equals(name)){
                return professor;
            }
        }
        return null;
    }
}
