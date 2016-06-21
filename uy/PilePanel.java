package uy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PilePanel extends JPanel {
    private final static int CARDHEIGHT = 120;
    private final static int CARDWIDTH = 100;
    private final static int CARDDIST = 30;
    //BOTTOMMARGIN = 230
    private static final int HEIGHT = 560;//SolitaireGUI.frame_height - BOTTOMMARGIN - DrawPileAndSuitPilesField.HEIGHT;
    //private static final int XCONTAINERGAP = 600;
    //private static final int YCONTAINERGAP = 370;
    private static final Dimension SIZE = new Dimension(CARDWIDTH, HEIGHT);
    // the adjustment that were made in the form of marginerrors in a card's released position to still let the card be dragged irrespective of the wrong location  where the mouse is pressed at
    private static final int MARGINERROR = 40;
    private static final int TWICEMARGINERROR = 80;
    private PilesField piles_field;
    private int loc_X;
    private int loc_Y;
    private int top_removed_card_index = -1;
    //private int num_
    //private  CardField[] pile_field = new CardField[num_cards];
    private  CardField[] pile_cards = new CardField[13];
    private CardField[] cards_dragged;
    private Pile pile;
    private Rectangle rectangle_covered;
    private int num_cards = 0;
    private JLayeredPane cards_layout;
    private LayeredPaneLayout layered_layout;
    private CardField background_pic;

    public PilePanel(PilesField piles_field) {
        cards_layout = new JLayeredPane();
        layered_layout = new LayeredPaneLayout(this, 1);
        cards_layout.setLayout(layered_layout);
        cards_layout.setOpaque(false);
        //cards_layout.setOpaque(true);
        cards_layout.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setSize(SIZE);
        setOpaque(false);
        //setOpaque(true);
        add(cards_layout);
        setBackground(Color.ORANGE);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        this.piles_field = piles_field;
        background_pic = new CardField("jb.png");
        background_pic.removeMouseListeners();
        cards_layout.add(background_pic);
    }

    public Rectangle getRectangleCovered() {
        return rectangle_covered;
    }

    public int getNumCards() {
        return num_cards;
    }

    public void setPileCard(int loc_X, int loc_Y) {
        //System.out.println("pile_panel x " + loc_X + "y " + loc_Y);
        this.loc_X = loc_X;
        this.loc_Y = loc_Y;
        background_pic.setLocation(loc_X, loc_Y);
        //cards_layout.setBounds(loc_X - XCONTAINERGAP, STARTGAP, CARDWIDTH, HEIGHT);
        //setBounds(loc_X, loc_Y, CARDWIDTH, HEIGHT);
        //setBounds(loc_X, 50, CARDWIDTH, HEIGHT);
        //pile_card.setSize(SIZE);
        //System.out.println("Pile_panel wew at " + (loc_X - XCONTAINERGAP) + " " + (STARTGAP) + "with widht and height" + (CARDWIDTH + TWICEMARGINERROR) + " " + (HEIGHT + TWICEMARGINERROR));;
        rectangle_covered = new Rectangle(loc_X - MARGINERROR, loc_Y - MARGINERROR, CARDWIDTH + TWICEMARGINERROR,  HEIGHT + TWICEMARGINERROR);
        //System.out.println("Rectangle on parent is at " + rectangle_covered.getX() + " " + rectangle_covered.getY());
        //rectangle_covered = new Rectangle(getBounds());
        //System.out.println("Rectangles are at " + getBounds().getX() + " " + getBounds().getY());
        /**
        for (Component c: cards_layout.getComponents()) { //this is the same with for (int i = 0; i < cards_layout.getComponents().length; i++) {cards_layout.getComponents()[i]}
            cards_layout.setLayer(c, JLayeredPane.DRAG_LAYER, 0);
            System.out.print("Setted");
            ((CardField)c).getCardName();
        }*/
        //PilesPanelMouseAdapter piles_panel_listener = new PilesPanelMouseAdapter();
        //addMouseMotionListener(piles_panel_listener);
        //cards_layout.addMouseListener(piles_panel_listener);
    }
    /**
    private class PilesPanelMouseAdapter extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            SolitaireGUI.setPileCards(cards_dragged);
            System.out.println("Grandpa claus");
            System.out.println("Entered the roof");
        }
    } */

    public void changeZOrder() {
        /**
        for (int i = num_cards - 1; i >= 0; i--) {
            cards_layout.remove(pile_cards[i]);
            revalidate();
        }*/

        cards_layout.removeAll();
        revalidate();

        //for (int i = num_cards - 1; i >= 0; i--) {
        for (int i = 0; i < num_cards; i++) {
            cards_layout.add(pile_cards[i], JLayeredPane.DRAG_LAYER, num_cards - 1 - i);
            if (i == num_cards - 1) {
                //System.out.print("You can drag ");
                //pile_cards[i].getCardName();
            }

            //pile_cards[i]
            validate();
        }
        repaint();

        //System.out.println("Validated for " + num_cards);
        /**
        for (int i = 0; i < num_cards; i++) {
            cards_layout.setComponentZOrder(pile_cards[i], num_cards - 1 - i);
            System.out.println("z order " + cards_layout.getComponentZOrder(pile_cards[i]));
            validate();
        } */

    }
    //top_or_bottom = 0 if card to be placed should be at top while top_or_bottom = -1 if card to be placed should be at bottom
    public void addCard(CardField pile_card) {//, int top_or_bottom) {
        pile_cards[num_cards] = pile_card;
        pile_card.setLocation(loc_X, loc_Y + (num_cards * CARDDIST));
        //System.out.println("num_card " + num_cards);
        //changeZOrder();
        //places the card at the layer where it can be dragged
        System.out.print("pile card added is ");
        pile_card.getCardName();
        cards_layout.add(pile_card, JLayeredPane.DRAG_LAYER, 0);//);
        //System.out.println("PILE CARD gogo at " + (loc_X - XCONTAINERGAP) + " " + ((loc_Y + (num_cards * CARDDIST)) - YCONTAINERGAP));
        //System.out.println("pile_cards is at X :" + pile_card.getX() + " Y: " + pile_card.getY());
        //System.out.println("with parent at x: " + pile_card.getParent().getX() + " y: " + pile_card.getParent().getY());
        repaint();
        validate();
        //System.out.println("Piles");
        num_cards++;
        //PilesPanelMouseAdapter piles_panel_listener = new PilesPanelMouseAdapter();
        //MouseAdaptForCardInPiles pilecard_listener = new MouseAdaptForCardInPiles(pile_card);
    }
    /**
    private class PilesPanelMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(Event e) {
            for (Component c: cards_layout.getComponents()) {
                cards_layout.setLayer(c, JLayeredPane.DRAG_LAYER, 0);
            }
        }
    } */

    public int searchCard(CardField card_seek) {
        //System.out.println("num cards at search card method is " + num_cards);
        for (int i = 0; i < num_cards; i++) {
            if (pile_cards[i] == card_seek) {
                return i;
            }
            //System.out.println("While searching " + getXOnScreen() + " " + getYOnScreen());
            pile_cards[i].getCardName();
        }
        return -1;
    }

    public void setDraggedCards(CardField top_removed_card) {
        top_removed_card_index = searchCard(top_removed_card);
        //puts the card in the uppermost DRAG_LAYER so that the card is allowed to be dragged

        //System.out.println("top removed card with index " + top_removed_card_index + " " + top_removed_card.getXOnScreen() + " " + top_removed_card.getYOnScreen() + "and at " + " is ");
        //top_removed_card.getCardName();
        if ((top_removed_card_index != -1) && (num_cards != 1)) {
            /**
            System.out.println("top removed " + top_removed_card_index + " num cards " + num_cards);
            cards_layout.setLayer(top_removed_card, -1);
            System.out.print("nasetlayer ang ");
            pile_cards[top_removed_card_index].getCardName();
            System.out.println("is in location " + pile_cards[top_removed_card_index].getXOnScreen() + " " + pile_cards[top_removed_card_index].getYOnScreen());
            */
            cards_dragged = new CardField[num_cards - top_removed_card_index];
            for (int i = top_removed_card_index; i < num_cards; i++) {
                pile_cards[i].getCardName();
                System.out.println("is in location " + pile_cards[i].getXOnScreen() + " " + pile_cards[i].getYOnScreen());
                //removed_card.setCanBeRemovedInPile(true);
                //pile_cards[i].removeMouseListeners();
                //removed_card.removeMouseListeners();
                //MouseAdaptForCardInPiles piles_cards_listener = new MouseAdaptForCardInPiles(pile_cards[i]);
                cards_dragged[i - top_removed_card_index] = pile_cards[i];
            }
            SolitaireGUI.setDragPileCards(cards_dragged);
            //System.out.println("nadrag silang lahat");
        }


        else {
            //System.out.println("hindi nadrag silang lahat");
        }
    }

public void removeCard(CardField top_removed_card) {
    //System.out.println("removed card with index " + top_removed_card_index +" is ");
    top_removed_card.getCardName();
    if (top_removed_card_index != -1) {
        for (int i = num_cards - 1; i >= 0; i--) {
            //removed_card.setCanBeRemovedInPile(true);
            //pile_cards[i].removeMouseListeners();
            //removed_card.removeMouseListeners();
            //MouseAdaptForCardInPiles piles_cards_listener = new MouseAdaptForCardInPiles(pile_cards[i]);
            remove(pile_cards[i]);
            //cards_layout.
            revalidate();
            repaint();
        }
        num_cards = top_removed_card_index;
        top_removed_card_index = -1;
        //System.out.println("hindi sya nagnegative one dito");
    }


    else {
        //System.out.println("nagnegative one dito");
    }

}

    public void setPile(Pile pile) {
        this.pile = pile;
    }

    public int getXOnScreen() {
        return loc_X;
    }

    public int getYOnScreen() {
        return loc_Y;
    }

    public Dimension getPreferredSize() {
        return SIZE;
    }

    public void draw(Graphics g) {
        background_pic.drawCard(g);
        for (int i = 0; (i < num_cards); i++) {
            pile_cards[i].drawCard(g);
        }
    }
}
