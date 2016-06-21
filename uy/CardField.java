package uy;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

/**
* Implements the image of a card in  the GUI
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class CardField extends JLabel{
    private BufferedImage card_image;
    private int location_x;
    private int location_y;
    private String img_filename = "";
    private MouseAdaptForCard card_listener;
    public final static int CARDWIDTH = 100;
    public final static int CARDSTARTHEIGHT = 120;
    public static int CARDHEIGHT = CARDSTARTHEIGHT;
    private final static Dimension SIZE = new Dimension(CARDWIDTH, CARDHEIGHT);
    private Card card_logic;
    //private boolean is_in_pile = false;

    public CardField(String img_filename) {
        //the same as this.setMaximumSize(SIZE);
        setMaximumSize(SIZE);
        setMinimumSize(SIZE);
        setBounds(location_x, location_y, CARDWIDTH, CARDHEIGHT);
        this.img_filename = img_filename;
        setOpaque(false);
        //setOpaque(true);
        setBackground(Color.RED);
        //this.card_logic = card_logic;
        try {
            //System.out.println("filename is " + img_filename);
            //takes the card image from  the package graphics
            card_image = ImageIO.read(getClass().getResourceAsStream("/uy/" + img_filename));
        }
        catch (IOException e) {
            //not handled
            System.out.println("IOException");
        }
        //setVerticalAlignment(JLabel.CENTER);
        card_listener = new MouseAdaptForCard(this);
    }

    public void setCardListener (MouseAdaptForCard card_listener) {
        this.card_listener = card_listener;
    }

    public void setCardLogic(Card card_logic) {
        this.card_logic = card_logic;
    }

    public void removeMouseListeners() {
        removeMouseMotionListener(card_listener);
        removeMouseListener(card_listener);
    }


    public void drawCard(Graphics g) {
        //System.out.println(card_image + " locx " + location_x + " locy " + location_y);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g.clearRect(location_x, location_y, CARDWIDTH, CARDHEIGHT);
        g2d.drawImage(card_image, location_x, location_y, CARDWIDTH, CARDHEIGHT, null);
    }


    public void setLocation(int location_x, int location_y) {
        this.location_x = location_x;
        this.location_y = location_y;
        //System.out.println("x: " + location_x  + " y: " + location_y);
    }

     public int getXOnScreen() {
         return location_x;
     }

     public int getYOnScreen() {
         return location_y;
     }

     /**
     public void setCanBeRemovedInPile(boolean can_be_removed_in_pile) {
         this.can_be_removed_in_pile = can_be_removed_in_pile;
     }


     public boolean getCanBeRemovedInPile() {
         return can_be_removed_in_pile;
     } */

     public Dimension getPreferredSize() {
         return new Dimension(CARDWIDTH, CARDHEIGHT);
     }

     public void getCardName() {
         System.out.println("The card is " + img_filename);
     }
}
