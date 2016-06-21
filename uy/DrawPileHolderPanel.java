package uy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawPileHolderPanel extends JPanel {
    public static final int WIDTH  = 550; //(CARDWIDTH * 2) +  350 EXTRA SPACE
    private static final int HEIGHT = 270; //LOCY + CARDHEIGHT + SolitaireGUI.MIDGAP
    public final static int CARDWIDTH = 100;
    private static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    private static MainDrawPilePanel main_draw_pile_panel;
    private static SubDrawPilePanel sub_draw_pile_panel;
    private BorderLayout layout;
    //private BoxLayout layout;

    public DrawPileHolderPanel() {
        setOpaque(false);
        //layout = new BoxLayout(this, BoxLayout.X_AXIS);
        //setOpaque(true);
        setLayout(layout);
        //setSize(SIZE);
        //setLocation(LOCX, LOCY);
        setBackground(Color.ORANGE);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        MouseAdaptForGameField drawpile_holder_panel_listener = new MouseAdaptForGameField(this);
        addMouseListener(drawpile_holder_panel_listener);
        addMouseMotionListener(drawpile_holder_panel_listener);
    }

    private class MouseAdaptForDrawPileField extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent event) {
            //System.out.println("Nagenter at nagrelease sa draw piles field");
            //SolitaireGUI.setMouseInPilesField(false);
        }
    }

    public Dimension getPreferredSize() {
        return SIZE;
    }


    public void draw(Graphics g) {
        main_draw_pile_panel.draw(g);
        sub_draw_pile_panel.draw(g);
    }

    /**
    * Sets the main draw pile's main draw pile panel by which is the object associated with the GUI
    * @param main_draw_pile_panel  the GUI holder of the main_draw_pile object
    */
    public void setMainDrawPilePanel (MainDrawPilePanel main_draw_pile_panel) {
        this.main_draw_pile_panel = main_draw_pile_panel;
        add(main_draw_pile_panel, BorderLayout.WEST);
        //main_draw_pile_panel.setBounds(LOCX, LOCY, CARDWIDTH, CARDHEIGHT);
        //System.out.println("DHP x: " + (LOCX) + " y: " + LOCY);
    }

    /**
    * Sets the sub draw pile's sub draw pile panel by which is the object associated with the GUI
    * @param sub_draw_pile_panel  the GUI holder of the sub_draw_pile object
    */

    public void setSubDrawPilePanel(SubDrawPilePanel sub_draw_pile_panel) {
        this.sub_draw_pile_panel = sub_draw_pile_panel;
        add(sub_draw_pile_panel, BorderLayout.EAST);
        //sub_draw_pile_panel.setBounds(LOCX + GAP + CARDWIDTH, LOCY, CARDWIDTH, CARDHEIGHT);
    }
}
