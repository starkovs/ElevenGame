/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volsta;

import elevengame.DataStore;
import elevengame.GameInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements GameInterface {

    //Attributes
    private final String name = "Eleven Game";
    private int nCard;
    private Deck deck;
    private static List<Card> table = new ArrayList<>();
    //private Card[] table = new Card[nCards()];

    //Constructor
    public Game() {
        deck = new Deck();
        //fillTable();
    }

    //Private methods
    private void fillTable() {
        try {
            // table = deck.pick(nCard);
            table = deck.pick(nCards());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void isTable() {
        try {
            table = deck.pick(nCard);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    //Public methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int nCards() {
        return DataStore.getNCards();
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }

    @Override
    public String getCardDescriptionAt(int index) {
        return table.get(index).toString();
    }


    @Override
    public boolean anotherPlayIsPossible() {
        return true;    
    }

    @Override
    public boolean playAndReplace(List<Integer> iSelectedCards) {
        int count = iSelectedCards.size();
        if ((count < 2) || (count > 3)) {
            return false;
        }
        if (count == 2) {
            if ((table.get(iSelectedCards.get(0)).getPoints() + table.get(iSelectedCards.get(1)).getPoints()) == 11) {
                //replace those cards by new cards from the deck
                table.remove(iSelectedCards.get(0));
                try {
                    table.add(iSelectedCards.get(0), deck.pick());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                table.remove(iSelectedCards.get(1));
                try {
                    table.add(iSelectedCards.get(1), deck.pick());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } else {
                return false;
            }
        }
        if (count == 3) {
            String[] triple = new String[3];
            for (int i = 0; i <= 2; i++) {
                triple[i] = table.get(i).getValue();
            }
            if (triple.equals(DataStore.getTriple())) {
                //replace those cards by new cards from the deck
                table.remove(iSelectedCards.get(0));
                try {
                    table.add(iSelectedCards.get(1), deck.pick());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                table.remove(iSelectedCards.get(1));
                try {
                    table.add(iSelectedCards.get(1), deck.pick());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                table.remove(iSelectedCards.get(2));
                try {
                    table.add(iSelectedCards.get(1), deck.pick());
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isWon() {
        return table.isEmpty() && deck.isEmpty();
    }
    
    public static String display() {
      StringBuilder builder = new StringBuilder();
      for (int i=1; i <= table.size(); i++) {
         builder.append(i);
         builder.append(". ");
         builder.append(table.get(i - 1).toString());
         builder.append("\n");
      }
      builder.append("*********************************************\n");
      builder.append("Vyberte 2 nebo 3 karty abyste získali 11 bodů.\n");
      builder.append("Zadejte počet vybraných karet a poté jejich cisla:\n");

      return builder.toString();
   }
    

    public static void main(String[] args) {
        Game g = new Game();
        System.out.println(g.getName());
        Deck d = new Deck();
        System.out.println(g.getDeckSize());
        g.fillTable();

        System.out.println(g.getDeckSize());
        System.out.println(g.getCardDescriptionAt(0));
        
        while (!table.isEmpty()) {
            try {
                System.out.print(display());
                // metoda, ve ktere uzivatel napise počet vybraných karet a poté jejich cisla 
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        if (g.isWon()) {
            System.out.println("You won!");
        }
        else{
            System.out.println("You lose");
        }
     
    }
}
