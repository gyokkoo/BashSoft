package repository;

import io.OutputWriter;
import staticData.ExceptionMessages;
import staticData.SessionData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsRepository {

    private boolean isDataInitialized = false;
    private HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;
    private RepositoryFilter filter;
    private RepositorySorter sorter;

    public StudentsRepository(RepositoryFilter filter, RepositorySorter sorter) {
        this.filter = filter;
        this.sorter = sorter;
    }

    public void loadData(String fileName) throws IOException {
        if (this.isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }

        studentsByCourse = new HashMap<>();
        readData(fileName);
    }

    public void unloadData() {
        if (!this.isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return;
        }

        this.studentsByCourse = null;
        this.isDataInitialized = false;
    }

    private void readData(String fileName) throws IOException {
        String regex = "([A-Z][a-zA-Z#+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Z][a-z]{0,3}\\d{2}_\\d{2,4})\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            matcher = pattern.matcher(line);

            if (!line.isEmpty() && matcher.find()) {
                String course = matcher.group(1);
                String student = matcher.group(2);
                Integer mark = Integer.parseInt(matcher.group(3));

                if (mark >= 0 && mark <= 100) {
                    if (!this.studentsByCourse.containsKey(course)) {
                        this.studentsByCourse.put(course, new LinkedHashMap<>());
                    }

                    if (!this.studentsByCourse.get(course).containsKey(student)) {
                        this.studentsByCourse.get(course).put(student, new ArrayList<>());
                    }

                    this.studentsByCourse.get(course).get(student).add(mark);
                }
            }
        }

        this.isDataInitialized = true;
        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    public void printFilteredStudents(String course, String filter, Integer numberOfStudents) {
        if (! isQueryForCoursePossible(course)) {
            return;
        }

        if (numberOfStudents == null) {
            numberOfStudents = this.studentsByCourse.get(course).size();
        }

        this.filter.printFilteredStudents(this.studentsByCourse.get(course), filter, numberOfStudents);
    }

    public void printOrderedStudents(String course, String compareType, Integer numberOfStudents) {
        if (!isQueryForCoursePossible(course)) {
            return;
        }

        if (numberOfStudents == null) {
            numberOfStudents = this.studentsByCourse.get(course).size();
        }

        this.sorter.printSortedStudents(studentsByCourse.get(course), compareType, numberOfStudents);
    }

    public void getStudentMarksInCourse(String course, String student) {
        if (!isQueryForStudentPossible(course, student)) {
            return;
        }

        ArrayList<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.printStudent(student, marks);
    }

    public void getStudentsByCourse(String course) {
        if (!isQueryForCoursePossible(course)) {
            return;
        }

        OutputWriter.writeMessageOnNewLine(course + ":");
        for (Map.Entry<String, ArrayList<Integer>> student : studentsByCourse.get(course).entrySet()) {
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }

    private boolean isQueryForCoursePossible(String courseName) {
        if (!this.isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if (!this.studentsByCourse.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private boolean isQueryForStudentPossible(String courseName, String studentName) {
        if (!isQueryForCoursePossible(courseName)) {
            return false;
        }

        if (!this.studentsByCourse.get(courseName).containsKey(studentName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }

        return true;
    }

    public void filterAndTake(String courseName, String filter) {
        int studentsToTake = this.studentsByCourse.get(courseName).size();
        filterAndTake(courseName, filter, studentsToTake);
    }

    public void filterAndTake(String courseName, String filter, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        this.filter.printFilteredStudents(studentsByCourse.get(courseName), filter, studentsToTake);
    }

    public void orderAndTake(String courseName, String orderType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        this.sorter.printSortedStudents(this.studentsByCourse.get(courseName), orderType, studentsToTake);
    }

    public void orderAndTake(String courseName, String orderType) {
        int studentsToTake = this.studentsByCourse.get(courseName).size();
        orderAndTake(courseName, orderType, studentsToTake);
    }
}
