package Player_Details;

import Playground_Unit.*;

import java.util.Scanner;
import java.util.ArrayList;

public class team {

    // ====================== Private attributes ================= //


    private String teamName;

    private ArrayList <player> teamMembers;

    // ====================== Public methods ================= //

    team(){
        teamName = "NULL";
        teamMembers = new ArrayList <player> ();
    }

    public void deleteMember(){

        Scanner input = new Scanner(System.in);

        String playerName;
        boolean founded = false;
        System.out.print("Please enter the player name : ");
        playerName = input.nextLine();

        for(int i = 0; i < teamMembers.size() ; i++)
        {
            if(teamMembers.get(i).getName() == playerName)
            {
                teamMembers.remove(teamMembers.get(i));
                System.out.println("The player : "+playerName+" , Has been added removed from the team");
                founded = true;
                break;
            }

            else
            {
                founded = false;
            }

            if(!founded)
            {
                System.out.println("The player : "+playerName+" Does not exist.\n");
            }

        }



    }

    public void addMember(){

        player newMember = new player();

        newMember.setName();

        teamMembers.add(newMember);


    }

    public void setTeam(player teamOwner){  // The parameter is the global list which contain players data

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your team name : ");
        this.teamName = input.nextLine();

        System.out.println("You have been added to the team as a owner");
        teamMembers.add(teamOwner);

        System.out.println("The team has been created successfully");

    }

    public void displayTeam(){

        if(teamMembers.size() == 0)
        {
            System.out.println("There is no members in your team yet");
        }

        else {
            System.out.println("\t\t\tThe team name is : "+this.teamName+"\t\t\t");
            for (int i = 0; i < teamMembers.size(); i++) {
                System.out.println("\n======================================\n");
                System.out.println("Member NO "+i+" name : "+ teamMembers.get(i).getName());
                System.out.println("Member NO "+i+" ID : "+ teamMembers.get(i).getID());
                System.out.println("\n======================================\n");
            }
        }

    }

    public String getTeamName(){

        return this.teamName;

    }

}
