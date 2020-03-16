/********************************************************************************************************************************************************
 File name	: CalculatorModel
 Author		: Hasan Skaiky
 Date		: March 2020
 Purpose	: Main method that executes the program , contains splash screen and progress bar
 ********************************************************************************************************************************************************/


import javax.swing.*;
import java.awt.*;


public class Calculator {
    private final static String Frame_Title ="Calculator";

    public static void main(String[] args) {

        int durationTime = 5000;
        CalculatorSplashScreen splashWindow = new CalculatorSplashScreen(durationTime);

        if(args.length == 1){
            try{
                durationTime = Integer.parseInt(args[0]);
            }catch (NumberFormatException mfe){
                System.out.println("Wrong command line argument: must be an integer number");
                System.out.println("The default duration 5000 milliseconds will be used");
            }
        }
        splashWindow.showSplashWindow();

        EventQueue.invokeLater(new Runnable(){
            @Override public void run(){
                JFrame frame = new JFrame(Frame_Title);
                JPanel pane = new CalculatorViewController();
                frame.add(pane);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(300,460));
                frame.pack();
                frame.setResizable(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    } // end main
}// end class

