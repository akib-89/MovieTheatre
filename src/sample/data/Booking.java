package sample.data;

import java.time.LocalDateTime;

public class Booking {

    private static final Hall royal = new Hall("Royal Suit");
    private static final Hall suit = new Hall("Suit");

    private static final Hall deluxe = new Hall("Deluxe");
    private static final Hall primary = new Hall("Primary");
    private static final Hall secondary = new Hall("Secondary");

    private String name;
    private int number;

    public Booking(String name, int number){
        this.name = name;
        this.number = number;
    }

    public boolean book(String hall, LocalDateTime time, Movie movie){
        switch (hall.toLowerCase()) {
            case "royal suit":
                return royal.book(time,movie,number);
            case "suit":
                return suit.book(time,movie,number);
            case "deluxe":
                return deluxe.book(time,movie,number);
            case "secondary hall":
                return secondary.book(time,movie,number);
            case "primary hall":
                return primary.book(time,movie,number);
            default:
                return false;
        }
    }


}
