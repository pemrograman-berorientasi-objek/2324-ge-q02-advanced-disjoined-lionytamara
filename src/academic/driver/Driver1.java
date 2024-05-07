package academic.driver;

import java.util.*;
import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;   
import academic.model.Student;
import academic.model.Lecturer;

/**
 * @author 12S22019 Liony Tamara Lewinsky
 * @author 12S22029 Jeremy Samosir
 */
public class Driver1 {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<>();

        while (input.hasNext()) {
            String str = input.nextLine();

            if (str.equals("---")) {
                break;
            } else {
                String[] tokens = str.split("#");
                String command = tokens[0];

                if (command.equals("lecturer-add")) {
                    String lecturerId = tokens[1];
                    String lecturerName = tokens[2];
                    String lecturerInitial = tokens[3];
                    String lecturerEmail = tokens[4];
                    String studyProgram = tokens[5];

                    Lecturer lecturer = new Lecturer(lecturerId, lecturerName, lecturerInitial, lecturerEmail, studyProgram);
                    lecturers.add(lecturer);

                } else if (command.equals("student-add")) {
                    String studentId = tokens[1];
                    String studentName = tokens[2];
                    String year = tokens[3];
                    String studyProgram = tokens[4];

                    Student student = new Student(studentId, studentName, year, studyProgram);
                    students.add(student);

                } else if (command.equals("course-add")) {
                    String courseId = tokens[1];
                    String courseName = tokens[2];
                    double credit = Double.parseDouble(tokens[3]);
                    String passingGrade = tokens[4];

                    Course course = new Course(courseId, courseName, credit, passingGrade);
                    courses.add(course);

                } else if (command.equals("course-open")) {
                    String courseCode = tokens[1];
                    String academicYear = tokens[2];
                    String semester = tokens[3];
                    List<String> lecturerList = Arrays.asList(tokens[4].split(","));

                    CourseOpening opening = new CourseOpening(courseCode, academicYear, semester, lecturerList);
                    courseOpenings.add(opening);

                } else if (command.equals("enrollment-add")) {
                    String courseId = tokens[1];
                    String studentId = tokens[2];
                    String academicYear = tokens[3];
                    String semester = tokens[4];

                    Enrollment enrollment = new Enrollment(courseId, studentId, academicYear, semester);
                    enrollments.add(enrollment);

                } else if (command.equals("enrollment-grade")) {
                    String courseId = tokens[1];
                    String studentId = tokens[2];
                    String academicYear = tokens[3];
                    String semester = tokens[4];
                    String grade = tokens[5];

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCourseId().equals(courseId) && enrollment.getStudentId().equals(studentId)
                                && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester)) {
                            enrollment.setGrade(grade);
                            break;
                        }
                    }
                } else if (command.equals("enrollment-remedial")) {
                    String courseId = tokens[1];
                    String studentId = tokens[2];
                    String academicYear = tokens[3];
                    String semester = tokens[4];
                    String grade = tokens[5];

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCourseId().equals(courseId) && enrollment.getStudentId().equals(studentId)
                                && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester)) {
                            String originalGrade = enrollment.getGrade();
                            if (!originalGrade.contains("(")) {
                                enrollment.setGrade(grade + "(" + originalGrade + ")");
                            }
                            break;
                        }
                    }
                } else if (command.equals("student-details")) {
                    String studentId = tokens[1];
                    double totalCredit = 0;
                    double totalGradePoints = 0;
                    Map<String, Double> courseCredits = new HashMap<>();

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getStudentId().equals(studentId) && !enrollment.getGrade().equals("None")) {
                            double courseCredit = getCourseCredit(enrollment.getCourseId(), courses);
                            double gradePoints = calculateGradePoints(enrollment.getGrade()) * courseCredit;

                            if (courseCredits.containsKey(enrollment.getCourseId())) {
                                if (courseCredits.get(enrollment.getCourseId()) < gradePoints) {
                                    totalCredit -= courseCredit;
                                    totalGradePoints -= courseCredits.get(enrollment.getCourseId());
                                    courseCredits.put(enrollment.getCourseId(), gradePoints);
                                }
                            } else {
                                courseCredits.put(enrollment.getCourseId(), gradePoints);
                            }

                            totalCredit += courseCredit;
                            totalGradePoints += gradePoints;
                        }
                    }

                    double gpa = totalCredit != 0 ? totalGradePoints / totalCredit : 0;

                    for (Student student : students) {
                        if (student.getId().equals(studentId)) {
                            System.out.printf("%s|%s|%s|%s|%.2f|%.0f\n", student.getId(), student.getName(),
                                    student.getYear(), student.getStudyProgram(), gpa, totalCredit);
                            break;
                        }
                    }
                } else if (command.equals("course-history")) {
                    String courseCode = tokens[1];


                    Collections.sort(courseOpenings, new Comparator<CourseOpening>() {
                        public int compare(CourseOpening o1, CourseOpening o2) {
                            return o2.getSemester().compareTo(o1.getSemester());
                        }
                    });

                    for (CourseOpening opening : courseOpenings) {
                        if (opening.getCourseCode().equals(courseCode)) {
                            Course course = findCourseByCode(courseCode, courses);
                            Lecturer lecturer = findLecturerByInitial(opening.getLecturerList().get(0), lecturers);
                            System.out.println(course.getId() + "|" + course.getName() + "|" + (int)course.getCredit() + "|" + course.getPassingGrade() + "|" + opening.getAcademicYear() + "|" + opening.getSemester() + "|" + lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
                            for (Enrollment enrollment : enrollments) {
                                if (enrollment.getCourseId().equals(courseCode) && enrollment.getAcademicYear().equals(opening.getAcademicYear()) && enrollment.getSemester().equals(opening.getSemester())) {
                                    System.out.println(enrollment.getCourseId() + "|" + enrollment.getStudentId() + "|" + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
                                }
                            }
                        }
                    }
                }
            }
        }

        // Output the required information
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());

        }
        for (Course course : courses) {
            double credit = course.getCredit();
            System.out.println(course.getId() + "|" + course.getName() + "|" + (int)credit + "|" + course.getPassingGrade());
        }
        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getCourseId() + "|" + enrollment.getStudentId() + "|" + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
        }

        input.close();
    }

    private static double calculateGradePoints(String grade) {
        String gradeWithoutParentheses = grade.contains("(") ? grade.substring(0, grade.indexOf("(")) : grade;
        switch (gradeWithoutParentheses) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }

    private static Course findCourseByCode(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getId().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
    
    private static Lecturer findLecturerByInitial(String initial, List<Lecturer> lecturers) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getInitial().equals(initial)) {
                return lecturer;
            }
        }
        return null;
    }
    private static double getCourseCredit(String courseId, ArrayList<Course> courses) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course.getCredit();
            }
        }
        return 0; // return 0 if no course with the given id is found
    }
}
