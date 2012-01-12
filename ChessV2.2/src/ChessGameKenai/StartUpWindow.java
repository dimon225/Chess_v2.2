/*
 * Description: Class StartUpWindow.java
 * Author: Dimtri Pankov
 * Date: 23-Jan-2011
 * Version: 1.0
 */
package ChessGameKenai;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * The StartUpWindow class is a JWindow object that is used to start up the chess game
 * The Window is Runnable object that only appears at the beginning of the game
 * @author Dimitri Pankov
 * @see JWindow
 * @see Runnable
 * @version 1.0
 */
public class StartUpWindow extends JWindow implements Runnable {

    private Container c;
    private JPanel mainPanel;
    private Progress progress;
    private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private int x = d.width / 2;
    private int y = d.height / 2;

    /**
     * Empty constructor of the class
     * needs all the components to represent itself graphically on the screen
     */
    public StartUpWindow() {
        c = this.getContentPane();

        progress = new Progress();
        mainPanel = new JPanel() {

            /**
             * The method painComponent of blackCapturedPiecesPanel
             * is used here to paint the JPanel as we want
             * @param g Graphics object used to paint this object
             */
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int w = mainPanel.getWidth();
                int h = mainPanel.getHeight();

                //OVERRRIDE THE PAINT COMPONENT AS YOU WANT
                URL url = mainPanel.getClass().getResource("Icons/board.jpg");
                Toolkit toolkit = this.getToolkit();
                Image image = toolkit.getImage(url);
                g.drawImage(image, 0, 0, w, h, mainPanel);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Verdana", Font.PLAIN, 20));
                g.drawString("All Rights Reserved @Dimitri", 140, 50);
            }
        };

        //START THIS OBJECT IN THE THREAD
        new Thread(this).start();

        //ADD ALL COMPONENTS TO THE CONTAINER
        c.add(mainPanel);
        c.add(progress, BorderLayout.SOUTH);

        //INITIALIZES JFRAMES' PROPERTIES
        this.setSize(600, 455);
        this.setLocation((int) (x - this.getWidth() / 1.5), (int) (y - this.getHeight() / 1.5));
        this.setVisible(true);
    }

    /**
     * The run method of the Runnable object 
     * It is executed when the Thread on this object is started
     */
    public void run() {
        int counter = 0;

        //START THE THREAD FOR JPROGRESS BAR
        new Thread(progress).start();

        //TRY TO SLEEP ONE SECOND UNTILL THE COUNTER REACHES 7
        try {
            while (counter != 10) {
                Thread.sleep(400);
                counter++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.toString(), "Error Message", JOptionPane.ERROR_MESSAGE);

        } finally {

            //DISPOSE OF THE JWINDOW
            this.dispose();

            //START A NEW CHESS GAME IN THE THREAD
            new Thread(new Runnable() {

                public void run() {
                    new Start_Game();
                }
            }).start();
        }
    }

    /**
     * The Progress class is a JProgressBar object that is Runnable
     * whenever the Thread is started on the object it will execute its run method
     * @author Dimitri Pankov
     * @see JProgressBar
     * @see Runnable
     * @version 1.0
     */
    private class Progress extends JProgressBar implements Runnable {

        /**
         * Empty constructor of the class has all needed components
         * to represent itself graphically on the screen
         */
        public Progress() {
            this.setValue(0);
            this.setForeground(new Color(47, 79, 79));
            this.setStringPainted(true);
            this.setPreferredSize(new Dimension(600, 25));
        }

        /**
         * The run method of the class is executed
         * whenever the Thread on this object is started
         */
        public void run() {
            int counter = 0;
            try {

                //EXECUTE THE WHILE LOOP UNTILL COUNTER REACHES 14
                while (counter != 20) {
                    Thread.sleep(200);
                    this.setValue((int) (counter * (5.5)));
                    counter++;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.toString(), "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
