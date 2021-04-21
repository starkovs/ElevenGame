/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volsta;

/**
 *
 * @author starkov
 */
import java.util.ArrayList;
import java.util.Collections;

//balíček karet
public class Deck {

    private ArrayList<Card> cards;

    public static Deck initFull() {
        return new Deck();
    }

    public Deck() {
        this.cards = Card.allCards();
        Collections.shuffle(cards);
    }

    /**
     * Is deck empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }
    
    /**
     * Returns size of deck
     * @return 
     */
    public int getSize(){
        return this.cards.size();
    }

    /**
     * Picks specific amount of cards
     * @param count
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException 
     */
   public ArrayList<Card> pick(Integer count) throws IllegalAccessException, IllegalArgumentException {
      if (count < 1) {
         throw new IllegalArgumentException("you cannot pick " + count + " card");
      }
      
      ArrayList<Card> picked = new ArrayList<>();

      for(int i = 0; i < count; i++) {
         picked.add(pick());
      }

      return picked;
   }

    /**
     * Picks card at index
     * @return
     * @throws IllegalAccessException 
     */
    public Card pick() throws IllegalAccessException {
        if (isEmpty()) {
            throw new IllegalAccessException("You cannot pick a card, the deck is empty");
        }

        Card card = cards.get(0);
        cards.remove(0);

        return card;
    }
}