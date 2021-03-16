package model;

import java.awt.desktop.QuitEvent;
import java.security.Policy;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    List<Monomial> polynom = new ArrayList<>();

    public void addMonomialToPolynom(Monomial x){
        if(Math.abs(x.getCoefficient().doubleValue()) < 0.000000001) return;
        for(Monomial m : this.polynom){
            if(m.getExponent().intValue() == x.getExponent().intValue()) {
                m.addMonomial(x);
                if(Math.abs(m.getCoefficient().doubleValue()) < 0.000000001)this.polynom.remove(m);
                return;
            }
        }
        this.polynom.add(x);
    }

    public void substractMonomialFromPolynom(Monomial x){
        for(Monomial m : this.polynom){
            if(m.getExponent().intValue() == x.getExponent().intValue()) {
                m.substractMonomial(x);
                if(Math.abs(m.getCoefficient().doubleValue()) < 0.000000001)this.polynom.remove(m);
                return;
            }
        }
        double coeff = x.getCoefficient().doubleValue();
        x.setCoefficient(-coeff);
        this.polynom.add(x);
    }

    public void substractMonomialFromPolynomWithoutRemove(Monomial x){
        for(Monomial m : this.polynom){
            if(m.getExponent().intValue() == x.getExponent().intValue()) {
                m.substractMonomial(x);
                if(Math.abs(m.getCoefficient().doubleValue()) < 0.000000001)m.setCoefficient(0);
                return;
            }
        }
        double coeff = x.getCoefficient().doubleValue();
        x.setCoefficient(-coeff);
        this.polynom.add(x);
    }

    public void addPolynom(Polynomial x){
        for(Monomial m : x.polynom) {
            this.addMonomialToPolynom(m);
        }
    }

    public void substractPolynom(Polynomial x){
        for(Monomial m : x.polynom) {
            this.substractMonomialFromPolynom(m);
        }
    }

    public void substractPolynomWithoutRemove(Polynomial x){
        for(Monomial m : x.polynom) {
            this.substractMonomialFromPolynomWithoutRemove(m);
        }
    }

    public void multiplyPolynom(Polynomial x) {
        Polynomial y = new Polynomial(this);
        this.polynom.clear();
        this.print();
        for(Monomial mx : x.polynom){
            for(Monomial my : y.polynom) {
                Monomial copyMx = new Monomial(mx);
                copyMx.multiplyMonomial(my);
                this.addMonomialToPolynom(copyMx);
            }
        }
    }

    public void derivatePolynomial (){
        Polynomial x = new Polynomial(this);
        this.polynom.clear();
        for(Monomial m : x.polynom){
            if(m.getExponent().intValue() > 0){
                double coeff = m.getCoefficient().doubleValue() * m.getExponent().doubleValue();
                int exp = m.getExponent().intValue() - 1;
                m.setCoefficient(coeff);
                m.setExponent(exp);
                this.addMonomialToPolynom(m);
            }
        }
    }

    public void integratePolynomial (){
        Polynomial x = new Polynomial(this);
        this.polynom.clear();
        for(Monomial m : x.polynom){
            int exp = m.getExponent().intValue() + 1;
            double coeff = m.getCoefficient().doubleValue() / exp;
            m.setCoefficient(coeff);
            m.setExponent(exp);
            this.addMonomialToPolynom(m);

        }
    }

    public Polynomial divideByPolynomial (Polynomial x) {
        Polynomial P = new Polynomial(this);
        P.clearPolynomial();
        Polynomial Q = new Polynomial(x);
        Polynomial Quotient = new Polynomial(x);
        Polynomial Remainder = new Polynomial(x);
        Quotient.clearPolynomial();
        Remainder.clearPolynomial();

        int degQ = Q.polynom.get(0).getExponent().intValue();
        int degP = Q.polynom.get(0).getExponent().intValue();
        for(int i = degP; i >=0; --i){
            Monomial newM = new Monomial(0,i);
            P.polynom.add(newM);
        }
        P.addPolynom(this);
        P.sortDescending();

        for(Monomial mP : P.polynom){
            if(mP.getExponent().intValue() >= degQ) {
                double divisionCoeff = mP.getCoefficient().doubleValue() / Q.polynom.get(0).getCoefficient().doubleValue();
                int divisionExp = mP.getExponent().intValue() - degQ;

                for(Monomial mCopy : Q.polynom) {
                    double coeff = mCopy.getCoefficient().doubleValue() * divisionCoeff;
                    int exp = mCopy.getExponent().intValue() + divisionExp;
                    Monomial toSub = new Monomial(coeff, exp);
                    P.substractMonomialFromPolynomWithoutRemove(toSub);
                }
                Monomial divisionMonomialResult = new Monomial(divisionCoeff, divisionExp);
                Quotient.addMonomialToPolynom(divisionMonomialResult);
            }
            else {
                Remainder.addMonomialToPolynom(mP);
            }
        }
        this.copyPolynomial(Quotient);
        return Remainder;
    }

    public void copyPolynomial (Polynomial x){
        this.polynom.clear();
        for(Monomial m : x.polynom) {
            this.addMonomialToPolynom(m);
        }
    }

    public void clearPolynomial() {
        this.polynom.clear();
    }

    public boolean polynomValidation (String polynomAsString) {
        String polynomToCheck = polynomAsString;
        int len = polynomAsString.length() - 1;
        if(polynomToCheck.charAt(0) != '-' && polynomToCheck.charAt(0) != '+') polynomToCheck = '+' + polynomToCheck;
        String patternRegexCheck = "([+-](?<coefficient>\\d+)?(?<var>[x])(?<exponent>\\d+)?)+";
        Pattern patternCheck = Pattern.compile(patternRegexCheck);
        Matcher matcherCheck = patternCheck.matcher(polynomToCheck);
        if(matcherCheck.matches()) { return true; }
        else { return false; }
    }

    public String generatePolynomToParse (String polynomAsString){ //Remove all white space, '*' and '^'
        polynomAsString = polynomAsString.replaceAll("\\s+","");
        polynomAsString = polynomAsString.replaceAll("\\*","");
        polynomAsString = polynomAsString.replaceAll("\\^","");
        if(polynomAsString.charAt(0) != '-' && polynomAsString.charAt(0) != '+') polynomAsString = '+' + polynomAsString;
        int i = polynomAsString.length() - 1;
        while(i>=0 && '0' <= polynomAsString.charAt(i) && polynomAsString.charAt(i) <= '9') --i;
        if(polynomAsString.charAt(i) == '-' || polynomAsString.charAt(i) == '+') polynomAsString = polynomAsString + "x0";
        return polynomAsString;
    }

    public Polynomial(Polynomial toCopy){ for(Monomial x: toCopy.polynom) { this.polynom.add(x); } }

    public Polynomial(String polynomAsString) throws Exception{

        polynomAsString = generatePolynomToParse(polynomAsString);
        System.out.println("After generatePolynomToParse: " + polynomAsString);

        if(polynomValidation(polynomAsString)){ System.out.println("GOOD Stuff"); }
        else { throw new Exception("Bad Input"); }


        String patternMonomial = "[+-](?<coefficient>\\d+)?(?<var>[x])(?<exponent>\\d+)?";
        Pattern pattern = Pattern.compile(patternMonomial);
        Matcher matcher = pattern.matcher(polynomAsString);


        while(matcher.find()) {
            char sign = polynomAsString.charAt(matcher.start());
            String coeffString = matcher.group(1);
            String expString = matcher.group(3);
            if(expString == null) expString = "1";
            if(coeffString == null) coeffString = "1";
            Number coeff = Integer.parseInt(sign + coeffString);
            Number exp = Integer.parseInt(expString);
            addMonomialToPolynom(new Monomial(coeff, exp));
        }

        Collections.sort(this.polynom);
    }

    public void sortDescending() {
        Collections.sort(this.polynom);
    }

    public void print(){
        for (Monomial x: polynom) {
            System.out.println(x);
        }
        System.out.println();
    }


    public String toStringInt() {
        String polynomialToString ="";
        for(Monomial x : this.polynom){
            if(x.getCoefficient().intValue() >= 0) polynomialToString = polynomialToString + "+";
            if(x.getCoefficient().intValue() != 1) polynomialToString = polynomialToString + x.getCoefficient().intValue();
            polynomialToString = polynomialToString + "x" + x.getExponent().intValue();
        }
        int len = polynomialToString.length();
        if(len == 2 && polynomialToString == "x0")polynomialToString = "1";
        else if(len > 2 && polynomialToString.charAt(len - 2) == 'x' && polynomialToString.charAt(len - 1) == '0') polynomialToString = polynomialToString.substring(0, len - 2);
        return polynomialToString;
    }

    public String toStringDouble() {
        String polynomialToString ="";
        for(Monomial x : this.polynom){
            if(x.getCoefficient().doubleValue() >= 0) polynomialToString = polynomialToString + "+";
            if(x.getCoefficient().doubleValue() != 1.0) polynomialToString = polynomialToString + x.getCoefficient().doubleValue();
            polynomialToString = polynomialToString + "x" + x.getExponent().intValue();
        }
        int len = polynomialToString.length();
        if(len == 2 && polynomialToString == "x0")polynomialToString = "1";
        else if(len > 2 && polynomialToString.charAt(len - 2) == 'x' && polynomialToString.charAt(len - 1) == '0') polynomialToString = polynomialToString.substring(0, len - 2);
        return polynomialToString;
    }
}
