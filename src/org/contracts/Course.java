package org.contracts;

import java.util.Map;

public interface Course {

    String getName();

    Map<String, Student> getStudentsByName();

    void enrollStudent(Student student);
}
