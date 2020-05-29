package math;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class MathGame {
    //Instance fields linked directly to the application
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text Question;
    @FXML
    private Text incorrect;
    @FXML
    private TextField response;
    //Instance fields for the questions
    private int numQuestions,     //total number of questions
                currentQuestion,  //question user is on
                numRight;         //total number correct
    private QuestionGen gen;      //question generator

    //initializes the fields of the main application
    @FXML
    public void initialize() {
        incorrect.setVisible(false);
        numQuestions = IntroScene.getTotalNumber();
        currentQuestion = 1;
        System.out.println(numQuestions);
        gen = new QuestionGen();
        setQuestion();
    }

    //changes the question after each input
    private void setQuestion() {
        Question.setText(gen.getQuestion() + "\t\tProblem # " + currentQuestion + "/" + numQuestions);
    }

    //when the user clicks enter they trigger the checking process
    //the question field will be updated if there are still questions to complete unless the user types exit
    public void submitResponse(KeyEvent keyEvent) {
        if(response.getText().compareToIgnoreCase("exit")==0){
            System.exit(0);
        }
        if(numQuestions >= currentQuestion){
            if(keyEvent.getCode() == KeyCode.ENTER){
                try{
                    if(Integer.parseInt(response.getText()) == gen.getAnswer()){
                        numRight++;
                    }
                    updateProgress();
                }catch(Exception e){
                    updateProgress();
                }
                currentQuestion++;
                if(currentQuestion > numQuestions){
                    DecimalFormat df = new DecimalFormat("##.##");
                    Question.setText("PROBLEMS FINISHED\n"
                                    + "Percent Correct: "
                                    + df.format((100.0*numRight)/((double)numQuestions))
                                    + "%");
                        incorrect.setText("Type Exit to Close");
                        incorrect.setVisible(true);
                }else{
                    setQuestion();
                }
                response.setText("");
            }
        }
    }
    //updates the progress bar to correspond with the current question
    private void updateProgress() {
        progressBar.setProgress( (1.0d * currentQuestion) / numQuestions);
    }

}
