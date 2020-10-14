package Playground_Unit;

import Player_Details.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class playground {

    // ====================== Private attributes ================= //


    private String name;
    private String location;
    private String description;
    private boolean availability;
    private float pricePerHour;
    playground_owner owner ;

   // private booking [] bookings ;

    //List<MyType> fixed = Arrays.asList(new MyType[100]);
    List <booking> bookings;


    // ====================== Public methods ================= //

    playground(){

        owner = new playground_owner();
        bookings = Arrays.asList(new booking[24]);

    }

    public playground(booking P1){

        owner = new playground_owner();
        bookings = Arrays.asList(new booking[24]);

        for(int i = 0 ; i < 24 ; i++)
        {
            bookings.set(i , P1);
        }


    }

    public void setName(){

        Scanner input = new Scanner(System.in);

        String playgroundName;

        System.out.print("Please enter the playground name : ");
        playgroundName = input.nextLine();

        this.name = playgroundName;


    }

    public String getName(){

        return this.name;

    }

    public void setLocation(){

        Scanner input = new Scanner(System.in);

        String address;
        String addressPattern = "^\\d{1,4}" + " " + "[a-zA-Z]{4,19}"+ "-" + "[a-zA-Z]{3,10}"; // Pattern to recognize the address

        System.out.print("Please enter the location in correct format : stNumber streetName-goverName, eg.(15 Orman-Giza) : ");
        address = input.nextLine();

        while(!address.matches(addressPattern))
        {
            System.out.print("Pleae enter the address in the correct format : (StNumber StreetName-GoverName) : ");
            address = input.nextLine();
        }

        location = address;
    }

    public String getLocation(){

        return this.location;
    }

    public  void setDescription(){

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the playground description : ");
        input.reset();
        this.description = input.nextLine();


    }

    boolean isAvailable(){

        return this.availability;
    }

    public void freeSlots(){

        for(int i = 0 ; i < bookings.size() ; i++)
        {

            if(!bookings.get(i).isBooked() && i == 0)
            {
                System.out.println("The slot 00:00 is not booked.");
            }

            else if(!bookings.get(i).isBooked())
            {
                System.out.println("The slot "+i+":00 is not booked");
            }

        }

    }

    public void bookedSlots(){

        boolean isThereFreeSlots = false; // variable to know if there any booked slots or all slots are free

        for(int i = 0 ; i < bookings.size() ; i++)
        {

            if(bookings.get(i).isBooked() && i == 0)
            {
                System.out.println("The slot 00:00 is booked.");
                isThereFreeSlots = true;
            }

            else if(bookings.get(i).isBooked())
            {
                System.out.println("The slot "+i+":00 is booked");
                isThereFreeSlots = true;
            }

        }

        if(!isThereFreeSlots)  // Inform the playground onwer that there is no booked slots yet
        {
            System.out.println("There is no booked slots yet in the playground : "+this.getName());
        }

    }

    public boolean isSlotfree(int slot){
        return bookings.get(slot).isBooked();

    }

    public void setPricePerHour(){

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the price per hour for this playground : ");
        this.pricePerHour = input.nextFloat();

    }

    public float getPricePerHour(){
        return this.pricePerHour;
    }

    public void setDetails(ArrayList <playground> playgrounds){

        this.setName();
        this.setLocation();

        for(int i = 0 ; i < playgrounds.size() ; i++)
        {
            while(playgrounds.get(i).getName().equals(this.name))
            {
                System.out.println("Sorry this name is already belongs to onther playground try enter new one ");
                this.setName();
                i = 0;
            }

            while(playgrounds.get(i).getLocation().equals(this.location))
            {
                System.out.println("Sorry this location is already belongs to onther playground try enter new one ");
                this.setLocation();
                i = 0;

            }

        }


        this.setDescription();
        this.setPricePerHour();

    }

    public void getDetails(){

        System.out.println("\n====================================\n");
        System.out.println("Playground name : "+ this.name);
        System.out.println("Playground location : "+ this.location);
        System.out.println("Playground price per hour "+ this.pricePerHour);
        System.out.println("Playground free slots : \n");
        this.freeSlots();
        System.out.println("====================================\n\n");

    }

    public void setOwner(playground_owner Owner){
        this.owner = Owner;
    }

    public playground_owner getOwner(){
        return this.owner;
    }

    public void bookSlot(booking bookSlot){

        bookings.set(bookSlot.getSlot() , bookSlot) ;
        bookings.get(bookSlot.getSlot()).book_it();

        if(bookings.get(bookSlot.getSlot()).isBooked())
        {
            System.out.println("The slot "+ bookSlot.getSlot()+":00 has been booked.\n");
        }

        else
        {
            System.out.println("The slot booking operation failed.");
        }
    }

    public void cancelSlot(booking cancelBook){

        booking freeBook = new booking();

        for(int i = 0 ; i < this.bookings.size() ; i++)
        {
            if(cancelBook.getSlot() == bookings.get(i).getSlot())
            {
                bookings.set(i,freeBook);
            }
        }

    }

}
