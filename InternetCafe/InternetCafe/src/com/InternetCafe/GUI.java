package com.InternetCafe;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class GUI {
    private int balance = 10000;
    private Product[] products = new Product[20];
    Vector<Customer> customersVector = new Vector<Customer>();
    Vector<Reservation> reservationsVector = new Vector<Reservation>();

    int nrProducts = 20;
    Font biggerFont = new Font("sherif", Font.BOLD, 22);
    Font biggerFontButSmaller = new Font("sherif", Font.BOLD, 18);
    Font biggerFontButEvenMoreSmaller = new Font("sherif", Font.BOLD, 18);

    // The general layout
    JFrame frame = new JFrame("Excellent Era");
    JPanel home = new JPanel();
    JPanel storePanel = new JPanel();
    JPanel managerPanel = new JPanel();



    // The navBar
    JButton manager = new JButton("Manager");
    JButton store = new JButton("Store");
    JTextField balField = new JTextField(balance + " RON");

    // The STORE
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable scrollTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(scrollTable);
    JTextField id = new JTextField("ID");
    JTextField quantity = new JTextField("QUANTITY");
    JButton buy = new JButton("BUY");
    JButton sell = new JButton("SELL");

    // The MANAGER
    JPanel controlPanel = new JPanel();
    JButton searchCustomer = new JButton("Search Customer");
    JButton registerCustomer = new JButton("Register Customer");
    JButton makeReservation = new JButton("Make Reservation");
    JPanel searchCustomerPanel = new JPanel();
    JPanel registerCustomerPanel = new JPanel();
    JPanel makeReservationPanel = new JPanel();

    // The searchCustomerPanel
    JTextField inputEmail = new JTextField("Input Email");
    JTextField inputID = new JTextField("Input ID");
    JButton idSearch = new JButton("Search");
    JButton emailSearch = new JButton("Search");
    JTextField outputID = new JTextField("Found ID");
    JTextField outputEmail = new JTextField("Found Email");
    JTextField outputFirstName = new JTextField("Found First Name");
    JTextField outputLastName = new JTextField("Found Last Name");

    // The register customer panel
    JTextField registerFirstName = new JTextField("Enter First Name");
    JTextField registerLastName = new JTextField("Enter Last Name");
    JTextField registerEmail = new JTextField("Enter Email");
    JButton finishCustomerRegistration = new JButton("Finish Registration");
    JTextField registeredID = new JTextField("CUSTOMER ID");

    // The Make Reservation Panel
    int nrRows = 5;
    int nrCols = 4;
    Station[] stationsArray = new Station[nrRows * nrCols];
    JButton[] buttonsArray = new JButton[nrRows * nrCols];
    JPanel stationsPanel = new JPanel();
    double pricePerHour = 10.00;
    JTextField reservationDate = new JTextField("Date dd:mm:yyyy");
    JTextField reservationTimeStart = new JTextField("Time Start hh:mm");
    JTextField reservationTimeEnd = new JTextField("Time End hh:mm");
    JTextField reservationCustomerID = new JTextField("Customer ID");
    JTextField reservationPricePerHour = new JTextField("Price Per Hour: " + pricePerHour + " RON");
    JButton showStations = new JButton("Show Stations");

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }


    public void initializeProducts() {
        for(int i = 0; i < 20; ++i){
            products[i] = new Product("Game ".concat(Integer.toString(i)), "PC", 10, 10 * i, 50 + 10 * i);
        }
        products[18].setType("PS5");
        products[19].setType("PS5");
    }

    public void initializeCustomers() {
        customersVector.add(new Customer("Tudor", "Tudor", "Tudor@gmail.com"));
        customersVector.add(new Customer("Alex", "Alex", "Alex@gmail.com"));
        customersVector.add(new Customer("Bogdan", "Bogdan", "Bogdan@gmail.com"));
        customersVector.add(new Customer("Cristi", "Cristi", "Cristi@gmail.com"));
        customersVector.add(new Customer("Mihai", "Mihai", "Mihai@gmail.com"));
        customersVector.add(new Customer("Luci", "Luci", "Luci@gmail.com"));
        customersVector.add(new Customer("Ale", "Ale", "Ale@gmail.com"));
        customersVector.add(new Customer("Teo", "Teo", "Teo@gmail.com"));
        customersVector.add(new Customer("David", "David", "David@gmail.com"));
    }

    public void initializeTable(Product[] products, int size){
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        for(int i = 0; i < size; ++i){
            tableModel.addRow(new Object[]{i, products[i].getName(), products[i].getType(), products[i].getQuantity(), products[i].getPrice_buy(), products[i].getPrice_sell()});
        }
    }

    public int searchByEmail(String givenEmail){
        for(int i = 0; i < customersVector.size(); ++i){
            if(customersVector.get(i).getEmail().equals(givenEmail)) {
                return i;
            }
        }
        return -1;
    }

    public int searchById(int ID){
        if(ID < 0 || ID >= customersVector.size()) return -1;
        return ID;
    }

    public boolean checkAvailability(String givenDate, String givenStart, String givenEnd, int givenId){
        for(int i = 0; i < reservationsVector.size(); ++i){
            if(givenId == reservationsVector.get(i).getStation().getID() && givenDate.compareTo(reservationsVector.get(i).getDate()) == 0  && !(givenStart.compareTo(reservationsVector.get(i).getEnd()) >= 0  || givenEnd.compareTo(reservationsVector.get(i).getStart()) <= 0 )) return false;
        }
        return true;
    }

    public GUI(FileWriter myFile) throws IOException {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Getting the screen dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) screenSize.getWidth();
        int heightScreen = (int) screenSize.getHeight();
        int divW = widthScreen / 64;
        int divH = heightScreen / 64;

        // https://stackoverflow.com/questions/15694107/how-to-layout-multiple-panels-on-a-jframe-java
        // We create the home panel

        int homeH = 16 * divH;
        int homeW = 64 * divW;

        home.setSize(homeW, homeH);
        home.setLayout(null);
        home.setBackground(Color.gray);


        // Now we add the button on the top panel
        int buttonH = divH * 4;
        int buttonW = divW * 12;

        home.add(manager);
        manager.setLocation(45 * divW, 4 * divH);
        manager.setSize(buttonW, buttonH);
        manager.setBackground(Color.darkGray);
        manager.setForeground(Color.white);
        manager.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        manager.setFont(biggerFont);
        manager.addActionListener(e -> {
            storePanel.setVisible(false);
            managerPanel.setVisible(true);
        });

        home.add(store);
        store.setLocation(7 * divW, 4 * divH);
        store.setSize(buttonW, buttonH);
        store.setBackground(Color.darkGray);
        store.setForeground(Color.white);
        store.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        store.setFont(biggerFont);
        store.addActionListener(e -> {
            storePanel.setVisible(true);
            managerPanel.setVisible(false);
        });

        home.add(balField);
        balField.setLocation(26 * divW, 4 * divH);
        balField.setSize(buttonW, buttonH);
        balField.setBackground(Color.darkGray);
        balField.setForeground(Color.white);
        balField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        balField.setHorizontalAlignment(JTextField.CENTER);
        balField.setEditable(false);
        balField.setFont(biggerFont);

        home.setVisible(true);
        frame.add(home, BorderLayout.NORTH);


        // THE STORE -> the panel was declared at the begining of the GUI

        int storePanelH = 48 * divH;
        int storePanelW = widthScreen;
        int panelBorderH = 12 * divH;
        int panelBorderW = 12 * divW;

        storePanel.setSize(storePanelW, storePanelH);
        storePanel.setLayout(null);
        storePanel.setBackground(Color.gray);
        storePanel.setLocation(0, homeH);

        // First we set up the shape of the table


        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Type");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Buy");
        tableModel.addColumn("Sell");

        // Secondly, we must create the content of the Scroll Pane

        scrollTable.setLocation(panelBorderW, panelBorderH);
        scrollTable.setSize(storePanelW - 2 * panelBorderW, storePanelH - 2 * panelBorderH);

        //Now we set up the scroll in the pane

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(true);
        scrollPane.setLocation(panelBorderW, panelBorderH);
        scrollPane.setSize(storePanelW - 2 * panelBorderW, storePanelH - 2 * panelBorderH);

        // Here we add the storePanel to the frame
        storePanel.add(scrollPane);
        storePanel.setVisible(true);
        frame.add(storePanel);

        // Next we load the data into the table
        initializeProducts();
        initializeTable(products, nrProducts);

        //  Next we do some stiling cause i hate the way the table looks

        scrollTable.setBackground(Color.DARK_GRAY);
        scrollTable.setForeground(Color.WHITE);
        scrollTable.getTableHeader().setPreferredSize(new Dimension(0, divH * 2));
        scrollTable.getTableHeader().setFont(biggerFontButEvenMoreSmaller);
        scrollTable.setRowHeight(2 * divH);
        scrollTable.setEnabled(false);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)scrollTable.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );

        // next we make 2 Buttons to interact with the store


        storePanel.add(id);
        id.setLocation(12 * divW, 4 * divH);
        id.setSize(8 * divW, 4 * divH);
        id.setBackground(Color.darkGray);
        id.setForeground(Color.white);
        id.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        id.setHorizontalAlignment(JTextField.CENTER);
        id.setFont(biggerFontButSmaller);

        storePanel.add(quantity);
        quantity.setLocation(20 * divW, 4 * divH);
        quantity.setSize(8 * divW, 4 * divH);
        quantity.setBackground(Color.darkGray);
        quantity.setForeground(Color.white);
        quantity.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        quantity.setHorizontalAlignment(JTextField.CENTER);
        quantity.setFont(biggerFontButSmaller);



        storePanel.add(buy);
        buy.setLocation(36 * divW, 4 * divH);
        buy.setSize(8 * divW, 4 * divH);
        buy.setBackground(Color.darkGray);
        buy.setForeground(Color.white);
        buy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        buy.setFont(biggerFontButSmaller);
        buy.addActionListener(e -> {
            String sID = id.getText();
            String sQUANTITY = quantity.getText();
            if(isNumeric(sID) && isNumeric(sQUANTITY)){
                int intID = Integer.parseInt(sID);
                int intQANTITY = Integer.parseInt(sQUANTITY);
                double buyPrice = products[intID].getPrice_buy();
                if (balance >= buyPrice * intQANTITY) {
                    balance -= products[intID].buy(intQANTITY);
                    tableModel.setValueAt(products[intID].getQuantity(), intID, 3);
                }
            }

            balField.setText(balance + " RON");
            id.setText("ID");
            quantity.setText("QUANTITY");
        });

        storePanel.add(sell);
        sell.setLocation(44 * divW, 4 * divH);
        sell.setSize(8 * divW, 4 * divH);
        sell.setBackground(Color.darkGray);
        sell.setForeground(Color.white);
        sell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        sell.setFont(biggerFontButSmaller);
        sell.addActionListener(e -> {
            String sID = id.getText();
            String sQUANTITY = quantity.getText();
            if(isNumeric(sID) && isNumeric(sQUANTITY)){
                int intID = Integer.parseInt(sID);
                int intQANTITY = Integer.parseInt(sQUANTITY);
                double sellPrice = products[intID].getPrice_sell();
                if (intQANTITY <= products[intID].getQuantity()) {
                    balance += products[intID].sell(intQANTITY);
                    tableModel.setValueAt(products[intID].getQuantity(), intID, 3);
                }
            }

            balField.setText(balance + " RON");
            id.setText("ID");
            quantity.setText("QUANTITY");
        });








        // THE MANAGER
        int managerPanelH = 48 * divH;
        int managerPanelW = widthScreen;
        int managerBorderH = 12 * divH;
        int managerBorderW = 12 * divW;

        managerPanel.setSize(managerPanelW, managerPanelH);
        managerPanel.setLayout(null);
        managerPanel.setBackground(Color.gray);
        managerPanel.setLocation(0, homeH);

        // First we set up the manager control panel

        managerPanel.add(controlPanel);
        controlPanel.setLayout(null);
        controlPanel.setVisible(true);
        controlPanel.setLocation(7 * divW,4 * divH);
        controlPanel.setSize(12 * divW, 32 * divH);
        controlPanel.setBackground(Color.white);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        // Now we set up 3 buttons : searchCustomer, registerCustomer, makeReservation
        controlPanel.add(searchCustomer);
        searchCustomer.setSize(6 * divW, 4 * divH);
        searchCustomer.setLocation(3 * divW, 5 * divH);
        searchCustomer.setBackground(Color.darkGray);
        searchCustomer.setForeground(Color.white);
        searchCustomer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        searchCustomer.setFont(biggerFontButSmaller);
        searchCustomer.addActionListener(e -> {
            inputEmail.setText("Input Email");
            inputID.setText("Input ID");
            outputID.setText("Found ID");
            outputEmail.setText("Found Email");
            outputFirstName.setText("Found First Name");
            outputLastName.setText("Found Last Name");
            searchCustomerPanel.setVisible(true);
            registerCustomerPanel.setVisible(false);
            makeReservationPanel.setVisible(false);
        });

        controlPanel.add(registerCustomer);
        registerCustomer.setSize(6 * divW, 4 * divH);
        registerCustomer.setLocation(3 * divW, 14 * divH);
        registerCustomer.setBackground(Color.darkGray);
        registerCustomer.setForeground(Color.white);
        registerCustomer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        registerCustomer.setFont(biggerFontButSmaller);
        registerCustomer.addActionListener(e -> {
            registerEmail.setText("Enter Email");
            registerLastName.setText("Enter Last Name");
            registerFirstName.setText("Enter First Name");
            searchCustomerPanel.setVisible(false);
            registerCustomerPanel.setVisible(true);
            makeReservationPanel.setVisible(false);
        });

        controlPanel.add(makeReservation);
        makeReservation.setSize(6 * divW, 4 * divH);
        makeReservation.setLocation(3 * divW, 23 * divH);
        makeReservation.setBackground(Color.darkGray);
        makeReservation.setForeground(Color.white);
        makeReservation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        makeReservation.setFont(biggerFontButSmaller);
        makeReservation.addActionListener(e -> {
            reservationDate.setText("Date dd:mm:yyyy");
            reservationTimeStart.setText("Time Start hh:mm");
            reservationTimeEnd.setText("Time End hh:mm");
            reservationCustomerID.setText("Customer ID");
            reservationPricePerHour.setText("Price Per Hour: " + pricePerHour + " RON");
            showStations.setText("Show Stations");
            searchCustomerPanel.setVisible(false);
            registerCustomerPanel.setVisible(false);
            makeReservationPanel.setVisible(true);
        });

        // Now we set up the buttons for each panel
        managerPanel.add(searchCustomerPanel);
        searchCustomerPanel.setLayout(null);
        searchCustomerPanel.setVisible(true);
        searchCustomerPanel.setLocation(26 * divW, 4 * divH);
        searchCustomerPanel.setSize(31 * divW, 32 * divH);
        searchCustomerPanel.setBackground(Color.WHITE);
        searchCustomerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        managerPanel.add(registerCustomerPanel);
        registerCustomerPanel.setLayout(null);
        registerCustomerPanel.setVisible(false);
        registerCustomerPanel.setLocation(26 * divW, 4 * divH);
        registerCustomerPanel.setSize(31 * divW, 32 * divH);
        registerCustomerPanel.setBackground(Color.WHITE);
        registerCustomerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        managerPanel.add(makeReservationPanel);
        makeReservationPanel.setLayout(null);
        makeReservationPanel.setVisible(false);
        makeReservationPanel.setLocation(26 * divW, 4 * divH);
        makeReservationPanel.setSize(31 * divW, 32 * divH);
        makeReservationPanel.setBackground(Color.WHITE);
        makeReservationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        // Now we set up the searchCustomerPanel (31 divW X 32 divH)

        searchCustomerPanel.add(inputEmail);
        inputEmail.setLocation(3 * divW, 5 * divH);
        inputEmail.setSize(10 * divW, 4 * divH);
        inputEmail.setBackground(Color.darkGray);
        inputEmail.setForeground(Color.white);
        inputEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        inputEmail.setHorizontalAlignment(JTextField.CENTER);
        inputEmail.setFont(biggerFontButSmaller);


        searchCustomerPanel.add(inputID);
        inputID.setLocation(20 * divW, 5 * divH);
        inputID.setSize(5 * divW, 4 * divH);
        inputID.setBackground(Color.darkGray);
        inputID.setForeground(Color.white);
        inputID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        inputID.setHorizontalAlignment(JTextField.CENTER);
        inputID.setFont(biggerFontButSmaller);

        // Buttonul de search va fi de 3 x 3

        searchCustomerPanel.add(emailSearch );
        emailSearch.setLocation(13 * divW, 5 * divH);
        emailSearch.setSize(3 * divW, 4 * divH);
        emailSearch.setBackground(Color.darkGray);
        emailSearch.setForeground(Color.white);
        emailSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        emailSearch.setFont(biggerFontButSmaller);
        emailSearch.addActionListener(e -> {
            String gotEmail = inputEmail.getText();
            int foundID = searchByEmail(gotEmail);
            inputEmail.setText("Input Email");
            inputID.setText("Input ID");
            outputID.setText("Found ID");
            outputEmail.setText("Found Email");
            outputFirstName.setText("Found First Name");
            outputLastName.setText("Found Last Name");
            if(foundID == -1) {
                inputEmail.setText("Input Email");
                inputID.setText("Input ID");
                outputID.setText("Mail not registered!");
                outputEmail.setText("Mail not registered!");
                outputFirstName.setText("Mail not registered!");
                outputLastName.setText("Mail not registered!");
            }
            else {
                inputEmail.setText("Input Email");
                inputID.setText("Input ID");
                outputID.setText(Integer.toString(foundID));
                outputEmail.setText(customersVector.get(foundID).getEmail());
                outputFirstName.setText(customersVector.get(foundID).getFirstName());
                outputLastName.setText(customersVector.get(foundID).getLastName());
            }
        });




        searchCustomerPanel.add(idSearch );
        idSearch.setLocation(25 * divW, 5 * divH);
        idSearch.setSize(3 * divW, 4 * divH);
        idSearch.setBackground(Color.darkGray);
        idSearch.setForeground(Color.white);
        idSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        idSearch.setFont(biggerFontButSmaller);
        idSearch.addActionListener(e -> {
            String gotID = inputID.getText();
            if(isNumeric(gotID)){
                int foundID = searchById(Integer.parseInt(gotID));
                if(foundID == -1){
                    inputEmail.setText("Input Email");
                    inputID.setText("Input ID");
                    outputID.setText("Given ID out of bounds!");
                    outputEmail.setText("Given ID out of bounds!");
                    outputFirstName.setText("Given ID out of bounds!");
                    outputLastName.setText("Given ID out of bounds!");
                }
                else {
                    inputEmail.setText("Input Email");
                    inputID.setText("Input ID");
                    outputID.setText(Integer.toString(foundID));
                    outputEmail.setText(customersVector.get(foundID).getEmail());
                    outputFirstName.setText(customersVector.get(foundID).getFirstName());
                    outputLastName.setText(customersVector.get(foundID).getLastName());
                }
            }
            else {
                inputEmail.setText("Input Email");
                inputID.setText("Input ID");
                outputID.setText("Given ID is not a number!");
                outputEmail.setText("Given ID is not a number!");
                outputFirstName.setText("Given ID is not a number!");
                outputLastName.setText("Given ID is not a number!");
            }

        });

        // Next we place the output fields

        searchCustomerPanel.add(outputID);
        outputID.setLocation(8 * divW, 12 * divH);
        outputID.setSize(15 * divW, 2 * divH);
        outputID.setBackground(Color.darkGray);
        outputID.setForeground(Color.white);
        outputID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        outputID.setHorizontalAlignment(JTextField.CENTER);
        outputID.setFont(biggerFontButSmaller);
        outputID.setEditable(false);


        searchCustomerPanel.add(outputEmail);
        outputEmail.setLocation(8 * divW, 16 * divH);
        outputEmail.setSize(15 * divW, 2 * divH);
        outputEmail.setBackground(Color.darkGray);
        outputEmail.setForeground(Color.white);
        outputEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        outputEmail.setHorizontalAlignment(JTextField.CENTER);
        outputEmail.setFont(biggerFontButSmaller);
        outputEmail.setEditable(false);


        searchCustomerPanel.add(outputFirstName);
        outputFirstName.setLocation(8 * divW, 20 * divH);
        outputFirstName.setSize(15 * divW, 2 * divH);
        outputFirstName.setBackground(Color.darkGray);
        outputFirstName.setForeground(Color.white);
        outputFirstName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        outputFirstName.setHorizontalAlignment(JTextField.CENTER);
        outputFirstName.setFont(biggerFontButSmaller);
        outputFirstName.setEditable(false);


        searchCustomerPanel.add(outputLastName);
        outputLastName.setLocation(8 * divW, 24 * divH);
        outputLastName.setSize(15 * divW, 2 * divH);
        outputLastName.setBackground(Color.darkGray);
        outputLastName.setForeground(Color.white);
        outputLastName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        outputLastName.setHorizontalAlignment(JTextField.CENTER);
        outputLastName.setFont(biggerFontButSmaller);
        outputLastName.setEditable(false);

        // Now we set up the register Panel : registerFirstName, registerLastName, registerEmail



        registerCustomerPanel.add(registerFirstName);
        registerFirstName.setLocation(8 * divW, 4 * divH);
        registerFirstName.setSize(15 * divW, 2 * divH);
        registerFirstName.setBackground(Color.darkGray);
        registerFirstName.setForeground(Color.white);
        registerFirstName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        registerFirstName.setHorizontalAlignment(JTextField.CENTER);
        registerFirstName.setFont(biggerFontButSmaller);


        registerCustomerPanel.add(registerLastName);
        registerLastName.setLocation(8 * divW, 8 * divH);
        registerLastName.setSize(15 * divW, 2 * divH);
        registerLastName.setBackground(Color.darkGray);
        registerLastName.setForeground(Color.white);
        registerLastName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        registerLastName.setHorizontalAlignment(JTextField.CENTER);
        registerLastName.setFont(biggerFontButSmaller);


        registerCustomerPanel.add(registerEmail);
        registerEmail.setLocation(8 * divW, 12 * divH);
        registerEmail.setSize(15 * divW, 2 * divH);
        registerEmail.setBackground(Color.darkGray);
        registerEmail.setForeground(Color.white);
        registerEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        registerEmail.setHorizontalAlignment(JTextField.CENTER);
        registerEmail.setFont(biggerFontButSmaller);





        registerCustomerPanel.add(finishCustomerRegistration);
        finishCustomerRegistration.setLocation(10 * divW, 16 * divH);
        finishCustomerRegistration.setSize(11 * divW, 4 * divH);
        finishCustomerRegistration.setBackground(Color.darkGray);
        finishCustomerRegistration.setForeground(Color.white);
        finishCustomerRegistration.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        finishCustomerRegistration.setFont(biggerFontButSmaller);
        finishCustomerRegistration.addActionListener(e -> {
            String registerEmailInput = registerEmail.getText();
            String registerLastNameInput = registerLastName.getText();
            String registerFirstNameInput = registerFirstName.getText();
            if(searchByEmail(registerEmailInput) != -1) {
                registeredID.setText("MAIL IN USE!!!");
            }
            else {
                customersVector.add(new Customer(registerLastNameInput, registerFirstNameInput, registerEmailInput));
                registeredID.setText(Integer.toString(customersVector.size() - 1));
                registerEmail.setText("Enter Email");
                registerLastName.setText("Enter Last Name");
                registerFirstName.setText("Enter First Name");
            }

        });

        registerCustomerPanel.add(registeredID);
        registeredID.setLocation(12 * divW, 22 * divH);
        registeredID.setSize(7 * divW, 4 * divH);
        registeredID.setBackground(Color.darkGray);
        registeredID.setForeground(Color.white);
        registeredID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        registeredID.setHorizontalAlignment(JTextField.CENTER);
        registeredID.setFont(biggerFontButSmaller);
        registeredID.setEditable(false);

        // (31 divW X 32 divH) => we split into 2 parts : 11 by 32 and 20 by 32
        // Grid layout



        makeReservationPanel.add(stationsPanel);
        stationsPanel.setLayout(new GridLayout( nrRows, nrCols));
        stationsPanel.setVisible(true);
        stationsPanel.setLocation(11 * divW, 0 * divH);
        stationsPanel.setSize(20 * divW, 32 * divH);
        stationsPanel.setBackground(Color.WHITE);
        stationsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));



        makeReservationPanel.add(reservationDate);
        reservationDate.setLocation(2 * divW, 4 * divH);
        reservationDate.setSize(7 * divW, 2 * divH);
        reservationDate.setBackground(Color.darkGray);
        reservationDate.setForeground(Color.white);
        reservationDate.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        reservationDate.setHorizontalAlignment(JTextField.CENTER);
        reservationDate.setFont(biggerFontButSmaller);

        makeReservationPanel.add(reservationTimeStart);
        reservationTimeStart.setLocation(2 * divW, 8 * divH);
        reservationTimeStart.setSize(7 * divW, 2 * divH);
        reservationTimeStart.setBackground(Color.darkGray);
        reservationTimeStart.setForeground(Color.white);
        reservationTimeStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        reservationTimeStart.setHorizontalAlignment(JTextField.CENTER);
        reservationTimeStart.setFont(biggerFontButSmaller);

        makeReservationPanel.add(reservationTimeEnd);
        reservationTimeEnd.setLocation(2 * divW, 12 * divH);
        reservationTimeEnd.setSize(7 * divW, 2 * divH);
        reservationTimeEnd.setBackground(Color.darkGray);
        reservationTimeEnd.setForeground(Color.white);
        reservationTimeEnd.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        reservationTimeEnd.setHorizontalAlignment(JTextField.CENTER);
        reservationTimeEnd.setFont(biggerFontButSmaller);

        makeReservationPanel.add(reservationCustomerID);
        reservationCustomerID.setLocation(2 * divW, 16 * divH);
        reservationCustomerID.setSize(7 * divW, 2 * divH);
        reservationCustomerID.setBackground(Color.darkGray);
        reservationCustomerID.setForeground(Color.white);
        reservationCustomerID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        reservationCustomerID.setHorizontalAlignment(JTextField.CENTER);
        reservationCustomerID.setFont(biggerFontButSmaller);

        makeReservationPanel.add(reservationPricePerHour);
        reservationPricePerHour.setLocation(2 * divW, 20 * divH);
        reservationPricePerHour.setSize(7 * divW, 2 * divH);
        reservationPricePerHour.setBackground(Color.darkGray);
        reservationPricePerHour.setForeground(Color.white);
        reservationPricePerHour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        reservationPricePerHour.setHorizontalAlignment(JTextField.CENTER);
        reservationPricePerHour.setFont(biggerFontButSmaller);
        reservationPricePerHour.setEditable(false);

        for(int i = 0; i < nrRows * nrCols; ++i){
            stationsArray[i] = new Station("PC", i);
        }

        for(int i = 0; i < nrRows * nrCols; ++i){
            buttonsArray[i] = new JButton((stationsArray[i].getType().concat(" ")).concat(Integer.toString(i)));
            stationsPanel.add(buttonsArray[i]);
            buttonsArray[i].setBackground(Color.darkGray);
            buttonsArray[i].setForeground(Color.white);
            buttonsArray[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            buttonsArray[i].setFont(biggerFontButSmaller);
        }

        makeReservationPanel.add(showStations);
        showStations.setLocation(2 * divW, 24 * divH);
        showStations.setSize(7 * divW, 4 * divH);
        showStations.setBackground(Color.darkGray);
        showStations.setForeground(Color.white);
        showStations.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        showStations.setFont(biggerFontButSmaller);
        showStations.addActionListener(e -> {

            String givenDate = reservationDate.getText();
            String givenStart = reservationTimeStart.getText();
            String givenEnd = reservationTimeEnd.getText();
            String givenIDString = reservationCustomerID.getText();
            if(isNumeric(givenIDString)){
                int givenID = Integer.parseInt(givenIDString);
                if(givenID <= customersVector.size()) {
                    for(int i = 0; i < nrRows * nrCols; ++i){
                        if(checkAvailability(givenDate, givenStart, givenEnd, i)) buttonsArray[i].setBackground(Color.GREEN);
                        else buttonsArray[i].setBackground(Color.RED);
                        Reservation tempRes = new Reservation(givenDate, givenStart, givenEnd, customersVector.get(givenID), stationsArray[i]);
                        int ind = i;
                        buttonsArray[i].addActionListener(e1 -> {
                            if(checkAvailability(givenDate, givenStart, givenEnd, ind)) reservationsVector.add(tempRes);
                        });
                    }
                }
            }
        });





        initializeCustomers();

        managerPanel.setVisible(false);
        frame.add(managerPanel);



        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
