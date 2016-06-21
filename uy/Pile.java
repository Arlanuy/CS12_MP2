//Arlan UY 2015-09385
package uy;
import java.io.*;
import java.util.*;

/**
* implements the characteristics of the  draw pile
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class Pile extends LinkedStack implements SuitPileAndPileInterface, PileAndDrawPileInterface{
    private int pile_number;
    private PilePanel pile_panel;

    public Pile (int pile_number) {
        this.pile_number = pile_number;
        kind = "Pile";
    }

    public Pile () {
        kind = "Pile";
    }

    public void setPilePanel(PilePanel pile_panel) {
        this.pile_panel = pile_panel;
        pile_panel.setPile(this);
    }

    public void setPileNumber(int pile_number) {
        this.pile_number = pile_number;
    }

    public int getPileNumber() {
        return pile_number;
    }

    public String toString(LinkedStack x) {
        String xpeek = "";
        if (x.peek().getSuit() == 'v') {
            xpeek = "non-existent";
        }

        else {
            xpeek = x.peek().toString();
        }
        return String.format("Its topmost card is %s  and the number of cards left is %d and its kind is a %s and its pile number is %d\n",
        xpeek, x.getNumberOfCards(), x.getKind(), ((PileAndDrawPileInterface)x).getPileNumber());
    }

    public void push(Card x) {
        Node newN = new Node();
        newN.INFO = x;
        newN.LINK = top;
        top = newN;
        number_of_cards++;
    }

    public boolean checkPush(Card x, Card temp) {
        boolean set_face_up = (x.getFaceUp() == true);
        boolean null_suit = (temp.getSuit() != 'v');
        boolean num_check = (x.getRank() == temp.getRank() - 1);
        boolean color_check = (((x.getColor() == 'B') && (temp.getColor() == 'R')) || ((x.getColor() == 'R') && (temp.getColor() == 'B')));
        if ((set_face_up == true)) {
            if ((temp.getSuit() == 'v') && x.getRank() == 13) {
                return true;
            }

            else if ((null_suit == true) && (num_check == true) && (color_check == true)) {
                return true;
            }

            else {
                return false;
            }
        }

        else {
            return false;
        }
    }

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
              if (top != null && peek_mode == false) {
                 top.INFO.setFaceUp(true);
              }
              number_of_cards--;
              return x;

            }
        return x;
      }

      public Card peek() {
          boolean peek_mode = true;
          boolean suppress_error = true;
          Card x = pop(suppress_error, peek_mode);
          if (x.getSuit() != 'v')
            push(x);
          return x;
      }

}
