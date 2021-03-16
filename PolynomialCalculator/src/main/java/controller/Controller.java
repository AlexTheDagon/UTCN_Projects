package controller;

import view.*;
import model.*;

public class Controller {

    GUI userInterface = new GUI();

    public Controller() {
        userInterface.getAddButton().addActionListener(e -> {
            String polynom1AsString = userInterface.getFirstPolynomial().getText();
            String polynom2AsString = userInterface.getSecondPolynomial().getText();
            try {
                Polynomial p1 = new Polynomial(polynom1AsString);
                Polynomial p2 = new Polynomial(polynom2AsString);
                p1.addPolynom(p2);
                p1.sortDescending();
                String res = p1.toStringInt();
                if(res == "") res = "0";
                if(res.charAt(0) == '+')res = res.substring(1);
                userInterface.getFirstPolynomial().setText(res);
                userInterface.getSecondPolynomial().setText("");

            } catch (Exception badInputException) {
                userInterface.badInputDialogue();
                System.err.println("Exception caught: " + badInputException);
            }
        });

        userInterface.getSubstractButton().addActionListener(e -> {
            String polynom1AsString = userInterface.getFirstPolynomial().getText();
            String polynom2AsString = userInterface.getSecondPolynomial().getText();
            try {
                Polynomial p1 = new Polynomial(polynom1AsString);
                Polynomial p2 = new Polynomial(polynom2AsString);
                p1.substractPolynom(p2);
                p1.sortDescending();
                String res = p1.toStringInt();
                if(res == "") res = "0";
                if(res.charAt(0) == '+')res = res.substring(1);
                userInterface.getFirstPolynomial().setText(res);
                userInterface.getSecondPolynomial().setText("");

            } catch (Exception badInputException) {
                userInterface.badInputDialogue();
                System.err.println("Exception caught: " + badInputException);
            }
        });

        userInterface.getMultiplyButton().addActionListener(e -> {
            String polynom1AsString = userInterface.getFirstPolynomial().getText();
            String polynom2AsString = userInterface.getSecondPolynomial().getText();
            try {
                Polynomial p1 = new Polynomial(polynom1AsString);
                Polynomial p2 = new Polynomial(polynom2AsString);
                p1.multiplyPolynom(p2);
                p1.sortDescending();
                String res = p1.toStringInt();
                if(res == "") res = "0";
                if(res.charAt(0) == '+')res = res.substring(1);
                userInterface.getFirstPolynomial().setText(res);
                userInterface.getSecondPolynomial().setText("");

            } catch (Exception badInputException) {
                userInterface.badInputDialogue();
                System.err.println("Exception caught: " + badInputException);
            }
        });

        userInterface.getDivideButton().addActionListener(e -> {
            String polynom1AsString = userInterface.getFirstPolynomial().getText();
            String polynom2AsString = userInterface.getSecondPolynomial().getText();
            try {
                Polynomial p1 = new Polynomial(polynom1AsString);
                Polynomial p2 = new Polynomial(polynom2AsString);
                Polynomial remainder = p1.divideByPolynomial(p2);
                p1.sortDescending();
                remainder.sortDescending();
                String quot = p1.toStringDouble();
                String rem = remainder.toStringDouble();
                if(quot == "") quot = "0";
                if(quot.charAt(0) == '+')quot = quot.substring(1);
                if(rem == "") rem = "0";
                if(rem.charAt(0) == '+')rem = rem.substring(1);
                userInterface.getFirstPolynomial().setText(quot);
                userInterface.getSecondPolynomial().setText(rem);

            } catch (Exception badInputException) {
                userInterface.badInputDialogue();
                System.err.println("Exception caught: " + badInputException);
            }
        });

        userInterface.getIntegrateButton().addActionListener(e -> {
            String polynom1AsString = userInterface.getFirstPolynomial().getText();
            String polynom2AsString = userInterface.getSecondPolynomial().getText();
            try {
                Polynomial p1 = new Polynomial(polynom1AsString);
                p1.integratePolynomial();
                p1.sortDescending();
                String res = p1.toStringDouble();
                if(res == "") res = "0";
                if(res.charAt(0) == '+')res = res.substring(1);
                res = res + "+C";
                userInterface.getFirstPolynomial().setText(res);
                userInterface.getSecondPolynomial().setText("");

            } catch (Exception badInputException) {
                userInterface.badInputDialogue();
                System.err.println("Exception caught: " + badInputException);
            }
        });

        userInterface.getDifferentiateButton().addActionListener(e -> {
            String polynom1AsString = userInterface.getFirstPolynomial().getText();
            String polynom2AsString = userInterface.getSecondPolynomial().getText();
            try {
                Polynomial p1 = new Polynomial(polynom1AsString);
                p1.derivatePolynomial();
                p1.sortDescending();
                String res = p1.toStringInt();
                if(res == "") res = "0";
                if(res.charAt(0) == '+')res = res.substring(1);
                userInterface.getFirstPolynomial().setText(res);
                userInterface.getSecondPolynomial().setText("");

            } catch (Exception badInputException) {
                userInterface.badInputDialogue();
                System.err.println("Exception caught: " + badInputException);
            }
        });

    }
}
