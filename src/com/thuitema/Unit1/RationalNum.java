package com.thuitema.Unit1;

public class RationalNum
{
    // fields
    private int numerator;
    private int denominator;

    // constructors
    public RationalNum() {
        this(1,1);
    }

    public RationalNum(RationalNum n) {
        this(n.getNumerator(), n.getDenominator());
    }

    public RationalNum(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplifyFraction();
    }

    // operational methods (+ - * /)
    public String add(RationalNum num) {
        int lcd = this.denominator * num.getDenominator();
        int resultNumerator = this.numerator * num.getDenominator() + this.denominator * num.getNumerator();

        // simplify lcd
        int i = 2;
        while(true) {
            if(resultNumerator % i == 0 && lcd % i == 0) {
                resultNumerator /= i;
                lcd /= i;
                break;
            }
            i++;
        }
        RationalNum result = new RationalNum(resultNumerator, lcd);

        return result.toString();
    }

    public String subtract(RationalNum num) {
        int lcd = this.denominator * num.getDenominator();
        int resultNumerator = this.numerator * num.getDenominator() - this.denominator * num.getNumerator();

        // simplify lcd
        int i = 2;
        while(true) {
            if(resultNumerator % i == 0 && lcd % i == 0) {
                resultNumerator /= i;
                lcd /= i;
                break;
            }
            i++;
        }

        RationalNum result = new RationalNum(resultNumerator, lcd); // making a new object will simplify the fraction
        return result.toString();
    }

    public String multiply(RationalNum num) {
        int resultNum = this.numerator * num.getNumerator();
        int resultDenom = this.denominator * num.getDenominator();

        RationalNum result = new RationalNum(resultNum, resultDenom);
        return result.toString();
    }

    public String divide(RationalNum num) {
        int resultNum = this.numerator * num.getDenominator();
        int resultDenom = this.denominator * num.getNumerator();

        RationalNum result = new RationalNum(resultNum, resultDenom);
        return result.toString();
    }
    // private methods
    private void simplifyFraction() {
        int commonDenominator = gcd(this.numerator, this.denominator);
        this.numerator /= commonDenominator;
        this.denominator /= commonDenominator;
    }

    private int gcd(int n, int d) {
        if(d == 0) {
            return n;
        }
        return gcd(d, n % d);
    }

    // toString
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    // Equals method
    public boolean equals(Object o) {
        RationalNum that = (RationalNum) o;
        return this.numerator == that.numerator && this.denominator == that.denominator;
    }

    // getters & setters
    public void setDenom(int num) {
        this.denominator = num;
    }

    public void setNumerator(int num) {
        this.numerator = num;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

}
