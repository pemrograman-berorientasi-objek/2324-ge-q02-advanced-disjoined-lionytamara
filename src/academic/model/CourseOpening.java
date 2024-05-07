package academic.model;

import java.util.ArrayList;
import java.util.List;

public class CourseOpening {
    private String courseCode;
    private String academicYear;
    private String semester;
    private List<String> lecturerList;

    //constructor
    public CourseOpening() {
        this.courseCode = "";
        this.academicYear = "";
        this.semester = "";
        this.lecturerList = new ArrayList<>();
    }

    public CourseOpening(String courseCode, String academicYear, String semester, List<String> lecturerList) {
        this.courseCode = courseCode;
        this.academicYear = academicYear;
        this.semester = semester;
        this.lecturerList = lecturerList;
    }
    public String getCourseCode() {
        return courseCode;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public List<String> getLecturerList() {
        return lecturerList;
    }

    @Override
    public String toString() {
        return this.courseCode + "|" + this.academicYear + "|" + this.semester + "| " + this.lecturerList;
    }
}
