package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculos {
    private String str;
    @FXML
    private TextArea textArea;

    public Calculos(String str) {
        this.str = str;
    }

    public double calculate(){
        ArrayList<String> postfix = infixToPostfix(extractEntities(this.str));
        System.out.println("\nInfix to postfix: " + postfix);
        Stack<Double> numStack = new Stack<>();
        double result =0;
        if(postfix==null){
            return 0;
        }
        for(int i =0; i<postfix.size(); i++){
            if(isNumeric(postfix.get(i))){
                result = numStack.push(Double.parseDouble(postfix.get(i)));
            } else if(postfix.get(i).equals("π")) {
                result = numStack.push(Math.PI);
            } else if(isUnaryOperator(postfix.get(i))) {
                result = calcUnaryOperator(numStack.pop(), postfix.get(i));
                numStack.push(result);
            } else { try{ result = calcBinaryOperator(numStack.pop(),
                        numStack.pop(), postfix.get(i).charAt(0));
            }
                        catch (EmptyStackException e){
                            System.out.println("Stack is empty");
                            return 0;
                        }
                numStack.push(result);
            }
        }
        return result;
    }

    /*Transform infix to postfix */
    public ArrayList<String> infixToPostfix(ArrayList<String> infix){
        if(!balancedParentheses(infix)){
            System.out.println("Missing parentheses");
            return null;
        }
        ArrayList<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(infix.get(0));
        for(int i =1; i<infix.size(); i++){
            if(isNumeric(infix.get(i)) || infix.get(i).equals("π")) {
                postfix.add(infix.get(i));
            } else if(infix.get(i).equals("^")){
                stack.push("^");
            } else if(infix.get(i).equals("√")){
                stack.push("√");
            } else if(precedence(infix.get(i))>precedence(stack.peek())){
                stack.push(infix.get(i));
            } else if(precedence(infix.get(i))<=precedence(stack.peek()) && precedence(infix.get(i))!=0){
                do{
                    postfix.add(stack.pop());
                }
                while(precedence(infix.get(i))<=precedence(stack.peek()) && precedence(infix.get(i))!=0);
                stack.push(infix.get(i));
            } else if(infix.get(i).equals("(")){
                stack.push("(");
            } else if(infix.get(i).equals(")")){
                while(!stack.peek().equals("(")){
                    postfix.add(stack.pop());
                }
                stack.pop();
            }
        }
        return postfix;
    }

    //Check if the passed entity/string is a Number
    private boolean isNumeric(String s){
        if(s.matches("[0-9.]+")){
            return true;
        } else return false;
    }


    /*Extract entities into An Array and add opened and closed parentheses at the beginning and end respectively
    * Also check if the "-" or "+" are unary operators then add 0 as a second operand*/
    private ArrayList<String> extractEntities(String s){
        ArrayList<String> entity = new ArrayList<>();
        s = s.replaceAll("[\\-][\\-]", "+");
        s = s.replaceAll("[\\-][+]|[+][\\-]", "\\-");
        Pattern pattern = Pattern.compile("[0-9.]+|[+]|[x]|[-]|[÷]|[/]|[(]|[)]|[\\^]|[\\|]" +
                "|[c][o][s]|[s][i][n]|[t][a][n]|[l][n]|[l][o][g]|[e]|[!]|[π]|[√]|[%]|[a][b][s]");
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            entity.add(m.group());
        }
        entity.add(0, "(");
        entity.add(")");
        for (int i = 0; i < entity.size(); i++) {
            //Replace "%" with divide by 100 "/100"
            if (entity.get(i).equals("%")) {
                entity.set(i, "/");
                entity.add(i+1, "100");
            }
            //Replace "|" with "abs(" and ")" on the pattern of the adjacent elements
            if (entity.get(i).equals("|")) {
                if ( (entity.get(i - 1).equals("(")||!isNumeric(entity.get(i - 1))) && !entity.get(i-1).equals(")") ) {
                    entity.set(i, "abs");
                    entity.add(i + 1, "(");
                } else entity.set(i, ")");
            }
        }
        //Add 0 as 2nd operand if "-" or "+" are unary
        for(int i =1; i<entity.size(); i++){
            if(shallAdd0(entity.get(i), entity.get(i-1))){
                entity.add(i,"0");
            }
        }
        //Add "x" between number and "(" if needed
        for (int i=1; i<entity.size();i++){
            if(!entity.get(i).equals("!")) { //If different of factorial
                if ((entity.get(i).equals("(")||isUnaryOperator(entity.get(i))) && isNumeric(entity.get(i - 1))) {
                    entity.add(i, "x");
                }
            }
        }

        System.out.println("Entity: " + entity);
        return entity;
    }

    //Check the precedence of the passed entity
    private int precedence(String s){
        if(s.equals("+") || s.equals("-")){
            return 1;
        } else if(s.equals("*") || s.equals("x") || s.equals("÷") || s.equals("/")){
            return 2;
        } else if(s.equals("^")){
            return 3;
        } else if(isUnaryOperator(s)){
            return 4;
        } else return 0;
    }

    private double calcUnaryOperator(double x1, String str) {
    double result =0;
    switch (str) {
        case "sin":
            result = Math.sin(Math.toRadians(x1));
            break;
        case "cos":
            result = Math.cos(Math.toRadians(x1));
            break;
        case "tan":
            result = Math.tan(Math.toRadians(x1));
            break;
        case "log":
            result = Math.log10(x1);
            break;
        case "ln":
            result = Math.log(x1);
            break;
        case "e":
            result = Math.exp(x1);
            break;
        case "√":
            result = Math.sqrt(x1);
            break;
        case "!":
            if(x1%1==0){
                result = factorial((int)x1);
            } else result = factorial(x1);
            break;
        case "abs":
            result = Math.abs(x1);
            break;
    }

    return result;
    }

    private double calcBinaryOperator(double x2, double x1, char op){
        double result = 0;
        switch(op){
            case 'x':
            case '*':
                result = x1 * x2;
                break;
            case '÷':
            case '/':
                try{
                    result = x1 / x2;
                } catch (ArithmeticException e){
                    System.out.println("Cannot divide by 0");
                }
                break;
            case '+':
                result = x1 + x2;
                break;
            case '-':
                result = x1 -x2;
                break;
            case '^':
                result = Math.pow(x1,x2);
        }
        return result;
    }

    //Check the parentheses, return false if left and right parentheses are not balanced
    private boolean balancedParentheses(ArrayList<String> equation){
        int count =0;
        for(String s:equation){
            if(s.equals("(")){
                count ++;
            } else if(s.equals(")")){
                count --;
            }
        }
        if (count ==0){
            return true;
        } else return false;
    }

    private boolean shallAdd0(String str2, String str1){
        if ( (str2.equals("-") || str2.equals("+")) && !isNumeric(str1)){
            if (!str1.equals(")") && !str1.equals("!")){
                return true;
            }
        }
        return false;
    }

    private boolean isUnaryOperator(String s){
        String[] unaryOperators = {"sin", "cos", "tan", "abs", "√", "log", "ln", "e", "!" };
        for(String str: unaryOperators){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

    private double factorial(int x1){
        if(x1>1){
            return x1*factorial(x1-1);
        }
        return 1;
    }
    private double factorial(double z){
        z=z+1; // n!=Gamma(n+1)
        double gamma = 1;
        int n = 1;
        while(n<=200000) {
            gamma = gamma*Math.pow(1+(double)1/n, z) / (1 + z / n);
            //eulerApprox=eulerApprox*Math.pow( (double)(k+1)/k,n) * ((double)k/(n+k));
            n++;
        }
        gamma = gamma/z;
        return gamma;
    }
}