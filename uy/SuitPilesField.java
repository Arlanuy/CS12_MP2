package uy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Implements the characteristics of the field area where the whole suit piles field is which represents the object associated with the GUI
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class SuitPilesField extends JPanel implements FieldInterface{
    private static final int WIDTH  = 1370;//SolitaireGUI.frame_width - DrawPileHolderPanel.WIDTH;
    private static final int HEIGHT = 240; //LOCY + CARDHEIGHT + SolitaireGUI.MIDGAP
    public final static int CARDWIDTH = 100;
    private static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    private static final int LOCX = 600;
    private static final int LOCY = 75;
    private static final int STARTGAP = 50;
    private static final int GAP = 200;
    private static final int FINALGAP = 50;
    private static SuitPile suit_piles[];
    private static SuitPilePanel suit_piles_panel[] = new SuitPilePanel[4];
    private MouseAdaptForGameField suitpiles_field_listener;
    private Rectangle rectangle_covered;
    private BoxLayout layout;

    public SuitPilesField() {
        setOpaque(false);
        //setOpaque(true);
        layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
        //BorderLayout b_layout = new BorderLayout(GAP, 0);
        //setLayout(b_layout);

        //System.out.println("Suit piles field GAP " + " width " + WIDTH +" frame " + SolitaireGUI.frame_width +" draw pile panel " + DrawPileHolderPanel.WIDTH);
        setLayout(layout);
        //setBounds(LOCX, LOCY, WIDTH, HEIGHT);
        setBackground(Color.ORANGE);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        rectangle_covered = new Rectangle(LOCX, 0, WIDTH, HEIGHT);
        MouseAdaptForSuitPilesField suit_piles_field_listener = new MouseAdaptForSuitPilesField();
            addMouseListener(suit_piles_field_listener);
            addMouseMotionListener(suit_piles_field_listener);
    }

    private class MouseAdaptForSuitPilesField extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent event) {
            System.out.println("Nagenter at nagrelease sa suit piles field");
            //SolitaireGUI.setMouseInPilesField(false);
            /**
            if  (SolitaireGUI.getMouseInPilesField() == true) {
                System.out.println("nagdisplayinorder siya");
                Point main_card_source = SolitaireGUI.getMainCardSourcePoint();
                SolitaireGUI.pileDisplayInOrder(original_x_position, original_y_position);
            } */
        }

        @Override
        public void mousePressed(MouseEvent event) {
            System.out.println("Pressed suit piles field at " + event.getXOnScreen() + " " + event.getYOnScreen());
        }
    }

    public Rectangle getRectangleCovered() {
        return rectangle_covered;
    }

    /**
    * By using a loop, this method assign each suit_pile to its corresponding suit_piles_panel
    * @param suit_piles  the logic used by the suit_piles represented in an array
     * @param suit_piles_panel  the representation of a suit_pile in the GUI
    */
    public void assignSuitPiles(LinkedStack suit_piles[], SuitPilePanel suit_piles_panel[]) {
        for (int i = 0; i < suit_piles.length; i++) {
            suit_piles_panel[i] = new SuitPilePanel();
            ((SuitPile)suit_piles[i]).setSuitPilePanel(suit_piles_panel[i]);
            //System.out.println("Success naman");
        }
        this.suit_piles_panel = suit_piles_panel;
        this.suit_piles = (SuitPile[])suit_piles;
    }


    public void createSuitPilesPanel() {
        add(Box.createRigidArea(new Dimension(STARTGAP, HEIGHT)));
        for (int i = 0; i < suit_piles_panel.length; i++) {
            //suit_piles_panel[i].setBounds(LOCX, LOCY, CARDWIDTH, HEIGHT);
            System.out.println("Suit Piles");
            add(suit_piles_panel[i]);
            add(Box.createRigidArea(new Dimension(GAP, HEIGHT)));
        }
        //add(Box.createRigidArea(new Dimension(FINALGAP, HEIGHT)));
    }

    public void setSuitPilesCards() {
        createSuitPilesPanel();
        for (int i =  0; i < suit_piles_panel.length; i++) {
            suit_piles_panel[i].setSuitPileCard((LOCX + STARTGAP + (GAP + CARDWIDTH) * i), LOCY);
            System.out.println("Placed suit cards at " + (LOCX + (GAP + CARDWIDTH) * i) + " " + (LOCY));
        }
    }

    public  void setCardLocationInChild(int index_child, CardField pushed_card) {
        pushed_card.getCardName();
        System.out.println("this is suit panel " + index_child);
        suit_piles_panel[index_child].addCard(pushed_card);
        //suitpiles_field_listener.setSelectedCard(pushed_card);
    }

    public  void removeCardLocationInChild(int index_child, CardField pushed_card) {
        pushed_card.getCardName();
        System.out.println("this is suit panel " + index_child);
        suit_piles_panel[index_child].removeCard(pushed_card);
        //suitpiles_field_listener.setSelectedCard(pushed_card);
    }

    public int containsCardInChild(int location_x, int location_y) {
        int index_child = -1;
        System.out.println("Location to be contained is " + location_x + " " + location_y);
        for (int i = 0; i < suit_piles_panel.length; i++) {
            System.out.println("Suit Piles panel is at X: " + suit_piles_panel[i].getXOnScreen() + " AND AT Y: " + suit_piles_panel[i].getYOnScreen());
            Rectangle rect = suit_piles_panel[i].getRectangleCovered();
            System.out.println("Rect error i" + i +" at location " + rect.getX() + " " + rect.getY() + " with dimension " + rect.getWidth() + " " + rect.getHeight());
            if (rect.contains(location_x, location_y)) {
                index_child = i;
                break;
            }
        }
        return index_child;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < suit_piles_panel.length; i++) {
            suit_piles_panel[i].draw(g);
        }
    }

    public Dimension getPreferredSize() {
        return SIZE;
    }

}
