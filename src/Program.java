import judge.Tester;

public class Program {

    public static void main(String[] args) {
        // traverseDirectory("C:\\Program Files (x86)");
        // StudentsRepository.initializeData();
        // StudentsRepository.getStudentsByCourse("Unity");

        String test1path = "test1.txt";
        String test2path = "test2.txt";
        String test3path = "test3.txt";
        Tester.compareContent(test2path, test3path);
    }
}
