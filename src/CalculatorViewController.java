/********************************************************************************************************************************************************
 File name	: CalculatorViewController
 Date		: March 2020
 Purpose		: Calculator GUI interface build with multiple different components, works in 4 different modes ( floats with different format-scientific
 and integer mode)
 Class list	: Controller inner class, that is responsible for the action performed for each button and actionCommand for the GUI components
 ********************************************************************************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class CalculatorViewController extends JPanel{

    private JTextField display1; // calc display1 field reference
    private JTextField display2; //	calc display2 field reference
    private JLabel error;		//  mode/error display label reference
    private JButton dotButton;  //	dotButton reference
    private String str = "";	//  userInput

    CalculatorModel calc = new CalculatorModel(); // CalculatorModel reference
    Controller controller = new Controller(); // controller reference (actionCommand)

    public CalculatorViewController(){  // CalculatorViewController constructor

        setLayout( new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(5,5 , 5, 5, Color.BLACK)); //set layout with black border and dimension gaps
        JPanel mainPanel = new JPanel(new BorderLayout()); // construct main panel
        JPanel textFieldPanel = new JPanel(new BorderLayout()); // textField panel
        JPanel radioPanel = new JPanel(new FlowLayout(1,0,1)); // radio panel
        JPanel addOnPanel = new JPanel(new FlowLayout());  // panel for radio Buttons
        JPanel topPanel = new JPanel(new BorderLayout()); // panel for textfeilds

        add(mainPanel); // add the panel to the container
        mainPanel.setBackground(Color.BLACK); //setBackground
        topPanel.setBackground(Color.BLACK); // setBackground for NorthPanel

        display1 = new JTextField(16); // display1 with 16 columns
        display2 = new JTextField("0.0",16); // display2 displays 0.0 at start mode float
        display1.setPreferredSize(new Dimension(16,30)); // display1 with 16 columns and 30 rows
        display1.setHorizontalAlignment((int) display1.RIGHT); // set alignment for display1
        display2.setPreferredSize(new Dimension(16,30));//display2 with 16 columns and 30 rows
        display1.setEditable(false); // display1 is not editable
        display1.setBackground(Color.white); // set background
        display1.setBorder(null); // remove border for display1
        display2.setEditable(false); // display2 is not editable
        display2.setBackground(Color.white); // set background for display2
        display2.setBorder(null);//remove border for display1
        display2.setHorizontalAlignment((int) display2.RIGHT);// set alignment for display1
        display2.setVisible(true);

        JPanel textFieldPanel2 = new JPanel(new BorderLayout()); //create panel for textfield
        textFieldPanel2.setBackground(Color.BLACK);// set background for panel
        textFieldPanel2.add(display1, BorderLayout.NORTH); // add display1 to text fieldpanel
        textFieldPanel2.add(display2, BorderLayout.SOUTH); // add display2 to textfield panel
        Font myFont = new Font("ArialBlack",Font.BOLD,18); // create new font
        error = new JLabel("F", SwingConstants.CENTER); // create new JLabel for the modes
        error.setFont(myFont); // add font to the label
        error.setPreferredSize(new Dimension(35, 55)); // set the size of label
        error.setBorder(BorderFactory.createMatteBorder(0,1 , 0, 1, Color.BLACK)); // set border to label
        error.setOpaque(true); // make label transparent
        error.setBackground(Color.YELLOW); // set the background for the label

        String unicode ="\u21B2"; // used for backSpace symbol
        JButton backSpace = new JButton(unicode); // add the symbol to the backspace button
        backSpace.setToolTipText("Alt-B (Backspace)"); // displays when hover over
        backSpace.setMnemonic(KeyEvent.VK_B); // used for keyboard input
        backSpace.setFont(myFont); // adds the font to the backspace label
        backSpace.setBackground(Color.yellow);
        backSpace.setActionCommand("\u21B2"); // backspace action command
        backSpace.addActionListener(controller); // add it to the controller class that holds the action performed
        backSpace.setPreferredSize(new Dimension(35, 55));
        backSpace.setBorder(BorderFactory.createMatteBorder(0,1 , 0, 1, Color.BLACK));
        backSpace.setOpaque(true); // set visibility (transparentcy)

        textFieldPanel.add(error, BorderLayout.WEST); // add the label to the textfield panel
        textFieldPanel.add(backSpace, BorderLayout.EAST); // add the backspace to the text field panel
        textFieldPanel.add(textFieldPanel2); // add text field panel2 to the textfield panel

        topPanel.add(textFieldPanel, BorderLayout.NORTH); // add the text field panel to the North

        radioPanel.setBackground(Color.BLACK); // set radio panel background
        JRadioButton zero = new JRadioButton(".0"); // create radio button for .0 mode
        JRadioButton doubleZero = new JRadioButton(".00",true);// create radio button for .00 mode
        JRadioButton sciButton = new JRadioButton("Sci");// create radio button for sci mode
        JButton invisible = new JButton(); // create radio button for space
        ButtonGroup radioGroup = new ButtonGroup();// add the buttons to a group
        invisible.setOpaque(false); // set transparentcy to false
        invisible.setBackground(Color.BLACK); // set background for buttons
        invisible.setVisible(false); // make it invisible , only used for spacing

        sciButton.setBackground(Color.YELLOW); // set background for sci button
        doubleZero.setBackground(Color.YELLOW);// set background for .00 button
        zero.setBackground(Color.YELLOW);// set background for 0 button

        /* add action listener to radio buttons */
        sciButton.addActionListener(controller);
        sciButton.setActionCommand("Sci");
        doubleZero.setActionCommand(".00");
        doubleZero.addActionListener(controller);
        zero.setActionCommand(".0");
        zero.addActionListener(controller);

        JCheckBox checkBox = new JCheckBox("Int"); // create checkbox for Integer mode

        radioGroup.add(checkBox); // add the checkbox to radio group buttons
        checkBox.setBackground(Color.green); // sets background for checkbox
        checkBox.setActionCommand("Int");
        checkBox.addActionListener(controller); // add action command to checkbox

        /* add radio buttons and checkbox to panel*/
        radioPanel.add(Box.createHorizontalStrut(40));
        radioPanel.add(invisible);
        addOnPanel.add(checkBox);
        radioPanel.add(zero);
        radioGroup.add(zero);
        radioPanel.add(doubleZero);
        radioGroup.add(doubleZero);
        radioPanel.add(sciButton);
        radioGroup.add(sciButton);

        addOnPanel.setBackground(Color.black); // set background for panel
        addOnPanel.add(radioPanel);
        topPanel.add(addOnPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);// add top panel to the main panel

        JPanel keyPad3 = new JPanel(); // create new JPanel for keypad
        keyPad3.setLayout(new GridLayout(4,4,2,2)); // gridLayout to make the size even
        keyPad3.setBackground(Color.white); // set background for panel
        String plusMinus = "\u00B1"; // unicode used for plusMinus button
        String[] elements = new String []{"7","8","9","/","4","5","6","*","1","2","3","-","0",".",plusMinus,"+"}; // array to display on buttons

        for (int i = 0 ;i <= 15 ; i++){ // loops through array and create buttons
            if (i == 14){
                keyPad3.add(createButton(elements[i],elements[i],Color.black,Color.pink, controller)); // create plusMinus button

            } else if (i==13){
                dotButton = createButton(elements[i],elements[i],Color.black,Color.BLUE, controller); // create dotBotton
                keyPad3.add(dotButton);

            }
            else if (i == 3 || i == 7 || i == 11 || i == 15 ){
                keyPad3.add(createButton(elements[i],elements[i],Color.black,Color.CYAN, controller)); // create arithmetic buttons

            } else {
                keyPad3.add(createButton(elements[i],elements[i],Color.black,Color.BLUE, controller)); // create all other keypad buttons
            }
        }

        JPanel keyPad2 = new JPanel(new GridLayout(0,1,2,2)); // new panel for Clear and equal button
        keyPad2.add(createButton("C","C",Color.BLACK, Color.red,controller));
        keyPad2.setFont(myFont);
        keyPad2.add(createButton("=","=",Color.BLACK,Color.magenta,controller));

        JPanel keyPad = new JPanel(new BorderLayout());
        keyPad.add(keyPad2,BorderLayout.EAST);
        keyPad.add(keyPad3,BorderLayout.CENTER);

        mainPanel.add(keyPad, BorderLayout.CENTER);

    } //end of CalculatorViewController()

    private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler){ // method to create buttons
        JButton button = new JButton(text);
        button.setForeground(fg);
        button.setBackground(bg);

        if (ac != null){
            button.setActionCommand(ac);
        }

        button.setFont(new Font( button.getFont().getName(),  button.getFont().getStyle(), 20)); //resize the font to size 20
        button.addActionListener(handler);

        if ("=".equals(text)){
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,0), text);
        } else if ("+".equals(text)){
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS,0), text);
        }else if ("-".equals(text)){
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,0), text);
        } else if ("*".equals(text)){
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY,0), text);
        } else if ("/".equals(text)){
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE,0),text);
        } else {
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(text),ac);
        }

        button.getActionMap().put(text, (Action) handler);
        return button;
    }

    public class Controller extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            CalculatorViewController viewController = new CalculatorViewController(); // reference to CalculatorViewController class
            String actionCommand = e.getActionCommand();

            int number = -1;

            try {  // checks if number is > -1 and < 10
                number = Integer.parseInt (actionCommand);

            } catch (NumberFormatException e1) {
            }


            if (number >= 0 && calc.getErrorState()== false ) {
                display2.setText("");
                str += actionCommand; // adds the string to actionCommand to have more than one digit numbers
                display2.setText(str); // display the string
            }

            if (".0".equals(actionCommand)&& !calc.getErrorState()){ // check for float .0 mode
                if (calc.getMode().equals("Int")){
                    str = "";
                    display2.setText("0.0");
                }
                error.setText("F");
                calc.setMode(".0");
                error.setBackground(Color.YELLOW);
                dotButton.setEnabled(true);
                dotButton.setBackground(Color.BLUE);

            } else if (".00".equals(actionCommand)&& !calc.getErrorState()){ // checks for float mode .00
                if (calc.getMode().equals("Int")){ // if Int is selected
                    str = "";
                    display2.setText("0.0");
                }
                error.setText("F");
                calc.setMode(".00");
                error.setBackground(Color.YELLOW);
                dotButton.setEnabled(true);
                dotButton.setBackground(Color.BLUE);

            } else if ("Int".equals(actionCommand)&& !calc.getErrorState()){ // checks for Integer mode
                str = "";
                display2.setText("0");
                calc.setMode("Int");
                error.setText("I");
                error.setBackground(Color.green);
                dotButton.setEnabled(false); // disable dotButton
                dotButton.setBackground(new Color(178,156,250));

            } else if ("Sci".equals(actionCommand)&& !calc.getErrorState()){ // checks for scientific mode
                if (calc.getMode().equals("Int")){ // if mode changes to Int
                    str = "";
                    display2.setText("0.0");
                }
                calc.setMode(actionCommand);
                error.setText("F");
                error.setBackground(Color.YELLOW);
                dotButton.setEnabled(true); // enable dotButton
                dotButton.setBackground(Color.BLUE);
            }

            if ("+".equals(actionCommand) && !calc.getErrorState()){ // check if addition
                display1.setText(actionCommand);
                calc.setArithmeticOp("+");

                if(!str.isEmpty()){ // checks if string is not empty
                    calc.setOperand1(str); // sets it to both operands
                    calc.setOperand2(str);
                }
                display1.setText(calc.getOperand1()+ "+");
                str = "";

            } else if ("-".equals(actionCommand)&& !calc.getErrorState()){ // checks for subtraction
                display1.setText(actionCommand);
                calc.setArithmeticOp("-");
                if(!str.isEmpty()){
                    calc.setOperand1(str);
                    calc.setOperand2(str);
                }
                display1.setText(calc.getOperand1()+ "-");
                str = "";
            } else if ("*".equals(actionCommand)&& !calc.getErrorState()){ // checks for multiplication
                display1.setText(actionCommand);
                calc.setArithmeticOp("*");
                if(!str.isEmpty()){
                    calc.setOperand1(str);
                    calc.setOperand2(str);
                }
                display1.setText(calc.getOperand1()+ "*");
                str = "";

            } else if ("/".equals(actionCommand) && !calc.getErrorState()){ // checks for division
                display1.setText(actionCommand);
                calc.setArithmeticOp("/");
                if(!str.isEmpty()){
                    calc.setOperand1(str);
                    calc.setOperand2(str);
                }
                display1.setText(calc.getOperand1()+ "/");
                str = "";

            } else if ("=".equals(actionCommand)&& !calc.getErrorState()){ // get the total for arithmetic oeration
                if ( calc.getTotal().equals("")){ // if only = button is clicked , do nothing
                    str = "";
                    return;
                }

                if (calc.getTotal().equals("/") && str.equals("0")){ // if any number is divided by zero , generate error
                    if (calc.getOperand1().equals("0")){
                        display2.setText("Result is undefined");
                    } else {
                        display2.setText("cannot divide by zero");
                    }
                    error.setText("E");
                    error.setBackground(Color.RED);
                    calc.setErrorState(true);

                } else {
                    if (!str.isEmpty()){
                        calc.setOperand2(str);
                    }
                    display1.setText("");
                    display2.setText((calc.precesion()));
                    calc.setOperand1(calc.precesion());
                    str ="";
                }

            } else if ("C".equals(actionCommand)){ // calls reset method

                if (calc.getMode().equals("Int")){
                    calc.reset();
                    display1.setText("");
                    display2.setText("0");
                    str = "";
                } else {
                    calc.reset();
                    display1.setText("");
                    display2.setText("0");
                    str = "";
                    error.setText("F");
                    error.setBackground(Color.YELLOW);
                    calc.setErrorState(false);
                }
            } else if ("\u21B2".equals(actionCommand)&& !calc.getErrorState()){ /*backSpace*/
                StringBuilder strB = new StringBuilder(str);

                if (str.length() > 0) {

                    if(strB.toString().length()== 1 || strB.toString().length()== 2 && display2.getText().contains("-")){ // condition for the last digit if it was < 0
                        display2.setText("0");
                        str = "";
                    }
                    else {
                        strB.deleteCharAt(str.length() - 1); // delete chars when backSpace is pressed
                        str = strB.toString();
                        display2.setText(str);
                    }
                }

            } else if (".".equals(actionCommand)&& !calc.getErrorState()){ // checks for . button
                if (str.contains(".")){
                    return;
                }

                str += actionCommand;
                display2.setText(str);

            } else if ("\u00B1".equals(actionCommand)&& !calc.getErrorState()){ /*plusMinus*/
                if (str.length()==0){
                    display2.setText("0");
                    display1.setText("negate(0)");
                }

                if (str.length()> 0){
                    StringBuilder strB = new StringBuilder(str);
                    if (str.contains("-")){
                        strB.deleteCharAt(0);
                        str = strB.toString();
                        display2.setText(str);
                    } else {
                        str = "-" + str;
                        display2.setText(str);
                    }
                }
            }
        } // end of actionPerformed
    } // end of Controller Class
}// end of  CalculatorViewController Class