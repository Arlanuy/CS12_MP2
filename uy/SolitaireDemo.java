//Arlan UY 2015-09385
package uy;
import java.util.*;
import java.io.*;
import javax.swing.*;

/**
* This is the entry program of this Solitaire Game
* @author  Arlan Uy
* @version 1.8.0_60
*/

public class SolitaireDemo{
    private static SolitaireGUI game_display;
    private static LinkedStack[] piles = new Pile[7]; // the playing area piles
    private static LinkedStack draw_pile = new DrawPile(1); //the main draw pile
    private static DrawPile sub_draw_pile = new SubDrawPile(3);
    // the pile consisting of 3 cards  wherein these are the only cards
    //you can use at a certain moment of playing
    //the temporary pile where the cards from the sub_draw_pile will go after choosing 0 in the selection of the mainMenu0
    private static LinkedStack[] suit_piles = new SuitPile[4]; // the piles needed to be satisfied to achieve
    private static char[] suitschar = new char[4];
    private static SuitPileAndPileInterface[] grouped_piles = new SuitPileAndPileInterface[11];
    private static MainDrawPilePanel main_draw_pile_panel = new MainDrawPilePanel();
    private static SubDrawPilePanel sub_draw_pile_panel = new SubDrawPilePanel();
    private static SuitPilesField suit_piles_field = new SuitPilesField();
    private static SuitPilePanel suit_piles_panel[] = new SuitPilePanel[4];
    private static PilesField piles_field = new PilesField();
    private static PilePanel piles_panel[] = new PilePanel[7];
    private static boolean restarted = false, check_win = false, renew_sub_draw_pile = false, prepared_game_already = false;;
    private static SolitaireGameCards prepared_game = new SolitaireGameCards(draw_pile, sub_draw_pile, piles, suit_piles, grouped_piles);


    /**
    * Entry point in this class. Gives the overview of the program.
    * */

      public static void main(String[] args) {
          if (check_win == false) {
              draw_pile = prepared_game.getDrawPile();
              sub_draw_pile = prepared_game.getSubDrawPile();
              piles = prepared_game.getPiles();
              suit_piles = prepared_game.getSuitPiles();
              createSuits(suitschar);
              assignStacks (piles, suit_piles, suitschar, grouped_piles);
              grouped_piles = prepared_game.getGroupedPiles();
              ((DrawPile)draw_pile).setMainDrawPilePanel(main_draw_pile_panel);
              ((SubDrawPile)sub_draw_pile).setMainDrawPile(draw_pile);
              ((SubDrawPile)sub_draw_pile).setSubDrawPilePanel(sub_draw_pile_panel);
              piles_field.assignPiles((LinkedStack[])piles, piles_panel);
              suit_piles_field.assignSuitPiles((LinkedStack[])suit_piles, suit_piles_panel);
              if (prepared_game_already ==  false) {
                  prepared_game = prepareGame(prepared_game);
                  prepared_game_already = true;
              }
              check_win = prepared_game.getCheckWin();
              prepared_game = new SolitaireGameCards(draw_pile, sub_draw_pile, piles, suit_piles, grouped_piles);
          }

        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            game_display = new SolitaireGUI();
          }
        });
      }

    public static void createSuits(char[] suitschar) {
        suitschar[0] = 'H';
        suitschar[1] = 'S';
        suitschar[2] = 'D';
        suitschar[3] = 'F';
    }

    /**
    * Creates the stacks needed by the program
    * @param piles  the piles to where you can put an arrangement of cards (must be arranged from King to Ace)
    * @param suit_piles  the piles to where you can put an arrangement of cards that can make you won the game upon
    * filling each of the four piles by 13 cards from the same suit (must be arranged from  Ace to  King)
    * @param suitschar  array of the suits used in the cards
    * @param grouped_piles  the only methods in this interface are checkPush() and getKind()
    */

    public static void assignStacks (LinkedStack[] piles, LinkedStack[] suit_piles, char[] suitschar, SuitPileAndPileInterface[] grouped_piles) {
        for (int i = 0; i < 7; i++) {
            grouped_piles[i] = new Pile(i + 1);
            piles[i] = (LinkedStack)grouped_piles[i];
        }

        for (int i = 0; i < 4; i++) {
            grouped_piles[i + 7] = new SuitPile(suitschar[i]);
            suit_piles[i] = (LinkedStack)grouped_piles[i + 7];
        }
    }
    /**
    public static void printScreen(LinkedStack drawpile, DrawPile sub_draw_pile, LinkedStack[] piles, LinkedStack[] suit_piles) {
        System.out.println();
        System.out.println("Drawn Cards");
        sub_draw_pile.printContent();
        System.out.println();
        System.out.println("Suit Piles");
        for (int i = 0; i < 4; i++) {
            System.out.print(((SuitPile)suit_piles[i]).getSuitPile() + ".) ");
            suit_piles[i].printContent();
        }
        System.out.println();
        System.out.println("Piles");
        for (int i = 0; i < 7; i++) {
            System.out.print(((Pile)piles[i]).getPileNumber() + ".) ");
            piles[i].printContent();
        }
        System.out.println();
    }

    public static int checkMenuNum(int upper_bound) {
              Scanner console = new Scanner(System.in);
              String str = "";
              int num = -1;
              while (console.hasNextLine()) {
                 if (console.hasNextInt()){
                    str= console.next();
                     num = Integer.parseInt(str);
                     if (num >= 0 && num < upper_bound + 1) {
                         return num;
                     }

                     else {
                         System.out.println("Please enter only an integer between 0 and " + upper_bound + " inclusive on next line");
                     }

                 }
                else {
                    System.out.println("Please enter an integer");
                    str= console.next();
                }
            }
            return num;
        }

        public static int checkNum(int upper_bound) {
              Scanner console = new Scanner(System.in);
              String str = "";
              int num = 0;
              while (console.hasNextLine()) {
                 if (console.hasNextInt()){
                    str= console.next();
                     num = Integer.parseInt(str);
                     if (num > 0 && num < upper_bound + 1) {
                         return num;
                     }

                     else {
                         System.out.println("Please enter only an integer between 1 and " + upper_bound + " inclusive on next line");
                     }

                 }
                else {
                    System.out.println("Please enter an integer");
                    str= console.next();
                }
            }
            return num;
        }

        public static char checkSuit() {
              Scanner console = new Scanner(System.in);
              String str = "";
              int num = 0;
              char suit = 'v';
              while (console.hasNextLine()) {
                    str= console.next();
                    str = str.trim();
                    if (str.length() == 1) {
                        String diamond = Character.toString('D');
                        String flower = Character.toString('F');
                        String hearts = Character.toString('H');
                        String spades = Character.toString('S');

                        if (str.equalsIgnoreCase(diamond)) {
                            suit = 'D';
                            break;
                        }

                        else if (str.equalsIgnoreCase(spades)) {
                            suit = 'S';
                            break;
                        }

                        else if (str.equalsIgnoreCase(hearts)) {
                            suit = 'H';
                            break;
                        }

                        else if (str.equalsIgnoreCase(flower)){
                            suit = 'F';
                            break;
                        }


                        else {
                            System.out.println("Please enter only a single character from the suits");

                        }

                    }

                    else {
                        System.out.println("Please enter only a single character from the suits");
                    }


                 }
            return suit;

        } */
            /**
        public static void printMenu() {
            System.out.println();
            System.out.println("Enter 0 to draw card/s");
            System.out.println("Enter 1 to move the top of the drawn cards to the corresponding suit stack");
            System.out.println("Enter 2 to move the top of the drawn cards to one of the piles");
            System.out.println("Enter 3 to move a card from one of the piles to the suit stack");
            System.out.println("Enter 4 to move card(s) from one of the piles to another pile");
            System.out.println("Enter 5 to move a card from a suit stack to one of the piles");
             System.out.println("Enter 6 to withdraw and deal the cards again");
             System.out.println();
        }

        public static SolitaireGameCards getMenuChoice(SolitaireGameCards game) {
            LinkedStack[] piles = game.getPiles();
             DrawPile sub_draw_pile = game.getSubDrawPile();
            LinkedStack draw_pile = game.getDrawPile();
            LinkedStack[] suit_piles = game.getSuitPiles();
            SuitPileAndPileInterface[] grouped_piles = game.getGroupedPiles();
            SolitaireGameCards restarted_game = new SolitaireGameCards();
            ((SubDrawPile)sub_draw_pile).setMainDrawPile(draw_pile);
            boolean restarted = false, check_win = false, renew_sub_draw_pile = false;
            int num_menu = -1, pile_num = 0;
            printScreen(draw_pile, sub_draw_pile, piles, suit_piles);
            printMenu();
            System.out.println("Enter a number from the menu: ");
            while (restarted == false) {
               //num_menu = checkMenuNum(6);
               switch (num_menu) {
                   case 0:
                      doSubMenu0Choice(sub_draw_pile);
                      break;

                   case 1:
                        doSubMenu1Choice(suit_piles, sub_draw_pile);
                        break;

                  case 2:
                      System.out.println("Give the number to where you want to put the card on the top of your drawn card/s");
                      pile_num = checkNum(7) - 1;
                      doSubMenu2Choice(piles[pile_num], sub_draw_pile);
                      break;


                   case 3:
                      System.out.println("Enter the pile number to where the card will be taken: ");
                      pile_num = checkNum(7) - 1;
                      doSubMenu3Choice(piles[pile_num], suit_piles);
                      break;

                  case 4:
                      System.out.println("Enter the pile number to where the card(s) will be taken: ");
                      int src_pile_num = checkNum(7) - 1;
                      System.out.println("Enter the pile number to where the card(s) will go: ");
                      int dest_pile_num = checkNum(7) - 1;
                      doSubMenu4Choice(piles[src_pile_num], piles[dest_pile_num]);
                      break;

                  case 5:
                    System.out.println("Enter the suit pile character to where the card will be taken: ");
                    char suit_pile_char = checkSuit();
                    System.out.println("Enter the pile number to where the card(s) will go: ");
                    int destin_pile_num = checkNum(7) - 1;
                    doSubMenu5Choice(piles[destin_pile_num], suit_piles, suit_pile_char);
                    break;

                   case 6:
                      restarted_game = withdrawAndDealAgain();
                      restarted = true;
                      break;
                   }
                   printScreen(draw_pile, sub_draw_pile, piles, suit_piles);
                   if (restarted == false) {
                       printMenu();
                       System.out.println("Enter a number from the menu: ");
                   }
                   check_win = checkWin(suit_piles);
              }
              if (check_win == true) {
                  System.out.println("Congratulations you won!!!");
                  restarted_game.setCheckWin();
              }
              return restarted_game;
        } */
        /**
        public static void doSubMenu0Choice(LinkedStack sub_draw_pile) {
            if (sub_draw_pile.isEmpty() == false) {
                ((SubDrawPile)sub_draw_pile).pushTempDrawnCards();
                //upon doing this, the private field of this namely, main_draw_pile is also gone
                //and because  of this we first referred to it through the draw_pile variable
                //and used again the setMainDrawPile method below to set the new draw pile that has the original content before this line 's execution'
                sub_draw_pile.initialize();
            }
          ((SubDrawPile)sub_draw_pile).drawMainPileCards();
        }

        public static void doSubMenu1Choice(LinkedStack[] suit_piles, LinkedStack sub_draw_pile) {
            boolean suppress_error = false, peek_mode = false;
            Card x = ((DrawPile)sub_draw_pile).getDrawnCard();
            Card temp = new Card();
            if (x == null) {
                try {
                    throw new StackUnderFlowException(sub_draw_pile);
                }

                catch (StackUnderFlowException e) {

                }
            }

            else {
                for (int i = 0; i < 4; i++) {
                    temp = suit_piles[i].peek();
                    if (((SuitPile)suit_piles[i]).getSuitPile() == x.getSuit()) {
                        if (((SuitPileAndPileInterface)suit_piles[i]).checkPush(x, temp)) {
                            suit_piles[i].push(x);
                            Card y = sub_draw_pile.pop(suppress_error, peek_mode);
                            if (sub_draw_pile.isEmpty() == true) {
                                DrawPile temp_draw_pile = ((SubDrawPile)sub_draw_pile).getTempDrawPile();
                                Card z =  temp_draw_pile.pop(suppress_error, peek_mode);
                                if (z.getSuit() != 'v') {
                                    sub_draw_pile.push(z);
                                }
                            }
                        }

                        else {
                            System.out.println("No suit stack is suitable to your card");
                        }
                    }
                }
            }


        }

        public static void doSubMenu2Choice(LinkedStack pile, DrawPile sub_draw_pile) {
            boolean suppress_error = false, peek_mode = false;
            Card x = sub_draw_pile.peek();
            Card temp = pile.peek();

            if (x == null) {
                try {
                    throw new StackUnderFlowException(sub_draw_pile);
                }

                catch (StackUnderFlowException e) {

                }
            }

            else {
                if (((SuitPileAndPileInterface)pile).checkPush(x, temp)) {
                    pile.push(x);
                    sub_draw_pile.pop(suppress_error, peek_mode);
                    if (sub_draw_pile.isEmpty() == true) {
                        DrawPile temp_draw_pile = ((SubDrawPile)sub_draw_pile).getTempDrawPile();
                        Card z =  temp_draw_pile.pop(suppress_error, peek_mode);
                        if (z.getSuit() != 'v') {
                            sub_draw_pile.push(z);
                        }
                    }
                }

                else {
                    try {
                        throw new InvalidPushException(x, pile);
                    }

                    catch (InvalidPushException e){
                    }
                }
            }

        }

    public static void doSubMenu3Choice(LinkedStack pile, LinkedStack[] suit_piles) {
        int num_menu = -1, pile_num = 0;
        boolean do_again = true, suppress_error = true, peek_mode = false;
        Card x = pile.peek();
        if (x.getSuit() == 'v')
            System.out.println("The pile you want to take your card from is empty");

        else {
            Card temp;
            for (int i = 0; i < 4; i++) {
                temp = suit_piles[i].peek();
                if (((SuitPile)suit_piles[i]).getSuitPile() == x.getSuit()) {
                    if (((SuitPileAndPileInterface)suit_piles[i]).checkPush(x, temp)) {
                        (suit_piles[i]).push(x);
                        pile.pop(suppress_error, peek_mode);
                    }

                    else {
                        try {
                            System.out.println("No suit stack is suitable to your card: " + x.toString());
                            throw new InvalidPushException(x, suit_piles[i]);
                        }

                        catch (InvalidPushException e){
                        }
                }
            }

         }
        }

}

    public static void doSubMenu4Choice(LinkedStack srcpile, LinkedStack destpile) {
        int num_menu = -1;
        boolean do_again = true, suppress_error = true, peek_mode = false;
        LinkedStack reverser = new Pile();
        int src_num_cards = srcpile.getNumberOfCards(), transfer_cards = 0;
        Node face_up_finder = srcpile.getTop();
        for(int i = 0; i < src_num_cards; i++) {
            if (face_up_finder.INFO.getFaceUp() == true) {
                transfer_cards++;
            face_up_finder = face_up_finder.LINK;
            }
        }
        System.out.println("How many cards: ");
        if (transfer_cards == 0) {
            System.out.println("Your source pile is empty");
        }

        else{
            int num_cards = checkNum(transfer_cards);
            Node bulk_peeker = srcpile.getIndexNode(num_cards);
            Card src_card = bulk_peeker.INFO;
            Card dest_card = destpile.peek();
            if (((SuitPileAndPileInterface)srcpile).checkPush(src_card, dest_card)) {
                System.out.println("The cards: ");
                for (int j = 0; j < num_cards; j++) {
                    Card temp = srcpile.pop(suppress_error, peek_mode);
                    System.out.print(temp.toString() + " ");
                    reverser.push(temp);
                }
                System.out.println("is pushed to pile number " + ((Pile)destpile).getPileNumber());

                for (int k = 0; k < num_cards; k++) {
                    Card temp = reverser.pop(suppress_error, peek_mode);
                    destpile.push(temp);
                }

            }

            else {
                try {
                    System.out.println("The chosen destination pile is not is suitable to your card: " + src_card.toString());
                    throw new InvalidPushException(src_card, destpile);
                }

                catch (InvalidPushException e){
                }
            }
        }

    }



    public static void doSubMenu5Choice (LinkedStack pile, LinkedStack[] suit_piles, char suit_pile_char) {
        int num_menu = -1;
        boolean do_again = true, suppress_error = true, peek_mode = false;
        Card temp = pile.peek();
        Card x;
        int i;
        for (i = 0; i < 4; i++) {
            if (((SuitPile)suit_piles[i]).getSuitPile() == suit_pile_char) break;
        }
        LinkedStack src_suit_pile = suit_piles[i];
        x = src_suit_pile.peek();
        if (x.getSuit() == 'v')
            System.out.println("The pile you want to take your card from is empty");

        else {

            if (((SuitPileAndPileInterface)pile).checkPush(x, temp)) {
                pile.push(x);
                src_suit_pile.pop(suppress_error, peek_mode);
            }

            else {
                try {
                    System.out.println("The indicated pile is not suitable to your card: " + x.toString());
                    throw new InvalidPushException(x, pile);
                }

                catch (InvalidPushException e){
                }
            }
        }
    } */

    /**
    * Creates a new set of stacks to implement the game again
    * @return the restarted game
    */
    public static SolitaireGameCards withdrawAndDealAgain() {
        LinkedStack[] piles = new Pile[7];
        LinkedStack draw_pile = new DrawPile(1);
        DrawPile sub_draw_pile = new SubDrawPile(3);
        LinkedStack[] suit_piles = new SuitPile[4];
        char[] suitschar = new char[4];
        createSuits(suitschar);
        SuitPileAndPileInterface[] grouped_piles = new SuitPileAndPileInterface[11];
        assignStacks (piles, suit_piles, suitschar, grouped_piles);
        SolitaireGameCards restarted_game = new SolitaireGameCards(draw_pile, sub_draw_pile, piles, suit_piles, grouped_piles);
        return restarted_game;
    }


    /**
    * Assigns a deck number for each and every card in the draw_pile and pops the card
    * corresponding to the deck_number entered as one of the method's arguments
    * @param draw_pile  pile where the program draws its cards from
    * @return the new draw_pile having all its cards assigned to a particular deck number */

    public static DrawPile deckNumberSetter(LinkedStack draw_pile) {
        boolean add_cardfield = true;
        DrawPile new_draw_pile = new DrawPile(0, add_cardfield);
        ((DrawPile)new_draw_pile).setMainDrawPilePanel(main_draw_pile_panel);
        int quant_of_cards = draw_pile.getNumberOfCards();
        //System.out.println("quant_of_cards " + quant_of_cards);
        boolean suppress_error = true, peek_mode = false;
        for (int j = 1; j <= quant_of_cards; j++) {
            Card temp = draw_pile.pop(suppress_error, peek_mode);
            temp.setDeckNumber(j);
            new_draw_pile.push(temp);
        }
        //System.out.println();
        return new_draw_pile;
    }

    public static int randomWithRange(int min, int max)
    {
       int range = (max - min) + 1;
       return (int)(Math.random() * range) + min;
   }

    public static LinkedStack shuffle(LinkedStack draw_pile) {
        boolean suppress_error = false, peek_mode = false;
        LinkedStack shuffled_draw_pile = new DrawPile(2);
        int number_of_cards = draw_pile.getNumberOfCards();
        for(int i=1; i<=number_of_cards; i++) {
            int cards_left = draw_pile.getNumberOfCards();
            int deck_number = randomWithRange(1, cards_left);
            Card temp = ((DrawPile)draw_pile).popCardNumber(deck_number, suppress_error);
            if (temp.getSuit() != 'v') {
                draw_pile = deckNumberSetter(draw_pile);
                ((DrawPile)shuffled_draw_pile).push(temp);
            }
        }
        return shuffled_draw_pile;
    }

    /**
    * Distributes  the card from the draw_pile to  the piles
    * @param game  the current stacks used by the program, I desperately need this variable because in Java everything is passed by value
    * what I mean by that is that is like for example when main passed the game variable as an argument to this prepareGame method
    * and then suppose in this method the return type is void and I used inside here the statement "game = prepared_game"
    * then soon after the execution of prepareGame ends, leaving the execution back to main then what happens is that back in
    * the main method, game isn't really referenced to variable "prepared_game" but instead to the original variable game instead
    * before the execution of prepareGame. This can be solved by using return statement though, I can only return one variable and hence
    * I grouped the piles as one in the object datatype SolitaireGameCards
    * @return      the prepared game */

    public static SolitaireGameCards prepareGame(SolitaireGameCards game) {
        LinkedStack[] piles = game.getPiles();
        LinkedStack draw_pile = game.getDrawPile();
        DrawPile sub_draw_pile = game.getSubDrawPile();
        LinkedStack[] suit_piles = game.getSuitPiles();
        SuitPileAndPileInterface[] grouped_piles = game.getGroupedPiles();
        boolean suppress_error = true, peek_mode = false;
        char color, suit, suit_filename, rank_filename;
        boolean faceup;
        Card new_card;
        CardField card_field;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                switch (i) {
                    case 0:
                        suit = 'H';
                        suit_filename = '0';
                        break;
                    case 2:
                        suit = 'S';
                        suit_filename = '2';
                        break;
                    case 1:
                        suit = 'D';
                        suit_filename = '1';
                        break;
                    case 3:
                        suit = 'F';
                        suit_filename = '3';
                        break;
                    default:
                        suit = 'v';
                }
                if (i < 2) {
                    color = 'R';
                }

                else {
                    color = 'B';
                }

                if (j == 1) {
                   rank_filename = 'A';
                }

                else if (j == 10) {
                    rank_filename = 'T';
                }

                else if (j == 11) {
                    rank_filename = 'J';
                }

                else if (j == 12) {
                    rank_filename = 'Q';
                }

                else if (j == 13) {
                    rank_filename = 'K';
                }

                else {
                    rank_filename = ' ';
                }

                String img_filename = "";
                 if (rank_filename != ' ') {
                     img_filename = String.format(i + "-" + rank_filename + ".png");
                     System.out.println("img_filename is " + img_filename);
                     card_field = new CardField(img_filename);
                     new_card = new Card(false, color, suit, j, card_field);
                 }

                 else if (suit != 'v'){
                     img_filename = String.format(i + "-" + j + ".png");
                     card_field = new CardField(img_filename);
                     new_card = new Card(false, color, suit, j, card_field);
                     card_field.setCardLogic(new_card);
                 }

                 else {
                    new_card = new Card();
                 }
                draw_pile.push(new_card);
            }

        }
        draw_pile = deckNumberSetter(draw_pile);
        int very_new_quant_of_cards = draw_pile.getNumberOfCards();
        //System.out.println("latest quant_of_cards: " + very_new_quant_of_cards);
        draw_pile = shuffle(draw_pile);
        int shufled_quant_of_cards = draw_pile.getNumberOfCards();
        //System.out.println("shuffled quant_of_cards: " + shufled_quant_of_cards);

        for (int i = 1; i < 8; i++) {
                for (int j = 0; j < i; j++) {
                Card x = draw_pile.pop(suppress_error, peek_mode);
                if (j != i - 1) {
                    x.setFaceUp(false);
                }
                piles[i - 1].push(x);
                }
            }
        SolitaireGameCards prepared_game = new SolitaireGameCards (draw_pile, sub_draw_pile, piles, suit_piles, grouped_piles);
        return prepared_game;
    }

    public static boolean checkWin(LinkedStack[] suit_piles) {
        for (int i = 0; i < 4; i++) {
            if (((SuitPile)suit_piles[i]).checkForWin() == false) {
                return false;
            }
        }
        return true;
    }
}
