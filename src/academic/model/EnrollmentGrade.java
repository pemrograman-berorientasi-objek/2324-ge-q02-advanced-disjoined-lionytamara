package academic.model;

public enum EnrollmentGrade {
    NONE("None"),
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    F("F");

    private final String gradeValue;

    EnrollmentGrade(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    @Override
    public String toString() {
        return gradeValue;
    }
}
