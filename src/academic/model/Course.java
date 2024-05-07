package academic.model;

public class Course {
    private String id;
    private String name;
    private double credit;
    private String passingGrade;

    //constructor
    public Course() {
    this.id = "";
    this.name = "";
    this.credit = 0.0;
    this.passingGrade = "";
    }

    public Course(String id, String name, double credit, String passingGrade) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.name + "|" + this.credit + "|" + this.passingGrade;
    }
}

