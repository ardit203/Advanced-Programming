package Exam.SecondMidtermExam.Task31;

import java.util.HashMap;
import java.util.Map;

public class Sector {
    private String code;
    private int seats;
    private Map<Integer, Boolean> taken;
    private int type;
    private int seatsTaken;
    private boolean flag = false;

    public Sector(String code, int seats) {
        this.code = code;
        this.seats = seats;
        taken = new HashMap<>();
        type = 0;
        seatsTaken = 0;
    }

    public void buyTicket(int seat, int type) {

        if(taken.containsKey(seat)){
            throw new SeatTakenException();
        }


        if (!flag && type != 0) {
            this.type = type;
            flag = true;
        }

        if (this.type != type && type != 0) {
            throw new SeatNotAllowedException();
        }

        taken.put(seat, true);
        seatsTaken++;
    }

    public int freeSeats(){
        return seats - seatsTaken;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        int freeSeats = freeSeats();
        double percentage = 100 - (freeSeats * 1.0 / seats) * 100.0;;
        return String.format("%s\t%d/%d\t%.1f%%", code,freeSeats , seats, percentage);
    }
}
