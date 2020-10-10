package sample;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

import java.lang.ref.Reference;

public class Controller {
    //MathExpression expression = new MathExpression();

    @FXML
    private TextArea textArea;

    public void onButtonNineClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "9");
    }

    public void onButtonEightClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "8");
    }

    public void onButtonSevenClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "7");
    }

    public void onButtonSixClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "6");
    }

    public void onButtonFiveClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "5");
    }

    public void onButtonFourClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "4");
    }

    public void onButtonThreeClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "3");
    }

    public void onButtonTwoClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "2");
    }

    public void onButtonOneClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "1");
    }

    public void onButtonZeroClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "0");
    }

    public void onButtonDotClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), ".");
    }

    public void onButtonEqualClicked(){
        Calculos calculos = new Calculos(textArea.getText());
        textArea.end();
        textArea.setText(textArea.getText() + '=');
        int equals =0;
        double d = calculos.calculate();
        if ( d%1 == 0){
            equals = (int)d;
            textArea.appendText("\n➡  " + Integer.toString(equals));
        } else {
            textArea.appendText("\n➡  " + d);
       }
    }

    public void onButtonPlusClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "+");
    }

    public void onButtonMinusClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "-");
    }

    public void onButtonTimesClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "x");
    }


    public void onButtonDivClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "÷");
    }

    public void onPercentageButtonClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "%");
    }

    public void onSquareRootButtonClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "√");
    }

    public void onPowTwoButtonClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "^2");
    }

    public void onPowThreeButtonClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "^3");
    }

    public void onButtonLeftParClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "(");
    }

    public void onButtonRightParClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), ")");
    }

    public void onButtonAcClicked(){
        textArea.setText("");
    }

    public void onButtonDelClicked(){
        textArea.requestFocus();
        textArea.deletePreviousChar();
    }
    /*****************************************************************************/
    //Scientific Area Buttons
    public void onButtonMoveLeftClicked(){
        textArea.requestFocus();
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonMoveRightClicked(){
        textArea.requestFocus();
        textArea.positionCaret(textArea.getCaretPosition()+1);
    }

    public void onButtonTanClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "tan()");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonCosClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "cos()");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonSinClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "sin()");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonExpClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "e()");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonLogClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "log()");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonLnClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "ln()");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }

    public void onButtonFactorialClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "!");
    }

    public void onButtonPiClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "π");
    }

    public void onButtonPowerClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "^");
    }

    public void onAbsoluteButtonClicked(){
        textArea.requestFocus();
        textArea.insertText(textArea.getCaretPosition(), "||");
        textArea.positionCaret(textArea.getCaretPosition()-1);
    }
}