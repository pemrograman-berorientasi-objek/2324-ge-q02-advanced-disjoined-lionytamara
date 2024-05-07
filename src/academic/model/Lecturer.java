package academic.model;

public class Lecturer {
    private String id;
    private String name;
    private String initial;
    private String email;
    private String studyProgram;

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getInitial() {
        return initial;
    }
    public String getEmail() {
        return email;
    }
    public String getStudyProgram() {
        return studyProgram;
    }

    @Override
    public String toString() {
        return this.id + " | " + this.name + " | " + this.initial + " | " + this.email + " | " + this.studyProgram;
    }
}  