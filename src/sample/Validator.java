package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private String str;

    public Validator(String str) {
        this.str = str;
    }

    public boolean isFirstOpenPare(){
        Pattern pattern = Pattern.compile("^\\)");
        Matcher m = pattern.matcher(this.str);
        if (m.find()) {
            return true;
        } else return false;
    }

    public boolean isBinaryOpAside(){
        Pattern pattern = Pattern.compile("[+x\\-÷/*][x\\÷/*]");
        Matcher m = pattern.matcher(this.str);
        if (m.find()){
            return true;
        } else return false;
    }

    public boolean unbalancedParentheses(){
        int count = 0;
        for(char c: this.str.toCharArray()){
            if(count<0){
                return true;
            } else if(c=='('){
                count ++;
            } else if(c==')'){
                count --;
            }
        }
        if(count ==0){
            return false;
        } else return true;
    }

    public boolean foundBinaryOpertarAfterOpenedPara() {
        Pattern pattern = Pattern.compile("[(][x*÷/]");
        Matcher m = pattern.matcher(this.str);
        if (m.find()){
            return true;
        } else return false;
    }
    public boolean isFirstBinaryOperator(){
        Pattern pattern = Pattern.compile("^[x*/÷]");
        Matcher m = pattern.matcher(this.str);
        if (m.find()){
            return true;
        } else return false;
    }

    public boolean isError(){
        Pattern pattern = Pattern.compile("[+x\\-÷/*]$|[!][1-9.]|[+x\\-÷/*][!]");
        Matcher m = pattern.matcher(this.str);
        if (m.find()){
            return true;
        } else return false;
    }


}
