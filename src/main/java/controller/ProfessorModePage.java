package controller;

import controller.ProgramController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

public class ProfessorModePage {
    public TextField professorName;
    public TextField departmentName;
    public TextArea professorInfoArea;
    public ListView allProfessorsList;
    public TextArea allProfessorsInfoArea;
    public ListView coursesList;
    public TextArea courseInfoArea;
    public ListView courseStudentsList;
    public TextField courseName;
    public ChoiceBox courseUnit;
    public TextField studentName;
    public TextField studentId;
    @FXML
    private VBox infoVbox;


    DataBase dataBase = DataBase.getInstance();

    private Professor loggedInProfessor;

    public Professor getLoggedInProfessor() {
        return loggedInProfessor;
    }

    public void setLoggedInProfessor(Professor loggedInProfessor) {
        this.loggedInProfessor = loggedInProfessor;
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

    public void departmentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("departmentMode");
    }

    public void initialize() {
        for (int i=1; i<=6; i++){
            courseUnit.getItems().add(i);
        }
    }

    public void login(MouseEvent mouseEvent) {
        courseInfoArea.clear();
        professorInfoArea.clear();
        allProfessorsList.getItems().clear();
        coursesList.getItems().clear();
        professorInfoArea.clear();

        String pName = professorName.getText();
        String dName = departmentName.getText();
        Professor professor = Professor.getProfessorByName(pName);
        if (professor != null) {
            if (professor.getDepartment() == Department.getDepartmentByName(dName)) {
                setLoggedInProfessor(professor);
                loggedIn(professor);
            }
        }
        professorName.clear();
        departmentName.clear();
    }

    private void loggedIn(Professor professor) {
        String professorInfo = "name : " + professor.getName() + "\n"
                + "academic rank : " + professor.getRank() + "\n"
                + "date of birth : " + professor.getBirth().toString() + "\n"
                + "department : " + professor.getDepartment().getName();
        professorInfoArea.setText(professorInfo);

        for (Professor professor1 : professor.getDepartment().getProfessors()) {
            allProfessorsList.getItems().add(professor1.getName());
        }
        for (Course course : professor.getCourses()) {
            coursesList.getItems().add(course.getName());
        }
    }

    public void showProfessorInfo(MouseEvent mouseEvent) {
        Professor professor = Professor.getProfessorByName(allProfessorsList.getSelectionModel().getSelectedItem().toString());
        String professorInfo = "name : " + professor.getName() + "\n"
                + "academic rank : " + professor.getRank() + "\n"
                + "date of birth : " + professor.getBirth().toString() + "\n"
                + "department : " + professor.getDepartment().getName();
        allProfessorsInfoArea.setText(professorInfo);
    }

    public void showCourseInfo(MouseEvent mouseEvent) {
        courseInfoArea.clear();
        Course course = Course.getCourseByName(coursesList.getSelectionModel().getSelectedItem().toString());
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
        if (courseUnit.getValue()!=null){
            int credit = Integer.parseInt(courseUnit.getValue().toString());
            if (!name.equals("")){
                Course course = new Course(name,credit,getLoggedInProfessor().getDepartment(),getLoggedInProfessor());
                dataBase.getCourses().add(course);
                getLoggedInProfessor().getCourses().add(course);
                getLoggedInProfessor().getDepartment().getCourses().add(course);
                coursesList.getItems().add(name);
            }
        }
        courseName.clear();
        courseUnit.setValue(null);
    }

    public void removeStudentFromCourse(ActionEvent actionEvent) {
        Course course = Course.getCourseByName(coursesList.getSelectionModel().getSelectedItem().toString());
        String name = studentName.getText();
        String id = studentId.getText();
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
        studentName.clear();
        studentId.clear();
    }
}
