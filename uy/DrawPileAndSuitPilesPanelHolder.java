package uy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
this class exists to make the layout of the game possible
This is the north component of the DrawCanvas border layout
If this won't be the case then the north component will be DrawPileAndSuitPilesField class
which is in BorderLayout. The south component of the DrawCanvas border layout
is the PilesPanelHolder which has a BorderLayout. If such, will be the case
and you grouped the north and south component into a BorderLayout, say the DrawCanvas class
then the DrawPileAndSuitPilesField class will be much larger than the preferred size
because the DrawCanvas' BorderLayout do not respect the set preferred sizes of its child
 upon making the argument of the setOpaque action command in the PilesField class
*/

public class DrawPileAndSuitPilesPanelHolder extends JPanel {
    private static final int WIDTH  = 2050;
    private static final int HEIGHT = 160;
    private static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    //private static final int LOCX = 150;
    //private static final int LOCY = 150;
    private static final int GAP = 75;
    private static DrawPileAndSuitPilesField drawpile_and_suit_piles_field;

    public DrawPileAndSuitPilesPanelHolder() {
        //setOpaque(false);
        setOpaque(true);
        setLayout(new BorderLayout());
        //setLocation(LOCX, LOCY);
        setSize(SIZE);
        setBackground(Color.BLACK);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }

    public void setDrawPileAndSuitPilesField(DrawPileAndSuitPilesField drawpile_and_suit_piles_field) {
        this.drawpile_and_suit_piles_field = drawpile_and_suit_piles_field;
        add(drawpile_and_suit_piles_field);
    }

    public Dimension getPreferredSize() {
        return SIZE;
    }
}
