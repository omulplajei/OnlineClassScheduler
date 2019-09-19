package simedia;

import java.time.LocalDate;

public class DatePosition {

    private LocalDate date;
    private int position;

    public DatePosition(LocalDate date, int position) {
        this.date = date;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public boolean equals(DatePosition datePosition) {

        if (datePosition.getLocalDate().isEqual(this.date)&&datePosition.getPosition() == this.position){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder datePosition = new StringBuilder();
        datePosition.append(this.date);
        switch (this.position){
            case 1:
                datePosition.append(" - 08:00 - 10:00");
                break;
            case 2:
                datePosition.append(" - 11:00 - 13:00");
                break;
            case 3:
                datePosition.append(" - 14:00 - 16:00");
                break;
            case 4:
                datePosition.append(" - 17:00 - 19:00");
                break;
        }
        return datePosition.toString();
    }

}
