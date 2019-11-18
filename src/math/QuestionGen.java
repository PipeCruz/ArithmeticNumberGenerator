package math;
import java.util.Random;
class QuestionGen {

    private Random random = new Random();
    private int firstNum, secondNum;
    private String operator;
    private String[] OperatorList = {"+","-"};

    int getAnswer(){
        return (operator.equals("+") ? firstNum + secondNum : firstNum - secondNum);
    }

    String getQuestion(){
        firstNum = random.nextInt(10);//0-9
        operator = OperatorList[random.nextInt(2)];
        if(operator.equals("+")){
            secondNum = random.nextInt(10);
        }else{
            secondNum = random.nextInt(firstNum +1);
        }
        return firstNum + " " + operator + " " + secondNum;
    }

}
