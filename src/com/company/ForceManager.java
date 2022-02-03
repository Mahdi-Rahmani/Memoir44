package com.company;

import java.util.ArrayList;

/**
 * in this class we manage the forces and the groups of them
 * we make forces group with suitable method
 * this groups are made due to the rules of game and type of player(axis or allied)
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class ForceManager {
    // the list of forces of player
    private ArrayList<Force> forces;

    /**
     * creat new force manager
     */
    public ForceManager()
    {
        forces = new ArrayList<>();
    }
    /**
     * we make a group of soldier with a given group number with this method
     * also we know each group of soldiers have four forces
     * @param groupNumber the group number
     */
    public void addSoldierGroup(int groupNumber)
    {
        for (int i =0; i<4;i++)
        {
            Soldier soldier = new Soldier(groupNumber);
            forces.add(soldier);
        }
    }
    /**
     * we make a group of Artillery with a given group number with this method
     * also we know each group of artillerys have two forces
     * @param groupNumber the group number
     */
    public void addArtilleryGroup(int groupNumber)
    {
        for (int i =0; i<2;i++)
        {
            Artillery artillery= new Artillery(groupNumber);
            forces.add(artillery);
        }
    }
    /**
     * we make a group of Tanks with a given group number with this method
     * also we know each group of tanks have four forces for axis team
     * @param groupNumber the group number
     */
    public void addAxisTankGroup(int groupNumber)
    {
        for (int i =0; i<4;i++)
        {
            Tank tank = new Tank(groupNumber);
            forces.add(tank);
        }
    }
    /**
     * we make a group of Tanks with a given group number with this method
     * also we know each group of tanks have three forces for allied team
     * @param groupNumber the group number
     */
    public void addAlliedTankGroup(int groupNumber)
    {
        for (int i =0; i<3;i++)
        {
            Tank tank = new Tank(groupNumber);
            forces.add(tank);
        }
    }

    /**
     * set the forces of axis team
     * also we set the color of forces due to its team
     */
    public void setAxisForces()
    {
        for (int i =0 ; i<7 ; i++)
        {
            addSoldierGroup(i);
            if (i<6)
                addAxisTankGroup(i);
        }
        colorOfPlayerForce("white");
    }

    /**
     * set the forces of allied team
     * also we set the color of forces due to its team
     */
    public void setAlliedForces() {
        for (int i =0 ; i<9 ; i++)
        {
            addSoldierGroup(i);
            if (i<3)
                addAlliedTankGroup(i);
            if (i<2)
                addArtilleryGroup(i);
        }
        colorOfPlayerForce("magenta");
    }

    /**
     * get the list of forces of player
     * @return forces
     */
    public ArrayList<Force> getForces() {
        return forces;
    }

    /**
     * set the color of forces
     * this color show that the forces for which team
     * @param color color of team
     */
    public void colorOfPlayerForce(String color)
    {
        for (Force force:forces)
        {
            force.setColor(color);
        }
    }
}
