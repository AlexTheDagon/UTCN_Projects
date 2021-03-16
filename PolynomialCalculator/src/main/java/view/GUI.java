package view;

import javax.swing.*;
import java.awt.*;

public class GUI {

    Font biggerFont = new Font("Serif", Font.BOLD, 22);
    Font biggerFontButSmaller = new Font("Serif", Font.BOLD, 18);
    Font biggerFontButEvenMoreSmaller = new Font("Serif", Font.BOLD, 18);

    JFrame frame = new JFrame("Not so pretty Polynomial Calculator");
    JPanel home = new JPanel();

    // Operations
    private JButton addButton = new JButton("Add");
    private JButton substractButton = new JButton("Substract");
    private JButton multiplyButton = new JButton("Multiply");
    private JButton divideButton = new JButton("Divide");
    private JButton integrateButton = new JButton("Integrate");
    private JButton differentiateButton = new JButton("Differentiate");

    //Polynomials fields
    private JTextField firstPolynomial = new JTextField();
    private JTextField secondPolynomial = new JTextField();
    JLabel firstLabel = new JLabel("Polynom 1:");
    JLabel secondLabel = new JLabel("Polynom 2:");

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubstractButton() {
        return substractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getIntegrateButton() {
        return integrateButton;
    }

    public JButton getDifferentiateButton() {
        return differentiateButton;
    }

    public JTextField getFirstPolynomial() {
        return firstPolynomial;
    }

    public JTextField getSecondPolynomial() {
        return secondPolynomial;
    }

    public void badInputDialogue() {
        JOptionPane.showMessageDialog(frame, "The Input given is not a polynomial.\n");
    }

    public GUI() {


        // Getting the screen dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) 640; //screenSize.getWidth();
        int heightScreen = (int) 640;//screenSize.getHeight();
        int divW = widthScreen / 64;
        int divH = heightScreen / 64;

        int homeH = 64 * divH;
        int homeW = 64 * divW;

        int buttonH = divH * 6;
        int buttonW = divW * 24;

        int textFieldH = divH * 6;
        int textFieldW = divW * 40;

        //Setting up the frame
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(widthScreen, heightScreen);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Setting up the the panel inside the frame
        frame.add(home);
        home.setVisible(true);
        home.setSize(homeW, homeH);
        home.setLayout(null);
        home.setBackground(Color.gray);

        //Setting up the text fields
        home.add(firstPolynomial);
        firstPolynomial.setSize(textFieldW, textFieldH);
        firstPolynomial.setLocation(18 * divW, 6 * divH);
        firstPolynomial.setBackground(Color.darkGray);
        firstPolynomial.setForeground(Color.white);
        firstPolynomial.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        firstPolynomial.setFont(biggerFont);
        firstPolynomial.setHorizontalAlignment(JTextField.CENTER);

        home.add(firstLabel);
        firstLabel.setSize(12 * divW, 6 * divH);
        firstLabel.setLocation(6 * divW, 6 * divH);
        firstLabel.setForeground(Color.white);
        firstLabel.setFont(biggerFont);

        home.add(secondPolynomial);
        secondPolynomial.setSize(textFieldW, textFieldH);
        secondPolynomial.setLocation(18 * divW, 14 * divH);
        secondPolynomial.setBackground(Color.darkGray);
        secondPolynomial.setForeground(Color.white);
        secondPolynomial.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        secondPolynomial.setFont(biggerFont);
        secondPolynomial.setHorizontalAlignment(JTextField.CENTER);

        home.add(secondLabel);
        secondLabel.setSize(12 * divW, 6 * divH);
        secondLabel.setLocation(6 * divW, 14 * divH);
        secondLabel.setForeground(Color.white);
        secondLabel.setFont(biggerFont);



        // Now we add the button on the top panel
        home.add(addButton);
        addButton.setSize(buttonW, buttonH);
        addButton.setLocation(6 * divW, 34 * divH);
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.white);
        addButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        addButton.setFont(biggerFont);

        // Now we add the button on the top panel
        multiplyButton.setSize(buttonW, buttonH);
        home.add(multiplyButton);
        multiplyButton.setLocation(6 * divW, 42 * divH);
        multiplyButton.setBackground(Color.darkGray);
        multiplyButton.setForeground(Color.white);
        multiplyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        multiplyButton.setFont(biggerFont);

        // Now we add the button on the top panel
        integrateButton.setSize(buttonW, buttonH);
        home.add(integrateButton);
        integrateButton.setLocation(6 * divW, 50 * divH);
        integrateButton.setBackground(Color.darkGray);
        integrateButton.setForeground(Color.white);
        integrateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        integrateButton.setFont(biggerFont);

        // Now we add the button on the top panel
        substractButton.setSize(buttonW, buttonH);
        home.add(substractButton);
        substractButton.setLocation(34 * divW, 34 * divH);
        substractButton.setBackground(Color.darkGray);
        substractButton.setForeground(Color.white);
        substractButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        substractButton.setFont(biggerFont);

        // Now we add the button on the top panel
        divideButton.setSize(buttonW, buttonH);
        home.add(divideButton);
        divideButton.setLocation(34 * divW, 42 * divH);
        divideButton.setBackground(Color.darkGray);
        divideButton.setForeground(Color.white);
        divideButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        divideButton.setFont(biggerFont);

        // Now we add the button on the top panel
        differentiateButton.setSize(buttonW, buttonH);
        home.add(differentiateButton);
        differentiateButton.setLocation(34 * divW, 50 * divH);
        differentiateButton.setBackground(Color.darkGray);
        differentiateButton.setForeground(Color.white);
        differentiateButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        differentiateButton.setFont(biggerFont);

    }
}
