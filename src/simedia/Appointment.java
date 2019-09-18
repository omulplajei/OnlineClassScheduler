package simedia;

import java.time.LocalDate;

public class Appointment {

    private Classroom classroom;
    private DatePosition datePosition;

    public Appointment(Classroom classroom, LocalDate date, int position) {
        this.classroom = classroom;
        datePosition = new DatePosition(date,position);
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public DatePosition getDatePosition() {
        return datePosition;
    }

    @Override
    public String toString() {

        StringBuilder toString = new StringBuilder();
        toString.append("Classroom - ");
        toString.append(classroom.getClassroomNumber());
        toString.append(" - ");
        toString.append(datePosition);

        return toString.toString();
    }

}
