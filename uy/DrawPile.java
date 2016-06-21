//Arlan UY 2015-09385
package uy;
import java.io.*;
import java.util.*;

/**
* Implements the characteristics of the  draw pile
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class DrawPile extends LinkedStack implements PileAndDrawPileInterface {
    private int pile_number;
    protected Card drawn_card;
    private MainDrawPilePanel main_draw_pile_panel;
    private boolean add_cardfield = false;
    //The following numbers are useful for debugging only
    //#0 pile pertains to the one that had just undergone the deckNumberSetter method in SolitaireDemo class
    //while #1, #2, #3, #4 pertains to pile not yet shuffled, pile shuffled, sub_draw_pile and temp_draw_pile respectively

    public DrawPile(int pile_number) {
        this.pile_number = pile_number;
        kind = "Draw Pile";
    }

    public DrawPile(int pile_number, boolean add_cardfield) {
        this.pile_number = pile_number;
        kind = "Draw Pile";
        this.add_cardfield = add_cardfield;
    }

    public void setMainDrawPilePanel( MainDrawPilePanel main_draw_pile_panel) {
        this.main_draw_pile_panel = main_draw_pile_panel;
        main_draw_pile_panel.setMainDrawPile(this);
    }

    public void push(Card x) {
        Node newN = new Node();
        newN.INFO = x;
        x.setFaceUp(true);
        newN.LINK = top;
        number_of_cards++;
        top = newN;
        drawn_card = top.INFO;
        if (add_cardfield == true) {
            CardField x_field = x.getCardField();
            main_draw_pile_panel.addCard(x_field);
            //System.out.print("pushed card from draw pile is ");
            //x_field.getCardName();
        }
    }

    public void setPileNumber(int pile_number) {
        this.pile_number = pile_number;
    }

    public boolean getIsAddCardField() {
        return add_cardfield;
    }

    public int getPileNumber() {
        return pile_number;
    }

    public Card getDrawnCard() {
        return drawn_card;
    }



    public void setDrawnCard() {
       Card x = peek();
       this.drawn_card = x;
    }

   public void pushDrawnCards(Card[] drawn_cards) {
       //System.out.println("The drawn cards below are arranged from the first card you can use\n up to the last): ");
      for (int i = drawn_cards.length - 1; i >= 0; i--) {

          if (drawn_cards[i] != null) {
              push(drawn_cards[i]);
           }
      }
      setDrawnCard();
      System.out.println();
  }

   public void reverseCards() {
       boolean suppress_error = true, peek_mode = false;
       int num_cards = getNumberOfCards();
       Card[] temp_cards = new Card[num_cards];
       for(int i = 0; i < num_cards; i++) {
           temp_cards[i] = pop(suppress_error, peek_mode);
           //System.out.println("Legendary card is " + temp_cards[i]);
       }

       pushDrawnCards(temp_cards);
   }

   public void drawCard() {
       boolean suppress_error = true, peek_mode = false;
       Card x = peek();
       if (x.getSuit() != 'v') {
             drawn_card = pop(suppress_error, peek_mode);
            System.out.println("null card is " + drawn_card.toString());
            setDrawnCard();
       }
   }
   //overrides the toString method in the  Object Class
    public String toString(LinkedStack x) {
        String xpeek = "";
        if (x.peek().getSuit() == 'v') {
            xpeek = "non-existent";
        }

        else {
            xpeek = x.peek().toString();
        }
        return String.format("Its topmost card is %s  and the number of cards left is %d and its kind is a %s and its pile number is %d \n",
        xpeek, x.getNumberOfCards(), x.getKind(), ((PileAndDrawPileInterface)x).getPileNumber());
    }

    public Card pop(boolean suppress_error, boolean peek_mode) {
        Card x = new Card();
        if (top == null) {
            if (suppress_error == false) {
                underflow(this);
                System.out.println("Entered pop and top is null");
            }
        }

        else {
              Node alpha  = top;
              x = alpha.INFO;
              top = alpha.LINK;
              setDrawnCard();
              number_of_cards--;
              if (add_cardfield == true) {
                  CardField x_field = x.getCardField();
                  main_draw_pile_panel.removeCard(x_field);
                  //System.out.println("popped card from draw pile is " +  x);
              }
              return x;

            }
        return x;
      }

    /**
    * Pops a random card out from the stack associated with a deck_number that is chosen by random
    * this method is not used by sub_draw_pile
    * @param num_card   erm coined to be the deck_number of a card that will be popped from the stack
    * @param suppress_error  suppresses the error thrown by an exception when true
    * @return j  returns the node at the given index
    */

      public Card popCardNumber(int num_card, boolean suppress_error) {
          Card x = new Card();
          if (top == null) {
              if (suppress_error == false) {
                  underflow(this);
              }
          }

          else {
              Node temp = top;
               for (int i = 1; i <= getNumberOfCards(); i++) {
                   if (temp != null) {
                       if (temp.INFO.getDeckNumber() == num_card) {
                           Node alpha  = top;
                           x = alpha.INFO;
                           top = alpha.LINK;
                           number_of_cards--;
                           break;
                       }
                       else if ((temp.LINK != null) && (temp.LINK.INFO.getDeckNumber() == num_card)) {
                           Node alpha  = temp.LINK;
                           x = alpha.INFO;
                           temp.LINK = alpha.LINK;
                           number_of_cards--;
                           break;
                       }

                       else if ((temp.LINK == null) && (temp.INFO.getDeckNumber() == num_card)) {
                           x = temp.INFO;
                           temp = null;
                           number_of_cards--;
                           break;
                       }

                       else {
                           temp = temp.LINK;
                       }
                   }



                 }

             }
        //System.out.println("The card popped is: " + x.toString());
        return x;
    }

  }
