package com.company;

/**
 * this class is used for saving the information related to tank
 * Its a subclass for Force class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Tank extends Force{

    /**
     * creat new tank with a given group number
     * @param groupNumber the number of group that other forces of this type inside it
     */
    public Tank(int groupNumber)
    {
        super(groupNumber);
    }

    /**
     * this method return a string contains the symbol of Tank forces and the group number
     * @return string
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }

    /**
     * this method check the limits of works that this type of force has
     * @param numberOfCell the number of cell that the force moves
     * @return boolean value (true if the distance is valid)
     */
    @Override
    public boolean checkAction(int numberOfCell)
    {
        if (numberOfCell > 3) {
            System.out.println("The tank does`t have this ability to go longer than this");
            super.setAttackAbility(true);
            return false;
        }
        else
        {
            super.setAttackAbility(true);
            return true;
        }
    }

    /**
     * Calculate the number of dices due to the distance
     * @param distance the distance between current cell and destination
     * @return the number of dice throwing
     */
    public int diceRelatedDistance(int distance)
    {
        if (distance >3)
            return 0;
        else
            return 3;
    }
}
