package org.contracts;

public interface OrderedTaker {

    void orderAndTake(String courseName, String orderType);

    void orderAndTake(String courseName, String orderType, int studentsToTake);
}
