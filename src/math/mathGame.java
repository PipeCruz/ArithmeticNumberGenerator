package math;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class mathGame {
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Text Question;
    @FXML
    public Text incorrect;
    @FXML
    public TextField response;

    private int numQuestions;//total number of questions
    private int currentQuestion;//question user is on
    private int numRight;//total number correct
    private QuestionGen gen;

    @FXML
    public void initialize() {
        incorrect.setVisible(false);
        numQuestions = IntroScene.getTotalNumber();
        currentQuestion = 1;
        System.out.println(numQuestions);
        gen = new QuestionGen();
        setQuestion();
    }

    private void setQuestion() {
        Question.setText(gen.getQuestion() + "\t\tProblem # " + currentQuestion + "/" + numQuestions);
    }

    public void submitResponse(KeyEvent keyEvent) {
        if(response.getText().compareToIgnoreCase("exit")==0){
            System.exit(0);
        }
        if(numQuestions>=currentQuestion){
            if(keyEvent.getCode()== KeyCode.ENTER){
                try{
                    if(Integer.parseInt(response.getText()) == gen.getAnswer()){
                        numRight++;
                    }
                    updateProgress();
                }catch(Exception e){
                    updateProgress();
                }
                currentQuestion++;
                if(currentQuestion>numQuestions){
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

    private void updateProgress(){
        progressBar.setProgress((1.0*currentQuestion)/numQuestions);
    }

}
