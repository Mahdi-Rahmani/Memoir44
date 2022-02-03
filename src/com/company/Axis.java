package com.company;
/**
 * This class is used for storing the allied information that isn`t supported in PLayer class
 * also this class is a subclass fo Player class
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Axis extends Player{
    // The force manger for allied team
    private ForceManager forceManager;

    /**
     * creat new Axis team with a given player name
     * also we initialize the color and forces of this team
     * @param name name of player
     */
    public Axis(String name)
    {
        super(name);
        forceManager = new ForceManager();
        super.setPlayerColor("white");
        setForces();
    }

    /**
     * set the forces of this team due to the rules of game
     */
    public void setForces()
    {
        forceManager.setAxisForces();
        super.setForces(forceManager.getForces());
    }
}
