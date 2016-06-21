package uy;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Container;
import javax.swing.*;

public class MouseAdaptForCardInMainDrawPile extends MouseAdapter {
    private boolean pressed_card = false;
    private boolean entered_card = false;
    private CardField card;
    private Container original_parent;
    private int original_x_position;
    private int original_y_position;
    public static final int Y_ADJUST = 100; //for the card to directly be attached to the mouse

    public MouseAdaptForCardInMainDrawPile(CardField card) {
        card.addMouseListener(this);
        card.addMouseMotionListener(this);
        this.card = card;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        saySomething("Mouse moved", event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        pressed_card = false;
        //saySomething("Mouse released", event);
        /**
        Container parent = card.getParent();
        parent.add(card);
        if (card.validatePosition() == true) {
            card.setLocation(parent.getXOnScreen, parent.getYOnScreen - Y_ADJUST);
        }

        else {
            card.setLocation(original_x_position, original_y_position);
        } */
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
        //int pressed_count = 0;
        //if ((event.getX() <= (location_x  + width)) && (event.getX() >= location_x) && (event.getY() <= (location_y  + height)) && (event.getY() >= location_y)) {
        pressed_card = true;
        //original_parent = event.getParent();
        original_x_position = card.getXOnScreen();
        original_y_position = card.getYOnScreen() - Y_ADJUST;
        /**
        Container parent = card.getParent();
        parent.remove(card);
        parent.validate();
        parent.repaint(); */
        //System.out.println("Nope");

        //saySomething("Mouse pressed", event);
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        + "\n");
    }
}
