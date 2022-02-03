package com.company;

import java.util.ArrayList;

/**
 * This class is used to hold the information related to the field of game
 * it save a list of cells
 * initialize the cell`s coordinate and have a method two show the field
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Field {

    // The list of cells
    ArrayList<Cell> cells;

    /**
     * create anew field and the list of cells
     */
    public Field()
    {
        cells = new ArrayList<>();
    }

    /**
     * we creat each cell and asign a number and coordinate to each cell
     */
    public void createCells()
    {
        int index =0 ;
        for(int i =0 ; i<9 ; i++)
        {
            for (int j = 0; j<25 ; j++)
            {
                if(i%2 == 0)
                {
                    if(j%2 == 0)
                    {
                        int[] coordinate = {j , i};
                        Cell cell = new Cell(coordinate);
                        cells.add(cell);
                        cell.setNumber(index);
                        index++;
                    }
                }
                else
                {
                    if(j%2 == 1)
                    {
                        int[] coordinate = {j , i};
                        Cell cell = new Cell(coordinate);
                        cells.add(cell);
                        cell.setNumber(index);
                        index++;
                    }
                }
            }
        }
    }

    /**
     * get the list of cells
     * @return cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * save the information that we need to show the field in a suitable array
     * then whenevr we call this method it can show the field to us
     */
    public void showField()
    {
        String [][] field = new String[9][7];
        for (int i =0; i<9 ; i++)
        {
            for (int j =0; j<7; j++)
                field[i][j] = "";
        }
        for (Cell cell : cells){
            int row = cell.getCoordinate()[1];
            if (cell.getCoordinate()[1]%2 == 1 && cell.getCoordinate()[0]==1)
            {
                field[row][0] += "     ";
                field[row][1] += "     ";
                field[row][2] += "     ";
                field[row][3] += "     ";
                field[row][4] += "     ";
                field[row][5] += "     ";
                field[row][6] += "     ";

            }
            field[row][0] += cell.myString()[0] ;
            field[row][1] += cell.myString()[1];
            field[row][2] += cell.myString()[2];
            field[row][3] += cell.myString()[3];
            field[row][4] += cell.myString()[4];
            field[row][5] += cell.myString()[5];
            field[row][6] += cell.myString()[6];

            if ((cell.getCoordinate()[1]%2 == 0 && cell.getCoordinate()[0]==24) ||
                    (cell.getCoordinate()[1]%2 == 1 && cell.getCoordinate()[0] == 23))
            {
                field[row][0] += "\n";
                field[row][1] += "\n";
                field[row][2] += "\n";
                field[row][3] += "\n";
                field[row][4] += "\n";
                field[row][5] += "\n";
                field[row][6] += "\n";
            }
        }
        for (int i =0; i<9 ; i++)
        {
            for (int j =0; j<7; j++)
            {
                System.out.print(field[i][j]);
            }
        }
    }
}
