package org.models;

import org.contracts.Course;
import org.contracts.Student;
import org.exceptions.DuplicateEntryException;
import org.exceptions.InvalidStringException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnlineCourse implements Course {

    public static final Integer NUMBER_OF_TASKS_ON_EXAM = 5;
    public static final Integer MAX_SCORE_ON_EXAM = 100;

    private String name;
    private LinkedHashMap<String, Student> studentsByName;

    public OnlineCourse(String name) {
        this.setName(name);
        this.studentsByName = new LinkedHashMap<>();
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new InvalidStringException();
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
            throw new DuplicateEntryException(student.getUserName(), this.name);
        }

        this.studentsByName.put(student.getUserName(), student);
    }

    @Override
    public int compareTo(Course other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
