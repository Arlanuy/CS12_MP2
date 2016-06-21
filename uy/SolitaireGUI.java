package uy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SolitaireGUI extends JFrame implements ActionListener{
    public static int frame_width;
    public static int frame_height;
    public final static int CARDWIDTH = 100;
    public final static int CARDHEIGHT = 120;
    public static int menu_height;
    public  final static int Y_ADJUST = 100;
    private MenuPanels menu_panels;
   // Declare an instance of the drawing canvas,
   // which is an inner class called DrawCanvas extending javax.swing.JPanel.
   private static DrawCanvas canvas;
   private static CardField selected_main_card; //= new CardField("0-K.png");
   private DrawPileHolderPanel draw_pile_holder_panel;
   private MainDrawPilePanel main_draw_piles_panel;
   private SubDrawPilePanel sub_draw_piles_panel;
   //private SuitPilesAndPilesField suitpiles_and_piles_panel;
   private DrawPileAndSuitPilesField drawpile_and_suitpiles;
   private SuitPilesField suit_piles_field;
   private PilesField piles_field;
   private static FieldInterface field_interfaces[] = new FieldInterface[2];
   private static CardField[] pile_cards_dragged;
    private final static int DRAGGEDPILECARDDIST = 30;
    private static boolean mouse_in_piles_field = false;

  public SolitaireGUI() {
    Container cp = getContentPane();
    menu_panels = new MenuPanels(this);
    setJMenuBar(menu_panels);
    menu_height = menu_panels.getHeight();
    System.out.println("menu height in gui is " + menu_height);
    cp.setLayout(new FlowLayout());
    //setSize(500, 700);
    setName("SolitaireGUI");
    setTitle("SolitaireGUI");
    Font myFont1 = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    Font myFont2 = new Font(Font.SERIF, Font.BOLD | Font.ITALIC, 16);
    JLabel header = new JLabel("Welcome to my " + getName());
    header.setFont(myFont1);
    header.setBackground(Color.CYAN);
    header.setForeground(Color.RED);
    //field_interfaces[0] = sub_draw_piles_panel;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
    frame_width = getWidth();
    frame_height = getHeight();
    System.out.println("width is " + getWidth() + " height is " + getHeight());
    canvas = new DrawCanvas();    // Construct the drawing canvas
    canvas.setPreferredSize(new Dimension(frame_width, frame_height));
    cp.add(canvas);
    setResizable(false);
    pack(); //We don't have to set anymore drawcanvas' preferred size because of this line. The program automatically arranges this container's component/s based on the number of component/s this container have
    setVisible(true);
  }

  public static int getCardWidth() {
      return frame_width;
  }

  public static int getCardHeight() {
      return frame_width;
  }

  public static void redisplay() {
      canvas.repaint();
  }

  public static void setSelectedCard(CardField selected_card) {
      selected_main_card = selected_card;
      //MouseAdaptForSelectedCard selected_card_listener = new MouseAdaptForSelectedCard(selected_main_card);
      //canvas.add(selected_card);
    }

    public static CardField getSelectedCard() {
        return selected_main_card;
    }

    public static boolean validatePosition(int source_x, int source_y, int destination_x, int destination_y) {
        return canvas.validatePosition(source_x, source_y, destination_x, destination_y);
    }
    /**
    public static void pileDisplayInOrder(int source_x, int source_y) {
        canvas.pileDisplayInOrder(source_x, source_y);
    } */

    public static void createDragPileCards(int pile_panel_locX, int pile_panel_locY) {
        canvas.createDragPileCards(pile_panel_locX, pile_panel_locY);
    }

    public static void setDragPileCards(CardField[] cards_dragged) {
        canvas.setDragPileCards(cards_dragged);
    }

    public static void dragPileCards(int loc_X, int loc_Y) {
        canvas.dragPileCards(loc_X, loc_Y);
    }

    public static void setMouseInPilesField(boolean is_in_piles_field) {
        canvas.setMouseInPilesField(is_in_piles_field);
    }

    public static boolean getMouseInPilesField() {
        return canvas.getMouseInPilesField();
    }

    /**
    public static boolean isCompInContainer(JComponent comp, JContainer cont) {

    Container c = comp.getParent();

    while (c != null) {
        if (c instanceof cont) {
            return true;
        } else {
            c = c.getParent();
        }
    }
    return false;
} */
    /**
    public static void findCardParentByLocation(CardField c) {
        /**
        if ((c.getXOnScreen() >=  suit_piles_field.getXOnScreen()) && (c.getXOnScreen() <=  suit_piles_field.getLocationX() + CARDWIDTH)
        && (c.getYOnScreen() >=  suit_piles_field.getLocationY()) && (c.getYOnScreen() <=  suit_piles_field.getLocationY() + CARDHEIGHT - Y_ADJUST)) {
            System.out.println("yehey");
        }

        else if ((c.getXOnScreen() >=  piles_field.getXOnScreen()) && (c.getXOnScreen() <=  piles_field.getXOnScreen() + CARDWIDTH)
        && (c.getYOnScreen() >=  piles_field.getYOnScreen()) && (c.getYOnScreen() <=  piles_field.getYOnScreen() + CARDHEIGHT - Y_ADJUST)) {
            System.out.println("yahoo");
        } */


  /**
    * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
    */
   private class DrawCanvas extends JPanel {
       private int selected_cardX;
       private int selected_cardY;
       private final static int MIDGAP = 50;
       private BoxLayout layout;
      // Override paintComponent to perform your own painting
      public DrawCanvas() {
         layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
         setLayout(layout);
         setBackground(Color.GREEN);
         //setBounds(0,0, 2100, 1000);
         //setBounds(0, 0, WIDTH, HEIGHT);
         draw_pile_holder_panel = new DrawPileHolderPanel();
         main_draw_piles_panel = new MainDrawPilePanel();
         sub_draw_piles_panel = new SubDrawPilePanel();
         /**
         String img_filename = "0-2.png";
         String img_filename2 = "0-3.png";
         String img_filename3 = "0-4.png";
         String img_filename4 = "0-5.png";
         String img_filename5 = "0-6.png";
         String img_filename6 = "0-7.png";
         String img_filename7 = "0-8.png";
         String img_filename8 = "0-9.png";
         String img_filename9 = "1-2.png";
         String img_filename10 = "0-A.png";
         String img_filename11 = "0-J.png";
         String img_filename12 = "0-Q.png";
          String img_filename13 = "0-K.png";
         CardField card = new CardField(img_filename);
         CardField card2 = new CardField(img_filename2);
         CardField card3 = new CardField(img_filename3);
         CardField card4 = new CardField(img_filename4);
         CardField card5 = new CardField(img_filename5);
         CardField card6 = new CardField(img_filename6);
         CardField card7 = new CardField(img_filename7);
         CardField card8 = new CardField(img_filename8);
         CardField card9 = new CardField(img_filename9);
         CardField card10 = new CardField(img_filename10);
         CardField card11 = new CardField(img_filename11);
         CardField card12 = new CardField(img_filename12);
          CardField card13 = new CardField(img_filename13);
          main_draw_piles_panel.setMainDrawPileField(card);
          sub_draw_piles_panel.setSubDrawPileField(card2); */
          draw_pile_holder_panel.setMainDrawPilePanel(main_draw_piles_panel);
          draw_pile_holder_panel.setSubDrawPilePanel(sub_draw_piles_panel);
        // add(draw_pile_holder_panel);
         //suitpiles_and_piles_panel = new SuitPilesAndPilesField();
         drawpile_and_suitpiles = new DrawPileAndSuitPilesField();
         suit_piles_field = new SuitPilesField();
         piles_field = new PilesField();
        // DrawPileAndSuitPilesPanelHolder drawpile_and_suitpiles_panelholder = new DrawPileAndSuitPilesPanelHolder();
        /**
         CardField suit_piles_cards[] = new CardField[4];
         suit_piles_cards[0] = card3;
         suit_piles_cards[1] = card4;
         suit_piles_cards[2] = card5;
         suit_piles_cards[3] = card6;
         suit_piles_field.setSuitPilesCards(suit_piles_cards); */
         /**
         CardField piles_cards[] = new CardField[7];
         piles_cards[0] = card7;
         piles_cards[1] = card8;
         piles_cards[2] = card9;
         piles_cards[3] = card10;
         piles_cards[4] = card11;
         piles_cards[5] = card12;
         piles_cards[6] = card13;
         piles_field.setPilesCards(piles_cards); */
         //suitpiles_and_piles_panel_holder.setSuitPilesField(suit_piles_field_holder);
         drawpile_and_suitpiles.setDrawPileHolderPanel(draw_pile_holder_panel);
         drawpile_and_suitpiles.setSuitPilesField(suit_piles_field);
        //drawpile_and_suitpiles_panelholder.setDrawPileAndSuitPilesField(drawpile_and_suitpiles);

        piles_field.setPilesCards();
        suit_piles_field.setSuitPilesCards();
        /**
        main_draw_piles_panel.setMainDrawPileCard(cards3[0][0]);
        sub_draw_piles_panel.setSubDrawPileCard(cards3[0][1]);
        sub_draw_piles_panel.addCard(cards3[1][1]); */
        main_draw_piles_panel.setSubDrawPilePanel(sub_draw_piles_panel);
         //suitpiles_and_piles_panel.setPilesField(piles_panel);
        // add(suitpiles_and_piles_panel);
        PilesPanelHolder piles_panel_holder = new PilesPanelHolder();
        piles_panel_holder.setPilesField(piles_field);
        add(drawpile_and_suitpiles);//, BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(frame_width, MIDGAP)));
        add(piles_panel_holder);//, BorderLayout.SOUTH);
        field_interfaces[0] = suit_piles_field;
        field_interfaces[1] = piles_field;
        //System.out.println("Dimension of drawcanvas is " + getWidth() + " " + getHeight());
         //repaint();
        //MouseAdaptForGameField game_listener =  new MouseAdaptForGameField(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selected_cardX = e.getX();
                selected_cardY = e.getY();
                System.out.println("GOGO CLAUS AT " + e.getX() + " " + e.getY());
            }

           @Override
           public void mouseReleased(MouseEvent e) {
              validatePosition(selected_cardX, selected_cardY, e.getX(), e.getY());
              System.out.println("released at background");

          }
      });


    }

    public boolean validatePosition(int source_x, int source_y, int destination_x, int destination_y) {
         /**
         if ((c.getXOnScreen() >=  suit_piles_field.getXOnScreen()) && (c.getXOnScreen() <=  suit_piles_field.getLocationX() + CARDWIDTH)
         && (c.getYOnScreen() >=  suit_piles_field.getLocationY()) && (c.getYOnScreen() <=  suit_piles_field.getLocationY() + CARDHEIGHT - Y_ADJUST)) {
             System.out.println("yehey");
         }

         else if ((c.getXOnScreen() >=  piles_field.getXOnScreen()) && (c.getXOnScreen() <=  piles_field.getXOnScreen() + CARDWIDTH)
         && (c.getYOnScreen() >=  piles_field.getYOnScreen()) && (c.getYOnScreen() <=  piles_field.getYOnScreen() + CARDHEIGHT - Y_ADJUST)) {
             System.out.println("yahoo");
         } */
         //System.out.println("source_x and y: " + source_x + "/" + source_y + "destination_x and y" + destination_x +"/" +destination_y + "less" + field_interfaces.length);
         boolean setted_card = false;
         int index_destination_child = -1, index_destination_field = -1;
         if (sub_draw_piles_panel.getRectangleCovered().contains(source_x, source_y) == true) {
             for (int i = 0; i < field_interfaces.length; i++) {
                    //System.out.println("GOGO LABAN LANG");
                     Rectangle rectangle_covered = field_interfaces[i].getRectangleCovered();
                     if (rectangle_covered.contains(destination_x, destination_y) == true) {
                         index_destination_field = i;
                     }
                     //System.out.println(rectangle_covered);
                     if ((index_destination_child == -1) && (index_destination_field != - 1)) {
                         //System.out.println("contains ba ng i DEST " + rectangle_covered.contains(destination_x, destination_y));
                          index_destination_child = field_interfaces[i].containsCardInChild(destination_x, destination_y);
                     }
                }
             //System.out.println("index src field sub draw pile "  + " index dest field " + index_destination_field);
             if (index_destination_child == -1) {
                 setted_card = false;
                // System.out.println("Nagfalse siya");
             }

             else {
                 sub_draw_piles_panel.removeCard(selected_main_card);
                 field_interfaces[index_destination_field].setCardLocationInChild(index_destination_child, selected_main_card);
                 //System.out.println("Nagtrue siya sa source na subdrawpile" + " at dest na " + index_destination_child);
                 setted_card = true;
             }
         }

         else {
             int index_source_child = -1, index_source_field = -1;
             //System.out.println("field_length " + field_interfaces.length);
             for (int i = 0; (i < field_interfaces.length) ; i++) {
                //System.out.println("GOGO LABAN LANG");

                Rectangle rectangle_covered = field_interfaces[i].getRectangleCovered();
                if (rectangle_covered.contains(destination_x, destination_y) == true) {
                    index_destination_field = i;
                }

                if (rectangle_covered.contains(source_x, source_y) == true) {
                    index_source_field = i;
                }

                //System.out.println(rectangle_covered);
                if ((index_destination_child == -1) && (index_destination_field != - 1)) {
                    //System.out.println("contains ba ng i DEST " + rectangle_covered.contains(destination_x, destination_y));
                     index_destination_child = field_interfaces[i].containsCardInChild(destination_x, destination_y);
                }

                if ((index_source_child == -1) && (index_source_field != - 1)) {
                    //System.out.println("contains ba ng i SOURCE " + rectangle_covered.contains(source_x, source_y));
                    index_source_child = field_interfaces[i].containsCardInChild(source_x, source_y);
               }
                //System.out.println("index_dest " + index_destination_child + "index source " + index_source_child);
            }
            //System.out.println("index src field " + index_source_field + " index dest field " + index_destination_field);
            if ((index_destination_child == -1) || (index_source_child == -1)) {
                setted_card = false;
                //System.out.println("Nagfalse siya");
            }

            else {
                boolean add_top_card_first = false;
                if (index_source_field == 1) {
                    if ((pile_cards_dragged != null) && (pile_cards_dragged.length != 1)) {
                        if (index_destination_field == 1) {
                            if (index_source_child == index_destination_child) {
                                setted_card = false;
                                transferBackPilesCardPosition(source_x, source_y);
                                //for the clicked card to be not drawn in front of the other cards whenever it is only clicked and not moved to the other piles
                                System.out.println("Baka may lumusot a " + index_source_child + " " + index_destination_child);
                            }

                            else {
                                setted_card = true;
                                add_top_card_first = true;
                            }
                        }
                        else{
                            setted_card = false;
                            transferBackPilesCardPosition(source_x, source_y);
                            System.out.println("Dapat bawal ito");
                        }
                    }

                    else {
                        setted_card = true;
                        System.out.println("iisang card ung mamomove galing sa piles");
                    }

                }

                else {
                    setted_card = true;
                }

                if (setted_card == true) {
                    field_interfaces[index_source_field].removeCardLocationInChild(index_source_child, selected_main_card);
                    field_interfaces[index_destination_field].setCardLocationInChild(index_destination_child, selected_main_card);
                }

                if (add_top_card_first == true) {
                    transferPilesCardPosition(index_source_child, index_destination_child);
                }
                System.out.println("Nagtrue siya sa source na " + index_source_child + " at dest na " + index_destination_child);
            }
        }
        System.out.println("Ito ung setted card " + setted_card);
        pile_cards_dragged = null;
        selected_main_card = null;
        return setted_card;
     }

     public void transferBackPilesCardPosition(int source_x, int source_y) {
         for (int i = 1; i < pile_cards_dragged.length; i++) {
            pile_cards_dragged[i].setLocation(source_x, source_y + (DRAGGEDPILECARDDIST * i));
            System.out.println("Pabalik na pagpasa ng pile card na ");
            pile_cards_dragged[i].getCardName();
         }
     }

     public void transferPilesCardPosition(int index_source_child, int index_destination_child) {
         for (int i = 1; i < pile_cards_dragged.length; i++) {
            piles_field.removeCardLocationInChild(index_source_child,  pile_cards_dragged[i]);
            piles_field.setCardLocationInChild(index_destination_child, pile_cards_dragged[i]);
            System.out.println("Nagtrue siya sa pagpasa ng pile card na ");
            pile_cards_dragged[i].getCardName();
         }
     }
     /**
     public void pileDisplayInOrder(int source_x, int source_y) {
         for (int i =  pile_cards_dragged.length - 1; i >= 0; i--) {
             pile_cards_dragged[i].setLocation(source_x, source_y + (DRAGGEDPILECARDDIST * i));
             System.out.println("setted location at " + (source_y + (DRAGGEDPILECARDDIST * i)));
         }
     } */

     public void createDragPileCards(int pile_panel_locX, int pile_panel_locY) {
        // System.out.println("pile_panel_locX " + pile_panel_locX + " pile_panel_locY " + pile_panel_locY);
         int pile_panel_index = piles_field.containsCardInChild(pile_panel_locX, pile_panel_locY);
         if ((selected_main_card != null) && (pile_panel_index != -1)) {
             piles_field.createDragPileCards(selected_main_card, pile_panel_index);
             System.out.println("nagdaan sa createdragpilecards method sa GUI");
         }

         else {
            System.out.println("hindi nagdaan sa createdragpilecards method smc" + selected_main_card +"pile panel ind " + pile_panel_index);
         }

     }

     public void setDragPileCards(CardField[] cards_dragged) {
         pile_cards_dragged = cards_dragged;
         //System.out.println("nagdaan sa setdragpilecards method");
    }

     public void dragPileCards(int loc_X, int loc_Y) {
         if ((selected_main_card != null) && (pile_cards_dragged != null)) {
              for (int i = 1; i < pile_cards_dragged.length; i++) {
                  pile_cards_dragged[i].setLocation(loc_X, loc_Y + (i * DRAGGEDPILECARDDIST));
                  //pile_cards_dragged[i].repaint();
                  //System.out.println("should be dragged");
                  //pile_cards_dragged[i].getCardName();
                  if (pile_cards_dragged[i] == null) {
                      //System.out.println("null in dragpilecards method at index i:" + i );
                      break;
                  }
                  //canvas.repaint();
              }
              //System.out.println("nagdaan sa canvasdragpile method");
          }

          else {
             System.out.println("nagnull sa canvasdragpile method at ang card ay ");
             selected_main_card.getCardName();
             System.out.println(" " + pile_cards_dragged);
          }
     }

     public void setMouseInPilesField(boolean is_in_piles_field) {
         mouse_in_piles_field = is_in_piles_field;
     }

     public boolean getMouseInPilesField() {
         return mouse_in_piles_field;
     }

      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.GREEN);  // set background color for this JPanel
         draw_pile_holder_panel.draw(g);
         suit_piles_field.draw(g);
         piles_field.draw(g);

        if (selected_main_card != null) {
           selected_main_card.drawCard(g);
        }

        if (pile_cards_dragged != null) {
            for (int i = 1; i < pile_cards_dragged.length; i++) {
               pile_cards_dragged[i].drawCard(g);
           }
       }
      }
   }


  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
