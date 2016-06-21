package uy;
import java.awt.event.*;
import javax.swing.*;

public class MouseAdaptForGameField extends MouseAdapter {
    //private boolean pressed_card = false;
    //private boolean entered_card = false;
    //private CardField card;
    protected JPanel game_field;

    public MouseAdaptForGameField(JPanel game_field) {
        this.game_field = game_field;
        game_field.addMouseListener(this);
        game_field.addMouseMotionListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        //entered_card = true;
        saySomething("Mouse entered", event);
    }

    @Override
    // Store the mouse location when it is pressed
    public void mousePressed(MouseEvent event) {
        //if ((event.getX() <= (location_x  + width)) && (event.getX() >= location_x) && (event.getY() <= (location_y  + height)) && (event.getY() >= location_y)) {
        System.out.println("Game field At " + event.getX() + " and " + event.getY());
        /**
        if (entered_card == true) {
            pressed_card = true;
        }

        else {
            System.out.println("Nope");
        }
        saySomething("Mouse pressed", event); */
    }
    /**
    @Override
    public void mouseMoved(MouseEvent event) {
        System.out.println("x-move: " + event.getX() + "y-move: " + event.getY());
        saySomething("Mouse moved", event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        pressed_card = false;
        saySomething("Mouse released", event);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        entered_card = false;
        saySomething("Mouse exited", event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (pressed_card == true) {
            saySomething("Mouse Dragged", event);
        }

    } */



    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        + "\n");
    }
}
