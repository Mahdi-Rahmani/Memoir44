package com.company;

import java.util.ArrayList;

/**
 * This class is used for saving the information and objects
 * that player need. player needs some things such as  forces
 * and cards and etc.
 * @author Mahdi Rahmani
 * @version 1.0
 */
public  class Player {
    // the list of forces of player
    private ArrayList<Force> forces;

    // the list of cards of player
    private ArrayList<Card> cards;

    // the name of player
    private String name;

    /* the score of player
    it increases if player get some special cell
    or kill all the forces of enemy in one cell
     */
    private int score;

    // the boolean value for recognizing the turn(if the player turn it has true value)
    private boolean turn;

    // the color related to player(we recognize each team with this)
    private String color;

    /**
     * creat new player with a given name
     * @param name the name of player
     */
    public Player(String name)
    {
        forces = new ArrayList<>();
        cards = new ArrayList<>();
        this.name = name;
        turn = false;
    }

    /**
     * get the list of forces of player
     * @return list of forces
     */
    public ArrayList<Force> getForces() {
        return forces;
    }

    /**
     * set the forces of this player
     * @param forces list of forces
     */
    public void setForces(ArrayList<Force> forces) {
        this.forces = forces;
    }

    /**
     * set the name of player
     * @param name name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the name of player
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * each player has some cards
     * add the card to his/her cards
     * @param card the card object
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * if the player use a card this card must be removed
     * @param card a card of player
     */
    public void removeCard(Card card)
    {
        card.setUsed(true);
        cards.remove(card);
    }

    /**
     * we check the player turn with this method
     * if return true it means that the player turn
     * @return boolean value
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * if become the player turn now we should set his turn false for next set
     * @param turn boolean value(true if player`s turn)
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * get the score of player
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * if we call this method we should give the earned score az an entry to
     * sum up the last score
     * @param score the score of player
     */
    public void setScore(int score) {
        this.score += score;
    }

    /**
     * get the list of cards of player
     * @return cards of player as a list
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    /**
     * set the color of player
     * @param color color of player
     */
    public void setPlayerColor(String color) {
        this.color = color;
    }

    /**
     * get the color of player
     * @return color of player
     */
    public String getColor() {
        return color;
    }

    /**
     * after one set we should set the attack ability of forces true
     */
    public void setForcesAttackAbilityTrue()
    {
        for (Force force:forces)
            force.setAttackAbility(true);
    }

    /**
     * after one set we should set the readiness of forces, false
     */
    public void makeUnreadyForces()
    {
        for (Force force:forces)
            force.setBeReady(false);
    }
}


