package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Course;
import model.DataBase;
import model.Student;

public class StudentModePage {

    public ListView courseList;
    public TextArea studentInfoArea;
    public ChoiceBox allCourses;
    public TextArea allCourseInfo;
    public TextArea courseInfoArea;
    public TextField name;
    public TextField id;

    DataBase dataBase = DataBase.getInstance();

    private Student loggedInStudent;

    public void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }
    public Student getLoggedInStudent() {
        return loggedInStudent;
    }


    public void initialize(){
        for (Course course : dataBase.getCourses()) {
            allCourses.getItems().add(course.getName());
        }
//        if (getLoggedInStudent()!=null){
//            for (Course course : getLoggedInStudent().getCourses()) {
//                courseList.getItems().add(course.getName());
//            }
//        }

    }

    public void welcomePage(MouseEvent mouseEvent) {
        ProgramController.changeMenu("welcome");
    }

    public void godMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("godMode");
    }

    public void ProfessorMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("professorMode");
    }

    public void departmentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("departmentMode");
    }

    public void login(MouseEvent mouseEvent) {
        setLoggedInStudent(null);
        studentInfoArea.clear();
        allCourseInfo.clear();
        courseInfoArea.clear();
        courseList.getItems().clear();
        allCourses.setValue(null);
        String studentName = name.getText();
        String studentId = id.getText();
        Student student = Student.getStudentByName(studentName);
        if (student != null){
            if (student.getId().equals(studentId)){
                setLoggedInStudent(student);
                loggedIn(student);
            }
        }
        name.clear();
        id.clear();
    }

    private void loggedIn(Student student) {
        String studentInfo =  "name : " + student.getName() + "\n"
                + "id : " + student.getId() + "\n"
                + "date of birth : " + student.getBirth().toString() + "\n"
                + "department : " + student.getDepartment().getName() + "\n"
                + "credits : " + student.getCredit();
        studentInfoArea.setText(studentInfo);

        for (Course course : getLoggedInStudent().getCourses()) {
            courseList.getItems().add(course.getName());
        }
    }

    public void takeCourse(ActionEvent actionEvent) {
        Course course = Course.getCourseByName(allCourses.getValue().toString());
        if (!getLoggedInStudent().getCourses().contains(course)){
            course.getStudents().add(getLoggedInStudent());
            getLoggedInStudent().getCourses().add(course);
            getLoggedInStudent().setCredit(getLoggedInStudent().getCredit()+course.getCredit());
            courseList.getItems().add(course.getName());
        }
        allCourses.setValue(null);
    }

    public void showAllCourseInfo(ActionEvent mouseEvent) {
        Course course = Course.getCourseByName(allCourses.getValue().toString());
        String courseInfo = "name : " + course.getName() + "\n"
                + "credit : " + course.getCredit() + "\n"
                + "professor : " + course.getProfessor().getName() + "\n"
                + "department : " + course.getDepartment().getName();
        allCourseInfo.setText(courseInfo);
    }

    public void showCourseInfo(MouseEvent mouseEvent) {
        Course course = Course.getCourseByName(courseList.getSelectionModel().getSelectedItem().toString());
        String courseInfo = "name : " + course.getName() + "\n"
                + "credit : " + course.getCredit() + "\n"
                + "professor : " + course.getProfessor().getName() + "\n"
                + "department : " + course.getDepartment().getName();
        courseInfoArea.setText(courseInfo);
    }
}
