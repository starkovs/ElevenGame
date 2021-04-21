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
public class Table {

    private Card[] cards;

    /**
     * Constructor with existing array as parameter
     * @param cards 
     */
    public Table(Card[] cards){
        this.cards = cards.clone();
    }
    
    /**
     * Constructor with size of table as parameter
     * @param nCard 
     */
    public Table(int nCard){
        cards = new Card[nCard];
    }

    /**
     * Returns number of cards on table
     * @return int - number of cards
     */
    public int getSize(){
        int sum = 0;
        for (int i = 0; i < cards.length; i++) {
            if (getCardAt(i) != null) sum++;
        }
        return sum;
    }
    
    /**
     * Returns card at index
     * @param i - index of card
     * @return Card - card at index
     */
    public Card getCardAt(int i){
        return cards[i];
    }

    /**
     * Is table empty?
     * @return boolean - true if yes
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }
    
    /**
     * Removes card at index and returns that specific card
     * @param i - index of card
     * @return Card - removed card
     */
    public Card removeCardAt(int i){
        Card c = cards[i];
        cards[i] = null;
        return c;
    }
    
    /**
     * Does table contains all specified symbols?
     * @param symbols
     * @return boolean - true if yes
     */
    public boolean doesContainSymbols(String ...symbols){
        boolean doesContain;
        for (String s : symbols) {
            doesContain = false;
            for (int i = 0; i < cards.length; i++) {
                if(getCardAt(i).getSymbol().equals(s)){
                    doesContain = true;
                    break;
                }
            }
            if(!doesContain){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Is it possible to sum points of two cards on table to specific value
     * @param value - value that should be checked
     * @return boolean - true if contains, false if not
     */
    public boolean doesContainPoints(int value){
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                if(i != j && getPointsOfCards(i,j) == value){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns sum of card points
     * @param indexes - indexes of cards to be sumed
     * @return int - sum of points
     */
    public int getPointsOfCards(int ...indexes){   
        int sum = 0;
        for(int i : indexes){
            sum += getCardAt(i).getPoints();
        }
        return sum;
    }
    
}
