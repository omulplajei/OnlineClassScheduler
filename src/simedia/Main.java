package simedia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
                    balcescu.createStudent(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "b":
                    balcescu.deleteStudentsFromCollege(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "c":
                    balcescu.createTeacher(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "d":
                    balcescu.deleteTeachersFromCollege(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "e":
                    balcescu.printStudents();
                    stayInLoop = continueProgram();
                    break;
                case "f":
                    balcescu.printTeachers();
                    stayInLoop = continueProgram();
                    break;
                case "g":
                    balcescu.createCollegeClass(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "h":
                    balcescu.deleteCollegeClass(scanner);
                    stayInLoop = continueProgram();
                    break;
                case "i":
                    balcescu.printClasses();
                    stayInLoop = continueProgram();
                    break;
                case "j":
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
                                classToManage.setTeacher(balcescu.selectTeacherById("add to class", scanner));
                                System.out.println(classToManage);
                                stayInClassManagement = continueProgram();
                                break;
                            case "b":
                                classToManage.addStudents(balcescu.selectStudents("add to class", scanner));
                                System.out.println(classToManage);
                                stayInClassManagement = continueProgram();
                                break;
                            case "c":
                                classToManage.deleteStudents(scanner);
                                stayInClassManagement = continueProgram();
                                break;
                            case "d":
                                classToManage.setTeacher(null);
                                stayInClassManagement = continueProgram();
                                break;
                            case "e":
                                classToManage.printStudents();
                                stayInClassManagement = continueProgram();
                                break;
                            case "f":
                                System.out.print("Input the number of maximum students of the class. Should be between 5 and 50: ");
                                while (!scanner.hasNextInt()) {
                                    System.out.print("Wrong input. Enter the number of maximum students of the class: ");
                                }
                                int maxStudents = scanner.nextInt();
                                if (maxStudents > 50 || maxStudents < 5) {
                                    System.out.println("Invalid number. The maximum number of students will remain the same");
                                    maxStudents = classToManage.getMaximumStudents();
                                }
                                classToManage.setMaximumStudents(maxStudents);
                                stayInClassManagement = continueProgram();
                                break;
                            case "g":
                                classToManage.printAppointments();
                            case "h":
                                Classroom classroom = balcescu.getclassroom(scanner);
                                classroom.printSchedule();
                                System.out.println("Input the date for class appointment.");
                                int year = 0;
                                int month = 0;
                                int day = 0;
                                boolean isYearValid = false;
                                while (!isYearValid) {
                                    System.out.print("Year = ");
                                    while (!scanner.hasNextInt()) {
                                        System.out.print("Wrong input! Year = ");
                                        scanner.next();
                                    }
                                    year = scanner.nextInt();
                                    if (year>=2019 && year<2021){
                                        isYearValid = true;
                                    }
                                    else {
                                        System.out.println("Year must be between 2019 and 2021");
                                    }
                                }
                                boolean isMonthValid = false;
                                while (!isMonthValid) {
                                    System.out.print("Month = ");
                                    while (!scanner.hasNextInt()) {
                                        System.out.print("Wrong input! Year = ");
                                        scanner.next();
                                    }
                                    month = scanner.nextInt();
                                    if (year==2019 && (month<10 || month>12)){
                                        isMonthValid = true;
                                    }
                                    else if (month>1 && month<12){
                                        isMonthValid = true;
                                    }
                                    else {
                                        System.out.println("You entered a wrong month!");
                                    }
                                }
                                boolean isDayValid = false;
                                while (!isMonthValid) {
                                    System.out.print("Month = ");
                                    while (!scanner.hasNextInt()) {
                                        System.out.print("Wrong input! Year = ");
                                        scanner.next();
                                    }
                                    month = scanner.nextInt();
                                    if (year==2019 && (month<10 || month>12)){
                                        isMonthValid = true;
                                    }
                                    else if (month>1 && month<12){
                                        isMonthValid = true;
                                    }
                                    else {
                                        System.out.println("You entered a wrong month!");
                                    }
                                }


                            case "q":
                                stayInClassManagement = false;
                        }
                    }
                    stayInLoop = continueProgram();
                    break;
                case "k":
                    balcescu.createClassroom(scanner);
                    break;
                case "l":
                    balcescu.deleteClassroom(scanner);
                case "m":
                    balcescu.printClassrooms();
                    break;
                case "n":
                    balcescu.printClassroomSchedule(scanner);
                    break;
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

        while (!str.matches("[abcdefghijklmnq]")) {

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
}
