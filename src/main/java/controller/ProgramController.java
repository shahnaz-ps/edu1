package controller;

import com.example.temp.Edu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ProgramController {
    public static void changeMenu(String menu) {
        FXMLLoader fxmlLoader = new FXMLLoader(Edu.class.getResource(menu + ".fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1280, 720);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Edu.stage.setScene(scene);
        Edu.stage.setResizable(false);
        Edu.stage.show();
    }
}
