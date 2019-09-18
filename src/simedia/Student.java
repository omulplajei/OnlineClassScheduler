package simedia;

public class Student {

    private String name;
    private String email;
    private int stage;
    private int age;
    private String gender;
    private int id;
    private static int studentCount = 0;

    Student(String name, String email, int stage, int age, String gender) {
        this.name = name;
        this.email = email;
        this.stage = stage;
        this.age = age;
        this.gender = gender;
        this.id = ++studentCount;
    }

    int getId() {
        return id;
    }

    String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Student [id = " + id + ", name = " + name + ", email = " + email + ", stage = " + stage + ", age = " + age + ", gender = " + gender + "]";
    }
}
