package uy;

import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardLayoutExample implements  ActionListener{

    JButton firstButton, lastButton, nextButton, previousButton;
    JPanel cardPanel;
    JLabel titleLabel, descLabel;
    String cardNames[] = new String[3];
    String cardDescription[] = new String[3];
    int cardCounter = 0;

    public JPanel createContentPane (){

        JPanel totalGUI = new JPanel();

        // We first create a JPanel to show the Buttons.
        // This is an ideal place to show how easy it is to set up a panel
        // of well spaced buttons using a BoxLayout.

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));

        previousButton = new JButton("<- Previous");
        previousButton.addActionListener(this);
        buttonPanel.add(previousButton);
        buttonPanel.add(Box.createHorizontalGlue());

        firstButton = new JButton("First");
        firstButton.addActionListener(this);
        buttonPanel.add(firstButton);
        buttonPanel.add(Box.createHorizontalGlue());

        lastButton =  new JButton("Last");
        lastButton.addActionListener(this);
        buttonPanel.add(lastButton);
        buttonPanel.add(Box.createHorizontalGlue());

        nextButton = new JButton("Next ->");
        nextButton.addActionListener(this);
        buttonPanel.add(nextButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));

        // Now we need to create three demonstration panels to show
        // the various ways we can align panels in a BoxLayout.
        // To make this a bit quicker, we've created a method called
        // createSquareJPanel that will generate the coloured panels
        // without loads of lines of code.

        // Shows the default alignment.
        JPanel align_1 = new JPanel();
        align_1.setLayout(new BoxLayout(align_1, BoxLayout.LINE_AXIS));

        // Created a red panel and a blue panel using my custom method.
        JPanel redPanel_1 = createSquareJPanel(Color.red, 50);
        JPanel bluePanel_1 = createSquareJPanel(Color.blue, 80);
        // Add the two panels to the BoxLayout panel with a space inbetween.
        align_1.add(redPanel_1);
        align_1.add(Box.createRigidArea(new Dimension(5,5)));
        align_1.add(bluePanel_1);

        // Shows the bottom alignment.
        JPanel align_2 = new JPanel();
        align_2.setLayout(new BoxLayout(align_2, BoxLayout.LINE_AXIS));

        JPanel redPanel_2 = createSquareJPanel(Color.red, 50);
        redPanel_2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        JPanel bluePanel_2 = createSquareJPanel(Color.blue, 80);

        align_2.add(redPanel_2);
        align_2.add(Box.createRigidArea(new Dimension(5,5)));
        align_2.add(bluePanel_2);

        // Shows the top alignment.
        JPanel align_3 = new JPanel();
        align_3.setLayout(new BoxLayout(align_3, BoxLayout.LINE_AXIS));

        JPanel redPanel_3 = createSquareJPanel(Color.red, 50);
        redPanel_3.setAlignmentY(Component.TOP_ALIGNMENT);
        JPanel bluePanel_3 = createSquareJPanel(Color.blue, 80);

        align_3.add(redPanel_3);
        align_3.add(Box.createRigidArea(new Dimension(5,5)));
        align_3.add(bluePanel_3);

        // This is the important bit of this application.
        // We use a JPanel with a cardPanel, and add the three panels in order.
        // Note that we have to instantiate this Panel outside of this method
        // so the ActionListener can change it on a buttons command.

        cardPanel = new JPanel(new CardLayout(150, 50));

        // To add a panel to a cardPanel Panel, we also provide a string
        // to explain the panel added.
        // To make it easier for the label on the GUI, we're going to put
        // the names into an array.

        cardNames[0] = "Component.CENTER";
        cardNames[1] = "Component.BOTTOM_ALIGNMENT";
        cardNames[2] = "Component.TOP_ALIGNMENT";

        cardDescription[0] = " - The Center of both widgets is aligned";
        cardDescription[1] = " - The bottom of the first widget is aligned with the center of the second";
        cardDescription[2] = " - The top of the first widget is aligned with the center of the second";

        cardPanel.add(align_1, cardNames[0]);
        cardPanel.add(align_2, cardNames[1]);
        cardPanel.add(align_3, cardNames[2]);

        // Now we've completed all that, we add our toolbar to the content pane
        // along with this cardPanel panel.
        // We will use a BorderLayout this time to position the toolbar above
        // the CardPanels and the titlebar below.

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(buttonPanel, BorderLayout.PAGE_START);
        bottomPanel.add(cardPanel, BorderLayout.CENTER);

        // To add a little bit extra, we'll put a label at the bottom
        // telling you what alignment we have.

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));

        titleLabel = new JLabel(cardNames[0]);
        descLabel = new JLabel(cardDescription[0]);

        titlePanel.add(titleLabel);
        titlePanel.add(descLabel);

        bottomPanel.add(titlePanel, BorderLayout.PAGE_END);

        // Finally we add that JPanel to the content pane.
        totalGUI.add(bottomPanel);
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // In this method, we create a square JPanel of a colour and set size
    // specified by the arguments.
    private JPanel createSquareJPanel(Color color, int size)
    {
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(color);
        tempPanel.setMinimumSize(new Dimension(size, size));
        tempPanel.setMaximumSize(new Dimension(size, size));
        tempPanel.setPreferredSize(new Dimension(size, size));
        return tempPanel;
    }

    // The action performed changes the JPanel on display
    // and the title/description bar at the bottom.
    public void actionPerformed(ActionEvent e) {

        // We need to get the current layout of the CardLayout panel
        // before we can change it.
        CardLayout cl = (CardLayout)(cardPanel.getLayout());

        // This section of code finds out the button that has been pressed
        // and changes the card displayed on the cardLayout.
        if(e.getSource() == firstButton)
        {
            cl.first(cardPanel);
            cardCounter = 0;
        }
        else if(e.getSource() == lastButton)
        {
            cl.last(cardPanel);
            cardCounter = 2;
        }
        else if(e.getSource() == nextButton)
        {
            cl.next(cardPanel);
            if(cardCounter < 2)
            {
                cardCounter++;
            }
            else
            {
                cardCounter = 0;
            }
        }
        else if(e.getSource() == previousButton)
        {
            cl.previous(cardPanel);
            if(cardCounter > 0)
            {
                cardCounter--;
            }
            else
            {
                cardCounter = 2;
            }
        }

        titleLabel.setText(cardNames[cardCounter]);
        descLabel.setText(cardDescription[cardCounter]);
    }


    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] CardLayout Demonstration [=]");

        CardLayoutExample demo = new CardLayoutExample();
        frame.setContentPane(demo.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
