package uy;
import java.awt.Dimension;
import java.awt.Rectangle;

public interface FieldInterface {

    /**
    * Adds the card currently selected into the pile_panel/suit_pile_panel with the number of index_child
    * @param index_child  the pile/suit_panel number
    * @param pushed_card the card currently
    */
    public void setCardLocationInChild(int index_child, CardField pushed_card);

    /**
    *
    * @param location_x the horizontal distance of the point where the mouse is released to the border of the screen
    * @param location_y  the vertical distance of the point where the mouse is released to the border of the screen
    * @return returns the pile_panel/suit_pile panel number associated with the location where the mouse is released into
    */
    public int containsCardInChild(int location_x, int location_y);

    public  void removeCardLocationInChild(int index_child, CardField pushed_card);

    public Rectangle getRectangleCovered();
}
