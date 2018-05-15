package org.contracts;

public interface Requester {

    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);
}
