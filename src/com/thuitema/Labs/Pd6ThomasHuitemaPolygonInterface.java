package com.thuitema.Labs;

/*
    Name: Thomas Huitema
    Period: 6
    Lab: Polygon Interface

    What I Learned:
        It may be a good idea to make a parent class abstract if its child classes share a method but with different implementations
        Abstract methods cannot be private
        You can combine the parent and child toString methods by calling super.toString() in the child toString method

    Note: it may be helpful to create an abstract superclass for Pentagon, Hexagon, and Octagon classes. Their contents are identical
        except for the area and perimeter formulas, which could be declared abstract in the superclass. This would remove redundant code
 */

public class Pd6ThomasHuitemaPolygonInterface {
    public static void main(String[] args) {
        Polygon[] polys = {
                new IsoscelesTriangle("Isosceles Triangle", 12, 13),
                new EquilateralTriangle("Equilateral Triangle", 4),
                new Rectangle("Rectangle", 10, 5),
                new Square("Square", 3),
                new Pentagon("Pentagon", 5),
                new Hexagon("Hexagon", 6),
                new Octagon("Octagon", 8)
        };

        for(Polygon p : polys) {
            System.out.println(p + "\n");
        }
    }
}

interface Polygon {
    public double area();
    public double perimeter();
}

class Triangle implements Polygon {
    private double a;
    private double b;
    private double c;
    private String name;

    public Triangle(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public double perimeter() { // perimeter is always the same
        return a + b + c;
    }

    public String toString() {
        return "Name: " + name +
                "\nSide A: " + a +
                "\nSide B: " + b +
                "\nSide C: " + c +
                "\nPerimeter: " + perimeter() +
                "\nArea: " + area();
    }

    public boolean equals(Object o) {
        Triangle t = (Triangle) o;
        return  name.equals(t.getName()) && a == t.getA() && b == t.getB() && c == t.getC();
    }

    public String getName() {
        return name;
    }
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}

class IsoscelesTriangle extends Triangle {
    private double base;
    private double leg;

    public IsoscelesTriangle(String name, double base, double leg) {
        super(name, base, leg, leg);
        this.base = base;
        this.leg = leg;
    }
}

class EquilateralTriangle extends Triangle {
    private double s;

    public EquilateralTriangle(String name, double s) {
        super(name, s, s, s);
        this.s = s;
    }
}

abstract class Quadrilateral implements Polygon {
    private String name;
    private double s1;
    private double s2;
    private double s3;
    private double s4;

    public Quadrilateral(String name, double s1, double s2, double s3, double s4) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    // area formula not used until subclass
    public abstract double area();

    // same for all quadrilaterals
    public double perimeter() {
        return s1 + s2 + s3 + s4;
    }

    public String toString() {
        return "Name: " + name +
                "\nSide 1: " + s1 +
                "\nSide 2: " + s2 +
                "\nSide 3: " + s3 +
                "\nSide 4: " + s4 +
                "\nPerimeter: " + perimeter(); // area added in subclass toString
    }

    public boolean equals(Object o) {
        Quadrilateral q = (Quadrilateral) o;
        return name.equals(q.getName()) && s1 == q.getS1() && s2 == q.getS2() && s3 == q.getS3() && s4 == q.getS4();
    }

    public String getName() {
        return name;
    }

    public double getS1() {
        return s1;
    }

    public double getS2() {
        return s2;
    }

    public double getS3() {
        return s3;
    }

    public double getS4() {
        return s4;
    }
}

class Rectangle extends Quadrilateral {
    private double length;
    private double width;

    public Rectangle(String name, double length, double width) {
        super(name, length, width, length, width);
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }

    public String toString() {
        return super.toString() +
                "\nArea: " + area();
    }
}

class Square extends Quadrilateral {
    private double side;

    public Square(String name, double side) {
        super(name, side, side, side, side);
        this.side = side;
    }

    public double area() {
        return Math.pow(side, 2);
    }

    public String toString() {
        return super.toString() +
                "\nArea: " + area();
    }
}

class Pentagon implements Polygon {
    private String name;
    private double side;

    public Pentagon(String name, double side) {
        this.name = name;
        this.side = side;
    }

    public double area() {
        return Math.pow(side, 2) * Math.sqrt(25 + 10 * Math.sqrt(5)) / 4;
    }

    public double perimeter() {
        return side * 5;
    }

    public String toString() {
        return "Name: " + name +
                "\nSide length: " + side +
                "\nPerimeter: " + perimeter() +
                "\nArea: " + area();
    }

    public boolean equals(Object o) {
        Pentagon p = (Pentagon) o;
        return name.equals(p.getName()) && side == p.getSide();
    }

    public String getName() {
        return name;
    }

    public double getSide() {
        return side;
    }
}

class Hexagon implements Polygon {
    private String name;
    private double side;

    public Hexagon(String name, double side) {
        this.name = name;
        this.side = side;
    }

    public double area() {
        return Math.pow(side, 2) * (3 * Math.sqrt(3)) / 2;
    }

    public double perimeter() {
        return side * 6;
    }

    public String toString() {
        return "Name: " + name +
                "\nSide length: " + side +
                "\nPerimeter: " + perimeter() +
                "\nArea: " + area();
    }

    public boolean equals(Object o) {
        Hexagon h = (Hexagon) o;
        return name.equals(h.getName()) && side == h.getSide();
    }

    public String getName() {
        return name;
    }

    public double getSide() {
        return side;
    }
}

class Octagon implements Polygon {
    private String name;
    private double side;

    public Octagon(String name, double side) {
        this.name = name;
        this.side = side;
    }

    public double area() {
        return Math.pow(side, 2) * (2 + 2 * Math.sqrt(2));
    }

    public double perimeter() {
        return side * 8;
    }
    public String toString() {
        return "Name: " + name +
                "\nSide length: " + side +
                "\nPerimeter: " + perimeter() +
                "\nArea: " + area();
    }

    public boolean equals(Object o) {
        Octagon oct = (Octagon) o;
        return name.equals(oct.getName()) && side == oct.getSide();
    }

    public String getName() {
        return name;
    }

    public double getSide() {
        return side;
    }
}

/*
Name: Isosceles Triangle
Side A: 12.0
Side B: 13.0
Side C: 13.0
Perimeter: 38.0
Area: 69.19537556802477

Name: Equilateral Triangle
Side A: 4.0
Side B: 4.0
Side C: 4.0
Perimeter: 12.0
Area: 6.928203230275509

Name: Rectangle
Side 1: 10.0
Side 2: 5.0
Side 3: 10.0
Side 4: 5.0
Perimeter: 30.0
Area: 50.0

Name: Square
Side 1: 3.0
Side 2: 3.0
Side 3: 3.0
Side 4: 3.0
Perimeter: 12.0
Area: 9.0

Name: Pentagon
Side length: 5.0
Perimeter: 25.0
Area: 43.01193501472417

Name: Hexagon
Side length: 6.0
Perimeter: 36.0
Area: 93.53074360871938

Name: Octagon
Side length: 8.0
Perimeter: 64.0
Area: 309.01933598375615
 */