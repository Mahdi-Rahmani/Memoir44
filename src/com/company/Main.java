package com.company;
import java.util.Scanner;

/**
 * this is the main class.
 * in this class we do some works like getting the name of player
 * and also show the menu and also the player can modify the information or see the
 * instruction of the game.
 * if player chooses start we create a new game and call the process method
 *
 * @author Mahdi Rahmani
 * @version 1.0
 * @since 2020-11-18
 */
public class Main {
    // The name of player in axis team
    static String axisName;
    // The name of player in allied team
    static String alliedName;

    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("for starting game we the name of the players.");
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name:(you are in axis team)");
        axisName = input.nextLine();
        System.out.println("Please enter your name:(you are in allied team)");
        alliedName = input.nextLine();

        menu();
        while (flag) {
            String item = input.nextLine();
            switch (item) {
                case "1":
                    start();
                    flag = false;
                    break;
                case "2":
                    instruction();
                    break;
                case "3":
                    modifyInformation();
                    break;
                case "4":
                    aboutUs();
                    break;
                default:
                    System.out.println("please enter valid item. ");
            }
        }
    }

    /**
     * the menu that we show to player
     */
    public static void menu()
    {
        System.out.println("Please choose one item from below:");
        System.out.println("1)Start");
        System.out.println("2)instruction");
        System.out.println("3)Modify information");
        System.out.println("4)About us");
    }

    /**
     * if player choose the start item we call this method
     */
    public static void start()
    {
        Game game = new Game(axisName,alliedName);
        while (true)
        {
            boolean endGame ;
            endGame =game.process();
            if (endGame)
                break;
        }
    }

    /**
     * The instruction for playing game
     */
    public static void instruction()
    {
        System.out.println("Memoir44:");
        System.out.println(" -in this game we have two teams. one team is Axis and the other is Allied.");
        System.out.println("Axis team place at the top of map and Allied team at the button of map.");
        System.out.println(" -Each team has some forces. axis has soldier and tank but allied has artillery in addition.");
        System.out.println(" -At first we should choose one card from our cards.(the Axis have two and Allied have four cards");
        System.out.println(" -next we should choose the group of forces with their name due to the kind of chosen card.");
        System.out.println(" -then we should decide to move them or not. we have some limits on the movement of forces.");
        System.out.println(" -then if our forces have attack ability we can attack to enemy by enter the number of the cell.");
        System.out.println("in this part also we can dont attack. also we have some limits on this part.");
        System.out.println(" -if your team can kills all of forces in one cell get score. ");
        System.out.println("also if you get the special cell of field uou get score");
        System.out.println(" - each teams that can get 6 scores at first can win in the game.");
    }

    /**
     * This method modify the information of players
     */
    public static void modifyInformation()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name:(you are in axis team)");
        axisName = input.nextLine();
        System.out.println("Please enter your name:(you are in allied team)");
        alliedName = input.nextLine();
    }

    /**
     * if player want to communicate with us we call this method
     * to give some info about us
     */
    public static void aboutUs() {
        System.out.println(" my name is Mahdi Rahmani . I am computer student in Amirkabir university. ");
        System.out.println(" my email is :");
        System.out.println(" mah.rah79@yahoo.com");
    }
}
