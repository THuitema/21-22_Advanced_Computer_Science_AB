package com.thuitema.Unit1.Interfaces_Abstract;

// If two interfaces are inherited with a matching method signature, they are treated as only one method

public class Circle implements CoolShape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double perimeter() {
        return 2 * Math.PI * this.radius;
    }

    public String getColor() {
        return null;
    }

    public void randColor() {

    }

    public String shapeProperties() {
        return null;
    }

    public static void main(String[] args) {
        Circle myCircle = new Circle(5);
        System.out.println("Area: " + myCircle.area());
        System.out.println("Circumference: " + myCircle.perimeter());
    }



}
