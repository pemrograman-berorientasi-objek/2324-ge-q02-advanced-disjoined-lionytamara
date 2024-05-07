package academic.model;

public class Enrollment {
    private String courseId;
    private String studentId;
    private String academicYear;
    private String semester;
    private String grade;

    //constructor
    public Enrollment() {
        this.courseId = "";
        this.studentId = "";
        this.academicYear = "";
        this.semester = "";
        this.grade = "";
    }
    
    public Enrollment(String courseId, String studentId, String academicYear, String semester) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = "None";
    }
    public String getCourseId() {
        return courseId;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public String getSemester() {
        return semester;
    } 
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return this.courseId + " | " + this.studentId + " | " + this.academicYear + " | " + this.semester + " | " + this.grade;
    }

}      