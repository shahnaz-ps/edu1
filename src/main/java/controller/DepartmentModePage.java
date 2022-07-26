package controller;

import controller.ProgramController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

public class DepartmentModePage {
    public TextField name;
    public TextArea departmentNameArea;
    public TextField studentName;
    public TextField studentId;
    public ListView studentList;
    public DatePicker studentBirth;
    public ListView professorList;
    public ListView courseList;

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

    public void initialize(){

    }

    public void login(MouseEvent mouseEvent) {
        setLoggedInDepartment(null);
        departmentNameArea.setText("");
        String departmentName = name.getText();
        Department department = Department.getDepartmentByName(departmentName);
        if (department != null){
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
        }
        for (Course course : getLoggedInDepartment().getCourses()) {
            courseList.getItems().add(course.getName());
        }

    }

    public void showStudentInfo(MouseEvent mouseEvent) {
    }

    public void addStudent(ActionEvent actionEvent) {
    }

    public void showProfessorInfo(MouseEvent mouseEvent) {
    }

    public void addProfessor(ActionEvent actionEvent) {
    }

    public void showCourseInfo(MouseEvent mouseEvent) {
    }

    public void addCourse(ActionEvent actionEvent) {
    }
}
