package Player_Details;

import Playground_Unit.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class player {

    // ====================== Private attributes ================= //

    private String name;
    private String email;
    private String password;
    private String phoneNumber;       // The main attribute for the player
    private int ID;
    private float eWallet;
    private ArrayList <team> playerTeams ;
    private ArrayList<booking> playerbookings ;
    private boolean emailValid(String mail) // Private function will check the validation of the player email
    {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";

        return mail.matches(emailPattern);
    }




    // ====================== Public methods ================= //


    public player(){
        this.name = "NULL";
        this.phoneNumber = "NULL";
        this.email = "NULL";
        this.password = "NULL";
        ID = 0;
        eWallet = 0;
        playerTeams = new ArrayList<team>();
        playerbookings = new ArrayList<booking>();
    }

    public void setName(){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your username : " );
        name = input.nextLine();

    }

    public String getName() {
        return this.name;
    }

    public void setPassword(){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your password (must be more than 5 characters and digits) : ");
        this.password = input.nextLine();

        while(this.password.length() <= 5)
        {
            System.out.print("Please your password must be more than 5 characters and digits : ");
            this.password = input.nextLine();

        }

    }

    public String getPassword(){

        return this.password;
    }

    public void setPhoneNumber(){

        Scanner input = new Scanner(System.in);

        String phonePattern = "^\\d{11}";  // Phone number pattern to validate the user phone number

        System.out.print("Enter your phone number eg.(01001288856) : ");
        this.phoneNumber = input.nextLine();

        while (!phoneNumber.matches(phonePattern))
        {

            System.out.print("The number can't contain characters or less than 11 digit eg.(01001288856) : ");
            this.phoneNumber = input.nextLine();

        }

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setEmail(){

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your email address in correct format eg.(person1@gmail.com) : ");
        this.email = input.nextLine();

        while(!emailValid(this.email))
        {
            System.out.print("Please enter a valid email address : ");
            this.email = input.nextLine();
        }


    }

    public String getEmail(){


        return this.email;

    }

    public void setID(int id){

        this.ID = id;

    }

    public int getID() {
        return this.ID;
    }

    public void ewalletDepoist(float amount){
        this.eWallet += amount;
    }

    public void eWalletWithdraw(float amount){
        this.eWallet -= amount;
    }

    public void addMoney(){

        Scanner input = new Scanner(System.in);
        float amount;

        System.out.println("Enter the amount that you will add : ");
        amount = input.nextFloat();

        this.ewalletDepoist(amount);
        System.out.println("The amount "+amount+" has been added");

    }

    public void getMoney(){

        Scanner input = new Scanner(System.in);
        float amount;

        System.out.println("Enter the amount that you will withdraw : ");
        amount = input.nextFloat();

        this.eWalletWithdraw(amount);
        System.out.println("The amount "+amount+" has been withdrawn");

    }

    public float geteWallet() {
        return eWallet;
    }

    public void setTeam(){

        // Adding the team in the last index

        team newTeam = new team();
        newTeam.setTeam(this);
        playerTeams.add(newTeam);

    }

    public void displayTeam(){

        Scanner input = new Scanner(System.in);

        if(playerTeams.isEmpty())
        {
            System.out.println("You didn't register any teams yet..\n\n");
        }

        else
        {

            String teamName;       // Variable which contain the team name
            boolean founded = true; // Variable help us to know if the team exist or not
            System.out.print("Enter the team name : ");
            teamName = input.nextLine();

            for(int i = 0 ; i < playerTeams.size() ; i++)
            {

                if(teamName.equals(playerTeams.get(i).getTeamName()))
                {
                    playerTeams.get(i).displayTeam();  // Display the team data
                    founded = true;

                }

                else
                {
                    founded = false;
                }
            }

            if(!founded)  // Telling the user if the team name does not exist
            {
                System.out.println("The team '"+teamName +"' , Does not exist");
            }

        }

    }

    public void updateTeam(){

        Scanner input = new Scanner(System.in);

        if(playerTeams.isEmpty())
        {
            System.out.println("You didn't register any team yet.\n");
        }

        else
        {
            boolean founded = false;
            String choice;

            System.out.println("Please enter the team name that you want to update it : ");
            String teamName = input.nextLine();

            for(int i = 0 ; i < playerTeams.size() ; i++)
            {
                if(playerTeams.get(i).getTeamName().equals(teamName))
                {
                    founded = true;

                    System.out.println("1- Add memeber ");
                    System.out.println("2- Remove member");
                    System.out.println("3- Remove team");

                    String updateChoice = input.nextLine();

                    if(updateChoice.equals("1"))
                    {
                        playerTeams.get(i).addMember();
                    }

                    else if(updateChoice.equals("2"))
                    {
                        playerTeams.get(i).deleteMember();
                    }

                    else if(updateChoice.equals("3"))
                    {
                        playerTeams.remove(i);
                    }

                    else
                    {
                        System.out.println("Invalid input");
                    }
                }

            }



        }


    }

    public void bookSlot(ArrayList <playground> playgrounds){

        Scanner input = new Scanner(System.in);

        boolean founded = false;
        booking playerBooking = new booking();
        String playgroundName;
        int slotTime;

        for(int i = 0 ; i < playgrounds.size() ; i++)  // Display the playgrounds details for player
        {
            playgrounds.get(i).getDetails();

        }

        System.out.println("Please enter the playground name that you want to book a slot into : ");
        playgroundName = input.nextLine();

        for(int i = 0 ; i < playgrounds.size() ; i++)   // Looking for the playground that player want to book a slot into
        {
            if(playgrounds.get(i).getName().equals(playgroundName)) // fill the booking details if we found it
            {
                team bookTeam = new team();  // The team that will be added to the booked slot
                System.out.println("Enter the slot time you want to book ( from 00 to 23)");

                System.out.print("Please enter a correct time from 0 to 23.");

                slotTime = input.nextInt();


                for(int j = 0 ; j < playerbookings.size() ; j++) // Check if the player already book a slot in this time
                {
                    while(playerbookings.get(j).getSlot() == slotTime)
                    {
                        System.out.println("You already booked a slot in this time before, please enter onther slot : ");

                        input.next();

                        while(!input.hasNextInt() || input.nextInt() < 0 || input.nextInt() > 23 ) // Validate the input
                        {
                            System.out.print("This slot is already booked enter other in correct format : ");
                            input.next();
                        }

                        slotTime = input.nextInt();

                        j = 0;

                    }

                }

                while(playgrounds.get(i).isSlotfree(slotTime)) // Check if the slot is free not booked
                {

                    while(!input.hasNextInt() || input.nextInt() < 0 || input.nextInt() > 23 ) // Validate the input
                    {
                        System.out.print("This slot is already booked enter other in correct format : ");
                        input.next();
                    }

                    slotTime = input.nextInt();
                }


                founded = true; // We found it!

                if(this.eWallet >= playgrounds.get(i).getPricePerHour()) // Check if the player have money in his eWallet
                {
                    this.eWalletWithdraw(playgrounds.get(i).getPricePerHour()); // Withdraw the price of booking from the organizer
                    playgrounds.get(i).getOwner().ewalletDepoist(playgrounds.get(i).getPricePerHour()); // Deposit the price of booking for the owner

                    playerBooking.setSlot(slotTime);   // Set the booking details(slot,playground,team and organizer)
                    playerBooking.setOrganizer(this);  // Set the player who book as a organizer
                    playerBooking.setPlayGround(playgrounds.get(i)); // Passing the playground in the book
                    playerBooking.setTeam(bookTeam); // Passing the team for the book
                    playerBooking.book_it();   // Making the book status true to know that it's booked
                    playgrounds.get(i).bookSlot(playerBooking); // Adding the book to the playground slots
                    this.playerbookings.add(playerBooking);
                    System.out.println("The book operation has been done successfully.");
                }

                else  // Telling the user that he doesn't have enough money
                {
                    System.out.println("Sorry you don't have enough money in your eWallet, please add some money");
                }

            }

            else
            {
                founded = false; // We didn't find it yet :""(
            }
        }

        if(!founded) // Telling the user
        {
            System.out.println("You didn't enter a correct playground name\n");
        }


    }

    public void cancelBooking(ArrayList <playground> playgrounds){

        Scanner input = new Scanner(System.in);

        if(playerbookings.isEmpty())
        {
            System.out.println("You didn't book any slot yet.");
        }

        else {

            booking bookCanel = new booking();  // Object will contain the details of booking that the user want to cancel
            String playgroundName; // variable which will contain the playground that the player booked slot into
            int slotNo;
            System.out.println("The slots you booked : ");

            for (int i = 0; i < this.playerbookings.size(); i++) {

                if(playerbookings.get(i).isBooked() && i == 0) {
                    System.out.println("1- " + playerbookings.get(i).getSlot() + ":00");
                    i++;
                }

                else
                {
                    System.out.println(i+"- "+playerbookings.get(i).getSlot()+":00");
                }

            }

            while( input.nextInt() > playerbookings.size() || input.nextInt() < 1) // Validate the input
            {
                System.out.println("Enter the slot number that you want to cancel : ");
                input.next();
            }

            slotNo = input.nextInt()-1; // Make the slotNO zero based;
            bookCanel =  playerbookings.get(slotNo);

            for(int i = 0 ; i < playgrounds.size() ; i++)
            {
                if(playgrounds.get(i).getName().equals(bookCanel.getPlayGround()))
                {
                    playgrounds.get(i).cancelSlot(bookCanel); // calling the method of deleting the booking from playground

                    playgrounds.get(i).getOwner().eWalletWithdraw(playgrounds.get(i).getPricePerHour()); // Take the money from the playground owner
                    this.ewalletDepoist(playgrounds.get(i).getPricePerHour()); // return the money to the user
                    System.out.println("The slot in the playground : "+playgrounds.get(i).getName()+", in the slot "+bookCanel.getSlot()+":00 , has been canceled");
                    playerbookings.remove(slotNo); // deleting the booking from the user bookings list
                    break;
                }

            }


        }

    }



}


    
