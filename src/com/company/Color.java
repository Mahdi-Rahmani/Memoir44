package com.company;

/**
 * This class is used for saving the colors
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public enum Color {
    //colors
    RESET("Reset"),
    PURPLE("Purple"),
    CYAN("Cyan"),
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    MAGENTA("Magenta"),
    BLACK("Black"),
    WHITE("White");
    //print color code
    private final String printColor;

    /**
     * set code of colors to printColor.
     *
     * @param color name of color.
     */
    Color(String color)
    {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[1;92m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_MAGENTA = "\u001B[35m";
        String ANSI_WHITE = "\u001B[37m";
        switch (color)
        {
            case "Reset":
                printColor = ANSI_RESET;
                break;
            case "Purple":
                printColor = ANSI_PURPLE;
                break;
            case "Cyan":
                printColor = ANSI_CYAN;
                break;
            case "Red":
                printColor = ANSI_RED;
                break;
            case "Green":
                printColor = ANSI_GREEN;
                break;
            case "Blue":
                printColor = ANSI_BLUE;
                break;
            case "Yellow":
                printColor = ANSI_YELLOW;
                break;
            case "Magenta":
                printColor = ANSI_MAGENTA;
                break;
            case "White":
                printColor = ANSI_WHITE;
                break;
            default:
                printColor = ANSI_BLACK;
                break;
        }
    }

    /**
     * get the print color code.
     *
     * @return printColor. a string of color code.
     */
    public String getPrintColor()
    {
        return printColor;
    }

}
