package simedia;

import java.time.LocalDate;
import java.util.*;

public class Classroom {

    private static int classroomCount = 0;
    private int classroomNumber;
    private int seats;
    private Map<DatePosition, String> schedule = new HashMap<>();

    Classroom(int seats){
        this.classroomNumber = ++classroomCount;
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public int getClassroomNumber() {
        return classroomNumber;
    }

    public Map<DatePosition, String> getSchedule() {
        return schedule;
    }

    void addToSchedule(DatePosition datePosition, String speciality) {

        for (Map.Entry<DatePosition,String> entry:schedule.entrySet()
        ) {
            if (entry.getKey().equals(datePosition)){
                System.out.println("Is occupied!");
                return;
            }
        }

        schedule.putIfAbsent(datePosition, speciality);
    }

    void printSchedule() {
        if (schedule.isEmpty()){
            System.out.println("The classroom is free for any appointment.");
            return;
        }
        schedule.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    @Override
    public String toString() {
        return "Classroom [classroom number = " + classroomNumber + ", seats = " + seats + "]";
    }
}

