package simedia;

import java.time.LocalDate;

public class ClassroomTest {

    public void testAddToSchedule() {
        //given
        Classroom classroom = new Classroom(10);

        //when
        DatePosition datePosition = new DatePosition(LocalDate.now(), 1);
        classroom.addToSchedule(datePosition, "Economy");

        //then
        if (!"Economy".equals(classroom.getSchedule().get(datePosition))) {
            System.out.println("Test failed");
        } else {
            System.out.println("Test OK");
        }

    }

    public static void main(String[] args) {
        ClassroomTest classroomTest = new ClassroomTest();
        classroomTest.testAddToSchedule();
    }
}
