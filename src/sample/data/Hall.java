package sample.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class Hall {
    private final String hallType;
    private final int capacity;
    //we will maintain a map to map the booking with time
    private final Map<Show , Integer> booked = new HashMap<>();

    public Hall(String hallType) {
        this.hallType = hallType;
        switch (hallType.toLowerCase()) {
            case "royal suit":
                this.capacity = 5;
                break;
            case "suit":
            case "deluxe":
                this.capacity = 7;
                break;
            case "secondary hall":
            case "primary hall":
                this.capacity = 10;
                break;
            default:
                this.capacity = 0;
        }
    }

    public boolean book(LocalDateTime time,Movie movie, int a){
        //this will return the current booking of the time stamp;
        Show show = new Show(time,movie);
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


    public String getHallType() {
        return hallType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj instanceof Hall){
            return this.hallType.equals(((Hall)obj).hallType);
        }

        return false;
    }

    private class Show{
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
    }

}
