package JavaUIandEventHandling;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {// Jframe for window and ActionListener for Event
                                                                  // handling as Event listener

    private static final long serialVersionUID = 1L;// erves as a version identifier for classes that are serialized and
                                                    // deserialized.
    JPanel[] row = new JPanel[5];
    JButton[] button = new JButton[19];
    String[] buttonString = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "/", "C", "?", "+/-",
            "=", "0" };

    int[] dimW = { 430, 70, 150, 140 };// dimension widths of the buttons,text view etc.,
    int[] dimH = { 50, 60 };// dimension heights of the buttons,text view etc.,
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);// dimension is a builtin class of awt package used to
                                                                 // give dimensions to diff ui components
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
    Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
    boolean[] function = new boolean[4];// calc logic//each value for each operation
    double[] temporary = { 0, 0 };// calc logic// each value for each operands
    JTextField display = new JTextField(25);// its a single line whereas JTextArea is multi line
    Font font = new Font("Times new Roman", Font.BOLD, 20);

    Calculator() {
        super("Calculator"); // the arg is the name(title) of the window
        // frame or its super class's methods
        setDesign();// for theme
        // If one attempt to call methods like setSize or
        // setResizable before the JFrame constructor is invoked, you don’t yet have a
        // JFrame object to operate on.
        setSize(500, 350);// it is a builtin methodset size of window and it can also be achieved by
                          // dimensions class
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5, 5);
        // frame or its super class's method
        setLayout(grid);

        for (int i = 0; i < 4; i++) {// calc logic //for 4 operations initially as false each
            function[i] = false;
        }
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
        for (int i = 0; i < 5; i++) {
            row[i] = new JPanel();// creating jpanel obj for each row in the output.rows include textarea too
        }
        row[0].setLayout(f1);
        for (int i = 1; i < 5; i++)
            row[i].setLayout(f2);

        for (int i = 0; i < 19; i++) {// create button obj for each buttons
            // jbuttons methods
            button[i] = new JButton();// create button objs for each button
            button[i].setText(buttonString[i]); // set the button strign as the text of each button ui
            button[i].setBackground(Color.white);// white is a static constant as at earlier time awt team didnot follow
                                                 // case conventions so white is in lowercase
            button[i].setFont(font);
            button[i].addActionListener(this);
        }
        // jtextarea methods
        display.setFont(font);
        display.setHorizontalAlignment(JTextField.RIGHT);// text flow is from left to right only but it is right alined
        display.setEditable(false);
        // display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);// flow
        // direction of content inside text
        // area
        display.setPreferredSize(displayDimension);

        for (int i = 0; i < 14; i++) {
            button[i].setPreferredSize(regularDimension);
        }
        for (int i = 14; i < 18; i++) {
            button[i].setPreferredSize(rColumnDimension);
        }
        button[18].setPreferredSize(zeroButDimension);

        row[0].add(display);// adding textview to first panel obj in row[]
        add(row[0]);// add that first panel obj to frame

        for (int i = 0; i < 4; i++) {
            row[1].add(button[i]); // adding button to first row arr ele(panel obj)
        }
        row[1].add(button[14]);// adding 14th button obj to first row arr ele(C)
        add(row[1]);
        for (int i = 4; i < 8; i++) {
            row[2].add(button[i]);
        }
        row[2].add(button[15]);
        add(row[2]);
        for (int i = 8; i < 12; i++) {
            row[3].add(button[i]);
        }
        row[3].add(button[16]);
        add(row[3]);
        row[4].add(button[18]);
        for (int i = 12; i < 14; i++) {
            row[4].add(button[i]);
        }
        row[4].add(button[17]);
        add(row[4]);

        setVisible(true);// makes the jframe visible on screen
    }

    public void clear() {
        try {
            display.setText("");
            for (int i = 0; i < 4; i++) {
                function[i] = false;
            }
            for (int i = 0; i < 2; i++) {
                temporary[i] = 0;
            }
        } catch (NullPointerException e) {
            System.out.println("Error");
        }
    }

    public void getSqrt() {
        try {
            String text = display.getText().trim();// gettext() and settext() are builtin
                                                   // method of JTextArea class
            if (text.isEmpty())
                return;

            double value = Double.parseDouble(text);

            if (value < 0) {
                display.setText("Square Root Error");
                return;
            }

            value = Math.sqrt(value);
            display.setText(Double.toString(value));

        } catch (NumberFormatException e) {
             display.setText(e.getClass().getSimpleName());
        }
    }

    public void getPosNeg() {
        try {
            String text = display.getText().trim();
            if (text.isEmpty())
                return;

            double value = Double.parseDouble(text);
            value = -value;
            display.setText(Double.toString(value));

        } catch (NumberFormatException e) {
             display.setText(e.getClass().getSimpleName());
        }
    }

    // private String formatNumberForDisplay(double value) {
    // String numStr = Double.toString(value);
    // // Only prepend LRM if the number is negative AND it's not NaN or infinity
    // if (value < 0 && !Double.isNaN(value) && !Double.isInfinite(value)) {// works
    // only for numbers <0 ,and not nan
    // // and not infinity
    // return "\u200E" + numStr; // When you prepend the special Unicode character
    // \u200E (Left-to-Right Mark or
    // // LRM) to a string, it forces the text following it to be displayed in
    // // left-to-right (LTR) direction, even if the environment (like your system
    // // locale or UI) is right-to-left (RTL).
    // }
    // return numStr;
    // }

    public void getResult() {
        double result = 0;
        temporary[1] = Double.parseDouble(display.getText());
        try {
            if (function[2] == true) {
                result = temporary[0] * temporary[1];
            } else if (function[3] == true) {
                result = temporary[0] / temporary[1];
            } else if (function[0] == true) {
                result = temporary[0] + temporary[1];
            } else if (function[1] == true) {
                result = temporary[0] - temporary[1];
            }
            display.setText(Double.toString(result));
            for (int i = 0; i < 4; i++) {
                function[i] = false;
            }
        } catch (NullPointerException e) {
            System.out.println("Error");
        }
    }

    public final void setDesign() {// for theme using Nimbus
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");// has to be called first
                                                                                         // before creating or showing
                                                                                         // components so setDesign() is
                                                                                         // called at start before them
        } catch (Exception e) {
            System.out.println("Nimbus Design theme Error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button[0]) {
            display.setText(display.getText() + "7");

        }
        if (ae.getSource() == button[1]) {
            // display.append("8");//append is a method of jtextarea,not jtextfield
            display.setText(display.getText() + "8");// instead jtextfield has this but it replaces entire text

        }
        if (ae.getSource() == button[2]) {
            display.setText(display.getText() + "9");

        }
        if (ae.getSource() == button[3]) {// button[3]="+"
            temporary[0] = Double.parseDouble(display.getText());
            function[0] = true;
            display.setText("");
        }
        if (ae.getSource() == button[4]) {
            display.setText(display.getText() + "4");

        }
        if (ae.getSource() == button[5]) {
            display.setText(display.getText() + "5");

        }
        if (ae.getSource() == button[6]) {
            display.setText(display.getText() + "6");

        }
        if (ae.getSource() == button[7]) {// button[3]="1"
            temporary[0] = Double.parseDouble(display.getText());
            function[1] = true;
            display.setText("");
        }
        if (ae.getSource() == button[8]) {
            display.setText(display.getText() + "1");

        }
        if (ae.getSource() == button[9]) {
            display.setText(display.getText() + "2");

        }
        if (ae.getSource() == button[10]) {
            display.setText(display.getText() + "3");

        }
        if (ae.getSource() == button[11]) {// button[3]="*"
            temporary[0] = Double.parseDouble(display.getText());
            function[2] = true;
            display.setText("");
        }
        if (ae.getSource() == button[12]) {
            display.setText(display.getText() + ".");

        }
        if (ae.getSource() == button[13]) {// button[3]="/"
            temporary[0] = Double.parseDouble(display.getText());
            function[3] = true;
            display.setText("");
        }
        if (ae.getSource() == button[14]) {
            clear();
        }
        if (ae.getSource() == button[15]) {// ? operator can have custom functionality in our code not like standard
                                           // operations like additon for operator +
            getSqrt();
        }
        if (ae.getSource() == button[16]) {// +/- toggle sign of num
            getPosNeg();
        }
        if (ae.getSource() == button[17]) {
            getResult();
        }
        if (ae.getSource() == button[18]) {
            display.setText(display.getText() + "0");

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator c = new Calculator();
        });
    }
}
