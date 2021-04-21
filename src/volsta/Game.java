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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements GameInterface {

    //Attributes
    private final String name = "Eleven Game";
    private int nCard;
    private Deck deck;
    //private static List<Card> table = new ArrayList<>();
    private Table table;
    private String[] triple;

    //Constructor
    public Game() {
        deck = Deck.initFull();
        loadData();
        createTable();
    }

    /**
     * Loads data from DataStore
     */
    private void loadData() {
        nCard = DataStore.getNCards();
        triple = DataStore.getTriple();
    }

    /**
     * Creates table
     */
    private void createTable() {
        try {
            table = new Table(deck.pick(nCard));
        } catch (IllegalAccessException | IllegalArgumentException e) {
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
        return nCard;
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }

//    @Override
//    public String getCardDescriptionAt(int index) {
//        return table.get(index).toString();
//    }
//
    @Override
    public String getCardDescriptionAt(int index) {
        Card c = table.getCardAt(index);
        if (c != null) {
            return c.toString();
        }
        return "";
    }

    @Override
    public boolean anotherPlayIsPossible() {
        return table.doesContainPoints(11) || table.doesContainSymbols(triple);
    }

    @Override
    public boolean playAndReplace(List<Integer> iSelectedCards) {
        int count = iSelectedCards.size();
        int[] numbers = new int[iSelectedCards.size()];
        for (int i = 0; i < iSelectedCards.size(); i++) {
            numbers[i] = iSelectedCards.get(i);
        }
        if ((count < 2) || (count > 3)) {
            return false;
        }
        if (count == 2) {
            if (table.getPointsOfCards(numbers) == 11) {
                //replace those cards by new cards from the deck
                table.removeCardAt(iSelectedCards.get(0));
//                table.add(iSelectedCards.get(0), deck.pick());
                table.removeCardAt(iSelectedCards.get(1));
//                table.add(iSelectedCards.get(1), deck.pick());

                return true;
            } else {
                return false;
            }
        }
        if (count == 3) {
            String[] triple = new String[3];
            for (int i = 0; i <= 2; i++) {
                triple[i] = table.getCardAt(i).getValue();
            }
            if (triple.equals(this.triple)) {
                //replace those cards by new cards from the deck
                table.removeCardAt(iSelectedCards.get(0));
                //table.add(iSelectedCards.get(0), deck.pick());
                table.removeCardAt(iSelectedCards.get(1));
                //table.add(iSelectedCards.get(1), deck.pick());
                table.removeCardAt(iSelectedCards.get(2));
                //table.add(iSelectedCards.get(2), deck.pick());
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

    public String display() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= table.getSize(); i++) {
            builder.append(i);
            builder.append(". ");
            builder.append(table.getCardAt(i - 1).toString());
            builder.append("\n");
        }
        builder.append("*********************************************\n");
        builder.append("Vyberte 2 nebo 3 karty abyste získali 11 bodů.\n");
        builder.append("Zadejte počet vybraných karet a poté jejich cisla:\n");

        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game g = new Game();
        System.out.println(g.getName());
        Deck d = new Deck();
        System.out.println(g.getDeckSize());
        List<Integer> iSelectedCards = new ArrayList<>();
        while (!table.isEmpty()) {
            try {
                //System.out.print(display());
                // metoda, ve ktere uzivatel napise počet vybraných karet a poté jejich cisla
                int count = sc.nextInt();
                for (int i = 0; i < count; i++) {
                    iSelectedCards.add(sc.nextInt());
                }
                //g.playAndReplace(iSelectedCards)

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        if (g.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lose");
        }

    }
}
