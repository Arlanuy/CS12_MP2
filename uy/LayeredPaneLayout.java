package uy;
import java.awt.*;

public class LayeredPaneLayout implements LayoutManager {
    private final static int CARDHEIGHT = 120;
    private final static int CARDWIDTH = 100;
    private final static int CARDDIST = 30;
    private static final Dimension SIZE = new Dimension(CARDWIDTH, CARDHEIGHT);
    private final Container target;
    private Component[] new_components;
    private boolean to_reverse = false;
    //if 1 then this is created for pile_panel but if it is 2 then this is instead created for sub_draw_pile_panel (3 cards)
    //but if it is 3 then this is created for suit_pile_panel and sub_draw_pile_panel (1 card)
    private int target_num = 0;

    public LayeredPaneLayout(final Container target, int target_num) {
            this.target = target;
            this.target_num = target_num;
            //setBackground(Color.ORANGE);
    }

    public void setToReverseComponents(boolean to_reverse) {
        this.to_reverse = to_reverse;
    }

    @Override
    public void addLayoutComponent(final String name, final Component comp) {
    }

    public Component[] reverseComponents(Component[] components) {
        new_components = new Component[components.length];
        for (int i = 0; i < components.length/2; i++) {
            new_components[i] = components[components.length - i];
        }
        return new_components;
    }

    @Override
    public void layoutContainer(final Container container) {
        Component[] components = container.getComponents();
        //new_components = reverseComponents(components);
        for (int i = 0; i < components.length; i++) {
            //((CardField)new_components[i]).getCardName();
            //System.out.println("layered layout is in location " + ((CardField)components[i]).getXOnScreen() + " " + ((CardField)components[i]).getYOnScreen());
            if (target_num == 1) {
                if ((i == 0) && (components.length != 1)) {
                    components[i].setBounds(new Rectangle(0, 0 + ((components.length - 2 - i) * 30), CARDWIDTH, CARDHEIGHT));
                }

                else if (i != components.length - 1) {
                    components[i].setBounds(new Rectangle(0, 0 + ((components.length - 2 - i) * 30), CARDWIDTH, 30));
                }

                else {
                    //the last component is the  background picture
                    components[i].setBounds(new Rectangle(0, 0, CARDWIDTH, 120));
                }
                System.out.println("naglayout sa i " + i);
            }

            else if (target_num == 2) {
                components[i].setBounds(new Rectangle(0 + (i * 30), 0, CARDWIDTH, CARDHEIGHT));
            }

            else if (target_num == 3) {
                components[i].setBounds(new Rectangle(0, 0, CARDWIDTH, CARDHEIGHT));
            }

            else {
                System.out.println("Target num is zero");
            }
            ((CardField)components[i]).getCardName();
            System.out.println("after layered layout is in location " + ((CardField)components[i]).getXOnScreen() + " " + ((CardField)components[i]).getYOnScreen() + " with bounds " + ((CardField)components[i]).getWidth() + " " + ((CardField)components[i]).getHeight());
        }
        to_reverse = false;
    }

    @Override
    public Dimension minimumLayoutSize(final Container parent) {
            return preferredLayoutSize(parent);
    }

    @Override
    public Dimension preferredLayoutSize(final Container parent) {
            return SIZE;
    }

    @Override
    public void removeLayoutComponent(final Component comp) {
    }
}
