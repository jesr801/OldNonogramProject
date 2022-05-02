package nonogrampuzzletwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Uses the Nonogram Class in a javafx to create a 5x5 Nonogram puzzle's solution
 * and clues
 * @author Jesus Rodriguez
 * @version 5/7/2021
 */
public class NonogramMain extends Application{

    // Displays the stage 
    public static void main(String[] args) {
        launch(args);
    }

    // Creates and sets up the stage
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NonoFXML.fxml"));
        stage.setScene(new Scene(root, 300, 350));
        stage.setTitle("Nonogram Puzzle");
        stage.show();
    }
    
}
