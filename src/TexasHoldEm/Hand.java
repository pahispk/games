package TexasHoldEm;

import java.util.ArrayList;
import java.util.List;


public class Hand {
    private List<Card> playerCards = new ArrayList<>();
    
    public void addCard(Card newCard) {
        playerCards.add(newCard);
    }
    @Override
    public String toString(){
        String help="";
        for(Card card:playerCards) {
            help+=card+" ";
        }
        return help;
    }
    
    public List<Card> getCards() {
        return playerCards;
    }
    
    public void reset() {
        playerCards.clear();
    }
    
//    public static void main(String[] args) {
//        Hand eka = new Hand();
//        eka.addCard(new Card (1, "spades"));
//        eka.addCard(new Card (10, "hearts"));
//        System.out.println(eka);
//        System.out.println(eka.getCards().get(1));
//    }
}
