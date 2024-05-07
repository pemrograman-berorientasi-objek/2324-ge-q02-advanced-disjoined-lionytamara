package academic.model;

public class EnrollmentRemedial {
    private String id;
    private String academicYear;
    private String semester;
    private String grade;
    private String PreviousGrade;

    //Constructor

    public  EnrollmentRemedial  () {
        
        this.id = "";
        this.academicYear = "";
        this.semester = "";
        this.grade = "";
        this.PreviousGrade = "";
    }

    public  EnrollmentRemedial  (String id,String academicYear,String semester,String grade,String PreviousGrade) {
        
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = grade;
        this.PreviousGrade = PreviousGrade;
    }



    public String getStudentId() {
        return id;
    }

    public void setStudentId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPreviousGrade(String PreviousGrade) {
        this.PreviousGrade = PreviousGrade;
    }

    public String getPreviousGrade() {
        return PreviousGrade;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    
    
    public String toString(){
        return this.id + 
        "|" + this.academicYear + 
        "|" + this.semester+
        "|" + this.grade +
        "|" + this.PreviousGrade;
    }
}           