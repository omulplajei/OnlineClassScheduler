package simedia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class College {

    private String name;
    private List<Teacher> teachers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<CollegeClass> classes = new ArrayList<>();
    private List<Classroom> classrooms = new ArrayList<>();

    College(String name) {
        this.name = name;
    }

    void printCollege() {
        System.out.println();
        System.out.println(name + " Colege\n");
        System.out.println("Number of students: " + students.size());
        System.out.println("Number of teachers: " + teachers.size());
        System.out.println("Number of classes: " + classes.size());
        System.out.println();
    }

    void addTeachersToCollege(List<Teacher> teachersToAdd) {
        for (Teacher teacher : teachersToAdd
        ) {
            addTeacherToCollege(teacher);
        }
    }

    private void addTeacherToCollege(Teacher teacher) {

        if (!(searchTeacherByEmail(teacher) == null)) {
            System.out.println("The teacher already exists!");
        } else teachers.add(teacher);
    }

    void createTeacher(Scanner sc) {

        System.out.print("Enter teacher full name: ");
        String name = sc.nextLine();

        //email

        System.out.print("Enter teacher email: ");

        String email = sc.nextLine().toLowerCase();

        while (!email.matches("^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            System.out.println("The email is not valid, please enter a valid email: ");
            email = sc.nextLine().toLowerCase();
        }
        for (Teacher teacher : teachers
        ) {
            if (teacher.getEmail().equals(email)) {
                System.out.println("Teacher already is our database.");
                return;
            }
        }

        //gender

        System.out.print("Input teacher gender (m or f): ");
        String gender;
        gender = sc.nextLine();

        while (!gender.matches("[fmFM]")) {
            System.out.print("Please enter teacher gender correctly. ('m' or 'f'): ");
            gender = sc.nextLine();
        }

        //speciality

        System.out.print("Enter teacher speciality: ");
        String speciality = sc.nextLine();

        //phone number

        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        while (!phoneNumber.matches("\\d+")) {
            System.out.print("Teacher phone number have to contain only digits. Please input the phone number corectly: ");
            phoneNumber = sc.nextLine();
        }
        teachers.add(new Teacher(name, email, speciality, phoneNumber, gender));
    }

    private Teacher searchTeacherByEmail(Teacher teacher) {
        for (Teacher tch : teachers
        ) {
            if (teacher.getEmail().equals(tch.getEmail())) {
                return tch;
            }
        }
        return null;
    }

    Teacher selectTeacherById(String purpose, Scanner sc) {
        printTeachers();
        System.out.print("Select teacher by id to " + purpose + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Wrong input! Select teacher by id to" + purpose + ": ");
        }
        int teacherId = sc.nextInt();
        sc.nextLine();
        for (Teacher teacher : teachers
        ) {
            if (teacher.getId() == teacherId) {
                return teacher;
            }
        }
        System.out.println("You did't select any teacher!");
        return null;
    }

    List<Student> selectStudents(String purpose, Scanner sc) {

        List<Student> listOfSelectedStudents = new ArrayList<>();
        System.out.println();
        System.out.println("List of students");

        printStudents();

        System.out.print("Choose a student or more by id separated with comma to " + purpose + ": ");

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
                listOfSelectedStudents.add(student);
            }
        }

        if (listOfSelectedStudents.size() == 0) {
            System.out.println("No student was selected.");
        }

        System.out.println();
        for (Student student : listOfSelectedStudents
        ) {
            System.out.println(student);
        }
        System.out.println();
        System.out.print("Confirm students that are going to be " + purpose + ". Type 'y' for yes or 'n' for no. : ");
        String confirmList = sc.nextLine();

        while (!confirmList.matches("[ynYN]")) {

            System.out.print("Enter a valid letter. Confirm students that are going to be " + purpose + ". Type 'y' for yes or 'n' for no. : ");
            confirmList = sc.nextLine();

        }

        if (confirmList.matches("[nN]")) {
            listOfSelectedStudents.clear();
        }

        return listOfSelectedStudents;
    }

    void printTeachers() {

        for (Teacher teacher : teachers
        ) {
            System.out.println(teacher);
        }

    }

    void addCollegeClasses(List<CollegeClass> classesToAdd) {
        classes.addAll(classesToAdd);
    }

    void createCollegeClass(Scanner sc) {

        System.out.print("Enter class speciality: ");
        String name = sc.nextLine();

        if (classes.size() == 0) {
            classes.add(new CollegeClass(name));
        } else {
            for (CollegeClass collegeClass : classes
            ) {
                if (collegeClass.getSpeciality().equalsIgnoreCase(name)) {
                    System.out.println("Class already exists.");
                    return;
                }
            }
            classes.add(new CollegeClass(name));
        }

    }

    void deleteCollegeClass(Scanner sc) {

        printClasses();
        System.out.print("Select the class you want to delete(by id): ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter the number which represents the id of the class you want to delete.");
            sc.next();
        }
        CollegeClass colegeToDelete = null;
        int id = sc.nextInt();
        sc.nextLine();
        for (CollegeClass collegeClass : classes
        ) {
            if (collegeClass.getId() == id) {
                colegeToDelete = collegeClass;
                System.out.println("Are you sure you want to delete " + collegeClass.getSpeciality() + " class from your college classes?");
                System.out.print("Type 'y' for yes or 'n' for no. : ");

                String confirmDelete = sc.nextLine();

                while (!confirmDelete.matches("[ynYN]")) {

                    System.out.print("Enter a valid letter. Type 'y' for yes or 'n' for no. : ");
                    confirmDelete = sc.nextLine();

                }

                if (confirmDelete.matches("[nN]")) {
                    return;
                } else colegeToDelete = collegeClass;
            }
        }
        if (!(colegeToDelete == null)) {
            classes.remove(colegeToDelete);
        }

    }

    void addStudentsToCollege(List<Student> studentsToAdd) {
        for (Student student : studentsToAdd
        ) {
            if (!(searchStudentByEmail(student) == null)) {
                System.out.println("The student already exists!");
            } else students.add(student);
        }
    }

    private Student searchStudentByEmail(Student student) {
        for (Student std : students
        ) {
            if (student.getEmail().equals(std.getEmail())) {
                return std;
            }
        }
        return null;
    }

    void createStudent(Scanner sc) {

        System.out.print("Enter student full name: ");
        String name = sc.nextLine();

        //email

        System.out.print("Enter student email: ");

        String email = sc.nextLine().toLowerCase();

        while (!email.matches("^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            System.out.println("The email is not valid, please enter a valid email: ");
            email = sc.nextLine().toLowerCase();
        }
        for (Student student : students
        ) {
            if (student.getEmail().equals(email)) {
                System.out.println("Student already is our database.");
                return;
            }
        }

        //gender

        System.out.print("Input student gender (m or f): ");
        String gender;
        gender = sc.nextLine();

        while (!gender.matches("[fmFM]")) {
            System.out.print("Please enter student gender correctly. ('m' or 'f'): ");
            gender = sc.nextLine();
        }

        //age

        System.out.print("Enter student age: ");
        boolean isAgeValid = false;
        int age = 0;
        while (!isAgeValid) {

            while (!sc.hasNextInt()) {
                System.out.print("Wrong input! Enter student age: ");
                sc.next();
            }
            age = sc.nextInt();
            sc.nextLine();
            if (age < 0 || age > 120) {
                System.out.print("Your input can't be an age. Please enter a valid age.");
            } else if (age < 10) {
                System.out.print("Too young to enter this college.");
                sc.nextLine();
                return;
            } else {
                isAgeValid = true;
            }
        }

        //stage

        System.out.print("Please enter student stage. Must be between 1 and 4: ");
        boolean isStageValid = false;
        int stage = 0;

        while (!isStageValid) {

            while (!sc.hasNextInt()) {
                System.out.print("Wrong input! Enter student stage: ");
                sc.next();
            }
            stage = sc.nextInt();
            sc.nextLine();
            if (stage < 1 || stage > 4) {
                System.out.print("Your input is not a valid stage. Please enter a valid stage: ");
            } else {
                isStageValid = true;
            }
        }
        students.add(new Student(name, email, stage, age, gender));
    }

    void deleteStudentsFromCollege(Scanner sc) {

        boolean studentsAreDeleted = false;

        List<Student> listOfStudentsToBeDeleted = new ArrayList<>();

        System.out.println();
        System.out.println("List of students");

        printStudents();

        System.out.print("Choose a student or more by id separated with comma to delete or 0 to exit. ");

        String choice = sc.nextLine();

        while (!choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
            if (choice.equals("0")) {
                return;
            }
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
                listOfStudentsToBeDeleted.add(student);
            }
        }

        if (listOfStudentsToBeDeleted.size() == 0) {
            System.out.println("Your numbers did't match any Id so no student will be deleted.");
            return;
        }

        System.out.println();
        for (Student student : listOfStudentsToBeDeleted
        ) {
            System.out.println(student);
        }
        System.out.println();
        System.out.print("Confirm students that will be deleted from your college. Type 'y' for yes or 'n' for no. : ");
        String confirmList = sc.nextLine();

        while (!confirmList.matches("[ynYN]")) {

            System.out.print("Enter a valid letter. Confirm students that will be deleted from your college. Type 'y' for yes or 'n' for no. : ");
            confirmList = sc.nextLine();

        }

        if (confirmList.matches("[nN]")) {
            return;
        }

        for (Student student : listOfStudentsToBeDeleted
        ) {
            students.remove(student);
        }

    }

    void deleteTeachersFromCollege(Scanner sc) {

        boolean teachersAreDeleted = false;

        List<Teacher> listOfTeachersToBeDeleted = new ArrayList<>();

        System.out.println();
        System.out.println("List of teachers");

        printTeachers();

        System.out.print("Choose a teacher or more by id separated with comma to delete or 0 to exit. ");

        String choice = sc.nextLine();

        while (!choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
            if (choice.equals("0")) {
                return;
            }
            System.out.print("Enter valid numbers(Id): ");
            choice = sc.nextLine();
        }

        List<Integer> ids = new ArrayList<>();
        for (String id : choice.split("\\s*,\\s*")
        ) {
            ids.add(Integer.parseInt(id));
        }

        for (Teacher teacher : teachers
        ) {
            if (ids.contains(teacher.getId())) {
                listOfTeachersToBeDeleted.add(teacher);
            }
        }

        if (listOfTeachersToBeDeleted.size() == 0) {
            System.out.println("Your numbers did't match any Id so no teacher will be deleted.");
            return;
        }

        System.out.println();
        for (Teacher teacher : listOfTeachersToBeDeleted
        ) {
            System.out.println(teacher);
        }
        System.out.println();
        System.out.print("Confirm teachers that will be deleted from your college. Type 'y' for yes or 'n' for no. : ");
        String confirmList = sc.nextLine();

        while (!confirmList.matches("[ynYN]")) {

            System.out.print("Enter a valid letter. Confirm teachers that will be deleted from your college. Type 'y' for yes or 'n' for no. : ");
            confirmList = sc.nextLine();

        }

        if (confirmList.matches("[nN]")) {
            return;
        }

        for (Teacher teacher : listOfTeachersToBeDeleted
        ) {
            teachers.remove(teacher);
        }

    }

    void printStudents() {
        for (Student student : students
        ) {
            System.out.println(student);
        }
    }

    void printClasses() {
        for (CollegeClass collegeClass : classes
        ) {
            System.out.println(collegeClass);
        }
    }

    CollegeClass selectCollegeClass(Scanner sc) {

        printClasses();
        System.out.print("Select the class you want to manage(by id): ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter the number which represents the id of the class you want to manage.");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        for (CollegeClass collegeClass : classes
        ) {
            if (collegeClass.getId() == id) {
                return collegeClass;
            }
        }
        return null;
    }

    String makeAChoiceManageClass(CollegeClass collegeClass, Scanner sc) {
        System.out.println();
        System.out.println("Manage \"" + collegeClass.getSpeciality() + "\" class.");
        System.out.println();
        System.out.println("a) Add teacher");
        System.out.println("b) Add students");
        System.out.println("c) Delete students");
        System.out.println("d) Delete teacher");
        System.out.println("e) List students");
        System.out.println("f) Change class number of maximum students");
        System.out.println("g) View schedule");
        System.out.println("h) Make appointment");
        System.out.println("i) Delete appointment");

        System.out.println("q) Exit class management");

        String str;
        System.out.print("Input the letter which represents your choice: ");
        str = sc.nextLine();

        while (!str.matches("[ABCDEFGHIQabcdefghiq]")) {

            System.out.print("You entered a wrong letter! Input the letter which represents your choice: ");
            str = sc.nextLine();

        }
        return str;
    }

    void printClassrooms() {
        if (classrooms.size() == 0) {
            System.out.println("There are no classrooms yet!");
            return;
        }
        for (Classroom classroom : classrooms
        ) {
            System.out.println(classroom);
        }
    }

    void addAppointmentToClass(CollegeClass collegeClass, Scanner sc) {
        printClassrooms();
        System.out.print("Enter classroom number to make an appointment: ");
        while (!sc.hasNextInt()) {
            System.out.print("Wrong input. Enter classroom number to make an appointment: ");
            sc.next();
        }
        int classroomNumber = sc.nextInt();
        sc.nextLine();
        for (Classroom classroom : classrooms
        ) {
            if (classroom.getClassroomNumber() == classroomNumber) {
                System.out.print("Enter the date you want to make an appointment (" + LocalDate.now().plusDays(3) + "):  ");
                String date = sc.nextLine();
                while (!date.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
                    System.out.print("Wrong input! Enter the date you want to make an appointment (" + LocalDate.now().plusDays(3) + "):  ");
                }
                LocalDate dateOfAppointment = LocalDate.parse(date);

            }
        }
        System.out.println("No such classroomnumber!");
    }

    void makeAppointment(CollegeClass collegeClass, Classroom classroom, LocalDate date, int position) {
        collegeClass.getAppointments().add(new Appointment(classroom, date, position));
        classroom.addToSchedule(new DatePosition(date, position), collegeClass.getSpeciality());
    }

    void createClassroom(Scanner sc) {
        System.out.print("Enter the seats for your classroom: ");
        while (!sc.hasNextInt()) {
            System.out.print("Wrong input! Enter the seats for your classroom: ");
            sc.next();
        }
        int seats = sc.nextInt();
        sc.nextLine();
        if (seats < 10 || seats > 200) {
            System.out.println("Can't crate a classroom with that amount of seats.");
            return;
        }
        classrooms.add(new Classroom(seats));
    }

    void deleteClassroom(Scanner sc) {
        if (classrooms.size() == 0) {
            System.out.println("There are no classroms in the college!");
            return;
        }
        printClassrooms();
        System.out.print("Select the number of classroom you want to delete: ");
        while (!sc.hasNextInt()) {
            System.out.print("Wrong input! Select the number of classroom you want to delete: ");
            sc.next();
        }
        int numberOfClassToDelete = sc.nextInt();
        sc.nextLine();
        for (Classroom classroom : classrooms
        ) {
            if (classroom.getClassroomNumber() == numberOfClassToDelete) {
                classrooms.remove(classroom);
                return;
            }
        }
        System.out.println("You entered a number but is not a classroom number.");
    }

    void printClassroomSchedule(Scanner sc) {
        if (classrooms.size() == 0) {
            System.out.println("There are no classroms in the college!");
            return;
        }
        printClassrooms();
        System.out.print("Select the number of classroom you want see schedule: ");
        while (!sc.hasNextInt()) {
            System.out.print("Wrong input! Select the number of classroom you want see schedule: ");
            sc.next();
        }
        int numberOfClassToDelete = sc.nextInt();
        sc.nextLine();
        for (Classroom classroom : classrooms
        ) {
            if (classroom.getClassroomNumber() == numberOfClassToDelete) {
                classroom.printSchedule();
                return;
            }
        }
        System.out.println("You entered a number but is not a classroom number.");
    }

    Classroom getclassroom(Scanner sc) {
        if (classrooms.size()==0){
            System.out.println("There is no classroom yet!");
            return null;
        }
        printClassrooms();
        System.out.print("Select the classroom number for your appointment: ");
        while (!sc.hasNextInt()) {
            System.out.print("Wrong input! Select classroom by id for your appointment: ");
            sc.next();
        }
        int classroomNumber = sc.nextInt();
        sc.nextLine();
        for (Classroom classroom : classrooms
        ) {
            if (classroom.getClassroomNumber() == classroomNumber){
                return classroom;
            }
        }
        return null;
    }

}



