package simedia;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        College balcescu = new College("Nicolae Balcescu");
        balcescu.addStudentsToCollege(initStudents());
        balcescu.addTeachersToCollege(initTeachers());
        balcescu.addCollegeClasses(initClasses());


        boolean stayInLoop = true;

        while (stayInLoop) {
            balcescu.printCollege();
            String choice = makeAChoiceMain(scanner);
            switch (choice) {
                case "a":
                case "A":
                    balcescu.createStudent(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "b":
                case "B":
                    balcescu.deleteStudentsFromCollege(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "c":
                case "C":
                    balcescu.createTeacher(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "d":
                case "D":
                    balcescu.deleteTeachersFromCollege(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "e":
                case "E":
                    balcescu.printStudents();
                    stayInLoop = continueProgram();
                    break;
                case "f":
                case "F":
                    balcescu.printTeachers();
                    stayInLoop = continueProgram();
                    break;
                case "g":
                case "G":
                    balcescu.createCollegeClass(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "h":
                case "H":
                    balcescu.deleteCollegeClass(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "i":
                case "I":
                    balcescu.printClasses();
                    stayInLoop = continueProgram();
                    break;
                case "j":
                case "J":
                    CollegeClass classToManage = balcescu.selectCollegeClass(scanner);
                    if (classToManage == null) {
                        System.out.println("You didn't sellect any class.");
                        break;
                    }
                    boolean stayInClassManagement = true;
                    while (stayInClassManagement) {
                        String choiceForClass = balcescu.makeAChoiceManageClass(classToManage, scanner);
                        switch (choiceForClass) {
                            case "a":
                            case "A":
                                classToManage.setTeacher(balcescu.selectTeacherById("add to class", scanner));
                                System.out.println(classToManage);
                                stayInClassManagement = continueProgram();
                                break;
                            case "b":
                            case "B":
                                classToManage.addStudents(balcescu.selectStudents("add to class", scanner));
                                System.out.println(classToManage);
                                stayInClassManagement = continueProgram();
                                break;
                            case "c":
                            case "C":
                                classToManage.deleteStudents(scanner);
                                stayInClassManagement = continueProgram();
                                break;
                            case "d":
                            case "D":
                                classToManage.setTeacher(null);
                                stayInClassManagement = continueProgram();
                                break;
                            case "e":
                            case "E":
                                classToManage.printStudents();
                                stayInClassManagement = continueProgram();
                                break;
                            case "f":
                            case "F":
                                System.out.print("Input the number of maximum students of the class. Should be between 5 and 50: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.print("Wrong input. Enter the number of maximum students of the class: ");
                                }
                                int maxStudents = scanner.nextInt();
                                scanner.nextLine();
                                if (maxStudents > 50 || maxStudents < 5) {
                                    System.out.println("Invalid number. The maximum number of students will remain the same");
                                    maxStudents = classToManage.getMaximumStudents();
                                }
                                classToManage.setMaximumStudents(maxStudents);
                                stayInClassManagement = continueProgram();
                                break;
                            case "g":
                            case "G":
                                classToManage.printAppointments();
                                stayInClassManagement = continueProgram();
                                break;
                            case "h":
                            case "H":
                                Classroom classroom = balcescu.getclassroom(scanner);
                                if (classroom == null) {
                                    break;
                                }
                                if (classToManage.getStudents().size()>classroom.getSeats()){
                                    System.out.println("The classroom is to small for you!");
                                    break;
                                }
                                classroom.printSchedule();
                                System.out.print("Input the date for class appointment(must be from tomorrow and an year from now) (yyyy-mm-dd): ");
                                String date = scanner.nextLine();
                                while (!isDateValid(date)) {
                                    System.out.print("Wrong input! Input the date for class appointment(must be from tomorrow and an year from now) (yyyy-mm-dd): ");
                                    date = scanner.nextLine();
                                }
                                System.out.println("1. 08:00 - 10:00");
                                System.out.println("2. 11:00 - 13:00");
                                System.out.println("3. 14:00 - 16:00");
                                System.out.println("4. 17:00 - 19:00");
                                System.out.println();
                                System.out.print("Select the position you want for your appointment: ");
                                Integer position = 0;
                                boolean isPositionCorrect = false;
                                while (!isPositionCorrect) {
                                    while (!scanner.hasNextInt()) {
                                        System.out.println("Wrong input! Select the position you want for your appointment(from 1 to 4): ");
                                        scanner.next();
                                    }
                                    position = scanner.nextInt();
                                    scanner.nextLine();
                                    if (position > 4 || position < 1) {
                                        System.out.println("Wrong input. Must be between 1 and 4 inclusive.");
                                    } else {
                                        isPositionCorrect = true;
                                    }
                                }
                                DatePosition datePosition = new DatePosition(LocalDate.parse(date), position);
                                boolean isOccupied = false;
                                for (Map.Entry<DatePosition, String> entry : classroom.getSchedule().entrySet()
                                ) {
                                    if (entry.getKey().equals(datePosition)) {
                                        isOccupied = true;
                                        break;
                                    }
                                }
                                if (isOccupied) {
                                    System.out.println("Classroom is occupied in that interval!");
                                } else {
                                    boolean appointmentOk = false;
                                    Appointment appToMake = new Appointment(classroom, LocalDate.parse(date), position);
                                    for (Appointment appointmentToCheck : classToManage.getAppointments()
                                    ) {
                                        if (appointmentToCheck.getDatePosition().equals(appToMake.getDatePosition())) {
                                            System.out.println("You already have an appointment at that hour in classroom number " + appointmentToCheck.getClassroom().getClassroomNumber() + ".");
                                            appointmentOk = true;
                                            break;
                                        }
                                    }

                                    if (!appointmentOk) {
                                        classToManage.addAppointment(classroom, LocalDate.parse(date), position);
                                        classroom.addToSchedule(new DatePosition(LocalDate.parse(date), position), classToManage.getSpeciality());
                                    }
                                }

                                stayInClassManagement = continueProgram();
                                break;
                            case "i":
                                classToManage.printAppointments();
                                System.out.println();
                                System.out.print("Enter the number of the appointment you want to delete: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.print("Wrong number! Enter the number of the appointment you want to delete: ");
                                    scanner.next();
                                }
                                int appointmentNumber = scanner.nextInt();
                                scanner.nextLine();
                                boolean isNumberValid = false;
                                for (Appointment appointment : classToManage.getAppointments()
                                ) {
                                    if (appointmentNumber > classToManage.getAppointments().size() || appointmentNumber < 1) {
                                        System.out.println("Wrong input.");
                                        break;
                                    } else {
                                        isNumberValid = true;
                                    }
                                }
                                for (Map.Entry<DatePosition, String> entry : classToManage.getAppointments().get(appointmentNumber-1).getClassroom().getSchedule().entrySet()
                                ) {
                                    if (entry.getKey().equals(classToManage.getAppointments().get(appointmentNumber-1).getDatePosition())) {
                                        classToManage.getAppointments().get(appointmentNumber-1).getClassroom().getSchedule().remove(entry.getKey());
                                        break;
                                    }
                                }
                                classToManage.getAppointments().remove(classToManage.getAppointments().get(appointmentNumber-1));
                                break;

                        case "q":
                        case "Q":
                            stayInClassManagement = false;
                    }
            }
            stayInLoop = continueProgram();
            break;
            case "k":
            case "K":
                balcescu.createClassroom(scanner);
                stayInClassManagement = continueProgram();
                break;
            case "l":
            case "L":
                balcescu.deleteClassroom(scanner);
                stayInClassManagement = continueProgram();
            case "m":
            case "M":
                balcescu.printClassrooms();
                stayInClassManagement = continueProgram();
                break;
            case "n":
            case "N":
                balcescu.printClassroomSchedule(scanner);
                stayInClassManagement = continueProgram();
                break;
            case "q":
            case "Q":
                return;
        }

    }

}

    static List<Student> initStudents() {

        List<Student> students = new ArrayList<>();
        students.add(new Student("George Vizitiu", "georgevizitiu@gmail.com", 3, 21, "m"));
        students.add(new Student("Stefan Iliescu", "stefanilie@gmail.com", 4, 39, "m"));
        students.add(new Student("George Stegaroiu", "georgesteg@gmail.com", 3, 21, "m"));
        students.add(new Student("Rares Albu", "raresalbu@gmail.com", 4, 22, "m"));
        students.add(new Student("Cristi Ambrozie", "cristiambrozie@yahoo.com", 2, 20, "m"));
        students.add(new Student("Cristi Geambasu", "cristigeamb@yahoo.com", 1, 45, "m"));
        students.add(new Student("Percy Talley", "percytalley@yahoo.com", 1, 19, "f"));
        students.add(new Student("Percya Talley", "percyty@yahoo.com", 1, 19, "f"));
        students.add(new Student("Derrick Randall", "derrickrandall@yahoo.com", 4, 27, "m"));
        students.add(new Student("Anna-Marie Curry", "anna-curry@gmail.com", 2, 23, "f"));
        students.add(new Student("Anna-Marie", "anna-marie-curry@gmail.com", 2, 23, "f"));
        students.add(new Student("Marie Curry", "anna-marie@gmail.com", 2, 23, "f"));
        students.add(new Student("Geo Viziu", "georgeviz@gmail.com", 3, 21, "m"));
        students.add(new Student("Gheorghe Steroiu", "georteg@gmail.com", 3, 21, "m"));
        students.add(new Student("Rares Albut", "raralbu@gmail.com", 4, 22, "m"));
        students.add(new Student("Cristian Ambroz", "crambrozie@yahoo.com", 2, 20, "m"));
        students.add(new Student("Cristinel Geambasiu", "crstigeamb@yahoo.com", 1, 45, "m"));
        students.add(new Student("Pey Taly", "peralley@yahoo.com", 1, 29, "f"));
        students.add(new Student("Percya Talley", "pety@yahoo.com", 1, 49, "f"));
        students.add(new Student("Rick Rand", "derrkrandall@yahoo.com", 4, 17, "m"));
        students.add(new Student("Ann Curtoise", "annary@gmail.com", 2, 52, "f"));
        students.add(new Student("Anna-Marie Vicky", "anna-mar-curry@gmail.com", 4, 44, "f"));
        students.add(new Student("Mar Curryelle", "ana-ie@gmail.com", 3, 21, "f"));
        students.add(new Student("Alint Tomescu", "alintomescu@gmail.com", 3, 27, "m"));
        students.add(new Student("Radu Soiu", "geeg@gmail.com", 3, 21, "m"));
        students.add(new Student("Rada Albescu", "rabu@gmail.com", 4, 22, "f"));
        students.add(new Student("Cristina Frazie", "crisrozie@yahoo.com", 4, 22, "f"));
        students.add(new Student("Fritz Gall", "fritzy@yahoo.com", 4, 19, "m"));
        students.add(new Student("Persida Acropol", "persida@yahoo.com", 1, 29, "f"));
        students.add(new Student("Perya Tey", "peryty@yahoo.com", 3, 29, "f"));
        students.add(new Student("Der Rand", "derriall@yahoo.com", 4, 43, "m"));
        students.add(new Student("An Gally", "angaly@gmail.com", 2, 25, "f"));
        students.add(new Student("Marie Vladescu", "marievlasdesc@gmail.com", 2, 34, "f"));
        students.add(new Student("Marry Poppins", "mariepop@gmail.com", 4, 22, "f"));
        students.add(new Student("Mihai Peiu", "geoviz@gmail.com", 1, 28, "m"));
        students.add(new Student("Vlad Tudor", "geotud@gmail.com", 1, 16, "m"));
        students.add(new Student("Raducu Micu", "copilmicu@gmail.com", 2, 21, "f"));
        students.add(new Student("Gelutu Amoz", "gelutu@yahoo.com", 3, 52, "m"));
        students.add(new Student("Madalin Pop", "madalinpop@yahoo.com", 4, 45, "m"));
        students.add(new Student("Madalina Popescu", "madalinapopescu@yahoo.com", 1, 22, "f"));
        students.add(new Student("Viorica Dancila", "vio@yahoo.com", 1, 65, "f"));
        students.add(new Student("Daniel Cotabita", "danilsihastru@yahoo.com", 1, 24, "m"));
        students.add(new Student("Mariana Ceausescu", "ceaus@gmail.com", 3, 21, "f"));
        students.add(new Student("Andrada Fane", "andradaf@gmail.com", 2, 37, "f"));
        students.add(new Student("Ofelia Nastase", "ofi@gmail.com", 4, 25, "f"));
        return students;
    }

    static List<Teacher> initTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Alexandru Avram", "alexandruavram@gmail.com", "mathematics", "0734653675", "m"));
        teachers.add(new Teacher("Christine Correa", "christinecorrea@yahoo.com", "english", "0778990765", "f"));
        teachers.add(new Teacher("Alice Clegg", "aliceclegg@gmail.com", "music", "0723554335", "f"));
        teachers.add(new Teacher("Bonita Knox", "bonitaknox@gmail.com", "sexology", "0789898989", "f"));
        teachers.add(new Teacher("Emilia Ciorcila", "emiliaciorcila@yahoo.com", "psychology", "0765437765", "f"));
        teachers.add(new Teacher("Bianca Antono", "biancaanton@gmail.com", "physics", "0788554766", "f"));
        teachers.add(new Teacher("Laurentiu Coman", "laurentiucoman@gmail.com", "philosophy", "0782112332", "m"));
        teachers.add(new Teacher("Victor Tomescu", "victort@gmail.com", "biology", "0743675665", "m"));
        teachers.add(new Teacher("Andrei Folta", "foltaandrei@gmail.com", "chemistry", "0723685746", "m"));
        teachers.add(new Teacher("Anca Petrescu", "ancapeterscu@gmail.com", "history", "0734566734", "f"));
        teachers.add(new Teacher("Liviu Topan", "liviu topan@gmail.com", "religion", "0733545454", "m"));
        return teachers;
    }

    static List<CollegeClass> initClasses() {
        List<CollegeClass> classes = new ArrayList<>();
        classes.add(new CollegeClass("mathematics"));
        classes.add(new CollegeClass("history"));
        classes.add(new CollegeClass("music"));
        classes.add(new CollegeClass("physics"));
        classes.add(new CollegeClass("psychology"));
        classes.add(new CollegeClass("commerce"));
        return classes;
    }

    private static String makeAChoiceMain(Scanner sc) {

        System.out.println("a) Add student to college");
        System.out.println("b) Delete student from college");
        System.out.println("c) Add teacher to college");
        System.out.println("d) Delete teacher from college");
        System.out.println("e) List students");
        System.out.println("f) List teachers");
        System.out.println("g) Create new college class");
        System.out.println("h) Delete college classes");
        System.out.println("i) List college classes");
        System.out.println("j) Manage a class");
        System.out.println("k) Create classroom");
        System.out.println("l) Delete classroom");
        System.out.println("m) List classrooms");
        System.out.println("n) List timetable of classroom");
        System.out.println("q) Exit the program");
        System.out.println();
        String str;
        System.out.print("Input the letter which represents your choice: ");
        str = sc.nextLine();

        while (!str.matches("[ABCDEFGHIJKLMNabcdefghijklmnq]")) {

            System.out.print("You entered a wrong letter! Input the letter which represents your choice: ");
            str = sc.nextLine();

        }
        return str;

    }

    private static boolean continueProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'q' to exit program or something else to continue: ");
        String check = scanner.nextLine();
        return (!check.equals("q"));

    }

    static boolean isDateValid(String date) {
        try {
            LocalDate date1 = LocalDate.parse(date);
            if (date1.isAfter(LocalDate.now()) && date1.isBefore(LocalDate.now().plusYears(1))) {
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }
}
