package org.models;

import org.io.OutputWriter;
import org.staticData.ExceptionMessages;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Course {

    public static final Integer NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final Integer MAX_SCORE_ON_EXAM = 100;

    private String name;
    private LinkedHashMap<String, Student> studentsByName;

    public Course(String name) {
        this.setName(name);
        this.studentsByName = new LinkedHashMap<>();
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_OR_EMPTY_VALUE);
        }

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Student> getStudentsByName() {
        return Collections.unmodifiableMap(studentsByName);
    }

    public void enrollStudent(Student student) {
        if (this.studentsByName.containsKey(student.getUserName())) {
            OutputWriter.displayException(String.format(
                    ExceptionMessages.STUDENT_ALREADY_ENROLLED_IN_COURSE,
                    student.getUserName(), this.name));
            return;
        }

        this.studentsByName.put(student.getUserName(), student);
    }
}
