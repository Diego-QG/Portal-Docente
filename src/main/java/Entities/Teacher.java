package Entities;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Teacher {

    private String TeacherID;

    private String email;
    private String password;

    // Personal Information
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthday;

    // Contact Information
    private String contactNumber;

    // Professional Information
    private String jobTitle;
    private ArrayList<Subject> taughtSubjects;
    private ArrayList<String> classGroups;

    // Employment Details
    private float salary;
    private int yearsOfExperience;

    // Constructors
    public Teacher() {
    }

    public Teacher(String TeacherID, String email, String password, String firstName, String lastName, String gender, String contactNumber, LocalDate birthday, String jobTitle, ArrayList<Subject> taughtSubjects, ArrayList<String> classGroups, float salary, int yearsOfExperience) {
        this.TeacherID = TeacherID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.birthday = birthday;
        this.jobTitle = jobTitle;
        this.taughtSubjects = taughtSubjects;
        this.classGroups = classGroups;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters and Setters
    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String TeacherID) {
        this.TeacherID = TeacherID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public ArrayList<Subject> getTaughtSubjects() {
        return taughtSubjects;
    }

    public void setTaughtSubjects(ArrayList<Subject> taughtSubjects) {
        this.taughtSubjects = taughtSubjects;
    }

    public ArrayList<String> getClassGroups() {
        return classGroups;
    }

    public void setClassGroups(ArrayList<String> classGroups) {
        this.classGroups = classGroups;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void printTeacherDetails() {
        System.out.println("TeacherID: " + TeacherID);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("Birthday: " + birthday);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Job Title: " + jobTitle);

        // Imprimir nombres de las asignaturas
        System.out.println("Taught Subjects:");
        if (taughtSubjects != null) {
            for (Subject subject : taughtSubjects) {
                System.out.println("- " + subject.getSubjectName());
            }
        }

        // Imprimir nombres de grupos de clase
        //System.out.println("Class Groups: " + classGroups);

        System.out.println("Salary: " + salary);
        System.out.println("Years of Experience: " + yearsOfExperience);
    }

}
