package com.company;

import java.util.ArrayList;

/**
 * this class is used for save the information related to each cell
 * each cell has a coordinate and type(like Jungle etc.) and
 * a number for choose them easily. also they save the list of forces inside it
 * to have access to the information of them like their number and etc. by cell
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Cell {
    // The coordinates of each cell
    private int[] coordinate;
    // The type of each cell (like Jungle, City ,etc.)
    private String type;
    // The color of the cell that related to its type
    private Color color;
    // The number of Cell(we have 113 numbers of cell)
    private int number;
    // The list of forces that placed in the cell
    private ArrayList<Force> forces;

    /**
     * This is the constructor of this class
     * Make a new cell with a given coordinates
     * @param coordinate the coordinates of cell
     */
    public Cell( int[] coordinate )
    {
        this.coordinate = coordinate;
        forces = new ArrayList<>();
        setType();
        setColor();
    }

    /**
     * get the coordinates of a cell
     * @return the coordinates
     */
    public int[] getCoordinate() {
        return coordinate;
    }

    /**
     * Set the type of each cell
     */
    public void setType() {
        if (coordinate [1] == 0) {
            if (coordinate[0]== 0 || coordinate[0]== 2)
                this.type = "Hill";
            else if (coordinate[0]== 6)
                this.type = "City";
            else if (coordinate[0]== 8)
                this.type = "Bridge";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 1) {
            if (coordinate[0] == 3 || coordinate[0] == 5 || coordinate[0] == 7)
                this.type = "River";
            else if (coordinate[0] == 9)
                this.type = "Shelter";
            else if (coordinate[0] == 13)
                this.type = "Hill";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 2) {
            if (coordinate[0] == 18 || coordinate[0] == 24 )
                this.type = "Jungle";
            else if (coordinate[0] == 0)
                this.type = "City";
            else if (coordinate[0] == 2)
                this.type = "River";
            else if (coordinate[0] == 12)
                this.type = "Hill";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 3) {
            if (coordinate[0] == 3 || coordinate[0] == 7 || coordinate[0] == 23 )
                this.type = "Jungle";
            else if (coordinate[0] == 1)
                this.type = "Bridge";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 4) {
            if (coordinate[0] == 2 || coordinate[0] == 16 || coordinate[0] == 24 )
                this.type = "Jungle";
            else if (coordinate[0] == 0)
                this.type = "River";
            else if (coordinate[0] == 10 || coordinate[0] == 22)
                this.type = "Hill";
            else if (coordinate[0] == 12 || coordinate[0] == 20)
                this.type = "City";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 5) {
            if (coordinate[0] == 7 || coordinate[0] ==23 )
                this.type = "Jungle";
            else if (coordinate[0] == 9 || coordinate[0] == 21)
                this.type = "Hill";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 6) {
            if (coordinate[0] == 14 || coordinate[0] ==16 )
                this.type = "Jungle";
            else if (coordinate[0] == 4)
                this.type = "City";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 7) {
            if (coordinate[0] == 7 || coordinate[0] ==9 || coordinate[0] ==17 )
                this.type = "Jungle";
            else
                this.type = "Plane";
        }
        if (coordinate [1] == 8) {
            if (coordinate[0] == 22 )
                this.type = "City";
            else
                this.type = "Plane";
        }
    }

    /**
     * set the color of each cell due to its type
     */
    public void setColor() {

        if (type.equals("Jungle"))
            this.color = Color.GREEN;
        else if (type.equals("Hill"))
            this.color = Color.PURPLE;
        else if (type.equals("River"))
            this.color = Color.BLUE;
        else if (type.equals("City"))
            this.color = Color.CYAN;
        else if (type.equals("Bridge"))
            this.color = Color.YELLOW;
        else if (type.equals("Shelter"))
            this.color = Color.WHITE;
        else
            this.color = Color.RED;
    }

    /**
     * get the forces that places in this cell
     * @return forces
     */
    public ArrayList<Force> getForces() {
        return forces;
    }

    /**
     * set the force to the list of this cell
     * we give a Force as a parameter to it
     * @param force the force object
     */
    public void setForces(Force force ){
        forces.add(force);
    }

    /**
     * get the number of the cell
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Remove the force from the list of the forces of this cell
     * @param force the force object
     */
    public void removeForces(Force force)
    {
        forces.remove(force);
    }

    /**
     * Set the number for the cell
     * @param number number of cell
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * get the type of cell
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * This method return a string array that contains the shape of thecell
     * with its information such as color and number forces and number
     * @return string array
     */
    public String[] myString() {
        String[] cell = new String[7];
        String printColor = color.getPrintColor();
        cell[0] = printColor;
        cell[1] = printColor;
        cell[2] = printColor;
        cell[3] = printColor;
        cell[4] = printColor;
        cell[5] = printColor;
        cell[6] = printColor;

        cell[0] += "     *     ";
        cell[0] += Color.RESET.getPrintColor();

        if (number == 25 || number == 111)
            cell[1] += "  * ##  *  ";
        else
            cell[1] += "  *     *  ";
        cell[1] += Color.RESET.getPrintColor();

        if (number< 10)
            cell[2] +="*   "+number + "     *";
        else if (number <100)
            cell[2] +="*   "+number + "    *";
        else
            cell[2] +="*   "+number + "   *";
        cell[2] += Color.RESET.getPrintColor();

        cell[3] +="*         *";
        cell[3] += Color.RESET.getPrintColor();

        if (forces.size() == 0)
            cell[4] +="*         *";
        else {
            String forceColor = "";
            if (forces.get(0).getColor().equals("white")) {
                forceColor += Color.WHITE.getPrintColor();
                forceColor += forces.size() + "_" + forces.get(0).toString();
                forceColor += Color.RESET.getPrintColor();
                forceColor += printColor;
                cell[4] += "* " + forceColor + "  *";
            }
            else {
                forceColor += Color.YELLOW.getPrintColor();
                forceColor += forces.size() + "_" + forces.get(0).toString();
                forceColor += Color.RESET.getPrintColor();
                forceColor += printColor;
                cell[4] += "* " + forceColor + "  *";
            }
        }
        cell[4] += Color.RESET.getPrintColor();

        cell[5] += "  *     *  ";
        cell[5] += Color.RESET.getPrintColor();

        cell[6] += "     *     ";
        cell[6] += Color.RESET.getPrintColor();

        return cell;
    }
}
