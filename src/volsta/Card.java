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
import elevengame.DataStore;
import java.util.ArrayList;

public class Card {

    private final String value;
    private final String symbol;
    private final int points;

    /**
     * @param value - value of card
     * @param symbol - symbol of card
     * @param points - points of card
     */
    public Card(String symbol, String value, int points) {
        this.value = value;
        this.symbol = symbol;
        this.points = points;
    }

    /**
     * Returns value of card
     *
     * @return String
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns symbol of card
     *
     * @return String
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns points of card
     *
     * @return int
     */
    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return symbol + "-" + value;
    }

    /**
     * Creates list of all possible cards 
     * @return List of cards
     */
    public static ArrayList<Card> allCards() {  
        ArrayList<Card> list = new ArrayList<>();
        String[] values = DataStore.loadValues();
        int[] points = DataStore.loadNPoints();

        for (String symbol : DataStore.loadSymbols()) {
            for (int i = 0; i < values.length; i++) {
                list.add(new Card(symbol, values[i], points[i]));
            }
        }

        return list;
    }


}