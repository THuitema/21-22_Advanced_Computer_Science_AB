package com.thuitema.Unit6; /*****************************************************************************************************************
 NAME: Thomas Huitema
 PERIOD: 6
 DUE DATE: 12/5/2021
 ASSIGNMENT: Stack Text Editor Program

 PURPOSE: Use stack structures to simulate a text editor

 LEARNED:
    If you pop all elements of one stack onto another, the new stack will be in reverse order
    EmptyStackException if you try to pop an empty stack - make sure to do !stack.isEmpty()
    Store peek() values in a variable to use them after doing pop()
    Stacks are last in, first out (LIFO)
 
 CREDITS: none
 ****************************************************************************************************************/

import java.util.*;

public class TextEditor_shell {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        String again;
        do {
            System.out.print("\nEnter a line of text: ");
            String input = sc.nextLine();
            editText(input);
            System.out.print("\nAgain (y/n)? ");
            again = choice.next();
        } while (!again.equals("n"));
    }//main

    //pre:  s is not null
    //post: edits a String according to certain characters it contains and prints the resulted string
    public static void editText(String s) {
        // '-' to backspace, '$' to erase line
        Stack<Character> text = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                if (!text.isEmpty()) {
                    text.pop();
                }
            }
            else if (c == '$') {
                while (!text.isEmpty()) {
                    text.pop();
                }
            }
            else {
                text.push(c);
            }
        }

        printStack(text);
    } //editText

    //pre:  none
    //post: prints the Stack in a nicer format, ex. abc instead of [a, b, c]
    public static void printStack(Stack<Character> s) {
        // your code goes here
        Stack<Character> temp = new Stack<>();
        while(!s.isEmpty()) {
            temp.push(s.pop()); // reversed order
        }

        while(!temp.isEmpty()) {
            char t = temp.peek();
            System.out.print(t);
            temp.pop();

            s.push(t); // put together stack s again
        }
    }//printStack
}

/*
Enter a line of text: Com$pu--Coo-mputer Scii-ence
Computer Science
Again (y/n)? y

Enter a line of text: MYY-nam$$My  -name is Tt-homasss--
My name is Thomas
Again (y/n)? y

Enter a line of text: $-HelO-h$y$Hello__--Worll-d
HelloWorld
Again (y/n)? y

Enter a line of text: Cu-$p-Cup 0O--Of  -Javu-a
Cup Of Java
Again (y/n)? n
*/