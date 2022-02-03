package com.company;

/**
 * This class is used for storing the axis information that isn`t supported in PLayer class
 * also this class is a subclass fo Player class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Allied extends Player{
    // The force manger for allied team
    private ForceManager forceManager;

    /**
     * creat new Allied team with a given player name
     * also we initialize the color and forces of this team
     * @param name name of player
     */
    public Allied(String name)
    {
        super(name);
        super.setPlayerColor("magenta");
        forceManager = new ForceManager();
        setForces();
    }

    /**
     * set the forces of this team due to the rules of game
     */
    public void setForces()
    {
        forceManager.setAlliedForces();
        super.setForces(forceManager.getForces());
    }

}
