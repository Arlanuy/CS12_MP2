package uy;

/**
* Contains the cards needed  to be used and displayed on the actual proogram as well as the state of the game (victory or  withdraw);
* @author  Arlan Uy
* @version 1.8.0_60
*/
public class SolitaireGameCards {
    private LinkedStack draw_pile;
    private DrawPile sub_draw_pile;
    private LinkedStack[] piles;
    private LinkedStack[] suit_piles;
    private SuitPileAndPileInterface[] grouped_piles;
    private boolean check_win;

    public SolitaireGameCards() {
        this.check_win = false;
    }

    public SolitaireGameCards(LinkedStack draw_pile,  DrawPile sub_draw_pile, LinkedStack[] piles,
     LinkedStack[] suit_piles, SuitPileAndPileInterface[] grouped_piles) {
         this.draw_pile = draw_pile;
         this.sub_draw_pile = sub_draw_pile;
         this.piles = piles;
         this.suit_piles = suit_piles;
         this.grouped_piles = grouped_piles;
         this.check_win = false;
     }

     public boolean getCheckWin() {
         return check_win;
     }

     public void setCheckWin() {
         check_win = true;
     }

     public DrawPile getSubDrawPile() {
         return sub_draw_pile;
     }

     public LinkedStack getDrawPile() {
         return draw_pile;
     }

     public LinkedStack[] getPiles() {
         return piles;
     }

     public LinkedStack[] getSuitPiles() {
         return suit_piles;
     }

     public SuitPileAndPileInterface[] getGroupedPiles() {
         return grouped_piles;
     }

     public void setSubDrawPile(DrawPile sub_draw_pile) {
         this.sub_draw_pile = sub_draw_pile;
     }

     public void setDrawPile(LinkedStack draw_pile) {
         this.draw_pile = draw_pile;
     }

     public void setPiles(LinkedStack[] piles) {
         this.piles = piles;
     }

     public void setSuitPiles(LinkedStack[] suit_piles) {
         this.suit_piles = suit_piles;
     }

     public void setGroupedPiles(SuitPileAndPileInterface[] grouped_piles) {
         this.grouped_piles = grouped_piles;
     }


}
