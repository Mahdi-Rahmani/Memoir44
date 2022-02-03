package com.company;

/**
 * This class is used for saving Card information
 * Each card has some information such as type(ORDER_1_UNIT and etc.),color, number and ...
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Card {
    // The enum for saving the types of Cards
    enum Type{
        ORDER_1_UNIT,
        ORDER_2_UNIT,
        ORDER_3_UNIT,
        ORDER_4_UNIT,
        ORDER_3_UNIT_ONE_KIND;
    }

    // The variable From enum type  for saving the type of card
    private Type type;

    // The boolean value for saving if the card is used or not
    private boolean isUsed;

    // The color of Card
    private Color color;

    // The card number
    private int cardNumber;

    /**
     * This is the constructor of Card class
     * Create new Card
     */
    public Card()
    {
        isUsed = false;
    }

    /**
     * set the type of card
     * @param type type of card
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * get the type of card
     * @return Card type
     */
    public Type getType() {
        return type;
    }

    /**
     * set the card using
     * @param used if the card is used we give true value as a parameter
     */
    public void setUsed(boolean used) {
        isUsed = used;
    }

    /**
     * get the boolean value that states us if the card is used or not
     * @return boolean value
     */
    public boolean isUsed() {
        return isUsed;
    }

    /**
     * set the color of card
     */
    public void setColor() {
        switch (type)
        {
            case ORDER_1_UNIT:
                this.color = Color.YELLOW;
                break;
            case ORDER_2_UNIT:
                this.color = Color.BLUE;
                break;
            case ORDER_3_UNIT:
                this.color = Color.PURPLE;
                break;
            case ORDER_4_UNIT:
                this.color = Color.RED;
                break;
            case ORDER_3_UNIT_ONE_KIND:
                this.color = Color.GREEN;
                break;
        }
    }

    /**
     * set the number of card
     * @param cardNumber the card number
     */
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * getthe card number
     * @return the card number
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * This method return a string that contains the type of card and its number
     * @return A string
     */
    @Override
    public String toString() {
        return type.toString() + " " + cardNumber;
    }

    /**
     * this method return a string array that contains the shape of card
     * and some information that must player know
     * @return a string array
     */
    public String[] myString()
    {
        String[] card = new String[5];
        String printColor = color.getPrintColor();
        card[0] = printColor;
        card[1] = printColor;
        card[2] = printColor;
        card[3] = printColor;
        card[4] = printColor;


        card[0] += "╔════════════════════════╗   ";
        card[0] += Color.RESET.getPrintColor();
        if (cardNumber<10)
            card[1] += "║       " +cardNumber + "                ║   ";
        else
            card[1] += "║       " +cardNumber + "               ║   ";
        card[1] += Color.RESET.getPrintColor();
        card[2] += "║                        ║   ";
        card[2] += Color.RESET.getPrintColor();
        if (type.equals(Type.ORDER_3_UNIT_ONE_KIND))
            card[3] += "║ "+type +"  ║   ";
        else
            card[3] += "║      "+type +"      ║   ";
        card[3] += Color.RESET.getPrintColor();
        card[4] += "╚════════════════════════╝   ";
        card[4] += Color.RESET.getPrintColor();
        return  card;
    }
}
