/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbot.code.retweetlisteners;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;
import twitterbot.example.tweet.RetweetMethods;

/**
 *
 * @author Joshua Powell
 * I give permission for you to use this source code for whatever purposes as long as I am credited with creating it inside
 * the code.
 */
public class RetweetListener extends JFrame
{
    //Declaring Class Level Objects
    JBackgroundPanel mainPanel = new JBackgroundPanel();
    private static final JButton startListener = new JButton("Start Listening For Tweets");
    private static final JButton stopListener = new JButton("Stop Listening For Tweets");
    
    static JFrame frame = new JFrame();
    
    //Constructor Built With Swing Components
    public RetweetListener()
    {
        frame.setTitle("G & E Golf Cars Ebay Competitor Tracker");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BuildMainPanel();

        frame.add(mainPanel);

        frame.setLocation(700,
                200);

        frame.setSize(500, 400);

        frame.setVisible(true);
    }
    
    //Method To Build The Main Panel
    private void BuildMainPanel()
    {
        startListener.addActionListener(new JButtonListener());
        stopListener.addActionListener(new JButtonListener());
        
        mainPanel.add(startListener);
        mainPanel.add(stopListener);
    }
    
    // I used the Carolina Panthers twitter page as a page to retweet in this method and you can change it to whatever 
    // you want by following the instructions in the method.
    private static void RetweetPanthers()
    {
        //Enter a page URL of your choice to replace the Carolina Panthers twitter page URL.
        String generate_URL = "https://twitter.com/Panthers";
        String inputLine;
        try
        {
            URL data = new URL(generate_URL);
            /**
             * Proxy code start If you are working behind firewall uncomment
             * below lines. Set your proxy server
             */

            /* Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.202", 8080)); */
            /* HttpURLConnection con = (HttpURLConnection) data.openConnection(proxy); */
            /* Proxy code end */
            /* Open connection */
            /* comment below line in case of Proxy */
            URLConnection con = (URLConnection) data.openConnection();
            /* Read webpage content */
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            /* Read line by line */
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            in.mark(1000000000);
            while ((inputLine = in.readLine()) != null)
            {
                try
                    {
                        // Replace href=\"/Panthers/status/ with href=\"/(the url ending after twitter.com/)/status/
                        stringBuilder.append(inputLine.substring(inputLine.indexOf("href=\"/Panthers/status/")).replace("href=\"/Panthers/status/", "").replace("\"", "").trim() + ",");
                    } catch (Exception ex)
                    {

                    }
            }

            Object[] linksObjectArray = stringBuilder.toString().trim().split(",");
            
            count = 0;
            while (count < 4)
            {
            RetweetMethods.RetweetTweet(String.valueOf(linksObjectArray[count]));
            count++;
            }
            
            in.close();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR!", 1);
        }
    }
    
    // I used the Charlotte Hornets twitter page as a page to retweet in this method and you can change it to whatever 
    // you want by following the instructions in the method.
    private static void RetweetHornets()
    {
        //Enter a page URL of your choice to replace the Charlotte Hornets twitter page URL.
        String generate_URL = "https://twitter.com/hornets";
        String inputLine;
        try
        {
            URL data = new URL(generate_URL);
            /**
             * Proxy code start If you are working behind firewall uncomment
             * below lines. Set your proxy server
             */

            /* Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.202", 8080)); */
            /* HttpURLConnection con = (HttpURLConnection) data.openConnection(proxy); */
            /* Proxy code end */
            /* Open connection */
            /* comment below line in case of Proxy */
            URLConnection con = (URLConnection) data.openConnection();
            /* Read webpage content */
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            /* Read line by line */
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            in.mark(1000000000);
            while ((inputLine = in.readLine()) != null)
            {
                try
                    {
                        // Replace href=\"/hornets/status/ with href=\"/(the url ending after twitter.com/)/status/
                        stringBuilder.append(inputLine.substring(inputLine.indexOf("href=\"/hornets/status/")).replace("href=\"/hornets/status/", "").replace("\"", "").trim() + ",");
                    } catch (Exception ex)
                    {

                    }
            }

            Object[] linksObjectArray = stringBuilder.toString().trim().split(",");
            
            count = 0;
            while (count < 4)
            {
            RetweetMethods.RetweetTweet(String.valueOf(linksObjectArray[count]));
            count++;
            }
            
            in.close();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR!", 1);
        }
    }

    // ActionListener for the JButtons
    private static class JButtonListener implements ActionListener
    {
        // Declaring a Timer object
        private static Timer timer = new Timer();
        public JButtonListener()
        {
        
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Using if statements to determine which button was clicked.
            if (startListener == e.getSource())
            {
                //Declaring a new timer object each time the startListener JButton is clicked to ensure a new instance of the 
                // object is created and no issues with having an already cancelled timer object occurs.
                timer = new Timer();
                
                // Scheduling the timer object to run the RunListener method every 5 seconds.
                timer.schedule(new RunListener(), 0, 5000);
            }
            
            if (stopListener == e.getSource())
            {
                // Cancelling the timer object and purging all built up cancelled timer objects whenever the stopListener JButton
                // is clicked.
                timer.cancel();
                timer.purge();
            }
        }
    }

    // Timer task class that runs at the user specified intervals.
    private static class RunListener extends TimerTask {
    @Override
    public void run() {
        //Replace methods with the names of the methods you renamed.
       RetweetPanthers();
       RetweetHornets();
    }
 }
    
    //This Class Extends The JPanel Class And Adds A Background To The JPanel So The User Can Call This Class And Have
    //A Background For The JPanel Already.
    public class JBackgroundPanel extends JPanel
    {

        private BufferedImage img;
        
        public JBackgroundPanel()
        {
            // load the background image
            try
            {
                img = ImageIO.read(new File("AbstractBackgroundOne.PNG"));
            } catch (IOException e)
            {
            }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // paint the background image and scale it to fill the entire space
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
