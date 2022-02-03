package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is used for control and manage the game
 * the main methods of this class are process and action
 * the process method manage the sequence of game for each player
 * the action method control the actions after choosing card such as movement , choose destination and...
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class Game {
    // The allied team
    private Allied allied;
    // The axis team
    private Axis axis;
    // The manage card field for controlling the cards of each player
    private ManageCards manageCards;
    // The field of game
    private Field field;

    /**
     * This is the constructor of the Game class
     * create new game with a given players information such as their name
     * @param axisName the name of player for axis team
     * @param alliedName the name of player for allied team
     */
    public Game(String axisName,String alliedName)
    {
        field = new Field();
        field.createCells();
        axis = new Axis(axisName);
        allied = new Allied(alliedName);
        initializeCoordinate();
        manageCards = new ManageCards();
        firstSetCards();
        chooseFirstTurn();
        field.showField();
    }
    /**
     * in the start of game we should give two cards to the axis team
     * and give four cards to the allied team
     * this function do this for us
     */
    public void firstSetCards()
    {
        for (int i = 0; i<4;i++)
        {
            if (i<2)
                axis.addCard(manageCards.getRandomCard());
            allied.addCard(manageCards.getRandomCard());
        }
    }
    /**
     * in the start of game we should choose the first player to start the game
     * this work is done random
     * this function do this for us
     */
    public void chooseFirstTurn()
    {
        Random random = new Random();
        int index = random.nextInt(2);
        if (index == 0)
            allied.setTurn(true);
        else
            axis.setTurn(true);
    }

    /**
     * This method is the main method of this class
     * we control the sequence of the game with this class
     * if the game end (it means that one of the player get 6 scores) return true
     * @return boolean value(if return true means the game ending)
     */
    public boolean process()
    {
        boolean endProcess = false;
        Player player = whoseTurn();
        Card cardInHand ;
        ArrayList<ArrayList<Force>> beReadyGroups ;
        System.out.println("\r"+player.getName() + " turn");
        System.out.println("\rScore:" + (player.getScore() + specialCell(player)));

        ///////////////////////choosing card///////////////////////////////////
        System.out.println("\rwhich of this cards do you want to use:(format: cardType cardNumber)");
        manageCards.showCards(player.getCards());// showing cards
        cardInHand = findCard(player);// choose a card from player`s card

        //////////////////////choose the forces from player forces/////////////
        beReadyGroups = chooseForces(cardInHand , player);

        //////////////////////do action  for selected forces///////////////////
        actions(beReadyGroups , player);
        //now this card is used and must be deleted
        player.removeCard(cardInHand);
        player.addCard(manageCards.getRandomCard());
        // set the attack ability of all forces true
        player.setForcesAttackAbilityTrue();
        // set the forces unready
        player.makeUnreadyForces();

        if((player.getScore()+specialCell(player)) == 6)
            endProcess = true;
        if (endProcess)
        {
            if (player.getColor().equals("white"))
                System.out.println("Axis win");
            else
                System.out.println("Allied win");
            System.out.println("Axis score:"+(axis.getScore()+specialCell(player))+ "  Allied score:"+ (allied.getScore()+specialCell(player)));
        }
        return endProcess;
    }

    /**
     * in each set we should know whose turn is it
     * this function tell us that and set the current turn false for next set
     *
     * @return player who should play now
     */
    public Player whoseTurn()
    {
        if (axis.isTurn())
        {
            axis.setTurn(false);
            allied.setTurn(true);
            return axis;
        }
        else {
            allied.setTurn(false);
            axis.setTurn(true);
            return allied;
        }
    }

    /**
     * first of all the player choose a card from اHis / Her own cards
     * in this function we check the chosen card is valid or not
     * if the card is valid we return it
     *
     * @param player the player
     * @return chosen card
     */
    public Card findCard(Player player)
    {
        Scanner input = new Scanner(System.in);
        while (true)
        {
            String cardName = input.nextLine();
            for (Card card : player.getCards())
                if (cardName.equals(card.toString()))
                    return card;

            // if we dont find the card or it isn`t valid
            System.out.println("your chosen card isn`t find please try again:");
        }
    }

    /**
     * after choosing the card player must choose some groups of His / Her forces
     * the number of groups is related to the type of card
     * we check the chosen group is valid or not and if it is valid we add it to the lis
     * the checking operation is done by forceGroup function
     *
     * @param card the chosen card
     * @param player the current player
     * @return list of forces group
     */
    public ArrayList<ArrayList<Force>> chooseForces(Card card,Player player)
    {
        ArrayList<ArrayList<Force>> beReadyGroups = new ArrayList<>();
        System.out.println("please choose your group of forces in format <forceType(groupNumber)>:(if you dont have enough groups enter 'NO')");
        int index = 0;
        ArrayList<Force> forces = new ArrayList<>();
        switch (card.getType())
        {
            case ORDER_1_UNIT:
            {
                index++;
                System.out.println("please choose group"+index+":");
                forces = forceGroup(player);
                if (forces != null)
                    beReadyGroups.add(forces);
                break;
            }
            case ORDER_2_UNIT:
            {
                for (int i =0;i<2;i++)
                {
                    index++;
                    System.out.println("please choose group"+index+":");
                    forces = forceGroup(player);
                    if (forces != null)
                        beReadyGroups.add(forces);
                }
                break;
            }
            case ORDER_3_UNIT:
            {
                for (int i =0;i<3;i++)
                {
                    index++;
                    System.out.println("please choose group"+index+":");
                    forces = forceGroup(player);
                    if (forces != null)
                        beReadyGroups.add(forces);
                }
                break;
            }
            case ORDER_4_UNIT:
            {
                for (int i =0;i<4;i++)
                {
                    index++;
                    System.out.println("please choose group"+index+":");
                    forces = forceGroup(player);
                    if (forces != null)
                        beReadyGroups.add(forces);
                }
                break;
            }
            case ORDER_3_UNIT_ONE_KIND:
            {
                for (int i =0;i<3;i++)
                {
                    index++;
                    System.out.println("please choose group"+index+":");
                    forces = forceGroup(player);
                    if (index > 1)
                    {
                        while (beReadyGroups.get(0).get(0).toString().charAt(0) != forces.get(0).toString().charAt(0) && forces != null)
                            forces = forceGroup(player);

                        if (forces != null)
                            beReadyGroups.add(forces);
                    }
                }
                break;
            }
        }
        return beReadyGroups;
    }

    /**
     * in this function we check the forces that player is choosing are valid or not
     * if valid we find each force and add it to the list
     * after all we return the group
     *
     * @param player the current player
     * @return a group of same type forces
     */
    public ArrayList<Force> forceGroup(Player player)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Force> forces = new ArrayList<>();
        boolean flag = true;
        while (true)
        {
            String groupName = input.nextLine();
            if (groupName.equals("NO"))
                return null;
            for (Force force: player.getForces()) {
                if (groupName.equals(force.toString()) && !force.isBeReady()) {
                    forces.add(force);
                    force.setBeReady(true);//the force after choosing is going to be ready for order
                    flag = false;
                }
            }
            if (flag)
                System.out.println("please enter a valid group:(notice that each force can be ready one time)");
            else
                return forces;
        }
    }

    /**
     * after choosing the forces we should do some actions
     * actions such as moving the group of forces and choosing destination and Rolling the dice
     * @param groups the groups of forces
     */
    public void actions(ArrayList<ArrayList<Force>> groups , Player player)
    {
        int distance;
        int flag = 0;
        String destNumber;
        int destinationCellNumber = 0;
        int numberOfDiceThrowing;
        int index = 0;
        Scanner input = new Scanner(System.in);
        // we get orders for each selected group of forces
        /////////////////////movement of each group//////////////////////////////////////
        System.out.println("first you should show the way for each group that you select.\nthe form of way it can be like:R R LU");
        System.out.println("if you dont want to move press enter");
        for (ArrayList<Force> group :groups) {
            index ++;
            System.out.println("Please enter the way for group"+ index +": ("+group.get(0).toString()+")");
            movement(group);
        }
        //////////////////////showing changes in map /////////////////////////////////////
        field.showField();

        index = 0;
        for (ArrayList<Force> group :groups)
        {
            index++;
            //////////////////////check attack ability /////////////////////////////////////
            // after we check if the forces have attack ability so we get order to attack
            if (group.get(0).getAttackAbility()) {
                System.out.println("Please enter the destination for group"+ index +": ("+group.get(0).toString()+")");
                System.out.println("(choose the cell number)");
                // 1) get the entry
                do {
                    //1-1)if player press enter means doesnt want to choose destination
                    destNumber = input.nextLine();
                    if(destNumber.isBlank())
                    {
                        flag = 1;
                        break;
                    }
                    else
                        destinationCellNumber = Integer.parseInt(destNumber);
                    //1-2) we check if the destination is null or not. also we check the forces in destination are not for current player
                    if (field.getCells().get(destinationCellNumber).getForces().size() != 0) {
                        if (!group.get(0).getColor().equals(player.getColor()))
                            System.out.println("this forces are yours");
                        else
                            break;
                    } else
                        System.out.println("in this cell we dont have any force");
                } while (true);
                // 2) the other action( calculate distance and throw dice)
                if (flag == 0) // the player choose the destination and doesnt press enter
                {
                    // 2-1) after getting suitable destination we should calculate distance
                    distance = calculateDistance(group, destinationCellNumber);
                    // 2-2)then we should calculate number of dice throwing due to the distance
                    numberOfDiceThrowing = rollingDice(group, distance, destinationCellNumber);
                    // 2-3)now if we can throwing dice then we can attack
                    if (numberOfDiceThrowing != 0) {
                        ArrayList<Integer> results ;
                        //2-3-1) now we should throw the dices
                        results = diceResult(numberOfDiceThrowing);
                        //2-3-2) we must show the result of throwing to the player
                        showDiceResult(results);
                        //2-3-3)  we should check the rules of throwing dice
                        boolean permissionToAttack = permissionToAttack(results, field.getCells().get(destinationCellNumber).getForces());
                        if (permissionToAttack) {
                            kill(field.getCells().get(destinationCellNumber).getForces());
                            if (field.getCells().get(destinationCellNumber).getForces().size() == 0) {
                                //field.getCells().get(destinationCellNumber).setForces(null);
                                player.setScore(1);
                            }
                            field.showField();
                        } else
                            System.out.println("you cant attack due to the the dice results");
                    } else
                        System.out.println("Sorry! you cant attack to that destination and the number of dice ypu can throw is less than0");
                }
            }
            else
            {
                System.out.println("you doesnt have attack ability for group"+ index +": ("+group.get(0).toString()+")");
            }
        }
    }

    /**
     * this method do the operation of movement
     * @param group one group of forces that player choose
     */
    public void movement(ArrayList<Force> group)
    {
        String[] wayGuidance;
        // first get the entry and check it
        String way = checkPathEntry(group);
        if (way.equals("false"))
            return;
        else
            wayGuidance = way.split(" ");

        // after getting the path we should move the forces
        //first we move one force of the group
        for (String myst : wayGuidance) {
            if (checkCells(group.get(0),myst) != 0) {
                field.getCells().get(group.get(0).getCellNumber()).removeForces(group.get(0));
                group.get(0).setCellNumber(checkCells(group.get(0), myst));
                field.getCells().get(group.get(0).getCellNumber()).setForces(group.get(0));
            }
            if(field.getCells().get(group.get(0).getCellNumber()).getType().equals("Jungle") ||
                    field.getCells().get(group.get(0).getCellNumber()).getType().equals("City") ) {
                group.get(0).setAttackAbility(false);
                break;
            }
            if ( checkCells(group.get(0), myst) == 0)
                break;
        }
        //then we should other members of a group
        for (Force force:group)
        {
            if (force.getCellNumber() != group.get(0).getCellNumber())
            {
                field.getCells().get(force.getCellNumber()).removeForces(force);
                force.setCellNumber(group.get(0).getCellNumber());
                field.getCells().get(group.get(0).getCellNumber()).setForces(force);
                force.setAttackAbility(group.get(0).getAttackAbility());
            }
        }
    }

    /**
     * this method check the states that player can press entry. states su as:
     * 1) check if player dont want to move the forces
     * 2) check the size of entry with board of each force
     * 3) if two previous level end successfully now we check if the player enter a valid entry or not
     *
     * @param group the group of forces
     * @return String value(if player doesnt want to move player return false as a string)
     */
    public String checkPathEntry(ArrayList<Force> group)
    {
        while (true) {
            int flag = 0;
            Scanner input = new Scanner(System.in);
            //1) check if player dont want to move the forces
            String way = input.nextLine();
            String[] wayGuidance = way.split(" ");
            if (way.isBlank())
                return "false";

            //2) check the size of entry with board of each force
            while (!group.get(0).checkAction(wayGuidance.length)) {
                way = input.nextLine();
                wayGuidance = way.split(" ");
            }
            if (way.isBlank())
                return "false";

            //3) if two previous level end successfully now we check if the player enter a valid entry or not
            for (String myst : wayGuidance) {
                if (myst.equals("L") || myst.equals("R") || myst.equals("RU") || myst.equals("RD") || myst.equals("LU") || myst.equals("LD")) {
                    continue;
                } else {
                    System.out.println("There is an invalid entry. try again");
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                return way;
        }
    }
    /**
     * for moving in cells we have some limits
     * this method check this limits and return the destination cell number
     * @param force each force in a group of foces
     * @param way the way that player give
     * @return destination cell number
     */
    public int checkCells(Force force, String way)
    {
        int nextIndex =0 ;
        // 1) get the current position
        int cellNumber = force.getCellNumber();
        // 2) with out the limits on the cells we calculate the difference between this cell and next cell
        switch (way)
        {
            case "R":
            {
                if (cellNumber==12 || cellNumber==24 ||cellNumber==37 ||cellNumber==49 ||
                        cellNumber==62 ||cellNumber==74 ||cellNumber==87 || cellNumber==99 ||cellNumber==112)
                    return 0;
                nextIndex += 1;
                break;
            }
            case "L":
            {
                if (cellNumber==0 || cellNumber==13 ||cellNumber==25 ||cellNumber==38 ||
                        cellNumber==50 ||cellNumber==63 ||cellNumber==75 || cellNumber==88 ||cellNumber==100)
                    return 0;
                nextIndex -= 1;
                break;
            }
            case "RU":
            {
                if (cellNumber<=12 || cellNumber==37 ||cellNumber==62 ||cellNumber==87 || cellNumber==112 )
                    return 0;
                nextIndex -= 12;
                break;
            }
            case "RD":
            {
                if (cellNumber==12 || cellNumber==37 ||cellNumber==62 ||cellNumber==87 || cellNumber>=100 )
                    return 0;
                nextIndex += 13;
                break;
            }
            case "LU":
            {
                if (cellNumber<=12 || cellNumber==25 ||cellNumber==50 ||cellNumber==75 || cellNumber==100 )
                    return 0;
                nextIndex -= 13;
                break;
            }
            case "LD":
            {
                if (cellNumber==0 || cellNumber==25 ||cellNumber==50 ||cellNumber==75 || cellNumber>=100  )
                    return 0;
                nextIndex += 12;
                break;
            }
        }
        // 3) with out the limits on the cells we calculate the next cell
        int temp = cellNumber+nextIndex;
        // 4) now check the limits(the destination is river or is full or not)
        //   also tank and artillery cant go to the shelter
        if (field.getCells().get(temp).getType().equals("River") || field.getCells().get(temp).getForces().size() != 0) {
            return 0;
        }
        if (force instanceof Tank || force instanceof Artillery)
            if (field.getCells().get(temp).getType().equals("Shelter"))
                return 0;
        return temp;
    }

    /**
     * after getting the destination of player want to attack, we should roll dice
     * @param group the group of forces
     * @param destinationCellNumber the cell number of destination
     * @return distance
     */
    public  int calculateDistance(ArrayList<Force> group , int destinationCellNumber )
    {
        int distance = 0;
        // finding the distance
        for (Force force:group)
        {
            //if the row of coordinates are equal:
            if (field.getCells().get(force.getCellNumber()).getCoordinate()[0] ==field.getCells().get(destinationCellNumber).getCoordinate()[0] )
            {
                distance = Math.abs(field.getCells().get(force.getCellNumber()).getCoordinate()[1] -field.getCells().get(destinationCellNumber).getCoordinate()[1]);
            }
            //if the column of coordinates are equal
            else if (field.getCells().get(force.getCellNumber()).getCoordinate()[1] ==field.getCells().get(destinationCellNumber).getCoordinate()[1])
            {
                distance = Math.abs(field.getCells().get(force.getCellNumber()).getCoordinate()[0] -field.getCells().get(destinationCellNumber).getCoordinate()[0]);
            }
            else
            {
                distance = Math.abs(field.getCells().get(force.getCellNumber()).getCoordinate()[1] -field.getCells().get(destinationCellNumber).getCoordinate()[1])
                        + Math.abs(field.getCells().get(force.getCellNumber()).getCoordinate()[0] -field.getCells().get(destinationCellNumber).getCoordinate()[0]);
                distance /= 2;
            }
        }
        return distance;
    }

    /**
     * we calculate the number of throwing dice
     *
     * @param group the group of forces
     * @param distance the distance between current cell and destination of attack
     * @param destinationCellNumber number of destination cell
     * @return number Of dice Throwing
     */
    public int rollingDice(ArrayList<Force> group , int distance , int destinationCellNumber)
    {
        int numberOfThrowing = 0;
        // 1) first we should pay attention to the bord of each force
        //    also we check if the destination is shelter and axis forces are in it we should decrease the number of dice
        if (group.get(0) instanceof Tank)
        {
            Tank tank = (Tank) group.get(0);
            numberOfThrowing = tank.diceRelatedDistance(distance);
        }
        else if (group.get(0) instanceof Soldier)
        {
            Soldier soldier = (Soldier) group.get(0);
            numberOfThrowing = soldier.diceRelatedDistance(distance);
        }
        else
        {
            Artillery artillery = (Artillery) group.get(0);
            numberOfThrowing = artillery.diceRelatedDistance(distance);
        }
        if (numberOfThrowing == 0)
        {
            System.out.println("you cant attack because the limit of Bord");
            return 0;
        }
        //2) now we should the limits related to the type of destination cell
        switch (field.getCells().get(destinationCellNumber).getType())
        {
            case "Hill":
            {
                if (group.get(0) instanceof Tank || group.get(0) instanceof Soldier)
                    numberOfThrowing -= 1;
                break;
            }
            case "Jungle":
            case "City": {
                if (group.get(0) instanceof Tank )
                    numberOfThrowing -= 2;
                else if (group.get(0) instanceof Soldier)
                    numberOfThrowing -= 1;
                break;
            }
            case "Shelter":
            {
                if (field.getCells().get(destinationCellNumber).getForces().get(0).getColor().equals("white"))
                {
                    if (group.get(0) instanceof Tank )
                        numberOfThrowing -= 2;
                    else if (group.get(0) instanceof Soldier)
                        numberOfThrowing -= 1;
                }
                break;
            }
            default:
                break;
        }
        // 3) now we should the limits related to the type of current cell
        if (field.getCells().get(group.get(0).getCellNumber()).getType().equals("City") && group.get(0) instanceof Tank )
            numberOfThrowing -= 2;

        if (numberOfThrowing > 0)
            return numberOfThrowing;
        else
            return 0;
    }

    /**
     * this method throw the dice due to the number of throwing that we say
     * @param numberOfThrowing the number of throwing dice that we say
     * @return the results of throwing as a list
     */
    public ArrayList<Integer> diceResult(int numberOfThrowing)
    {
        Random randomGenerator = new Random();
        ArrayList<Integer> results = new ArrayList<>();
        for (int i =0 ; i<numberOfThrowing ; i++)
        {
            results.add(randomGenerator.nextInt(6) + 1);
        }
        return results;
    }

    /**
     * show the result of throwing dice
     * @param results list of results
     */
    public void showDiceResult(ArrayList<Integer> results)
    {
        int index = 0;
        for (Integer result : results)
        {
            index ++;
            System.out.print("dice("+index+"): "+ result + "  ");
        }
        System.out.println();
    }

    /**
     * after throwing dice we should check the rules of attack
     * @param diceResults the result of throwing dice
     * @param group the group of forces
     * @return a boolean value (true if we have permission)
     */
    public boolean permissionToAttack(ArrayList<Integer> diceResults , ArrayList<Force> group)
    {
        boolean permission = false;
        for (Integer integer : diceResults)
        {
            if (integer.equals(1) || integer.equals(6))
            {
                if (group.get(0) instanceof Soldier)
                    return true;
            }
            else if (integer.equals(2))
            {
                if (group.get(0) instanceof Tank)
                    return true;
            }
            else if (integer.equals(5))
                return true;
            else
                permission  = false;
        }
        return permission;
    }

    /**
     * if we have permission to attack to a cell we kill one of their forces with this method
     * @param group the group of forces in destination cell
     */
    public void kill(ArrayList<Force> group)
    {
        if (group.get(0).getColor().equals("white"))
            axis.getForces().remove(group.get(0));
        else
            allied.getForces().remove(group.get(0));
        group.remove(0);
    }
    /**
     * in the start of the game we must place each group of forces in a specific cell
     * also we assign a group of forces to each cell
     */
    public void initializeCoordinate()
    {
        for (Force force: axis.getForces())
        {
            if (force.toString().equals("T(0)")) {
                field.getCells().get(0).setForces(force);
                force.setCellNumber(0);
            }
            if (force.toString().equals("T(1)")){
                field.getCells().get(5).setForces(force);
                force.setCellNumber(5);
            }
            if (force.toString().equals("T(2)")){
                field.getCells().get(8).setForces(force);
                force.setCellNumber(8);
            }
            if (force.toString().equals("T(3)")){
                field.getCells().get(11).setForces(force);
                force.setCellNumber(11);
            }
            if (force.toString().equals("T(4)")){
                field.getCells().get(18).setForces(force);
                force.setCellNumber(18);
            }
            if (force.toString().equals("T(5)")){
                field.getCells().get(23).setForces(force);
                force.setCellNumber(23);
            }
            /////////////////////////////////////////////
            if (force.toString().equals("S(0)")){
                field.getCells().get(1).setForces(force);
                force.setCellNumber(1);
            }
            if (force.toString().equals("S(1)")){
                field.getCells().get(2).setForces(force);
                force.setCellNumber(2);
            }
            if (force.toString().equals("S(2)")){
                field.getCells().get(7).setForces(force);
                force.setCellNumber(7);
            }
            if (force.toString().equals("S(3)")){
                field.getCells().get(10).setForces(force);
                force.setCellNumber(10);
            }
            if (force.toString().equals("S(4)")){
                field.getCells().get(12).setForces(force);
                force.setCellNumber(12);
            }
            if (force.toString().equals("S(5)")){
                field.getCells().get(17).setForces(force);
                force.setCellNumber(17);
            }
            if (force.toString().equals("S(6)")){
                field.getCells().get(21).setForces(force);
                force.setCellNumber(21);
            }
        }
        for (Force force: allied.getForces())
        {
            if (force.toString().equals("T(0)")){
                field.getCells().get(100).setForces(force);
                force.setCellNumber(100);
            }
            if (force.toString().equals("T(1)")){
                field.getCells().get(101).setForces(force);
                force.setCellNumber(101);
            }
            if (force.toString().equals("T(2)")){
                field.getCells().get(112).setForces(force);
                force.setCellNumber(112);
            }

            /////////////////////////////////////////////
            if (force.toString().equals("S(0)")){
                field.getCells().get(51).setForces(force);
                force.setCellNumber(51);
            }
            if (force.toString().equals("S(1)")){
                field.getCells().get(56).setForces(force);
                force.setCellNumber(56);
            }
            if (force.toString().equals("S(2)")) {
                field.getCells().get(58).setForces(force);
                force.setCellNumber(58);
            }
            if (force.toString().equals("S(3)")){
                field.getCells().get(61).setForces(force);
                force.setCellNumber(61);
            }
            if (force.toString().equals("S(4)")){
                field.getCells().get(66).setForces(force);
                force.setCellNumber(66);
            }
            if (force.toString().equals("S(5)")){
                field.getCells().get(82).setForces(force);
                force.setCellNumber(82);
            }
            if (force.toString().equals("S(6)")){
                field.getCells().get(88).setForces(force);
                force.setCellNumber(88);
            }
            if (force.toString().equals("S(7)")){
                field.getCells().get(97).setForces(force);
                force.setCellNumber(97);
            }
            if (force.toString().equals("S(8)")){
                field.getCells().get(108).setForces(force);
                force.setCellNumber(108);
            }
            /////////////////////////////////////////////
            if (force.toString().equals("A(0)")){
                field.getCells().get(89).setForces(force);
                force.setCellNumber(89);
            }
            if (force.toString().equals("A(1)")){
                field.getCells().get(93).setForces(force);
                force.setCellNumber(93);
            }
        }
    }

    /**
     * some of the cells are special and if one time get them and hold it they get score
     * @param player the current player
     * @return the score(if the team has this cell return 1 and else return 0)
     */
    public int specialCell(Player player)
    {
        if (player instanceof Allied)
        {
          if(field.getCells().get(25).getForces().size() != 0)
          {
              if (field.getCells().get(25).getForces().get(0).getColor().equals("magenta"))
                  return 1;
          }
        }
        else
        {
            if(field.getCells().get(111).getForces().size() != 0)
            {
                if (field.getCells().get(111).getForces().get(0).getColor().equals("white"))
                    return 1;
            }
        }
        return 0;

    }

}
