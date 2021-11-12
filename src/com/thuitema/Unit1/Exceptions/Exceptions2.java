package com.thuitema.Unit1.Exceptions;

public class Exceptions2 {
    public static void main(String[] args) {
        System.out.println("start program");
        try {
            m();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        finally {
            System.out.println("program closed");
        }

    }

    public static void m() {
        try {
            mm();
        }
        catch(MyException e) {
            System.out.println(e.toString());
        }
    }

    public static void mm() throws MyException {
        mmm();
    }

    public static void mmm() throws MyException {
        throw new MyException("error in program");
    }
}

// custom exception
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
    public MyException() {
        super();
    }
}
