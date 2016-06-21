package uy;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Container;
import javax.swing.*;

public class MouseAdaptForCardInPiles extends MouseAdapter {
    private boolean pressed_card = false;
    private boolean entered_card = false;
    public static final int Y_ADJUST = 100;
    private CardField card;
    private Container original_parent;
    private int original_x_position;
    private int original_y_position;
    public final static int CARDDIST = 30;
    private static int cards_listening = 0;
    private int own_card_index;

    public MouseAdaptForCardInPiles(CardField card) {
        card.addMouseListener(this);
        card.addMouseMotionListener(this);
        this.card = card;
        own_card_index = cards_listening;
        System.out.println("own index is " + own_card_index);
        cards_listening++;
        System.out.println("cards listening plus plus" + cards_listening);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        //saySomething("Mouse moved", event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        pressed_card = false;
        //saySomething("Mouse released", event);
        //card.setCanBeRemovedInPile(false);
        System.out.println("mouse released");
        card.removeMouseListeners();
        MouseAdaptForCard card_listener = new MouseAdaptForCard(card);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        entered_card = true;
        saySomething("Mouse entered", event);
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
        /**
        Container parent = card.getParent();
        parent.remove(card);
        parent.validate();
        parent.repaint(); */
        //saySomething("Mouse pressed", event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        //card.setLocation(event.getX(), event.getY());
        //card.setLocation(event.getXOnScreen(), event.getYOnScreen() - Y_ADJUST);
        //SolitaireGUI.redisplay();
        //saySomething("Mouse dragged gogo arlan", event);
        card.setLocation(event.getXOnScreen(), event.getYOnScreen() - Y_ADJUST + (own_card_index)* CARDDIST);
        card.getCardName();
        System.out.println("should be moving");
        System.out.println("should be dragged");
        card.getCardName();
        SolitaireGUI.redisplay();

    }
    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription
                        + " (" + e.getX() + ","
                        + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        + "\n");
    }
}
