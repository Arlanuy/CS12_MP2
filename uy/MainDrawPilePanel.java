package uy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Implements the characteristics of the  main draw pile
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class MainDrawPilePanel extends JPanel {

    private final static int CARDHEIGHT = 120;
    private final static int CARDWIDTH = 100;
    private static final Dimension SIZE = new Dimension(CARDWIDTH, CARDHEIGHT);
    private static final int LOCX = 150;
    //Because SuitPilesField that uses BoxLayout does not follow any setBounds method and thus having a y-gap-start of 75 pixels,
    //we must just adjust this accordingly. But I can't directly order this layout to place the cards at a certain y-coordinate and so we just follow the default y-gap-start of SuitPilesField
    private static final int LOCY = 75;
    private DrawPile main_draw_pile;
    private SubDrawPilePanel sub_draw_pile_panel;
    private static CardField[] main_draw_pile_cards = new CardField[52];
    private Rectangle rectangle_covered;
    private int num_cards = 0;
    private CardField background_pic;

    public MainDrawPilePanel() {
        setLayout(new BorderLayout());
        //setBounds(LOCX, LOCY, CARDWIDTH, CARDHEIGHT);
        setOpaque(false);
        //setOpaque(true);
        //It essential to use this method when you use a BorderLayout on the JContainer where this JComponent is located because if not this JPanel won't even appear in the program
        setBounds(LOCX, LOCY, CARDWIDTH, CARDHEIGHT); //
        //setLocation(LOCX, LOCY);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setBackground(Color.BLACK);
        addMouseListener(new MainDrawPileMouseListener());
        background_pic = new CardField("jb.png");
        add(background_pic);
        background_pic.setLocation(LOCX, LOCY);
        background_pic.removeMouseListeners();
    }

    private class MainDrawPileMouseListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent event) {
        }

        @Override
        public void mouseEntered(MouseEvent event) {

        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            if (num_cards == 0) {
                System.out.println("Main draw pile panel cards are still empty");
            }

            else {
                CardField top_card = getTopCard();
                MouseAdaptForCard card_listener = new MouseAdaptForCard(top_card);
                removeCard(top_card);
                sub_draw_pile_panel.addCard(top_card);
            }

            /**
            if (top_card == null) {
                try {
                    throw new StackUnderFlowException(main_draw_pile);
                }

                catch (StackUnderFlowException e) {

                }
            }

            else {

            }*/
        }

    }

    public void setSubDrawPilePanel(SubDrawPilePanel sub_draw_pile_panel) {
        this.sub_draw_pile_panel = sub_draw_pile_panel;
    }


    public void setMainDrawPile(DrawPile main_draw_pile) {
        this.main_draw_pile = main_draw_pile;
    }

    public void addCard(CardField pushed_card) {
        main_draw_pile_cards[num_cards] = pushed_card;
        //System.out.println("num_cards main " + num_cards);
        add(pushed_card);
        //cards_layout.add(pushed_card, JLayeredPane.DRAG_LAYER, num_cards);
        //System.out.println("Main Draw Pile");
        if (main_draw_pile.getIsAddCardField() == true) {
            //System.out.print("Main Draw Pile ");
            //pushed_card.getCardName();
            pushed_card.setLocation(LOCX, LOCY);
        }
        //System.out.println("main draw pile card " + pushed_card.getX() + " " + pushed_card.getY() + " " + pushed_card.getXOnScreen() + " " + pushed_card.getYOnScreen());
        //System.out.println("main_draw_pile_cards is at X :" + pushed_card.getX() + " Y: " + pushed_card.getY());
        //System.out.println("with parent at x: " + pushed_card.getParent().getX() + " y: " + pushed_card.getParent().getY());
        validate();
        repaint();
        num_cards++;
    }

    public void removeCard(CardField removed_card) {
        //System.out.println("whut " + this + "removed_card" + removed_card);
        remove(removed_card);
        MouseAdaptForCard card_listener = new MouseAdaptForCard(removed_card);
        revalidate();
        repaint();
        num_cards--;
    }


    public CardField getTopCard() {
        try {
            return main_draw_pile_cards[num_cards - 1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cards in main draw pile panel are already gone");
            return null;
        }

    }

    public void draw(Graphics g) {
        background_pic.drawCard(g);
        for (int i = 0; i < num_cards; i++) {
            main_draw_pile_cards[i].drawCard(g);
        }
    }

    public Dimension getPreferredSize() {
        return SIZE;
    }

}
