package uy;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Implements the characteristics of the sub_draw_pile's panel which represent the object associated with the GUI
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class SubDrawPilePanel extends JPanel{

    private final static int CARDHEIGHT = 120;
    public final static int CARDWIDTH = 100;
    private static final Dimension SIZE = new Dimension(CARDWIDTH, CARDHEIGHT);
    private static final int LOCX = 350;
    //Because SuitPilesField that uses BoxLayout does not follow any setBounds method and thus having a y-gap-start of 75 pixels,
    //we must just adjust this accordingly. But I can't directly order this layout to place the cards at a certain y-coordinate and so we just follow the default y-gap-start of SuitPilesField
    private static final int LOCY = 75;
    private static final int GAP = 50;
    private static final int MARGINERROR = 40;
    private static final int TWICEMARGINERROR = 80;
    private SubDrawPile sub_draw_pile;
    private Rectangle rectangle_covered;
    private int num_cards = 0;
    private CardField[] sub_draw_pile_cards = new CardField[24];
    private CardField background_pic;
    private JLayeredPane cards_layout = new JLayeredPane();
    private LayeredPaneLayout layered_layout;
    //private JLayeredPane cards_layout = new JLayeredPane();
    //private LayeredPaneLayout layered_layout;

    public SubDrawPilePanel() {
        setLayout(new BorderLayout());
        layered_layout = new LayeredPaneLayout(this, 3);
        cards_layout.setLayout(layered_layout);
        add(cards_layout);
        //layered_layout = new LayeredPaneLayout(this, 2);
        //setSize(SIZE);
        //setLocation(LOCX, LOCY);
        //It essential to use this method when you use a BorderLayout on the JContainer where this JComponent is located because if not this JPanel won't even appear in the program
        setBounds(LOCX, LOCY, CARDWIDTH, CARDHEIGHT); //
        //setBackground(Color.YELLOW);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setOpaque(false);
        //setOpaque(true);
        rectangle_covered = new Rectangle(LOCX - MARGINERROR, LOCY - MARGINERROR,  CARDWIDTH + TWICEMARGINERROR, CARDHEIGHT + TWICEMARGINERROR);
        //setBackground(Color.WHITE);
        //addMouseListener(new SubDrawPileMouseListener());
        background_pic = new CardField("jr.png");
        background_pic.removeMouseListeners();
        cards_layout.add(background_pic);
        background_pic.setLocation(LOCX, LOCY);
    }
    /**
    private class SubDrawPileMouseListener implements MouseListener {
        private CardField top_card;
        @Override
        public void mousePressed(MouseEvent e) {
            top_card = getTopCard();
            removeCard(top_card);

            if (top_card == null) {
                try {
                    throw new StackUnderFlowException(main_draw_pile);
                }

                catch (StackUnderFlowException e) {

                }
            }

            else {

            }
        }


        @Override
        public void mouseReleased(MouseEvent event) {
            addCard(top_card);
            System.out.println("released");
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            addCard(top_card);
            System.out.println("clicked");
        }

    } */

    public Rectangle getRectangleCovered() {
        return rectangle_covered;
    }

    public CardField getTopCard() {
        try {
            return sub_draw_pile_cards[num_cards - 1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cards in sub draw pile panel are already gone");
            return null;
        }
    }

    public void setSubDrawPile(SubDrawPile sub_draw_pile) {
         this.sub_draw_pile = sub_draw_pile;
         //sub_draw_pile_card = new Card[sub_draw_pile.getSubDrawPileNumCards()];
         //sub_draw_pile_cards = new CardField[sub_draw_pile.getSubDrawPileNumCards()];
         //System.out.println("nagpunta sa setsub");
    }

    /**
    public void changeZOrder() {


        cards_layout.removeAll();
        revalidate();
        for (int i = 0; i < num_cards; i++) {
            cards_layout.add(pile_cards[i], JLayeredPane.DRAG_LAYER, num_cards - 1 - i);
            if (i == num_cards - 1) {
                System.out.print("You can drag ");
                pile_cards[i].getCardName();
            }

            //pile_cards[i]
            validate();
        }
        repaint();

        System.out.println("Validated for " + num_cards);


    } */

    public void addCard(CardField pushed_card) {
        sub_draw_pile_cards[num_cards] = pushed_card;
        System.out.println("num_cards sub " + num_cards);
        //adds the card to the topmost part of the pile
        cards_layout.add(pushed_card, JLayeredPane.DRAG_LAYER, 0);
        //cards_layout.add(pushed_card, JLayeredPane.DRAG_LAYER, num_cards);
        System.out.println("Sub Draw Pile");
        pushed_card.setLocation(LOCX, LOCY);
        validate();
        repaint();
        num_cards++;
    }

    public void removeCard(CardField removed_card) {
        remove(removed_card);
        revalidate();
        repaint();
        num_cards--;
    }

    public void draw (Graphics g) {
        background_pic.drawCard(g);
        for (int i = 0; i < sub_draw_pile_cards.length; i++) {
            if (sub_draw_pile_cards[i] == null) break;
            sub_draw_pile_cards[i].drawCard(g);
        }
    }

    public Dimension getPreferredSize() {
        return SIZE;
    }
}
