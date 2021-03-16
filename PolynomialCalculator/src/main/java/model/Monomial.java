package model;

import java.util.Collections;

public class Monomial implements Comparable{
    private Number coefficient;
    private Number exponent;

    public Monomial(Number coefficient, Number exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Monomial(Monomial x){
        this.coefficient = x.coefficient;
        this.exponent = x.exponent;
    }

    public void addMonomial(Monomial x){
        this.coefficient = this.coefficient.doubleValue() + x.getCoefficient().doubleValue();
    }

    public void substractMonomial(Monomial x) {
        this.coefficient = this.coefficient.doubleValue() - x.getCoefficient().doubleValue();
    }

    public void multiplyMonomial(Monomial x) {
        this.coefficient = this.coefficient.doubleValue() * x.coefficient.doubleValue();
        this.exponent = this.exponent.doubleValue() + x.exponent.doubleValue();
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }

    public Number getExponent() {
        return exponent;
    }

    public void setExponent(Number exponent) {
        this.exponent = exponent;
    }

    public void addCoefficient(Number coefficient) { this.coefficient = this.coefficient.intValue() + coefficient.intValue(); }


    @Override
    public String toString() { return "Monomial{" + "coefficient=" + coefficient + ", exponent=" + exponent + '}'; }

    @Override
    public int compareTo(Object compareTo) {
        int compareToExp = ((Monomial)compareTo).getExponent().intValue();
        int exp = this.exponent.intValue();
        return compareToExp - exp;
    }
}
