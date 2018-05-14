package org.models;

import org.io.OutputWriter;
import org.staticData.ExceptionMessages;

import java.util.LinkedHashMap;

public class Course {

    public static final Integer NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final Integer MAX_SCORE_ON_EXAM = 100;

    public String name;
    public LinkedHashMap<String, Student> studentsByName;

    public Course(String name) {
        this.name = name;
        this.studentsByName = new LinkedHashMap<>();
    }

    public void enrollStudent(Student student) {
        if (this.studentsByName.containsKey(student.userName)) {
            OutputWriter.displayException(String.format(
                    ExceptionMessages.STUDENT_ALREADY_ENROLLED_IN_COURSE,
                    student.userName, this.name));
            return;
        }

        this.studentsByName.put(student.userName, student);
    }

}
