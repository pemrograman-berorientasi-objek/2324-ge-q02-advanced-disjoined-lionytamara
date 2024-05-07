package academic.model;


public class Student {
    private String id;
    private String name;
    private String year;
    private String studyProgram;

    //constructor
    public Student() {
        this.id = "";
        this.name = "";
        this.year = "";
        this.studyProgram = "";
    }

    public Student(String id, String name, String year, String studyProgram) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
    }
    //getter and setter methods
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getYear() {
        return year;
    }
    public String getStudyProgram() {
        return studyProgram;
    }
    @Override
    public String toString() {
        return this.id + " | " + this.name + " | " + this.year + " | " + this.studyProgram;
    }
}              
