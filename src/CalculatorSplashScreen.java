/********************************************************************************************************************************************************
 File name	: Splash Screen
 Date		: March 2020
 Purpose		: loads on start with progress bar
 ********************************************************************************************************************************************************/

import javax.swing.*;
import java.awt.*;

public class CalculatorSplashScreen extends JWindow{
    private static final long serialVersionUID = 6248477390124803341L;
    private int time;
    private JLabel current;
    private JProgressBar progressBar;
    private static final int PB_MINIMUM = 0; // min progressBar
    private static final int PB_MAXIMUM = 100; // max progresBar

    public CalculatorSplashScreen ( int time){ // constructor with duration parameter of splash screen
        this.time = time;
    }

    public void showSplashWindow() {
        //create content pane
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.GRAY);
        /*Progress Bar*/
        progressBar = new JProgressBar(); // creates the progress bar
        progressBar.setMinimum(PB_MINIMUM);
        progressBar.setMaximum(PB_MAXIMUM);
        progressBar.setString("Loading Calculator, Please wait..");
        progressBar.setStringPainted(true); // set the string on the bar
        progressBar.setForeground(Color.RED); // set the color to orange

        int width =  510;
        int height = 310;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        JLabel label = new JLabel(new ImageIcon(getClass().getResource("vikings1.jpg")));
        JLabel demo = new JLabel("Demo", JLabel.CENTER);
        JLabel current = new JLabel();

        demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        content.add(label, BorderLayout.CENTER);
        content.add(demo, BorderLayout.SOUTH);
        content.add(current, BorderLayout.NORTH);
        content.add(progressBar, BorderLayout.NORTH); // adds progress bar to the contents
        Color customColor = new Color(44, 197, 211);
        content.setBorder(BorderFactory.createLineBorder(customColor, 10));
        setContentPane(content);
        setVisible(true);

        for (int i = PB_MINIMUM; i <= PB_MAXIMUM; i++) { // loops for the progress bar percentage%
            final int percent=i;
            try {
                progressBar.setValue(percent);
                Thread.sleep(50);
            } catch (InterruptedException e) {;}
        } // end for loop
        dispose();
    } // end of showSplashWindow
} // end of CalculatorSplashScreen class