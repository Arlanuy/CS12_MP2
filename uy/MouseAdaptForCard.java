package uy;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Container;
import javax.swing.*;

/**
* Implements the adapter needed for a card to move
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class MouseAdaptForCard extends MouseAdapter {
    private boolean pressed_card = false;
    private boolean entered_card = false;
    private CardField card; //= new CardField("3-K.png");
    private Container original_parent;
    private int original_x_position;
    private int original_y_position;
    public final static int CONTAINERGAP = 600;
    //for the card to directly be attached to the mouse because the menu bar is part of the whole screen and so its height must be accounted
    public static final int Y_ADJUST = 50;//80;
    public static final int X_ADJUST = 100;

    public MouseAdaptForCard(CardField card) {
        card.addMouseListener(this);
        card.addMouseMotionListener(this);
        this.card = card;
    }
    /**
    @Override
    public void mouseClicked(MouseEvent event) {
        saySomething("Mouse clicked", event);
        SolitaireGUI.insertBackCard();
    } */

    @Override
    // Store the mouse location when it is pressed
    public void mousePressed(MouseEvent event) {
        //if ((event.getX() <= (location_x  + width)) && (event.getX() >= location_x) && (event.getY() <= (location_y  + height)) && (event.getY() >= location_y)) {
        pressed_card = true;
        SolitaireGUI.setSelectedCard(card);
        original_x_position = card.getXOnScreen();
        original_y_position = card.getYOnScreen();
        System.out.println("CLOSER " + original_x_position + " " + original_y_position + " CLICKED ON " + event.getXOnScreen() + " " + (event.getYOnScreen() - Y_ADJUST - 50));
        card.getCardName();
        if (SolitaireGUI.getMouseInPilesField() == true) {
            //dispatchEvent(event);
            SolitaireGUI.createDragPileCards(event.getXOnScreen(), event.getYOnScreen() - Y_ADJUST + 20);
            //SolitaireGUI.setCardInPilesLayer(event.getXOnScreen(), event.getYOnScreen());
            //System.out.println("clicked at " + original_x_position + " " + (original_y_position));
            //System.out.println("Entered creation");
            //card.getCardName();
            //System.out.println(" at location " + event.getXOnScreen() + " " + (event.getYOnScreen()));
        }

        else {
            //System.out.println("PRESSED at " + event.getXOnScreen() + " " + event.getYOnScreen());
        }
        /**
        Container parent = card.getParent();
        parent.remove(card);
        parent.validate();
        parent.repaint(); */
        //saySomething("Mouse pressed", event);
    }


    @Override
    public void mouseReleased(MouseEvent event) {
        pressed_card = false;
        //saySomething("Mouse released", event);
        //System.out.println("x " + event.getXOnScreen() + "y " + event.getYOnScreen());
        if (SolitaireGUI.validatePosition(original_x_position, original_y_position, event.getXOnScreen(), event.getYOnScreen() - Y_ADJUST) == true) {
            /**
            original_parent.remove(card);
            original_parent.validate();
            original_parent.repaint();
            card.setLocation(parent.getXOnScreen() + CONTAINERGAP, parent.getYOnScreen() - Y_ADJUST);
            parent.add(card);
            parent.validate();
            parent.repaint(); */
            SolitaireGUI.redisplay();
            System.out.println("Nailagay na siya");
        }

        else {
            System.out.println("Naibalik na siya");
            card.setLocation(original_x_position, original_y_position);
            SolitaireGUI.redisplay();
            //original_parent.add(card);
            //original_parent.validate();
            //original_parent.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        entered_card = true;
        //System.out.println("Yes ");
        //card.getCardName();
        //saySomething("Mouse entered", event);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        entered_card = false;
        //saySomething("Mouse exited", event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
            //card.setLocation(event.getX(), event.getY());
            card.setLocation(event.getXOnScreen(), event.getYOnScreen() - Y_ADJUST);
            if (SolitaireGUI.getMouseInPilesField() == true) {
                SolitaireGUI.dragPileCards(event.getXOnScreen(), event.getYOnScreen() - Y_ADJUST);
                //System.out.println("rudolph " + card.getParent());
            }
            SolitaireGUI.redisplay();
            //saySomething("Mouse dragged gogo arlan", event);
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        + "\n");
    }
}
