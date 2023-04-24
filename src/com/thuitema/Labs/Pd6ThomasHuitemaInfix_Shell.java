package com.thuitema.Labs;

/***********************************************************************
 Name: Thomas Huitema
 Period: 6
 Date: 12/16

 What I Learned:
    Operands do NOT include parenthesis
    It's important to remove the left parenthesis from the stack when you reach one (see else-if statement)
    You get an EmptyStackException when trying to do pop(), peek(), or push() on an empty stack
    You pop items from a stack in the reverse order you pushed them in (LIFO), so I switched variables a & b
    when they are initialized with operands.pop(). Popping into a then into b would result in the operations being backwards
    Practiced using pop(), push(), and peek() methods for stacks


 Credit (person who helped me): Evan Ho for helping me convert string to int using "Integer.parseInt()"

 Credit (person who helped me): none
 Student(s) whom I helped (to what extent): none
 ************************************************************************/

import java.util.*;

public class Pd6ThomasHuitemaInfix_Shell {
    public static void main(String[] args) {
        System.out.println("Enter an infix expression, single digits");
        System.out.println("such as 1+2*3 or (1+2)*3 ");
        Scanner keyboard = new Scanner(System.in);    //  (3*(4+5)-2)/5
        String s = keyboard.next();
        while (!s.equals("-1")) {
            String postfixExp = trans(s);
            System.out.println(s + " --> " + postfixExp + " --> " + Pd6ThomasHuitemaPostfix_Shell.eval(postfixExp) + "\n");
            //System.out.println(s + "  -->  " + postfixExp + "  -->  " + Pd6ThomasHuitemaPostfix_Shell.eval(postfixExp) + "\n");
            s = keyboard.next();
        }
    }  // end of main


    public static String trans(String x) {
        Stack<Character> stack = new Stack<>();
        String output = "";
        for (int i = 0; i < x.length(); i++) {
            char ch = x.charAt(i);

            if (ch != '+' && ch != '-' && ch != '*' && ch != '/' && ch != '(' && ch != ')') {
                output += ch;

            } else if (ch == ')') {
                while (stack.peek() != '(') {
                    output += stack.pop();

                    if (stack.peek() == '(') { // remove '(' from stack
                        stack.pop();
                        break;
                    }
                }
            } else {
                if (ch == '(') {
                    stack.push(ch);
                }
                else { // ch must be an operator here, the conditions above check if it is an operand or parenthesis
                    if (stack.size() != 0) {

                        if (ch == '+' || ch == '-') {
                            while (!stack.isEmpty() && stack.peek() != '(') { // nothing can be lower priority
                                output += stack.pop();
                            }
                        }
                        else {
                            while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != '+' && stack.peek() != '-') {
                                output += stack.pop();
                            }
                        }
                    }
                    stack.push(ch);
                }
            }
        }
        while(!stack.isEmpty()) {
            output += stack.pop();
        }
        return output;
    }  // end of trans
}  // end of Infix_Shell

class Pd6ThomasHuitemaPostfix_Shell {
    public static void main(String[] args) {
        System.out.println("Enter a valid postfix expression (single digits only),");
        System.out.println("such as 35*1+ ");
        Scanner keyboard = new Scanner(System.in);
        String s = keyboard.next();
        while (!s.equals("-1")) {
            System.out.println(s + "  --->  " + eval(s) + "\n");
            System.out.println((s = "354*+7*") + " = " + eval(s) + "\n");
            System.out.println((s = "82-") + " = " + eval(s) + "\n");
            System.out.println((s = "82/") + " = " + eval(s) + "\n");
            s = keyboard.next();
        }
    }

    public static int eval(String x) {
        Stack<String> operands = new Stack<>();
        for(int i = 0; i < x.length(); i++) {
            char ch = x.charAt(i);

            if (!isOperator(ch)) { // push operands to stack
                operands.push(ch + "");
            }
            else { // evaluate top two operands when there's an operator
                // order of operands is reversed in stack when pushed
                int b = Integer.parseInt(operands.pop());
                int a = Integer.parseInt(operands.pop());
                operands.push(eval(a, b, ch) + "");
            }
        }

        // after the loop, only the final result will remain in the stack
        return Integer.parseInt(operands.pop());
    }

    public static int eval(int a, int b, char ch) {
        if (ch == '+') {
            return a + b;
        }
        else if (ch == '-') {
            return a - b;
        }
        else if (ch == '*') {
            return a * b;
        }
        else if (ch == '/') {
            return a / b;
        }
        return 0;
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}


/*
Enter an infix expression, single digits
such as 1+2*3 or (1+2)*3
3*5+1
3*5+1 --> 35*1+ --> 16

2*(1+3)
2*(1+3) --> 213+* --> 8

1+((2+3)*4+5)*6
1+((2+3)*4+5)*6 --> 123+4*5+6*+ --> 151

-1

Process finished with exit code 0
 */