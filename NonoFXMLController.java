package nonogrampuzzletwo;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller selects a nonogram puzzle from Data folder and displays it in a GUI, 
 * also changes the colors of vBox and hBox inside GUI
 * @author Jesus Rodrigues
 */
public class NonoFXMLController implements Initializable {

    @FXML
    private VBox vBoxRowClues;
    @FXML
    private HBox hBoxColumnClues;
    @FXML
    private GridPane gPaneNonogram;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnNG;
    @FXML
    private Button btnReset;
    
    private final int SIZE = 5;
    private final String CLUE_COLOR = "-fx-background-color: LIGHTGRAY";
    private NonogramPool pool;
    private Nonogram current;
    private String fileName;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Changes color of Vbox and Hbox
        vBoxRowClues.setStyle(CLUE_COLOR);
        hBoxColumnClues.setStyle(CLUE_COLOR);
        
        //displays solution and clues
        newGame();
    }
    
    /**
     * Creates and displays solution and clues of a nonogram randomly selected 
     * from Data folder
     */
    private void newGame()
    {
        //Pulls a list of files from Data folder and 
        pool = new NonogramPool(Paths.get(".\\Data"));
        current = pool.getRandomPuzzle();
        fileName = pool.getPuzzleName();
        
        //Nonogram toString for comparison with GUI
        System.out.println(current);

        //Displays solution
        displayN(current.getEntered());
        
        //Displays clues
        displayClues(vBoxRowClues, current.getRowClues());
        displayClues(hBoxColumnClues, current.getColumnClues());
    }
    
    /**
     * Displays arr on a GridPane
     * @param arr 2D int array to be displayed 
     */
    private void displayN(int[][] arr)
    {
        Button button;
        int index = 0;
        
        for (int i = 0 ; i < arr.length; i++)
        {
            for (int j = 0; j < arr[i].length; j++)
            {
                button = (Button) gPaneNonogram.getChildren().get(index++);
                
                if (arr[i][j] == 1)
                {
                    button.setStyle("-fx-background-color: BLACK");
                }
                else
                {
                    button.setStyle("-fx-background-color: WHITE");
                }
            }
        }
    }
    
    
    /**
     * Displays clues to the nonogram's solution
     * @param clueBox Pane where clues are displayed
     * @param Clues clues to be displayed on Pane
     */
    private void displayClues(Pane clueBox, String[] Clues)
    {
        Label label;
        for (int i = 0; i < SIZE; i++)
        {
            label = (Label) clueBox.getChildren().get(i);
            label.setText(Clues[i]);
        }
    }

    /**
     * Closes the display and ends the program
     * @param event On button click
     */
    @FXML
    private void handlebtnClose(ActionEvent event) 
    {
        System.exit(0);
    }    
    
    /**
     * Displays a new puzzle and clues
     * @param event On N.G. button clicked
     */
    @FXML
    private void handlebtnNG(ActionEvent event) 
    {
        newGame();
    }
    
    /**
     * resets the entered Nonogram without starting a new game
     * @param event on Reset button clicked
     */
    @FXML
    private void handlebtnReset(ActionEvent event)
    {
        current.resetEntered();
        displayN(current.getEntered());
    }
    
    /**
     * toggles clicked Nonogram spaces between black and white, also displays a 
     * winning screen if entered Nonoram matches the solution
     * @param event 
     */
    @FXML
    private void handlebtnTgl(ActionEvent event)
    {
        current.toggleCell(GridPane.getRowIndex((Node) event.getSource()), GridPane.getColumnIndex((Node) event.getSource()));
        displayN(current.getEntered());
                
        if (current.puzzleSolved())
        {
            Alert winner = new Alert(INFORMATION);
            winner.setTitle("Winner!");
            winner.setContentText("Correct! Puzzle: " + fileName + " complete!" + "\nClick N.G to begin a new game, or Close to quit.");
            winner.show();
        }
    }
}
