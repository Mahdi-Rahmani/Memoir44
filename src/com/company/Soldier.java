package com.company;
/**
 * this class is used for saving the information related to Soldier
 * Its a subclass for Force class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Soldier extends Force{
    /**
     * creat new Soldier with a given group number
     * @param groupNumber the number of group that other forces of this type inside it
     */
    public Soldier(int groupNumber)
    {
        super(groupNumber);
    }

    /**
     * this method return a string contains the symbol of Soldier forces and the group number
     * @return string
     */
    @Override
    public String toString() {
        return "S" + super.toString();
    }
    /**
     * this method check the limits of works that this type of force has
     * @param numberOfCell the number of cell that the force moves
     * @return boolean value (true if the distance is valid)
     */
    @Override
    public boolean checkAction(int numberOfCell)
    {
        if (numberOfCell > 2) {
            System.out.println("The soldier does`t have this ability to go longer than this");
            super.setAttackAbility(true);
            return false;
        }
        else if (numberOfCell == 2) {
            super.setAttackAbility(false);
            return true;
        }
        else if (numberOfCell == 1){
            super.setAttackAbility(true);
            return true;
        }
        else {
            System.out.println("now you doesnt have attack ability . also you didnt move");
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
        if (distance > 3 || distance == 0 )
            return 0;
        else if (distance == 3)
            return 1;
        else if (distance == 2)
            return 2;
        else
            return 3;
    }
}
