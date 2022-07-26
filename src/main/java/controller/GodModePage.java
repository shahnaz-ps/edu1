package controller;

import controller.ProgramController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.*;

import java.time.LocalDate;

public class GodModePage {
    DataBase dataBase = DataBase.getInstance();

    @FXML
    private VBox studentsVbox;
    @FXML
    private VBox professorsVbox;
    @FXML
    private VBox courseVbox;
    @FXML
    private VBox departmentVbox;
    @FXML
    private VBox infoVbox;
    @FXML
    private ListView departmentList;
    @FXML
    private TextField departmentName;
    @FXML
    private TextField departmentId;
    @FXML
    private ListView studentList;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentId;
    @FXML
    private DatePicker studentBirth;
    @FXML
    private ChoiceBox studentDepartment;
    @FXML
    private ListView professorList;
    @FXML
    private TextField professorName;
    @FXML
    private DatePicker professorBirth;
    @FXML
    private ChoiceBox professorDepartment;
    @FXML
    private ChoiceBox professorRank;
    @FXML
    private ListView courseList;
    @FXML
    private TextField courseName;
    @FXML
    private ChoiceBox courseDepartment;
    @FXML
    private ChoiceBox courseProfessor;
    @FXML
    private ChoiceBox courseUnit;
    @FXML
    private TextArea infoArea;

    public void welcomePage(MouseEvent mouseEvent) {
        ProgramController.changeMenu("welcome");
    }

    public void studentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("studentMode");
    }

    public void ProfessorMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("professorMode");
    }

    public void departmentMode(MouseEvent mouseEvent) {
        ProgramController.changeMenu("departmentMode");
    }

    public void initialize(){
        professorRank.getItems().add("assistantProfessor");
        professorRank.getItems().add("professor");
        professorRank.getItems().add("associateProfessor");
        for (int i=1; i<=6; i++){
            courseUnit.getItems().add(i);
        }
        for (Department department : dataBase.getDepartments()) {
            departmentList.getItems().add(department.getName());
            studentDepartment.getItems().add(department.getName());
            professorDepartment.getItems().add(department.getName());
            courseDepartment.getItems().add(department.getName());
        }
        for (Student student : dataBase.getStudents()) {
            studentList.getItems().add(student.getName());
        }
        for (Professor professor : dataBase.getProfessors()) {
            professorList.getItems().add(professor.getName());
            courseProfessor.getItems().add(professor.getName());
        }
        for (Course course : dataBase.getCourses()) {
            courseList.getItems().add(course.getName());
        }
    }

    public void addDepartment(ActionEvent actionEvent) {
        String name = departmentName.getText();
        String id = departmentId.getText();
        if (!name.equals("") && !id.equals("")){
            Department department = new Department(name,id);
            dataBase.getDepartments().add(department);
            departmentList.getItems().add(name);
            studentDepartment.getItems().add(name);
            professorDepartment.getItems().add(name);
            courseDepartment.getItems().add(name);
        }
        departmentName.clear();
        departmentId.clear();
    }
    
    public void addStudent(ActionEvent actionEvent) {
        String name = studentName.getText();
        String id = studentId.getText();
        LocalDate birth = studentBirth.getValue();
        if (studentDepartment.getValue()!=null){
            Department department = Department.getDepartmentByName(studentDepartment.getValue().toString());
            if (!name.equals("") && !id.equals("") && birth!=null){
                Student student = new Student(name,id,department,birth);
                dataBase.getStudents().add(student);
                department.getStudents().add(student);
                studentList.getItems().add(name);
            }
        }
        studentBirth.setValue(null);
        studentDepartment.setValue(null);
        studentId.clear();
        studentName.clear();
    }

    public void addProfessor(ActionEvent actionEvent) {
        String name = professorName.getText();
        LocalDate birth = professorBirth.getValue();
        if (professorDepartment.getValue()!=null && professorRank.getValue()!=null){
            Department department = Department.getDepartmentByName(professorDepartment.getValue().toString());
            String pRank = professorRank.getValue().toString();
            ProfessorRank rank = ProfessorRank.valueOfLabel(pRank);
            if (!name.equals("") && birth!=null){
                Professor professor = new Professor(name,birth,rank,department);
                dataBase.getProfessors().add(professor);
                department.getProfessors().add(professor);
                professorList.getItems().add(name);
                courseProfessor.getItems().add(name);
            }
        }
        professorName.clear();
        professorBirth.setValue(null);
        professorDepartment.setValue(null);
        professorRank.setValue(null);
    }

    public void addCourse(ActionEvent actionEvent) {
        String name = courseName.getText();
        if (courseUnit.getValue()!=null && courseDepartment.getValue()!=null && courseProfessor.getValue()!=null){
            int credit = Integer.parseInt(courseUnit.getValue().toString());
            Department department = Department.getDepartmentByName(courseDepartment.getValue().toString());
            Professor professor = Professor.getProfessorByName(courseProfessor.getValue().toString());
            if (!name.equals("") && professor.getDepartment()==department){
                Course course = new Course(name,credit,department,professor);
                dataBase.getCourses().add(course);
                professor.getCourses().add(course);
                department.getCourses().add(course);
                courseList.getItems().add(name);
            }
        }
        courseName.clear();
        courseUnit.setValue(null);
        courseProfessor.setValue(null);
        courseDepartment.setValue(null);
    }

    public void showStudentInfo(MouseEvent mouseEvent) {
        Student student = Student.getStudentByName(studentList.getSelectionModel().getSelectedItem().toString());
        String studentInfo ="name : " + student.getName() + "\n"
                + "id : " + student.getId() + "\n"
                + "date of birth : " + student.getBirth().toString() + "\n"
                + "department : " + student.getDepartment().getName() + "\n"
                + "credits : " + student.getCredit();
        infoArea.setText(studentInfo);
    }

    public void showProfessorInfo(MouseEvent mouseEvent) {
        Professor professor = Professor.getProfessorByName(professorList.getSelectionModel().getSelectedItem().toString());
        String professorInfo = "name : " + professor.getName() + "\n"
                + "academic rank : " + professor.getRank() + "\n"
                + "date of birth : " + professor.getBirth().toString() + "\n"
                + "department : " + professor.getDepartment().getName();
        infoArea.setText(professorInfo);
    }

    public void showCourseInfo(MouseEvent mouseEvent) {
        Course course = Course.getCourseByName(courseList.getSelectionModel().getSelectedItem().toString());
        String courseInfo = "name : " + course.getName() + "\n"
                + "credit : " + course.getCredit() + "\n"
                + "professor : " + course.getProfessor().getName() + "\n"
                + "department : " + course.getDepartment().getName();
        infoArea.setText(courseInfo);
    }

    public void showDepartmentInfo(MouseEvent mouseEvent) {
        Department department = Department.getDepartmentByName(departmentList.getSelectionModel().getSelectedItem().toString());
        String departmentInfo = "name : " + department.getName() + "\n"
                + "id : " + department.getId();
        infoArea.setText(departmentInfo);
    }
}
