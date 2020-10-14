package Playground_Unit;

import Player_Details.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Scanner;

public class playground_owner {

    // ====================== Private attributes ================= //


    private String name;
    private String email;
    private String password;
    private String phoneNumber;       // The main attribute for the player
    private int ID;
    private float eWallet;
    private ArrayList <playground> playgrounds;

    private boolean emailValid(String mail) // Private function will check the validation of the player email
    {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                "[a-zA-Z0-9_+&*-]+)*@" +
                                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                    "A-Z]{2,7}$";

        return mail.matches(emailPattern);
    }


    // ====================== Public methods ================= //


   public playground_owner(){

       this.name = "NULL";
       this.phoneNumber = "NULL";
       this.email = "NULL";
       this.password = "NULL";
       ID = 0;
       eWallet = 0;
       playgrounds = new ArrayList <playground> ();
   }

    public void setName(){

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the username : " );
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

    public void addPlayground(ArrayList <playground> playgrounds){



        booking P1 = new booking();
        playground newPlayground = new playground(P1);

        newPlayground.setDetails(playgrounds);


        for(int i = 0 ; i < playgrounds.size() ; i++)
        {
            while(playgrounds.get(i).getLocation().equals(newPlayground.getLocation()))
            {
                System.out.println("This location is belongs to onther playground already..");
                newPlayground.setLocation();
                i = 0;

            }
        }

        this.playgrounds.add(newPlayground);

        playgrounds.add(newPlayground);

        System.out.println("The playground : "+newPlayground.getName()+" has been added.");


    }

    public void updatePlayground(){

        Scanner input = new Scanner(System.in);

        boolean founded = false;
        System.out.print("Enter the name of the playground that you want to update : ");
        String playgroundName = input.nextLine();

        for(int i = 0 ; i < this.playgrounds.size() ; i++)
        {
            if(playgrounds.get(i).getName().equals(playgroundName))
            {
                String operation;

                System.out.println("1- Update name");
                System.out.println("2- Update Location");
                System.out.println("3- Update description");
                System.out.println("4- Update the price per hour");
                operation = input.nextLine();

                if(operation.equals("1"))
                {
                    playgrounds.get(i).setName();

                }

                else if(operation.equals("2"))
                {
                    playgrounds.get(i).setLocation();
                }

                else if(operation.equals("3"))
                {
                    playgrounds.get(i).setDescription();
                }

                else if(operation.equals("4"))
                {
                    playgrounds.get(i).setPricePerHour();
                }

            }

        }



    }

    public void viewBookings(){

        for(int i = 0 ; i < playgrounds.size() ; i++)
        {
            System.out.println("======================== Display booked slots for playground '"+playgrounds.get(i).getName()+"' ========================\n");
            playgrounds.get(i).bookedSlots();
            System.out.println("\n==============================================\n");
        }

    }

}
