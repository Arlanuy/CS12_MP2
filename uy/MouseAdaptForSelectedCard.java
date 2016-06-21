package uy;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Point;

public class MouseAdaptForSelectedCard extends MouseAdapter {
    private boolean pressed_card = false;
    private boolean entered_card = false;
    private CardField card;
    public final static int CONTAINERGAP = 600;

    public MouseAdaptForSelectedCard(CardField card) {
        card.addMouseListener(this);
        card.addMouseMotionListener(this);
        this.card = card;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        //saySomething("Mouse moved", event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        pressed_card = false;
        //saySomething("Mouse released", event);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        entered_card = true;
        //saySomething("Mouse entered", event);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        entered_card = false;
        //saySomething("Mouse exited", event);
    }

    @Override
    // Store the mouse location when it is pressed
    public void mousePressed(MouseEvent event) {
        //if ((event.getX() <= (location_x  + width)) && (event.getX() >= location_x) && (event.getY() <= (location_y  + height)) && (event.getY() >= location_y)) {
        if (entered_card == true) {
            pressed_card = true;
            System.out.println("Yes");
        }

        else {
            System.out.println("Nope");
        }
        //saySomething("Mouse pressed", event);
    }
    /**
    public boolean contains(int mouse_x, int mouse_y) {
        if ()
    } */

    @Override
    public void mouseDragged(MouseEvent event) {
        if (pressed_card == true) {
            card.setLocation(event.getX(), event.getY());
            //card.setLocation(new Point(event.getX() + CONTAINERGAP, event.getY() + 150));
            SolitaireGUI.redisplay();
            //saySomething("Mouse dragged gogo arlan", event);
        }
    }
    /**
    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        + "\n");
    } */
}
