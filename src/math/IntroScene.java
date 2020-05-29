package math;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IntroScene {
    //instance fields
    private static int totalNumber;
    @FXML
    private TextField numOfQuestions;
    @FXML
    private Label invalidLabel;
    @FXML
    private AnchorPane currentScene;

    //returns total number of questions
    public static int getTotalNumber() {
        return totalNumber;
    }

    //when it starts, this sets the number of questions
    public void checkIn(KeyEvent keyEvent) throws InterruptedException {
        invalidLabel.setVisible(false);

        if(keyEvent.getCode()== KeyCode.ENTER){
            try{
                if (Integer.parseInt(numOfQuestions.getText())>0){
                    totalNumber = Integer.parseInt(numOfQuestions.getText());
                    System.out.println("VALID USER INPUT...\nSWITCHING TO NEW FRAME");
                    numOfQuestions.setText("");

                    Parent root = FXMLLoader.load(getClass().getResource("ActualGame.fxml"));
                    Stage stage = (Stage) currentScene.getScene().getWindow();
                    stage.setTitle("Arithmetic Problem Generator");
                    stage.setScene(new Scene(root, 600,  400));
                    stage.setResizable(false);
                    stage.show();
                }
            }catch(Exception e){
                numOfQuestions.setText("");
                invalidLabel.setVisible(true);
                System.out.println("INVALID USER INPUT. EXCEPTION CAUGHT\nTRYING AGAIN");
            }
        }
    }

    //when they click help button
    public void help() {
        AlertBox.display("HELP","Enter a whole number to generate arithmetic problems!" +
                "\nType exit to close at any time");
    }
    //when they click exit button
    public void exit() {
        AlertBox.display("BYE","See you again!");
        System.exit(0);
    }

}
