package controller;

import controller.ProgramController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.*;

import java.time.LocalDate;

public class DepartmentModePage {
    public TextField name;
    public TextArea departmentNameArea;
    public TextField studentName;
    public TextField studentId;
    public ListView studentList;
    public DatePicker studentBirth;
    public ListView professorList;
    public ListView courseList;
    public TextArea infoArea;
    public TextArea courseInfoArea;
    public ListView courseStudentsList;
    public TextField professorName;
    public DatePicker professorBirth;
    public ChoiceBox professorRank;
    public ChoiceBox courseProfessor;
    public TextField courseName;
    public ChoiceBox courseUnit;
    public TextField studentName_course;
    public TextField studentId_course;

    DataBase dataBase = DataBase.getInstance();

    private Department loggedInDepartment;

    public Department getLoggedInDepartment() {
        return loggedInDepartment;
    }

    public void setLoggedInDepartment(Department loggedInDepartment) {
        this.loggedInDepartment = loggedInDepartment;
    }

    public void welcomePage(MouseEvent mouseEvent) {
        ProgramController.changeMenu("welcome");
    }

    public void godMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("godMode");
    }

    public void studentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("studentMode");
    }

    public void ProfessorMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("professorMode");
    }

    public void initialize() {
        professorRank.getItems().add("assistantProfessor");
        professorRank.getItems().add("professor");
        professorRank.getItems().add("associateProfessor");
        for (int i = 1; i <= 6; i++) {
            courseUnit.getItems().add(i);
        }
//        for (Student student : getLoggedInDepartment().getStudents()) {
//            studentList.getItems().add(student.getName());
//        }
//        for (Professor professor : getLoggedInDepartment().getProfessors()) {
//            professorList.getItems().add(professor.getName());
//            courseProfessor.getItems().add(professor.getName());
//        }
//        for (Course course : getLoggedInDepartment().getCourses()) {
//            courseList.getItems().add(course.getName());
//        }

//        for (Department department : dataBase.getDepartments()) {
//            departmentList.getItems().add(department.getName());
//            studentDepartment.getItems().add(department.getName());
//            professorDepartment.getItems().add(department.getName());
//            courseDepartment.getItems().add(department.getName());
//        }
    }

    public void login(MouseEvent mouseEvent) {
        setLoggedInDepartment(null);
        departmentNameArea.clear();
        studentList.getItems().clear();
        professorList.getItems().clear();
        courseInfoArea.clear();
        courseList.getItems().clear();
        courseStudentsList.getItems().clear();
        infoArea.clear();
        String departmentName = name.getText();
        Department department = Department.getDepartmentByName(departmentName);
        if (department != null) {
            setLoggedInDepartment(department);
            loggedIn(department);
            departmentNameArea.setText(department.getName());
        }
        name.clear();
    }

    private void loggedIn(Department department) {
        for (Student student : getLoggedInDepartment().getStudents()) {
            studentList.getItems().add(student.getName());
        }
        for (Professor professor : getLoggedInDepartment().getProfessors()) {
            professorList.getItems().add(professor.getName());
            courseProfessor.getItems().add(professor.getName());
        }
        for (Course course : getLoggedInDepartment().getCourses()) {
            courseList.getItems().add(course.getName());
        }
    }

    public void showStudentInfo(MouseEvent mouseEvent) {
        Student student = Student.getStudentByName(studentList.getSelectionModel().getSelectedItem().toString());
        String studentInfo = "name : " + student.getName() + "\n"
                + "id : " + student.getId() + "\n"
                + "date of birth : " + student.getBirth().toString() + "\n"
                + "department : " + student.getDepartment().getName() + "\n"
                + "credits : " + student.getCredit();
        infoArea.setText(studentInfo);
    }

    public void addStudent(ActionEvent actionEvent) {
        String name = studentName.getText();
        String id = studentId.getText();
        LocalDate birth = studentBirth.getValue();
        if (!name.equals("") && !id.equals("") && birth != null) {
            Student student = new Student(name, id, getLoggedInDepartment(), birth);
            dataBase.getStudents().add(student);
            getLoggedInDepartment().getStudents().add(student);
            studentList.getItems().add(name);
        }
        studentBirth.setValue(null);
        studentId.clear();
        studentName.clear();
    }

    public void showProfessorInfo(MouseEvent mouseEvent) {
        Professor professor = Professor.getProfessorByName(professorList.getSelectionModel().getSelectedItem().toString());
        String professorInfo = "name : " + professor.getName() + "\n"
                + "academic rank : " + professor.getRank() + "\n"
                + "date of birth : " + professor.getBirth().toString() + "\n"
                + "department : " + professor.getDepartment().getName();
        infoArea.setText(professorInfo);
    }

    public void addProfessor(ActionEvent actionEvent) {
        String name = professorName.getText();
        LocalDate birth = professorBirth.getValue();
        String pRank = professorRank.getValue().toString();
        ProfessorRank rank = ProfessorRank.valueOfLabel(pRank);
        if (!name.equals("") && birth != null) {
            Professor professor = new Professor(name, birth, rank, getLoggedInDepartment());
            dataBase.getProfessors().add(professor);
            getLoggedInDepartment().getProfessors().add(professor);
            professorList.getItems().add(name);
            courseProfessor.getItems().add(name);
        }
        professorName.clear();
        professorBirth.setValue(null);
        professorRank.setValue(null);
    }

    public void showCourseInfo(MouseEvent mouseEvent) {
        courseStudentsList.getItems().clear();
        Course course = Course.getCourseByName(courseList.getSelectionModel().getSelectedItem().toString());
        String courseInfo = "name : " + course.getName() + "\n"
                + "credit : " + course.getCredit() + "\n"
                + "professor : " + course.getProfessor().getName() + "\n"
                + "department : " + course.getDepartment().getName();
        courseInfoArea.setText(courseInfo);
        for (Student student : course.getStudents()) {
            courseStudentsList.getItems().add(student.getName());
        }
    }

    public void addCourse(ActionEvent actionEvent) {
        String name = courseName.getText();
        if (courseUnit.getValue() != null && courseProfessor.getValue() != null) {
            int credit = Integer.parseInt(courseUnit.getValue().toString());
            Professor professor = Professor.getProfessorByName(courseProfessor.getValue().toString());
            if (!name.equals("")) {
                Course course = new Course(name, credit, getLoggedInDepartment(), professor);
                dataBase.getCourses().add(course);
                professor.getCourses().add(course);
                getLoggedInDepartment().getCourses().add(course);
                courseList.getItems().add(name);
            }
        }
        courseName.clear();
        courseUnit.setValue(null);
        courseProfessor.setValue(null);
    }

    public void addStudentToCourse(ActionEvent actionEvent) {
        Course course = Course.getCourseByName(courseList.getSelectionModel().getSelectedItem().toString());
        String name = studentName_course.getText();
        String id = studentId_course.getText();
        Student student = Student.getStudentByName(name);
        if (student.getId().equals(id)) {
            if (!course.getStudents().contains(student)) {
                student.getCourses().add(course);
                course.getStudents().add(student);
                student.setCredit(student.getCredit() + course.getCredit());
                courseStudentsList.getItems().add(student.getName());
            }
        }
        studentId_course.clear();
        studentName_course.clear();
    }

    public void removeStudentFromCourse(ActionEvent actionEvent) {
        Course course = Course.getCourseByName(courseList.getSelectionModel().getSelectedItem().toString());
        String name = studentName_course.getText();
        String id = studentId_course.getText();
        Student student = Student.getStudentByName(name);
        if (student.getId().equals(id)) {
            if (course.getStudents().contains(student)) {
                course.getStudents().remove(student);
                student.getCourses().remove(course);
                student.setCredit(student.getCredit() - course.getCredit());
                courseStudentsList.getItems().clear();
                for (Student students : course.getStudents()) {
                    courseStudentsList.getItems().add(students.getName());
                }
            }
        }
        studentId_course.clear();
        studentName_course.clear();
    }

    public void showStudentInfo2(MouseEvent mouseEvent) {
        Student student = Student.getStudentByName(courseStudentsList.getSelectionModel().getSelectedItem().toString());
        String studentInfo = "name : " + student.getName() + "\n"
                + "id : " + student.getId() + "\n"
                + "date of birth : " + student.getBirth().toString() + "\n"
                + "department : " + student.getDepartment().getName() + "\n"
                + "credits : " + student.getCredit();
        infoArea.setText(studentInfo);
    }
}
