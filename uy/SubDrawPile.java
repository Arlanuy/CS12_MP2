package uy;

    /**
    * Implements the characteristics of a sub_draw_pile which is the pile
    * where the card immediately taken from the draw pile goes to
    * @author  Arlan Uy
    * @version 1.8.0_60
    */

    public class SubDrawPile extends DrawPile {
    private int sub_draw_pile_num_cards = 3;
    private Card[] drawn_cards;
    private LinkedStack main_draw_pile;
    private DrawPile temp_draw_pile = new TempDrawPile(3);
    private SubDrawPilePanel sub_draw_pile_panel;
    private CardField background_pic;

    public SubDrawPile(int pile_number) {
        super(pile_number);
    }

    public void setMainDrawPile(LinkedStack main_draw_pile) {
        this.main_draw_pile = main_draw_pile;
    }

    public void setSubDrawPilePanel(SubDrawPilePanel sub_draw_pile_panel) {
        this.sub_draw_pile_panel = sub_draw_pile_panel;
        sub_draw_pile_panel.setSubDrawPile(this);
    }

    public LinkedStack getMainDrawPile() {
        return main_draw_pile;
    }

    public DrawPile getTempDrawPile() {
        return temp_draw_pile;
    }

    public int getSubDrawPileNumCards() {
        return sub_draw_pile_num_cards;
    }
    //overrides the pop method from the DrawPile c;ass
    public Card pop(boolean suppress_error, boolean peek_mode) {
        Card x = new Card();
        if (top == null) {
            if (suppress_error == false) {
                underflow(this);
            }

        }

        else {
              Node alpha  = top;
              x = alpha.INFO;
              top = alpha.LINK;
              number_of_cards--;
              return x;

            }
        return x;
      }

    public void pushTempDrawnCards() {
        setDrawnCards();
        Card[] drawn_cards = getDrawnCards();
        if (drawn_cards != null) {
            temp_draw_pile.pushDrawnCards(drawn_cards);
            System.out.println("Entered inside ");
            temp_draw_pile.printContent();
        }
    }

    public void drawMainPileCards() {
        if ((main_draw_pile != null) && (main_draw_pile.isEmpty() == true)) {
            if ((temp_draw_pile != null) && (temp_draw_pile.isEmpty() == true)) {
                System.out.println("There are no  more cards left in both your temp and main draw piles ");
            }

            else {
                ((TempDrawPile)temp_draw_pile).setDrawnCards();
                ((DrawPile)main_draw_pile).pushDrawnCards(((TempDrawPile)temp_draw_pile).getDrawnCards());
                System.out.println("entered here at 1");
            }

        }

        else if (main_draw_pile != null) {
            Card[] temp = new Card[sub_draw_pile_num_cards];
            for (int i = 0; i < sub_draw_pile_num_cards; i++) {
                Card x = main_draw_pile.peek();
                if (x.getSuit() != 'v') {
                    temp[i] = ((DrawPile)main_draw_pile).getDrawnCard();
                    ((DrawPile)main_draw_pile).drawCard();
                    System.out.println("top of main pile card is  " + temp[i].toString());
                }

                else {
                    System.out.println("Exhausted cards in main draw pile");
                    break;
                }
            }
            System.out.println("entered here at 2");
            pushDrawnCards(temp);
        }

        else {
            System.out.println("null main draw pile");
        }
    }


    public Card[] getDrawnCards() {
        /**
        int num_drawn_cards = drawn_cards.length;
        Card[] reverser_cards = new Card [num_drawn_cards];
        int j = 0;
        for (int i = num_drawn_cards - 1; i >= 0; i--) {
            if (reverser_cards[j] != null) {
                reverser_cards[j] = drawn_cards[i];
                System.out.println("Alloted on reverser is " + reverser_cards[j].toString());
            }

            else {
                System.out.println("null here");
            }

            j++;
        }
        return reverser_cards; */
        return drawn_cards;
    }

    public void setDrawnCards() {
        Card[] drawn_cards = new Card[sub_draw_pile_num_cards];
        int j = 0, i = 0;
        if (isEmpty() == false) {
            for (i =  sub_draw_pile_num_cards - 1; i >= 0; i--) {

                if ((drawn_card != null) && (drawn_card.getSuit() != 'v')) {

                    drawn_cards[i] = drawn_card;
                    j++;
                }

                else {
                    System.out.println("null on setdrawncards");
                    break;
                }
                drawCard();
                System.out.println(drawn_card.toString());
            }
            //removes the null card/s drawn
            if (j != sub_draw_pile_num_cards) {
                Card[] drawn_cards2 = new Card[j + 1];
                System.out.println("The cards from the method Sub setDrawnCards are: ");
                for (int k = 0; k <= j; k++) {
                        drawn_cards2[k] = drawn_cards[k];
                        System.out.print(drawn_cards2[k] + " | ");
                }
                System.out.println();
                drawn_cards = drawn_cards2;
            }
            this.drawn_cards = drawn_cards;
        }
    }

    //overrides the  printcontent method from the LinkedStack class
    public void printContent() {
        if ((top == null) && (temp_draw_pile.isEmpty() == true)) {
          System.out.println("The sub draw pile is currently empty");
        }

        else {
            int number = 0;
            for (Node i = top; i != null; i = i.LINK) {
                number = i.INFO.getRank();
                if (i.INFO.getFaceUp() == false) {
                     System.out.print("Hidden |" );
                }

                else {
                    if (number == 13)
                        System.out.print("K");
                    else if (number == 12)
                        System.out.print("Q");
                    else if (number == 11)
                        System.out.print("J");
                    else if (number == 1)
                        System.out.print("A");
                    else
                        System.out.print(number);
                    System.out.print(" "+ i.INFO.getSuit() +" | ");
                }

            }
            temp_draw_pile.printContent();
        }

    }
}
