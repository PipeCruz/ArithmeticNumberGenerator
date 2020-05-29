package math;
import java.util.Random;
class QuestionGen {
    //instance variables
    private final Random random = new Random();
    private int firstNum, secondNum;
    private String operator;
    private final String[] OperatorList = {"+","-"};
    //returns the answer of the question
    int getAnswer(){
        return (operator.equals("+") ? firstNum + secondNum : firstNum - secondNum);
    }
    //returns a new question with the bounds 0-9
    String getQuestion(){
        firstNum = random.nextInt(10);//0-9
        operator = OperatorList[random.nextInt(2)];
        if(operator.equals("+"))
            secondNum = random.nextInt(10);
        else
            secondNum = random.nextInt(firstNum +1);
        return firstNum + " " + operator + " " + secondNum;
    }

}
