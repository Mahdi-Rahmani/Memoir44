package com.company;

/**
 * This class is used for saving the information of each force
 * Its the super class for Tank and Soldier and Artillery classes
 * and contains the similar features and actions between them
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Force {
    // The live of the force
    private boolean live;

    // The group number of forces(each group of forces have a group number)
    private int groupNumber;

    // The cell number that the group of forces are places in
    private int cellNumber;

    // If player is chosen it must be ready to get order
    private boolean beReady;

    // The Color of Forces in form of string
    private String color;

    // The ability to attack
    private boolean attackAbility;

    /**
     * This is the constructor of this class
     * Create a new force with a given group number
     * @param groupNumber  the group number of forces
     */
    public Force(int groupNumber)
    {
        this.groupNumber = groupNumber;
        live = true;
        attackAbility = true;
    }

    /**
     * Set the live of force
     * @param live boolean value for live
     */
    public void setLive(boolean live)
    {
        this.live = live;
    }

    /**
     * get the live of force
     * if the force is living this method return true
     * @return boolean value
     */
    public boolean isLive()
    {
        return live;
    }

    /**
     * get the group number of force
     * @return group number
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * get the readiness of force
     * @return if the force is ready
     */
    public boolean isBeReady() {
        return beReady;
    }

    /**
     * set the readiness of force
     * if the force is chosen we set its readiness true
     * @param beReady the boolean value that state the force is ready or not
     */
    public void setBeReady(boolean beReady) {
        this.beReady = beReady;
    }

    /**
     * return a string that contains the group number
     * @return group number as a string
     */
    @Override
    public String toString() {
        return "("+groupNumber+")";
    }

    /**
     * get the cell number of force that it placed in
     * @return cell number
     */
    public int getCellNumber() {
        return cellNumber;
    }

    /**
     * set the cell number for force
     * @param cellNumber the cell number that the force placed in it
     */
    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    /**
     * check if force can go in that cell or not
     * @param numberOfCell the cell number
     * @return boolean value
     */
    public boolean checkAction(int numberOfCell)
    {
        if (numberOfCell == 0)
            return true;
        else
            return false;
    }

    /**
     * set the color of force due to its team
     * @param color color of team
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * get the color of force
     * @return color as a string
     */
    public String getColor() {
        return color;
    }

    /**
     * set the attack ability of force
     * @param attackAbility the attack ability
     */
    public void setAttackAbility(boolean attackAbility) {
        this.attackAbility = attackAbility;
    }

    /**
     * get the attack ability of force
     * @return attack ability
     */
    public boolean getAttackAbility() {
        return attackAbility;
    }

}
