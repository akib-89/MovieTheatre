package sample.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class Hall {
    //this is an approach inspired from the singleTone class design
    private static final Hall royal = new Hall(5);
    private static final Hall suit = new Hall(5);
    private static final Hall deluxe = new Hall(7);
    private static final Hall secondary = new Hall(10);
    private static final Hall primary = new Hall(15);

    //we will maintain a map to map the booking with time
    private final Map<Show , Integer> booked = new HashMap<>();

    private final int capacity;

    private Hall(int capacity) {
        this.capacity = capacity;
    }

    public  static Hall ROYAL() {
        return royal;
    }

    public static Hall SUIT() {
        return suit;
    }

    public static Hall DELUXE() {
        return deluxe;
    }

    public static Hall SECONDARY() {
        return secondary;
    }

    public static Hall PRIMARY() {
        return primary;
    }

    public boolean book(LocalDateTime time, Movie movie, int a){
        Show show = new Show(time, movie);

        //this will return the current booking of that particular show
        boolean contain = booked.containsKey(show);
        if (contain){
            int previous = booked.get(show);
            if (previous+a<capacity){
                booked.put(show,previous+a);
                return true;
            }

        } else {
            if (a<capacity){
                booked.put(show,a);
                return true;
            }

        }
        return false;
    }





    private static class Show{
        private final LocalDateTime time;
        private final Movie movie;

        public Show(LocalDateTime time, Movie movie) {
            this.time = time;
            this.movie = movie;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }

            if (obj instanceof Show){
                return this.time.equals(((Show)obj).time) && this.movie.equals(((Show)obj).movie);
            }

            return false;

        }

        @Override
        public int hashCode() {
            return this.time.hashCode() + this.movie.hashCode();
        }
    }

}
