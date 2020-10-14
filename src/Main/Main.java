package Main;

import Player_Details.*;
import Playground_Unit.*;

import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static Scanner in = new Scanner(System.in);
    public static ArrayList <player> players = new ArrayList<player>(); // Players container
    public static ArrayList <playground_owner> playground_owners = new ArrayList<playground_owner>();
    public static ArrayList <playground> playgrounds = new ArrayList<playground>();

    public static userID ID = new userID();

    public static  void main(String[] args) {



        System.out.println("\t\t\t =================== Welcome to GoFO system ===================\t\t\t\n");

        while(true) {


            activeUser active_user = new activeUser();  // Object to detect the active user and his index

            System.out.println("1- Login");
            System.out.println("2- Sign up");   // The first menu in the system
            System.out.println("3- Exit");
            String operation = in.nextLine();

            if (operation.equals("1")) {        // Login option

                String userRole = " ";

                userRole = userLogin(active_user);


                if (userRole.equals("P")) {   // if userLogin returned "P" the user will be player, And we will get his index by active_user object


                    System.out.println("Welcome "+players.get(active_user.index).getName()+" to the system\n");  // Welcome message

                    while(true)  // Loop helps to continue player thread flow
                    {
                        String playerOperation;
                        Scanner player_input = new Scanner(System.in);

                        System.out.println("1- Book a slot at playground");
                        System.out.println("2- Cancel booking ");
                        System.out.println("3- Add team");
                        System.out.println("4- Display team data");
                        System.out.println("5- Update team");
                        System.out.println("6- Add money");
                        System.out.println("7- Back to main menu");

                        playerOperation = player_input.nextLine();

                        if(playerOperation.equals("1"))
                        {
                            players.get(active_user.index).bookSlot(playgrounds);

                        }

                        else if(playerOperation.equals("2"))
                        {
                            players.get(active_user.index).cancelBooking(playgrounds);

                        }

                        else if (playerOperation.equals("3"))
                        {
                            players.get(active_user.index).setTeam();
                        }

                        else if (playerOperation.equals("4"))
                        {
                            players.get(active_user.index).displayTeam();

                        }

                        else if(playerOperation.equals("5"))
                        {
                            players.get(active_user.index).updateTeam();
                        }

                        else if(playerOperation.equals("6"))
                        {
                            players.get(active_user.index).addMoney();

                        }

                        else if(playerOperation.equals("7"))
                        {
                            break;
                        }

                    }


                }

                else if(userRole.equals("O")) {      // if userLogin returned "O" the user will be playground owner, And we will get his index by active_user object


                    System.out.println("Welcome " + playground_owners.get(active_user.index).getName() + " to the system\n"); // Welcome message


                    while (true) // Loop helps to continue the owner thread flow
                    {
                        Scanner owner_input = new Scanner(System.in);
                        String ownerOperation;

                        System.out.println("1- Add playground");
                        System.out.println("2- View bookings");
                        System.out.println("3- Deposit eWallet");
                        System.out.println("4- Update playground information");
                        System.out.println("5- Main menu");
                        ownerOperation = owner_input.nextLine();

                        if (ownerOperation.equals("1")) {
                            playground_owners.get(active_user.index).addPlayground(playgrounds);

                        } else if (ownerOperation.equals("2")) {
                            playground_owners.get(active_user.index).viewBookings();
                        } else if (ownerOperation.equals("3")) {
                            playground_owners.get(active_user.index).addMoney();
                        }

                        else if (ownerOperation.equals("4")){
                            playground_owners.get(active_user.index).updatePlayground();
                        }

                        else if (ownerOperation.equals("5")) {
                            break;
                        }

                    }

                }

            }

            else if (operation.equals("2")) // Sign up operation
            {
                Scanner input = new Scanner(System.in);

                System.out.println("1- Sign up as a player ");         // Roles of the users in the system
                System.out.println("2- Sign up as a playground owner ");

                String user_role = input.nextLine();

                if(user_role.equals("1")) // If user choice 1, then call the function RegistPlayer(), Which will add player to the system
                {
                    RegistPlayer(ID);

                }

                else if(user_role.equals("2"))   // If user choice 2, then call the function RegistPlaygroundOwner(), Which will add playground owner to the system
                {
                    RegistPlaygroundOwner(ID);

                }

                else        // Inform the user his choice is invalid input
                {
                    System.out.println("Invalid input.\n");
                }

            }


            else if(operation.equals("3"))  // Exit operation break the loop and out of program
            {
                break;
            }

            else  // Inform the user his choice is invalid input
            {
                System.out.println("Invalid choice\n");
            }

        }


        for(int i = 0 ; i < playground_owners.size() ; i++)
        {
            System.out.println("===================");
            System.out.println(playground_owners.get(i).getName());
            System.out.println("===================");

        }

        System.out.println("\t\t\t ===================  Thank's for using GoFo systems  ===================\t\t\t\n");


    }

    public static void RegistPlayer(userID ID){

        player Player = new player();

        Player.setName();  // Taking the player username
        Player.setEmail(); // Taking the player email

        for(int i = 0; i < players.size() ; i++)  // Search for the username and email in the players list
        {
            while(players.get(i).getName().equals(Player.getName()))
            {
                System.out.println("This name is already taken please try other");
                Player.setName();
                i = 0;

            }

            while( players.get(i).getEmail().equals(Player.getEmail()) )
            {
                System.out.println("This email is already taken please try other");
                Player.setEmail();
                i = 0;
            }

        }


        for(int i = 0 ; i < playground_owners.size() ; i++)  // Search for the username and email in the playgroundOwners list
        {
            while(playground_owners.get(i).getName().equals(Player.getName()))
            {
                System.out.println("This name is already taken please try onther");
                Player.setName();
                i = 0;
            }

            while( playground_owners.get(i).getEmail().equals(Player.getEmail()) )
            {
                System.out.println("This email is already taken please try onther");
                Player.setEmail();
                i = 0;
            }

        }



        Player.setPhoneNumber(); // Taking the player phone number
        Player.setPassword();     // Taking the player password
        Player.setID(ID.ID);
        players.add(Player);   // Push the player to the arraylist of the players
        ID.ID++;
        // Inform the player the he has been added successfully to the system
        System.out.println("The player '"+Player.getName()+"' with ID : "+ Player.getID() +", has been added to the system");



    }

    public static void RegistPlaygroundOwner(userID ID ){

        playground_owner playgroundOwner = new playground_owner();

        playgroundOwner.setName();  // Taking the playgroundOwner username
        playgroundOwner.setEmail(); // Taking the playgroundOwner email

        for(int i = 0; i < players.size() ; i++)  // Search for the username and email in the players list
        {
            while(players.get(i).getName().equals(playgroundOwner.getName()))
            {
                System.out.println("This name is already taken please try onther");
                playgroundOwner.setName();
                i = 0;
            }

            while( players.get(i).getEmail().equals(playgroundOwner.getEmail()) )
            {
                System.out.println("This email is already taken please try onther");
                playgroundOwner.setEmail();
                i = 0;
            }

        }


        for(int i = 0 ; i < playground_owners.size() ; i++)  // Search for the username and email in the playgroundOwners list
        {
            while(playground_owners.get(i).getName().equals(playgroundOwner.getName()))
            {
                System.out.println("This name is already taken please try onther");
                playgroundOwner.setName();
                i = 0;
            }

            while( playground_owners.get(i).getEmail().equals(playgroundOwner.getEmail()) )
            {
                System.out.println("This email is already taken please try onther");
                playgroundOwner.setEmail();
                i = 0;
            }

        }



        playgroundOwner.setPhoneNumber(); // Taking the playgroundOwner phone number
        playgroundOwner.setPassword();     // Taking the playgroundOwner password
        playgroundOwner.setID(ID.ID);
        playgroundOwner.addPlayground(playgrounds);
        playground_owners.add(playgroundOwner);   // Push the playgroundOwner to the arraylist of the players

        ID.ID++; // increment the ID for the next user
        // Inform the playgroundOwner the he has been added successfully to the system
        System.out.println("The playground owner '"+playgroundOwner.getName()+" with ID : '"+playgroundOwner.getID()+", has been added to the system");



    }

    public static String userLogin(activeUser active){

        Scanner input = new Scanner(System.in);
        boolean founded = false;
        String role = " "; // This will contain the rule of the object (player / owner)

        System.out.print("Enter your username : ");
        String userName = input.nextLine();

        System.out.print("Enter your password : ");
        String userPassword = input.nextLine();

        for(int i = 0 ; i < players.size() ; i++) // Search for the username and password in the players list
        {
            if(players.get(i).getName().equals(userName) && players.get(i).getPassword().equals(userPassword))  // Check from username and his password
            {
                founded = true;
                role = "P";  // P is reference for "player"
                active.index = i;
                break;

            }

        }

        if(!founded)  // If we didn't find him in the players will search for him at playground owners
        {

            for(int i = 0 ; i < playground_owners.size() ; i++)
            {
                if(playground_owners.get(i).getName().equals(userName) && playground_owners.get(i).getPassword().equals(userPassword))  // Check from username and his password
                {
                    founded = true;
                    role = "O";  // 'O' is reference for owner
                    active.index = i;
                    break;
                }


            }

        }


        if(!founded)  // If founded still false that mean the user does not exist in the system
        {
           System.out.println("The user '"+userName+"', does not exist in the system");

        }

        return role;
    }

// Class we will use to detect the current active user
    static class activeUser {
        public int index;  // static index

        activeUser(){
        }

    }

// Class we will use it to increment the ID of the next user
    static class userID
    {
         public int ID;


         public userID()
         {
             ID = 1;

         }

    }



}
