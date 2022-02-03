package com.company;
/**
 * this class is used for saving the information related to Artillery
 * Its a subclass for Force class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Artillery extends Force{
    /**
     * creat new Artillery with a given group number
     * @param groupNumber the number of group that other forces of this type inside it
     */
    public Artillery(int groupNumber)
    {
        super(groupNumber);
    }
    /**
     * this method return a string contains the symbol of Artillery forces and the group number
     * @return string
     */
    @Override
    public String toString() {
        return "A" + super.toString();
    }
    /**
     * this method check the limits of works that this type of force has
     * @param numberOfCell the number of cell that the force moves
     * @return boolean value (true if the distance is valid)
     */
    @Override
    public boolean checkAction(int numberOfCell)
    {
        if (numberOfCell > 1) {
            System.out.println("The soldier does`t have this ability to go longer than this");
            super.setAttackAbility(true);;
            return false;
        }
        else if (numberOfCell == 1) {
            super.setAttackAbility(false);;
            return true;
        }
        else {
            super.setAttackAbility(true);;
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
        if (distance > 6 || distance == 1 )
            return 0;
        else if (distance > 4 )
            return 1;
        else if (distance > 2)
            return 4;
        else
            return 3;
    }
}
