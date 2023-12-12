package Entities;

public class Subject {

    private String SubjectID;
    private String SubjectName;
    private String Credits;

    public Subject() {
    }

    public Subject(String SubjectID, String SubjectName, String Credits) {
        this.SubjectID = SubjectID;
        this.SubjectName = SubjectName;
        this.Credits = Credits;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getCredits() {
        return Credits;
    }

    public void setCredits(String credits) {
        Credits = credits;
    }
}
