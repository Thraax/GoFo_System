package Player_Details;

import Playground_Unit.*;

public class booking {

    // ====================== Private attributes ================= //

    private team Team;
    private player organizer;
    private int slotTime;
    private boolean status;
    private playground playGround;

    // ====================== Public methods ================= //

    public booking() {

        this.status = false;
        slotTime = 0;
        Team = new team();
        organizer = new player();
        playGround = new playground(this);
    }


    public void bookingTeam(player Organizer , team Team){

        this.Team = Team;
        this.organizer = Organizer;


    }

    public void getTeam(){

        Team.displayTeam();

    }

    public void setSlot(int time){

        this.slotTime = time;

    }

    public int getSlot(){
        return this.slotTime;
    }

    public boolean isBooked(){

        return this.status;
    }

    public void book_it(){
       this.status = true;
   }

   public void cancel_it(){

        this.status = false;
   }

   public void setOrganizer(player Organizer){

        this.organizer = Organizer;
   }

   public void setPlayGround(playground playGround){

        this.playGround = playGround;

   }

   public String getPlayGround(){
        return playGround.getName();
   }

   public void setTeam(team Team){
        this.Team = Team;
   }


}
