package simedia;

public class Teacher {

    private int id;
    private String name;
    private String speciality;
    private String email;
    private String phoneNumber;
    private String gender;
    private static int teacherCount = 0;

    Teacher(String name, String email, String speciality, String phoneNumber, String gender) {

        this.name = name;
        this.speciality = speciality;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.id = ++teacherCount;

    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Teacher [id = " + id + ", name = " + name + ", email = " + email + ", speciality = " + speciality + ", phone = " + phoneNumber + ", gender = " + gender + "]";
    }
}
