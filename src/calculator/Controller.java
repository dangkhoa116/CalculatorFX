package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import nguyenvanquan7826.com.Balan;
public class Controller {
    @FXML
    private Text output;
    @FXML
    private Label preOutput;
    @FXML
    private String operator = "";
    @FXML
    private double number = 0;
    private double result;
    private boolean start = true;
    private StringBuilder sb = new StringBuilder();
    private String[] operands = {"+","-","*","/"};
    @FXML
    public void processNumpad(ActionEvent event)
    {
        if (start)
        {
            output.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText()+ value);
        sb.append(value);
        preOutput.setText(sb.toString());
        number = Double.parseDouble(output.getText());
    }
    public void processOperator(ActionEvent event)
    {
        operator = ((Button)event.getSource()).getText();

        if (operator.equals("="))
        {
            Balan balan = new Balan();
            double result = balan.valueMath(preOutput.getText());
            System.out.println(preOutput.getText());
            System.out.println(result);
            output.setText(Double.toString(result));
        }
        else
        {
            output.setText("");
            sb.append(operator);
            preOutput.setText(sb.toString());

        }
    }
    public void processClear(ActionEvent event)
    {
        String value = ((Button) event.getSource()).getText();
        switch (value){
            case "C":
                output.setText("");
                number = 0;
                start = true;
                preOutput.setText("");
                sb.setLength(0);
                break;
            case "CE":
                output.setText("");
                break;
        }
    }
    public void processDot()
    {
        String lastStr = preOutput.getText();
        lastStr = lastStr.substring(lastStr.length()-1);
        if (!isOperand(lastStr))
        {
            sb.append(".");
            preOutput.setText(sb.toString());
            output.setText(sb.toString());
        }
        else {

        }

    }
    public boolean isOperand(String str)
    {
        for (String i: operands) {
            if (str.equals(i))
                return true;
        }
        return false;
    }
}
