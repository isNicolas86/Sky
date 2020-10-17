package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    private Validator validate;

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;



    public void onButtonNineClicked(){
        addCharToTextField("9");
    }

    public void onButtonEightClicked(){
        addCharToTextField("8");
    }

    public void onButtonSevenClicked(){
        addCharToTextField("7");
    }

    public void onButtonSixClicked(){
        addCharToTextField("6");
    }

    public void onButtonFiveClicked(){
        addCharToTextField("5");
    }

    public void onButtonFourClicked(){
        addCharToTextField("4");
    }

    public void onButtonThreeClicked(){
        addCharToTextField("3");
    }

    public void onButtonTwoClicked(){
        addCharToTextField("2");
    }

    public void onButtonOneClicked(){
        addCharToTextField("1");
    }

    public void onButtonZeroClicked(){
        addCharToTextField("0");
    }

    public void onButtonDotClicked(){
        addCharToTextField(".");
    }

    public void onButtonEqualClicked(){
        textField.end();
        if(shallIAddEqual(textField.getText())){
            textField.appendText("=");
        }
        validate = new Validator(textField.getText());
        if (validate.isFirstOpenPare()){
            textArea.appendText("Syntax ERROR\n");
            return;
        } else if(validate.isBinaryOpAside()){
            textArea.appendText("Syntax ERROR\n");
            return;
        } else if(validate.unbalancedParentheses()){
            textArea.appendText("Syntax ERROR\n");
            return;
        } else if(validate.foundBinaryOpertarAfterOpenedPara()) {
            textArea.appendText("Syntax ERROR\n");
            return;
        } else if(validate.isFirstBinaryOperator()){
            textArea.appendText("Syntax ERROR\n");
            return;
        } else if (validate.isError()){
            textArea.appendText("Syntax ERROR\n");
            return;
        }
        Calculos calculos = new Calculos(textField.getText());
        long equals;
        double d = calculos.calculate();
        if (d%1 == 0 && (d < Long.MAX_VALUE && d > Long.MIN_VALUE)){
            equals = (long)d;
            textArea.setText("➡  " + equals + "\n");
        } else {
            textArea.setText("➡  " + d + "\n");
       }
    }

    public void onButtonPlusClicked(){
        if(isUnaryOp()){
            addStrToTextFieldAndStepBackOne("(+)");
        } else addCharToTextField("+");
    }

    public void onButtonMinusClicked(){
        if(isUnaryOp()){
            addStrToTextFieldAndStepBackOne("(-)");
        } else addCharToTextField("-");
    }

    public void onButtonTimesClicked(){
        addCharToTextField("x");
    }

    public void onButtonDivClicked(){
        addCharToTextField("÷");
    }

    public void onPercentageButtonClicked(){
        addCharToTextField("%");
    }

    public void onSquareRootButtonClicked(){
        addCharToTextField("√");
    }

    public void onPowTwoButtonClicked(){
        addCharToTextField("^2");
    }

    public void onPowThreeButtonClicked(){
        addCharToTextField("^3");
    }

    public void onButtonLeftParClicked(){
        addCharToTextField("(");
    }

    public void onButtonRightParClicked(){
        addCharToTextField(")");
    }

    public void onButtonAcClicked(){
        textField.clear();
    }

    public void onButtonDelClicked(){
        textField.requestFocus();
        textField.deletePreviousChar();
    }
    /*****************************************************************************/
    //Scientific Area Buttons
    public void onButtonMoveLeftClicked(){
        textField.requestFocus();
        textField.positionCaret(textField.getCaretPosition()-1);
    }

    public void onButtonMoveRightClicked(){
        textField.requestFocus();
        textField.positionCaret(textField.getCaretPosition()+1);
    }

    public void onButtonTanClicked(){
        addStrToTextFieldAndStepBackOne("tan()");
    }

    public void onButtonCosClicked(){
        addStrToTextFieldAndStepBackOne("cos()");
    }

    public void onButtonSinClicked(){
        addStrToTextFieldAndStepBackOne("sin()");
    }

    public void onButtonExpClicked(){
        addStrToTextFieldAndStepBackOne("e()");
    }

    public void onButtonLogClicked(){
        addStrToTextFieldAndStepBackOne("log()");
    }

    public void onButtonLnClicked(){
        addStrToTextFieldAndStepBackOne("ln()");
    }

    public void onButtonFactorialClicked(){
        addCharToTextField("!");
    }

    public void onButtonPiClicked(){
        addCharToTextField("π");
    }

    public void onButtonPowerClicked(){
        addCharToTextField("^");
    }

    public void onAbsoluteButtonClicked(){
        addStrToTextFieldAndStepBackOne("||");
    }

    public void addCharToTextField(String s){
        textField.requestFocus();
        textField.insertText(textField.getCaretPosition(), s);
    }

    public void addStrToTextFieldAndStepBackOne(String s){
        textField.requestFocus();
        textField.insertText(textField.getCaretPosition(), s);
        textField.positionCaret(textField.getCaretPosition()-1);
    }

    public boolean isUnaryOp(){
        char c = textField.getText().charAt(textField.getCaretPosition()-1);
        String definedChars = "x*-+/÷^";
        for (char cInStr: definedChars.toCharArray()){
            if(c == cInStr){
                return true;
            }
        } return false;
    }

    public boolean shallIAddEqual(String s){
        if(s.contains("=")){
            return false;
        } else return true;
    }

    public void onKeyTyped(){
       // ke
    }

    public void exitItemSelected(){
        Platform.exit();
    }

    public void aboutItemSelected(){
    }

}