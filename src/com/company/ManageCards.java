package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * this class is used for managing the cards
 * also in this class we creat a list of cards for game
 * in this class we take a random card if each player needs
 * also we can shuffle the cards if all of cards are used
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class ManageCards {
    // list of all cards
    private Card[] cards;

    /**
     * Create a mange card
     * if this object is created we creat a list of cards and
     * initialize them
     */
    public ManageCards() {
        cards = new Card[40];
        initializeArrayOfCard();
        setCards();
    }

    /**
     * initialize the cards objects
     */
    public void initializeArrayOfCard()
    {
        for (int i = 0; i < 40; i++)
        {
            Card card = new Card();
            cards[i] = card;
        }
    }

    /**
     * set the cards
     * it means shuffle the cards
     */
    public void setCards() {
        for (int i = 0; i < 40; i++) {
            cards[i].setUsed(false);
            cards[i].setCardNumber(i);
            if (i < 6) {
                cards[i].setType(Card.Type.ORDER_1_UNIT);
                cards[i].setColor();
            } else if (i < 19) {
                cards[i].setType(Card.Type.ORDER_2_UNIT);
                cards[i].setColor();
            } else if (i < 29) {
                cards[i].setType(Card.Type.ORDER_3_UNIT);
                cards[i].setColor();
            } else if (i < 35) {
                cards[i].setType(Card.Type.ORDER_4_UNIT);
                cards[i].setColor();
            } else {
                cards[i].setType(Card.Type.ORDER_3_UNIT_ONE_KIND);
                cards[i].setColor();
            }
        }
    }

    /**
     * get a random card from the list
     * if we dont have any cards then shuffle them
     * @return a card
     */
    public Card getRandomCard()
    {
        Random randomGenerator = new Random();
        int counter= 0;
        //checking if all cards are used or not
        for (Card card : cards)
        {
            if (card.isUsed())
                counter++;
        }
        //if all cards are used we set new cards
        if (counter == 40)
            setCards();
        //return random card
        while (true)
        {
            int index = randomGenerator.nextInt(40);
            if (!cards[index].isUsed())
                return cards[index];
        }
    }

    /**
     * show the cards
     * @param cards card object
     */
    public void showCards(ArrayList<Card> cards)
    {
        String[] rows = new String[5];
        for (int i =0 ; i<5; i++)
            rows[i] = "";
        for (Card card: cards)
        {
            rows[0] += card.myString()[0];
            rows[1] += card.myString()[1];
            rows[2] += card.myString()[2];
            rows[3] += card.myString()[3];
            rows[4] += card.myString()[4];
        }
        for (int i =0 ; i<5; i++)
            System.out.println(rows[i]);

    }

}
