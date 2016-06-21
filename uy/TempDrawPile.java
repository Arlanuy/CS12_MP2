package uy;

public class TempDrawPile extends DrawPile {
    private Card[] drawn_cards;

    public TempDrawPile(int pile_number) {
        super(pile_number);
    }

    public Node getTop() {
        return top;
    }

    public Card[] getDrawnCards() {

        return drawn_cards;
    }

    public void setDrawnCards() {
        int num_cards = getNumberOfCards();
        Card[] drawn_cards = new Card[num_cards];
        int j = 0, i = 0;
        if (isEmpty() == false) {
            for (i = num_cards - 1; i >= 0; i--) {

                if ((drawn_card != null) && (drawn_card.getSuit() != 'v')) {

                    drawn_cards[i] = drawn_card;
                    j++;
                }

                else {
                    System.out.println("null on setdrawncards");
                    break;
                }
                drawCard(); //there is  an extra drawn card that has a suit of 'v'
                //so we have to adjust the value of j accordingly below
                System.out.println(drawn_card.toString());
            }
            //removes the null card/s drawn
            System.out.println("num_cards " + (num_cards - 1) + "j " + j + "i " + i);
            if (j != (num_cards + 1)) {
                Card[] drawn_cards2 = new Card[j + 1];
                System.out.println("The cards from the method Temp setDrawnCards are: ");
                for (int k = 0; k < j; k++) {
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
        if (top != null) {
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
            System.out.println();
        }

        else {
            System.out.println("The temp draw pile is currently empty");
        }

    }
}
