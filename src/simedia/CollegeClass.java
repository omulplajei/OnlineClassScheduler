package simedia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollegeClass {

    private Teacher teacher;
    private String speciality;
    private int maximumStudents = 30;
    private List<Appointment> appointments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private static int classCount = 0;
    private int id;

    CollegeClass(String speciality) {
        this.speciality = speciality;
        this.id = ++classCount;
    }

    int getId() {
        return id;
    }

    String getSpeciality() {
        return speciality;
    }

    void setMaximumStudents(int maximumStudents) {
        this.maximumStudents = maximumStudents;
    }

    int getMaximumStudents() {
        return maximumStudents;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Classroom classroom, LocalDate date,int position){
        appointments.add(new Appointment(classroom,date,position));
    }

    void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    void addStudents(List<Student> students) {
        if (students.size() == 0) {
            return;
        }
        int studToAddNumb = students.size();
        students.removeAll(this.students);
        int studRemain = students.size();
        if (!(studToAddNumb == studRemain)){
            System.out.println((studToAddNumb-studRemain) + " students are already in this class so they will not be added.");
        }
        if (this.students.size() + students.size() > maximumStudents) {
            System.out.println("Maximum capacity of this class is " + maximumStudents + ". You can add maximum " + (maximumStudents - this.students.size()) + " students.");
        } else {
            this.students.addAll(students);
        }
    }

    void deleteStudents(Scanner sc) {

        List<Student> listOfStudentsToDelete = new ArrayList<>();
        System.out.println();
        System.out.println("List of students");
        printStudents();

        System.out.print("Choose a student or more by id separated with comma to delete: ");

        String choice = sc.nextLine();

        while (!choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
            System.out.print("Enter valid numbers(Id): ");
            choice = sc.nextLine();
        }

        List<Integer> ids = new ArrayList<>();
        for (String id : choice.split("\\s*,\\s*")
        ) {
            ids.add(Integer.parseInt(id));
        }

        for (Student student : students
        ) {
            if (ids.contains(student.getId())) {
                listOfStudentsToDelete.add(student);
            }
        }

        if (listOfStudentsToDelete.size() == 0) {
            System.out.println("No student was selected.");
        }

        System.out.println();
        for (Student student : listOfStudentsToDelete
        ) {
            System.out.println(student);
        }
        System.out.println();
        System.out.print("Confirm students that are going to be deleted. Type 'y' for yes or 'n' for no. : ");
        String confirmList = sc.nextLine();

        while (!confirmList.matches("[ynYN]")) {

            System.out.print("Enter a valid letter. Confirm students that are going to be deleted. Type 'y' for yes or 'n' for no. : ");
            confirmList = sc.nextLine();

        }

        if (confirmList.matches("[nN]")) {
            listOfStudentsToDelete.clear();
        }

        this.students.removeAll(listOfStudentsToDelete);
    }

    void printStudents(){
        for (Student student:students
             ) {
            System.out.println(student);
        }
    }

    void printAppointments(){
        for (Appointment appointment:appointments
        ) {
            System.out.println(appointment);
        }
    }

    @Override
    public String toString() {

        StringBuilder toString = new StringBuilder();
        toString.append("Class [id = ");
        toString.append(this.id);
        toString.append(",speciality = ");
        toString.append(speciality);
        toString.append(", teacher = ");
        if (teacher == null) {
            toString.append("no teacher yet");
        } else {
            toString.append(teacher.getName());
        }
        toString.append(", number of students = ");
        toString.append(students.size());
        toString.append(", maximum of students = ");
        toString.append(maximumStudents);
        toString.append("]");
        return toString.toString();
    }
}
