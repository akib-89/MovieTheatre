package sample.data;

import java.time.LocalDateTime;

public class Booking {

    private final String name;
    //these field will have setter that is why i am not making the fields final
    private int number;
    private LocalDateTime time;
    private Movie movie;
    private String hall;

    public Booking(String name, int number,LocalDateTime time,Movie movie,String hall){
        this.name = name;
        this.number = number;
        this.time = time;
        this.movie = movie;
        this.hall = hall;
    }

    public boolean book(){
        switch (hall.toLowerCase()) {
            case "royal suit":
                return Hall.ROYAL().book(time,movie,number);
            case "suit":
                return Hall.SUIT().book(time,movie,number);
            case "deluxe":
                return Hall.DELUXE().book(time,movie,number);
            case "secondary hall":
                return Hall.SECONDARY().book(time,movie,number);
            case "primary hall":
                return Hall.PRIMARY().book(time,movie,number);
            default:
                return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getHall() {
        return hall;
    }
}
