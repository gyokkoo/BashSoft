package org.repository;

import org.contracts.DataSorter;
import org.io.OutputWriter;
import org.staticData.ExceptionMessages;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorter implements DataSorter {

    public void printSortedStudents(
            HashMap<String, Double> studentsWithMarks,
            String comparisonType,
            int numberOfStudents) {
        comparisonType = comparisonType.toLowerCase();

        if (!comparisonType.equals("ascending") && !comparisonType.equals("descending")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_COMPARISON_QUERY);
        }

        Comparator<Map.Entry<String, Double>> comparator = (x, y) -> {
            double value1 = x.getValue();
            double value2 = y.getValue();
            return Double.compare(value1, value2);
        };

        List<String> sortedStudents = studentsWithMarks.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }

        for (String student : sortedStudents) {
            OutputWriter.printStudent(student, studentsWithMarks.get(student));
        }
    }
}
