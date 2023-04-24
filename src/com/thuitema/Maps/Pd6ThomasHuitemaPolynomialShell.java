/**
 * Name: Thomas Huitema
 * Period: 6
 * Purpose of the Program: Sse map data structure to store polynomials. Write methods to add, multiply, and evaluate expressions
 *
 * What I learned:
 * You can access the private fields of objects of the same class at the class level (i.e. access the TreeMap of another
 * TreeMapPolynomial object in the TreeMapPolynomial class). Very useful for add() and multiply() methods, and non-default
 * constructor.
 * TreeMap orders keys from least to greatest. That is why when we iterate through the map in the toString() method, values
 * are printed from least to greatest exponent, which looks a little weird since polynomials are normally written from highest
 * to lowest degree.
 * Writing a non-default constructor for TreeMapPolynomial replicates the TreeMap non-default constructor, where you can
 * initialize a new object to an existing TreeMap. This is useful for the add() method.
 *
 * Credits: none
 */

package com.thuitema.Maps;

import java.text.DecimalFormat;
import java.util.TreeMap;

public class Pd6ThomasHuitemaPolynomialShell {
    public static void main(String[] args) {
        TreeMapPolynomial poly1 = new TreeMapPolynomial();
        // add a new term to the polynomial. The first argument is the exponent and the
        // second argument is the coefficient of the term.
        poly1.makeTerm(1, -4);  // Polynomial: -4x^1
        poly1.makeTerm(3, 2);   // Polynomial becomes: 2*x^3 + -4x
        poly1.makeTerm(0, 2);   // Polynomial becomes: 2*x^3 + -4x + 2*x^0

        System.out.println("Poly1: " + poly1);
        double evaluateAt = 2.0;
        System.out.println("Poly1 evaluated at " + evaluateAt + ": " + poly1.evaluateAt(evaluateAt));

        TreeMapPolynomial poly2 = new TreeMapPolynomial();
        poly2.makeTerm(1, -5);
        poly2.makeTerm(4, 2);
        poly2.makeTerm(0, -3);
        poly2.makeTerm(2, 1);
        System.out.println("Poly2: " + poly2);

        System.out.println("Poly1 + Poly2: " + poly1.add(poly2));
        System.out.println("Poly1 * Poly2: " + poly1.multiply(poly2));

        // extra methods
        System.out.println("Derivative of Poly1: " + poly1.derivative());
        System.out.println("Antiderivative of Poly2: " + poly2.antiderivative());
        System.out.println("Definite Integral of Poly1 from 1 to 4: " + poly1.definiteIntegral(1, 4));

    } // main
} // Polynomial_shell


interface Polynomial {
    void makeTerm(int degree, double coefficient);
    double evaluateAt(double x);
    Polynomial add(TreeMapPolynomial poly2);
    Polynomial multiply(TreeMapPolynomial poly2);
    Polynomial derivative();
    Polynomial antiderivative();
    double definiteIntegral(int start, int end);
}

class TreeMapPolynomial implements Polynomial {
    private TreeMap<Integer, Double> polyMap;

    // default constructor
    public TreeMapPolynomial() {
        polyMap = new TreeMap<>(); // initialized to empty map
    }

    // non-default constructor
    public TreeMapPolynomial(TreeMapPolynomial map) {
        polyMap = new TreeMap<>(map.polyMap); // initialized to existing map given from argument
    }

    // precondition: polyMap is initialized, degree & coefficient are not null
    // postcondition: adds term to the map, overwrites existing term with same degree/key
    public void makeTerm(int degree, double coefficient) {
        polyMap.put(degree, coefficient);
    }

    // precondition: polyMap is initialized
    // postcondition: returns the result of the polynomial evaluated at parameter x
    public double evaluateAt(double x) {
        double result = 0.0;

        for (int exp : polyMap.keySet()) { // loop through the keys in polyMap
            double coefficient = polyMap.get(exp);
            result += coefficient * Math.pow(x, exp); // x is the value we plug in
        }
        return result;
    }

    // precondition: poly2 is not null
    // postcondition: returns a Polynomial of the added polynomials
    public Polynomial add(TreeMapPolynomial poly2) {
        TreeMapPolynomial sum = new TreeMapPolynomial(poly2); // adds the terms of poly2 to sum

        for (int k : polyMap.keySet()) { // loop through keys of polyMap
            if (sum.polyMap.containsKey(k)) { // term w/ same exponent exists --> add values
                sum.polyMap.put(k, sum.polyMap.get(k) + polyMap.get(k));

                if (sum.polyMap.get(k) == 0) { // remove term if the sum of the values cancel out to 0
                    sum.polyMap.remove(k);
                }
            }
            else { // term w/ same exponent doesn't exist, put new term
                sum.polyMap.put(k, polyMap.get(k));
            }
        }
        return sum;
    }

    // precondition: poly2 is not null
    // postcondition: returns a Polynomial of the product of the polynomials
    public Polynomial multiply(TreeMapPolynomial poly2) {
        TreeMapPolynomial product = new TreeMapPolynomial();
        for (int k1 : polyMap.keySet()) {
            for (int k2 : poly2.polyMap.keySet()) {
                int newKey = k1 + k2; // when multiplying two numbers w/ the same exponent, you add the exponents together
                double newCoefficient = polyMap.get(k1) * poly2.polyMap.get(k2);

                if (product.polyMap.containsKey(newKey)) { // contains exponent --> add terms
                    product.polyMap.put(newKey, product.polyMap.get(newKey) + newCoefficient);

                    if (product.polyMap.get(newKey) == 0) { // remove term if the sum of the values cancel out to 0
                        product.polyMap.remove(newKey);
                    }
                }
                else { // term w/ same exponent doesn't exist, put new term
                    product.polyMap.put(newKey, newCoefficient);
                }
            }
        }
        return product;
    }

    // precondition: none
    // postcondition: returns a Polynomial of derivative of polyMap
    public Polynomial derivative() {
        TreeMapPolynomial derivMap = new TreeMapPolynomial();
        for (int k : polyMap.keySet()) {
            if (k != 0) { // the derivative of any constant is 0 --> don't need to add to new map
                derivMap.polyMap.put(k - 1, k * polyMap.get(k));
            }
        }
        return derivMap;
    }

    // precondition: none
    // postcondition: returns a Polynomial of antiderivative of polyMap
    public Polynomial antiderivative() {
        TreeMapPolynomial antiDerivMap = new TreeMapPolynomial();
        for (int k : polyMap.keySet()) {
            antiDerivMap.polyMap.put(k + 1, polyMap.get(k) / (k + 1));
        }
        return antiDerivMap;
    }

    // precondition: none
    // postcondition: returns a double of the definite integral between start & end of polyMap
    public double definiteIntegral(int start, int end) {
        // I used the first fundamental theorem of calculus to calculate the definite integral
        return antiderivative().evaluateAt(end) - antiderivative().evaluateAt(start);
    }

    // precondition: none
    // postcondition: prints polyMap as a string from least to greatest degree
    public String toString() {
        String out = "";

        for (int exp : polyMap.keySet()) { // loop through set of polyMap keys
            DecimalFormat d2 = new DecimalFormat("##.##"); // formats coefficients to 2 decimals
            Double coefficient = polyMap.get(exp);

            if (!out.isEmpty()) { // everything after first term - add plus/minus in front
                if (coefficient > 0) { // plus sign for positive coefficient
                    out += " + ";
                }
                else { // minus sign for negative coefficient
                    out += " - ";
                }
                out += d2.format(Math.abs(coefficient));
            }
            else { // first term - leave negative sign if negative, no plus/minus in front
                out += d2.format(coefficient);
            }

            if (exp != 0) { // only show coefficient if power is 0
                out += "x";

                if (exp != 1) { // don't show power if it is 1
                    out += "^" + exp;
                }
            }
        } // for

        return out;
    }
} // TreeMapPolynomial

/*
Poly1: 2 - 4x + 2x^3
Poly1 evaluated at 2.0: 10.0
Poly2: -3 - 5x + 1x^2 + 2x^4
Poly1 + Poly2: -1 - 9x + 1x^2 + 2x^3 + 2x^4
Poly1 * Poly2: -6 + 2x + 22x^2 - 10x^3 - 6x^4 - 6x^5 + 4x^7
Derivative of Poly1: -4 + 6x^2
Antiderivative of Poly2: -3x - 2.5x^2 + 0.33x^3 + 0.4x^5
Definite Integral of Poly1 from 1 to 4: 103.5

Process finished with exit code 0
 */